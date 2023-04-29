package ArcadeShooter;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;



public class Joueur extends Vaisseau {
	protected int DELAY = 100;
	protected Image im;
	public Joueur(Vector2f pos) throws SlickException {
		super(pos, 20); 
		// TODO Auto-generated constructor stub
		im = new Image("Images/sq.png");
	}
	public void render(GameContainer gc, Graphics g) throws SlickException{
		if(!isAlive()) {
			return;
		}
		super.render(gc, g);
//		g.setColor(Color.blue);
//		g.fillRect(pos.getX()-30, pos.getY()-30, 60, 60);
		g.drawImage(im, pos.getX()-30, pos.getY()-30);
		g.setColor(Color.red);
		g.fillRect(0, 0, 256*lives, 30);
	}
	public void update(GameContainer gc, int t) throws SlickException{
		super.update(gc, t);
		if(gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && delta>DELAY) {
			assaut(new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY()), new Balle());
		}
		if((gc.getInput().isKeyDown(Input.KEY_SPACE))&& delta>DELAY) {
			assaut(new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY()), new Balle());
		}
		float deltalenght = (float)t/5 ;
		if((gc.getInput().isKeyDown(Input.KEY_RIGHT))&& pos.getX()<=1280) {
			pos.add(new Vector2f(deltalenght, 0));
			if(pos.getX()>=gc.getWidth()-59) {
				pos = new Vector2f(gc.getWidth()-59, pos.getY());
			}
		}
		if((gc.getInput().isKeyDown(Input.KEY_LEFT))&& pos.getX()>-20) {
			pos.add(new Vector2f(-deltalenght, 0));
			if(pos.getX()<=29) {
				pos = new Vector2f(29, pos.getY());
			}
		}
		if((gc.getInput().isKeyDown(Input.KEY_DOWN))&& pos.getY()<=720) {
			pos.add(new Vector2f(0, deltalenght));
		}
		if((gc.getInput().isKeyDown(Input.KEY_UP))&& pos.getY()>-20) {
			pos.add(new Vector2f(0, -deltalenght));
		}
	}
	public void die() {
		alive = false;
	}
	public void init(GameContainer gc) throws SlickException {
		super.init(gc);
		RADIUS_SQUARED = 900;
		lives = 10;
	}
}
