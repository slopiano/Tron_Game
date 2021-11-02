package bigBoi;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Array;

import javax.swing.ImageIcon;

//This is the main application and should be run from here
//This uses applets which are compatible with jdk - 8 and lower

public class Tron extends JoeApplet implements KeyListener, MouseListener, MouseMotionListener {
	
	SolidObject firstTitle = new SolidObject();
	SolidObject secondTitle = new SolidObject();
	SolidObject thirdTitle = new SolidObject();
	SolidObject fourthTitle = new SolidObject();
	SolidObject ATitle = new SolidObject();
	SolidObject mouse = new SolidObject();
	SolidObject Fbike = new SolidObject();
	SolidObject Sbike = new SolidObject();
	SolidObject wall = new SolidObject();
	SolidObject wall2 = new SolidObject();
	SolidObject wall3 = new SolidObject();
	SolidObject wall4 = new SolidObject();
	Font title = new Font("This", Font.TYPE1_FONT, 60);
	Font menu = new Font("This", Font.PLAIN, 30);
	String path = "D:\\eclipse\\saveFiles\\";
	Image titleScreen = new ImageIcon(path + "Tron enhanced.jpg").getImage();
	Image Teacher = new ImageIcon(path + "HajduPhoto.jpg").getImage();
	Image E = new ImageIcon(path + "bro.jpg").getImage();
	Image bothCrash = new ImageIcon(path+"bothCrash.jpg").getImage();
	Image cyanImage = new ImageIcon(path+ "cyanImage.jpg").getImage();
	Image greenImage = new ImageIcon(path+ "greenImage.jpg").getImage();
	int mouseX,mouseY;
	int firstX[] = {390,390,415}; int firstY[] = {415,445,430};
	int secondX[] = {390,390,415}; int secondY[] = {490,520,505};
	int thirdX[] = {390,390,415}; int thirdY[] = {565,595,580};
	int fourthX[] = {390,390,415}; int fourthY[] = {640,670,655};
	int AX[]= {15,15,40}; int AY[] = {150, 180, 165};
	int AX2[]= {15,15,40}; int AY2[] = {190, 220, 205};
	int AX3[]= {15,15,40}; int AY3[] = {230, 260, 245};
	int AX4[]= {15,15,40}; int AY4[] = {270, 300, 285};
	int AX5[]= {15,15,40}; int AY5[] = {310, 340, 325};
	int AX6[]= {15,15,40}; int AY6[] = {350, 380, 365};
	

	int currentLevel=0,FirstTronX=696,FirstTronY=396, fvelocityX=6, fvelocityY=0;
	int SecondTronX=300, SecondTronY=396, svelocityX=-6, svelocityY=0,speed=6;
	int forGridX=0, forGridY=0;
	int[][] grid = new int[168][168];
	int[][] grid2 = new int[168][168];
	SolidObject[][] bumper = new SolidObject[168][168];
	SolidObject[][] bumper2 = new SolidObject[168][168];
	Color sam = new Color (69, 69, 69);
	int row, column, row2, column2, cyanPoint=0, greenPoint=0;
	
	public void init(){
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		for(row=0; row<167; row++){
			for(column=0; column<167; column++){
				bumper[row][column]= new SolidObject();
				bumper2[row][column] = new SolidObject();
			}
		}
	}
	
