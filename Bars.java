
import java.awt.Color;

import processing.core.PApplet;

/*
 This class defines what a bar is and how
  it is displayed on the screen
 */
public class Bars extends PApplet{
	
	protected PApplet parent;
	private int barWidth;	
	private int barHeight;
	private int xPos1;
	private int yPos1;
	private int xPos2;
	private int yPos2;
	private final Color barColor =  new Color(0, 100, 255);		//blue bars
	private boolean passedCentre = false;
	private double speed = 3;
	
	public Bars(PApplet parent)
	{
		this.parent = parent;
		this.barHeight = (int) (0.4 * 500);		//change to constant seen among all classes later***		
		this.barWidth = (int) (0.3 * 500);		//change to constant seen among all classes later***	
		this.xPos1 = 0;
		this.yPos1 = 0;
		this.xPos2 = 0;
		this.yPos2 = 0;
	}
	
	public Bars(PApplet parent, int xPos1, int yPos1, int xPos2, int yPos2, int barHeight)
	{
		this.parent = parent;					//must pass in parent so classes see processing libraries
		this.barHeight = barHeight;	
		this.barWidth = (int) (0.2 * 500);		//change to constant seen among all classes later*** //bar takes up 20% of screen
		this.xPos1 = xPos1;
		this.yPos1 = yPos1;
		this.xPos2 = xPos2;
		this.yPos2 = yPos2;
	}
	
	public int getxPos()
	{
		return this.xPos1; 		//xPos1 and xpos2 are always the same
	}
	
	public int getBarWidth()
	{
		return this.barWidth;
	}
	
	public int getBarHeight()
	{
		return this.barHeight;
	}
	
	public int getyPos1()
	{
		return this.yPos1;
	}
	
	public int getyPos2()
	{
		return this.yPos2;
	}
	//moves bars
	public void move()
	{
		xPos1 -= this.speed;
		xPos2 -= this.speed;
	}
	//increases speed of bars
	public void increaseSpeed(double howMuch)
	{
		this.speed = this.speed + howMuch;
	}
	//method used to count score in game - when bar passes centre then increase score
	public boolean getPassedCentre()
	{
		return passedCentre;
	}
	
	public void setPassedCentre(boolean passedCentre)
	{
		this.passedCentre = passedCentre;
	}
	
	public void draw()
	{
		parent.fill(barColor.getRGB());
		parent.stroke(0);
		parent.rect(xPos1, yPos1, barWidth, barHeight);
		parent.rect(xPos2, yPos2, barWidth, 500 - yPos2);   		//put in constant for height***
	}
}
