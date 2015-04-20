package objects;


import static framework.Game.GAME_TIME;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;




import org.lwjgl.input.Keyboard;


import org.lwjgl.util.Rectangle;

import framework.Animation;
import framework.Draw;
import framework.Game;
import framework.GameObject;
import framework.ObjectID;

public class Door extends GameObject{
	private Animation closedDoor;
	private Animation openDoor;
	private Animation opening;
	private Animation current;	//contains current animation
	private int state = 0; //0 = CLOSED, 1 = OPENING, 2 = OPEN
	private boolean test = false;
	
	public Door (float x, float y, int numLights){
		super(x,y,50,50,ObjectID.Door);
		hitbox = new Rectangle((int)(x),(int)(y),(int)(width),(int)(height));
		opening = new Animation("res/doors/"+ numLights + "_light",10);	//chooses numLights based on the parameter passed
		openDoor = new Animation("res/open_door/"+ numLights + "_light",0);
		closedDoor = new Animation("res/closed_door/"+ numLights + "_light",0);
		current = new Animation("res/closed_door/"+ numLights + "_light",0);
	}
	
	public void render(){
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		Draw.drawQuad(x, y, width, height,current.getCurrentFrame()); //Draw the player based on current position and current animation.
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
		
		if(Game.DEBUG)
		{
			Draw.drawQuad(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
		}
		if(test)
		{
		}
			
	}	
	
	public void tick() {
		if(current!=null) // In case of a loading error we want to make sure that we don't get a null pointer exception by checking first.
			current.update(); // Updates the animation so that it goes to the next frame.

		if(Keyboard.isKeyDown(Keyboard.KEY_Z))
			test = true;
		else
			test = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_X)) // PRESS X TO TURN ANOTHER LIGHT ON
		{
			if (state != 1){ //if door isn't in process of opening
				current.nextFrame(); //add a light
				if (current.isDone()){
					current = opening;
					state = 1;
				}
			}
		}
		
		
		updateHitbox();
	
	}
	
	private void updateHitbox()
	{
		hitbox.setLocation((int)(x), (int)(y));
	}
	
	public void collision() {
		// TODO runs every tick and checks each collision box to see if we're colliding with anything.
	}
}