	public void setScene(Graphics art){
		setSize(996,798);
		art.drawImage(titleScreen, 0, 0, this);
		art.setFont(title);
		art.setColor(Color.BLUE);
		art.drawString("2 Player Tron", 330, 100);
		art.setColor(Color.WHITE);
		art.fillRect(300, 350, 400, 400);
		art.setColor(Color.BLACK);
		art.fillRect(310, 360, 380, 380);
		art.setColor(Color.WHITE);
		art.setFont(menu);
		mouse.makeSolidObject(mouseX, mouseY, 1, 1);
		art.drawString("Play Game", 430, 440);
		firstTitle.makeSolidObject(430, 415, 150, 30);
		secondTitle.makeSolidObject(430, 490, 150, 30);
		thirdTitle.makeSolidObject(430, 565, 100, 30);
		fourthTitle.makeSolidObject(430, 640, 30, 30);
		art.drawString("Instructions", 430, 515);
		art.drawString("Credits",430,590);
		art.drawString("A", 430,665);
	}
	public void gameScreen(Graphics art){
		setBackground(Color.BLACK);
		art.setColor(Color.LIGHT_GRAY);
		for (int x=0; x<=800; x+=40){
			art.drawLine(0, x, 1000, x);       //the for loops create the lines
		}
		for (int x=0; x<=1000; x+=40){
			art.drawLine(x, 0, x, 800);
		}
		art.setColor(Color.CYAN);
		art.fillRect(FirstTronX, FirstTronY, 6, 6);
		art.setColor(Color.GREEN);
		art.fillRect(SecondTronX, SecondTronY, 6, 6);
		art.setColor(Color.CYAN);
		art.setFont(title);
		art.drawString(""+cyanPoint, 900, 65);
		art.setColor(Color.GREEN);
		art.drawString(""+greenPoint, 65, 65);
		if(FirstTronX<=0) {
			currentLevel=5;
			greenPoint++;
		}
		if(FirstTronX>=996) {
			currentLevel=5;
			greenPoint++;
		}
		if(SecondTronX<=0) {
			currentLevel=6;
			cyanPoint++;
		}
		if(SecondTronX>=996) {
			currentLevel=6;
			cyanPoint++;
		}
		if(FirstTronY<=0) {
			currentLevel=5;
			greenPoint++;
		}
		if(FirstTronY>=798) {
			currentLevel=5;
			greenPoint++;
		}
		if(SecondTronY<=0) {
			currentLevel=6;
			cyanPoint++;
		}
		if(SecondTronY>=798) {
			currentLevel=6;
			cyanPoint++;
		}
		art.setColor(Color.CYAN);
		for(row=0; row<168; row++){
			for(column = 0; column<168; column++){
				if(grid[row][column]==1){
					art.fillRect(column*6, row*6, 6, 6);
					bumper[row][column].makeSolidObject(column*6, row*6, 6, 6);
				}
			}
		}
		wall.makeSolidObject(-1, 0, 1, 798);
		wall2.makeSolidObject(996, 0, 1, 798);
		wall3.makeSolidObject(0, -1, 996, 1);
		wall4.makeSolidObject(0, 798, 996, 1);
		for(row2=0; row2<168; row2++){
			for(column2 = 0; column2<168; column2++){
				if(grid2[row2][column2]==2){
					art.setColor(Color.GREEN);
					art.fillRect(column2*6, row2*6, 6, 6);
					bumper2[row2][column2].makeSolidObject(column2*6, row2*6, 6, 6);
				}
			}
		}
		grid[FirstTronY/6][FirstTronX/6]=1;
		grid2[SecondTronY/6][SecondTronX/6]=2;
		Fbike.makeSolidObject(FirstTronX, FirstTronY, 6, 6);
		Sbike.makeSolidObject(SecondTronX, SecondTronY, 6, 6);
		FirstTronX=FirstTronX-fvelocityX;
		FirstTronY=FirstTronY-fvelocityY;
		SecondTronX=SecondTronX-svelocityX;
		SecondTronY=SecondTronY-svelocityY;
		System.out.println(FirstTronX + " " + FirstTronY + " " + grid[FirstTronX/6][FirstTronY/6]);
		System.out.println(FirstTronX + " " + FirstTronY + " " + grid[FirstTronX/6][FirstTronY/6]);
		if(Fbike.isCollidingWith(Sbike)) {
			currentLevel=7;
		}
		for(row=0; row<167; row++){
			for(column = 0; column<167; column++){
				if(Fbike.isCollidingWith(bumper[row][column])){
					currentLevel=5;
					greenPoint++;
				}
				if(Fbike.isCollidingWith(bumper2[row][column])){
					currentLevel=5;
					greenPoint++;
				}
				if(Sbike.isCollidingWith(bumper[row][column])){
					currentLevel=6;
					cyanPoint++;
				}
				if(Sbike.isCollidingWith(bumper2[row][column])){
					currentLevel=6;
					cyanPoint++;
				}		
			}
		}
	}
	public void animateFirstTitle(Graphics art){
		
		art.fillPolygon(firstX, firstY, 3);
			}
	public void animateSecondTitle(Graphics art){
		art.fillPolygon(secondX, secondY, 3);
			}
	public void animateThirdTitle(Graphics art){
		art.fillPolygon(thirdX, thirdY, 3);
			}
	public void animateFourthTitle(Graphics art){
		art.fillPolygon(fourthX, fourthY, 3);
			}
	public void titlecollision(Graphics art){
		if(mouse.isCollidingWith(firstTitle)){
			animateFirstTitle(art);
		}
		if(mouse.isCollidingWith(secondTitle)){
			animateSecondTitle(art);
		}
		if(mouse.isCollidingWith(thirdTitle)){
			animateThirdTitle(art);
		}
		if(mouse.isCollidingWith(fourthTitle)){
			animateFourthTitle(art);
		}
	}
	public void E (Graphics art){
		setBackground(Color.BLACK);
		setSize(996,798);
		ATitle.makeSolidObject(50, 150, 190, 225);
		art.setColor(Color.WHITE);
		mouse.makeSolidObject(mouseX, mouseY, 1, 1);
		art.setFont(title);
		art.drawImage(E, 275, 50, this);
		art.setColor(Color.WHITE);
		art.drawString("Return", 50, 200);
		art.drawString("to", 50, 275);
		art.drawString("Title", 50, 350);
		if(mouse.isCollidingWith(ATitle)){
			art.fillPolygon(AX,AY,3);
			art.fillPolygon(AX2, AY2, 3);
			art.fillPolygon(AX3, AY3, 3);
			art.fillPolygon(AX4, AY4, 3);
			art.fillPolygon(AX5, AY5, 3);
			art.fillPolygon(AX6, AY6, 3);
			
			}
	}
	public void firstBikeWon(Graphics art) {
		if(cyanPoint==3){
			currentLevel=8;
		}
		setSize(996,798);
		setBackground(Color.BLACK);
		art.drawImage(cyanImage, 108, 179, this);      //780 by 438

		for(row=0; row<168; row++){
			for(column = 0; column<168; column++){
				grid[row][column]=0;
				grid2[row][column]=0;
				bumper[row][column]= new SolidObject();
				bumper2[row][column]= new SolidObject();
				}
			}
		art.setFont(title);
		art.setColor(Color.ORANGE);
		art.drawString("Player 1 has won", 250, 300);
		art.drawString("Press R to replay", 250, 400);
		art.drawString("or press E to go back to the title", 15, 500);
		FirstTronX=696;
		FirstTronY=396;
		SecondTronX=300;
		SecondTronY=396;
		svelocityX=-speed;
		svelocityY=0;
		fvelocityX=speed;
		fvelocityY=0;
		
}
	public void secondBikeWon(Graphics art) {
		if(greenPoint==3){
			currentLevel=9;
		}
		setSize(996,798);
		setBackground(Color.BLACK);
		art.drawImage(greenImage, 133, 258, this);       //730 by 280
		for(row=0; row<168; row++){
			for(column = 0; column<168; column++){
				grid[row][column]=0;
				grid2[row][column]=0;
				bumper[row][column]= new SolidObject();
				bumper2[row][column]= new SolidObject();
			}
		}
		art.setFont(title);
		art.setColor(Color.RED);
		art.drawString("Player 2 has won", 250, 300);
		art.drawString("Press R to replay", 250, 400);
		art.drawString("or press E to go back to the title", 15, 500);
		FirstTronX=696;
		FirstTronY=396;
		SecondTronX=300;
		SecondTronY=396;
		svelocityX=-speed;
		svelocityY=0;
		fvelocityX=speed;
		fvelocityY=0;
	}
	public void bothDie(Graphics art) {
		setSize(996,798);
		setBackground(Color.BLACK);
		art.drawImage(bothCrash, -50, 50, this);
		for(row=0; row<168; row++){
			for(column = 0; column<168; column++){
				grid[row][column]=0;
				grid2[row][column]=0;
				bumper[row][column]= new SolidObject();
				bumper2[row][column]= new SolidObject();
			}
		}
		art.setFont(title);
		art.setColor(Color.ORANGE);
		art.drawString("Congrats, both of yalls ded", 100, 300);
		art.drawString("Press R to replay", 250, 400);
		art.drawString("or press E to go back to the title", 15, 500);
		FirstTronX=696;
		FirstTronY=396;
		SecondTronX=300;
		SecondTronY=396;
		svelocityX=-speed;
		svelocityY=0;
		fvelocityX=speed;
		fvelocityY=0;
	}
	public void cyanReallyWon(Graphics art){
		setBackground(Color.BLACK);
		art.setFont(menu);
		art.setColor(Color.CYAN);
		art.drawString("Cyan Won", 200, 200);
		art.drawString("Press E to go back to the menu", 300, 300);
	}
	public void greenReallyWon(Graphics art){
		setBackground(Color.BLACK);
		art.setFont(menu);
		art.setColor(Color.GREEN);
		art.drawString("Green Won", 200, 200);
		art.drawString("Press E to go back to the menu", 300, 300);
	}
	public void instructions(Graphics art){
		setBackground(Color.BLACK);
		art.setFont(menu);
		art.setColor(Color.GREEN);
		art.drawString("In order to play, use the WASD keys to move the green bike", 200, 200);
		art.drawString("And use the arrow keys to move the cyan bike", 200, 300);
		art.drawString("Press E to go back to the menu", 200, 400);
		art.drawString("You can always press E to go back to the menu from any screen", 100, 500);
		art.drawString("First to three points wins!!!!", 100, 600);
	}
	public void credit(Graphics art){
		setBackground(Color.BLACK);
		art.setFont(menu);
		art.setColor(Color.BLUE);
		art.drawString("Made by Me, like obviously Bro", 200, 200);
	}
	public void paint(Graphics art){
		switch (currentLevel){
		case 0:
			setScene(art);
			titlecollision(art);
			break;
		case 1:
			gameScreen(art);
			break;
		case 2:
			instructions(art);
			break;
		case 3:
			credit(art);
			break;
		case 4:
			E(art);
			break;
		case 5:
			secondBikeWon(art);
			break;
		case 6:
			firstBikeWon(art);
			break;
		case 7:
			bothDie(art);
			break;
		case 8:
			cyanReallyWon(art);
			break;
		case 9:
			greenReallyWon(art);
			break;
		}
			
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
if(currentLevel==1){
		if(svelocityY==0){
			if(key==e.VK_W){
				svelocityX=0;         //The velocities are just a number that changes 
				svelocityY=speed;
			}
		}
		if(fvelocityY==0){
			if(key==e.VK_UP){
				fvelocityX=0;         //The velocities are just a number that changes 
				fvelocityY=speed;
			}
		}
		if(fvelocityY==0){
			if(key==e.VK_DOWN){
				fvelocityX=0;
				fvelocityY=-speed;
			}
		}
		if(fvelocityX==0){
			if(key==e.VK_LEFT){         //whenever one velocity is change the other must be 0
				fvelocityX=speed;           //so the bike doesn't move diagonally 
				fvelocityY=0;
			}
		}
		if(fvelocityX==0){
			if(key==e.VK_RIGHT){
				fvelocityX=-speed;
				fvelocityY=0;
			}
		}
		if(svelocityY==0){
			if(key==e.VK_S){
				svelocityX=0;
				svelocityY=-speed;
			}
		}
		if(svelocityX==0){
			if(key==e.VK_A){         //whenever one velocity is change the other must be 0
				svelocityX=speed;           //so the bike doesn't move diagonally 
				svelocityY=0;
			}
		}
		if(svelocityX==0){
			if(key==e.VK_D){
				svelocityX=-speed;
				svelocityY=0;
			}
		}
}
		if(currentLevel==4 || currentLevel==5 || currentLevel==6 ||  currentLevel==7 || currentLevel==2 || currentLevel==3 || currentLevel==8 || currentLevel==9) {
			if(key==e.VK_E) {
				currentLevel=0;
				cyanPoint=0;
				greenPoint=0;
			}
		}
		if(currentLevel==5 || currentLevel==6 || currentLevel==7) {
			if(key==e.VK_R) {
				currentLevel=1;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(currentLevel == 0){
			if(mouse.isCollidingWith(firstTitle)){
				currentLevel=1;
			}
		}
		if(currentLevel == 0){
			if(mouse.isCollidingWith(secondTitle)){
				currentLevel=2;
			}
		}
		if(currentLevel == 0){
			if(mouse.isCollidingWith(thirdTitle)){
				currentLevel=3;
			}
		}
		if(currentLevel == 0){
			if(mouse.isCollidingWith(fourthTitle)){
				currentLevel=4;
			}
		}
		if(currentLevel==4) {
			if(mouse.isCollidingWith(ATitle)) {
				currentLevel=0;
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}




