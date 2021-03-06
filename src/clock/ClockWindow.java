package clock;

/*Imports from the Slick2d Game library*/
import games.*;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;


public class ClockWindow extends BasicGame{
	Blocks blcks;
	Clock clock;
	GameStarter start;
	Color col;
	GradientFill fill;
	GradientFill playerFill;
	Input input;
	Player player;

	public ClockWindow(){
		super("Clock");
	}

	public void init(GameContainer gc) throws SlickException{


		/*This is a little test case to see if my class works*/
		fill = new GradientFill(0, 0, Color.blue, 50, 50, col.blue);
		playerFill = new GradientFill(320, 240, Color.red, 640, 480, col.cyan);
		clock = new Clock();
		player = new Player();
		blcks = new Blocks(10);
		input = gc.getInput();
		start = new GameStarter();


	}

	public void update(GameContainer gc, int delta) throws SlickException{
		clock.update();
		if(GameStarter.checkGameState()){
			
			blcks.update();
			player.update(input);
		}
		start.update(input);
		for(int i=0; i<blcks.getNumBlocks(); i++){
			player.checkCollision((Block) blcks.getBlock(i));
		}

	}

	public void render(GameContainer gc, Graphics g) throws SlickException{
		clock.render();
		start.render();
		if(GameStarter.checkGameState()){
			for(int i=0; i<blcks.getNumBlocks(); i++){
				g.fill(blcks.getBlock(i), fill);
			}
			g.fill(player, playerFill);
		}

	}
	public static void startClockWindow() throws SlickException{
		AppGameContainer app = new AppGameContainer(new ClockWindow());
		app.setTargetFrameRate(60);
		app.setDisplayMode(640, 480, true);
		app.start();

	}
}
