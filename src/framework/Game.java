package framework;

import static framework.Draw.Background;
import static framework.Draw.Setup;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.ArrayList;

import objects.Platform;
import objects.Player;

import org.lwjgl.opengl.Display;


public class Game {
	
	public static boolean DEBUG = true;
	public final static Time GAME_TIME = new Time();
	
	public Game()
	{
		Setup();
		//for(int i = 0; i < WIDTH/64; i ++)
			//for(int j = 0; j < HEIGHT/64;j ++)
				Handler.getObjects().add(new Player(64,64,256,256));
				Handler.getObjects().add(new Platform(64,64,64,64));
		
		while(!Display.isCloseRequested())
		{
			GAME_TIME.update();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
			Background();
			for(int i = 0; i < Handler.getObjects().size();i++)
			{
				Handler.getObjects().get(i).tick();
				Handler.getObjects().get(i).render();
			}
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public static void createLevel(String path)
	{
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
	
}