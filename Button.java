import java.awt.Color;

import processing.core.PApplet;

public class Button {
	protected PApplet parent;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private String text;
	private int textSize;
	private Color buttonColor;
	
	public Button(PApplet parent)
	{
		this.parent = parent;
		this.xPos = 0;
		this.yPos = 0;
		this.width = 0;
		this.height = 0;
		this.text = "";
		this.textSize = 0;
		this.buttonColor = new Color(255);
	}
	
	public Button(PApplet parent, int xPos, int yPos, int width, int height, String text, int textSize, Color buttonColor)
	{
		this.parent = parent;			//must pass in parent so classes see processing libraries
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.text = text;
		this.textSize = textSize;
		this.buttonColor = buttonColor;
	}

	public Color getButtonColor() {
		return buttonColor;
	}

	public void setButtonColor(Color buttonColor) {
		this.buttonColor = buttonColor;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}
	//returns true if mouse is hovering over button
	public boolean hoveringOver(int mouseX, int mouseY)
	{
		if(mouseX >= this.xPos && mouseX <= this.xPos + this.width && 
				mouseY >= this.yPos && mouseY <= this.yPos + this.height)
		{
			return true;
		}
		else
		{
			return false;
		}
	} 
	//draws button
	public void draw(int mouseX, int mouseY)
	{
		if(hoveringOver(mouseX, mouseY))
		{
			parent.stroke(255);
		}
		else
		{
			parent.stroke(0);
		}
		//aligns text to centre of button
		parent.fill(this.buttonColor.getRGB());
		parent.rect(this.xPos, this.yPos, this.width, this.height);
		parent.textSize(textSize);	
		parent.textAlign(parent.CENTER);
		parent.fill(0);
		parent.text(this.text, this.xPos + this.width/2, (float) (this.yPos + this.height/1.5));
	}
}
