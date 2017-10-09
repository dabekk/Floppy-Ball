import processing.core.PApplet;

import java.awt.Color;

public class DriverClass extends PApplet {
	public final int SCREEN_WIDTH = 500;
	public final int SCREEN_HEIGHT = 500;
	private final Color backgroundColor = new Color(0);
	private Bars[] bars;
	private Player player;
	private int score;
	private boolean gameOver;
	private int highScore = 0;
	private Button restartButton;
	private Button startButton;
	private boolean start = false;

	public static void main(String[] args) {
		
		PApplet.main("DriverClass");

	}
	
	public void settings()
	{
		size(SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	public void setup()
	{
		frameRate(60);
		background(backgroundColor.getRGB());
		bars = new Bars[3];						//initialise first bars
		for(int i = 0; i < bars.length; i++)
		{
			initialiseBar(i, (int) (SCREEN_WIDTH + i * 0.5 * SCREEN_WIDTH));
		}
		//initialise game to start
		this.player = new Player(this, (int) SCREEN_WIDTH/2,(int) SCREEN_HEIGHT/2);
		this.score = 0;
		this.gameOver = false;
		this.startButton = new Button(this);
	}
	
	//method will generate bars onto the screen with random gaps
	public void initialiseBar(int index, int xPosition)
	{
		int xPos = xPosition;
		int yPos1 = 0;
		//set random barHeight
		int gap = (int) (SCREEN_HEIGHT * 0.3);			//dictates gap between top and bottom bar
		int max = (int) (SCREEN_HEIGHT * 0.9) - gap;
		int min = (int) (SCREEN_HEIGHT * 0.1);
		int barHeight = min + (int) (Math.random() * ((max - min) + 1));
		int yPos2 = (int) (barHeight + gap);			
		bars[index] = new Bars(this, xPos, yPos1, xPos, yPos2, barHeight);		//barHeight is only for top bar
	}
	//method to draw all bars on screen
	public void drawBars()
	{
		for(int i = 0; i < bars.length; i++)
		{
			bars[i].draw();
			if(bars[i].getxPos() <= - bars[i].getBarWidth())
			{
				initialiseBar(i, (int) (SCREEN_WIDTH + 0.3 * SCREEN_WIDTH));
			}
			if(!gameOver)
			{
				bars[i].move();
			}
		}
	}
	
	public void drawScore()
	{
		Color[] colors = new Color[10];				
		colors[0] = new Color(51, 51, 255);			//blue
		colors[1] = new Color(255, 102, 102);		//salmon
		colors[2] = new Color(178, 102, 255);		//purple	
		colors[3] = new Color(102, 200, 255);		//cyan
		colors[4] = new Color(51, 255, 51);			//green	
		colors[5] = new Color(255, 0, 127);			//pink	
		colors[6] = new Color(255, 180, 0);			//sand	
		colors[7] = new Color(255, 0, 0);			//red	
		colors[8] = new Color(250, 200, 204);		//pale pink	
		colors[9] = new Color(130, 255, 204);		//aqua
		
		int xPos = (int) (SCREEN_WIDTH * 0.1);
		int yPos = (int) (SCREEN_WIDTH * 0.1);
		int dimensions = (int) (SCREEN_WIDTH * 0.1);
		
		fill(colors[score % 10].getRGB());					//chooses color based on score
		this.rect(xPos, yPos, dimensions, dimensions);		//draws rectangle for score
		fill(255);
		textSize((float) (SCREEN_WIDTH * 0.05));
		textAlign(CENTER);
		this.text(String.valueOf(score), (float) (xPos + xPos/2), (float) (yPos + yPos/1.5));	//draws score in centre of rectangle
	}
	
	public void increaseDifficulty()
	{
		if(this.score % 5 == 0)
		{
			this.player.increaseSpeed(0.5);
			for(int i = 0; i < bars.length; i++)
			{
				bars[i].increaseSpeed(1);
			}
		}
	}
	
	public void checkAdjustScore()
	{
		for(int i = 0; i < bars.length; i++)
		{
			if(!bars[i].getPassedCentre() && bars[i].getxPos() <= SCREEN_WIDTH/2)
			{
				this.score++;
				bars[i].setPassedCentre(true);
			}
		}
	}
	
	public void endGame()
	{
		//checks if player hit any of the bars
		for(int i = 0; i < bars.length; i++)
		{
			if(this.player.offScreen() || (this.player.getxPos() + this.player.getRadius() >= bars[i].getxPos() &&
					this.player.getxPos() - this.player.getRadius() <= (bars[i].getBarWidth() + bars[i].getxPos()) &&
					(this.player.getyPos() - this.player.getRadius() <= bars[i].getBarHeight() ||
					this.player.getyPos() + this.player.getRadius() >= (bars[i].getBarHeight() + SCREEN_HEIGHT * 0.3))))
			{
				Color newColor = new Color(255, 0, 0);
				this.player.changeColor(newColor);
				this.gameOver = true;
				this.player.increaseSpeed(0.2);		//speeds up fall when player loses
			}
		}
	}
	
	
	
	public void mousePressed()
	{	
		//starts game when start button pressed
		if(startButton.hoveringOver(mouseX, mouseY))
		{
			this.start = true;
			setup();
		}
		//makes player jump when click on screen
		if(!gameOver)
		{
			this.player.jump();	
		}
		//restarts game
		if(gameOver && this.restartButton.hoveringOver(mouseX, mouseY))
		{
			this.start = false;
		}
	}
	
	//method to display end game message
	public void displayEndGame()
	{
		boolean beatHighScore = false;
		if(this.score > this.highScore)
		{
			this.highScore = this.score;
			beatHighScore = true;
		}
		fill(255);
		textAlign(CENTER);
		textSize((float) (SCREEN_WIDTH * 0.1));
		text("GAME OVER", SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
		if(beatHighScore)
		{
			textSize((float) (SCREEN_WIDTH * 0.05));
			textAlign(CENTER);
			text("NEW HIGHSCORE : " + String.valueOf(this.score), SCREEN_WIDTH/2, (float) (SCREEN_HEIGHT/1.5));
		}
		else
		{
			textSize((float) (SCREEN_WIDTH * 0.05));
			textAlign(CENTER);
			text("HIGH SCORE : " + String.valueOf(this.highScore) + 
					" YOUR SCORE : " + String.valueOf(this.score), SCREEN_WIDTH/2, (float) (SCREEN_HEIGHT/1.5));
		}
		this.restartButton = new Button(this, (int) (SCREEN_WIDTH * 0.3), (int) (SCREEN_HEIGHT * 0.7), (int) (SCREEN_WIDTH * 0.4), 
				(int) (SCREEN_HEIGHT * 0.1), "MENU", (int) (SCREEN_WIDTH * 0.05), new Color(57, 255, 20));
		restartButton.draw(mouseX, mouseY);
	}
	//method to display start game menu
	public void startGameMenu()
	{
		startButton = new Button(this, (int) ((SCREEN_WIDTH/2) - ((SCREEN_WIDTH * 0.6)/2)), (int) ((SCREEN_HEIGHT/2) - (SCREEN_HEIGHT * 0.1)), 
				(int) (SCREEN_WIDTH * 0.6), (int) (SCREEN_HEIGHT * 0.2), "PLAY", 
				(int) (SCREEN_WIDTH * 0.1), new Color(57, 255, 20));
		startButton.draw(mouseX, mouseY);
	}
	
	public void draw()
	{
		background(backgroundColor.getRGB());
		if(!this.start)
		{
			startGameMenu();
		}
		else
		{
			drawBars();
			this.player.draw();
			checkAdjustScore();
			drawScore();
			if(!this.player.offScreen())
			{
				this.player.fall();
			}
			endGame();
			if(this.gameOver)
			{
				displayEndGame();
			}
		}
	}
}
