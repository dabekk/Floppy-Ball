import java.awt.Color;

import processing.core.PApplet;
import processing.core.PImage;

public class Player {
	
	protected PApplet parent;
	private int xPos;
	private int yPos;
	private final int diameter = (int) (500 * 0.05);			//change to constant later********
	private Color playerColor = new Color(57, 255, 20);
	private boolean falling = true;
	private int stopRise = 0;
	private double speed = 2.75;
	
	public Player(PApplet parent)
	{
		this.parent = parent;
		this.xPos = 0;
		this.yPos = 0;
	}
	
	public Player(PApplet parent, int xPos, int yPos)
	{
		this.parent = parent;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void fall()	
	{
		if(falling)
		{
			this.yPos += this.speed;
		}
		else
		{
			if(this.yPos > this.stopRise && !falling)
			{
				this.yPos -= 10;
			}
			else
			{
				falling = true;
			}
		}
	}
	
	public void changeColor(Color newColor)
	{
		this.playerColor = newColor;
	}
	
	public void jump()
	{
		this.stopRise = this.yPos - (int) (500 * 0.1);			//Change to constant***********
		this.falling = false;
	}
	
	public boolean offScreen()
	{
		if(this.yPos - this.diameter/2 <= 0 || this.yPos + this.diameter/2 >= 500) 	//Change to constant
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getxPos()
	{
		return this.xPos;
	}
	
	public void setxPos(int xPos)
	{
		this.xPos = xPos;
	}
	
	public void increaseSpeed(double howMuch)
	{
		this.speed = this.speed + howMuch;
	}
	
	public int getyPos()
	{
		return this.yPos;
	}
	
	public double getSpeed()
	{
		return this.speed;
	}
	
	public int getRadius()
	{
		return this.diameter/2;
	}
	
	public void draw()
	{
		parent.fill(playerColor.getRGB());
		parent.ellipse(xPos, yPos, diameter, diameter);
	}
	
}
