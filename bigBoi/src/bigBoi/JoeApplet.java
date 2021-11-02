package bigBoi;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/**
The purpose of this class is to make an applet double buffered, have a frame rate, and proper focus
	Double Buffering, frame rate, and focus are each different and independent of each other!
	instead of extending Applet, extending MyApplet will make all of the above true
	you can reset the frame rate and many other things by using the accessor methods provided
NOTE:   !use the repaint method, or override the start, 
	run, stop, or update methods in your subclass.
 **/

public class JoeApplet extends Applet implements Runnable
{
	//objects for everything
	private Dimension dim = new Dimension();
	private Image offscreen;
	private Graphics screen;
	private Thread animator;
	private int fps = 30;
	private boolean rpnt = false;

	
	//this method starts your program if on the internet
	public void start()
	{
		//starts the thred
		animator = new Thread(this);
    	animator.start();
    	
    	//gets the frame rate from the HTML tag or defaults to 30 unless it has already been set
	    String str = getParameter("fps");
	    if(str != null && Integer.parseInt(str) > 0) 
	    	fps=Integer.parseInt(str);
	    if(fps <= 0)
	    	fps = 30;
	}
	
	//runs the applet, pauses for the proper amount of time between each frame
	public void run() 
	{
		//gets the current time in milliseconds
		long tm = System.currentTimeMillis();
		
		//while it started but did not stop.....
		while(Thread.currentThread() == animator)
		{
			//repaints everything on the screen, and makes this only happen here
			rpnt = true;
    		repaint();
    		rpnt = false;
    		
    		try 
    		{
    			//adds the time between each frame to the above time, then waits untill that time has passed
    			tm += 1000 / fps;
    			Thread.sleep(Math.max(0, tm - System.currentTimeMillis()));
    		} 
    		catch (InterruptedException e) 
    		{break;}
		}
	}
	
	//stops your applet
	public void stop()
	{animator = null;}
		
	//this method is used for the double buffering, and also calls the paint method
	public void update(Graphics g)
	{
		//if the screensize has changed, we need a new image, so we make one
		Dimension d = getSize();
		if ((screen == null) || (d.width != dim.width) || (d.height != d.height)) 
		{
			dim = d;
			//makes a new image, and graphics object that paints to that image
			offscreen = createImage(d.width, d.height);	
			screen = offscreen.getGraphics();
		}
		//otherwise, we will just paint over the screen to get rid of the old images
		else
		{
			screen.setColor(getBackground());
			screen.fillRect(0, 0, dim.width, dim.height);
		}
		
		//request focus gives this focus, in this case it could make your listeners cooperate or
			//just make it so you don't need to click on your applet before it responds to inputs
		requestFocus();
		//calls the paint method with our doublebuffered graphics object.
			//override the paint method in your subclass to make your applet do things
		paint(screen);
		//draws everything from our offscreen image onto the screen
		g.drawImage(offscreen, 0, 0, null);	
	}
	
	//repaint should only be called by this class, otherwise the frame rate is useless...
	public void repaint()
	{
		if(rpnt)
			super.repaint();
	}
	
	//sets the frame rate for this applet
	public void setFrameRate(int rate)
	{fps = rate;}
	
	//gets the frame rate
	public int getFrameRate()
	{return fps;}
}
