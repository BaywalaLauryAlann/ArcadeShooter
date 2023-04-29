package ArcadeShooter;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Ennemi extends Vaisseau {
	protected int DELAY = 500;
	protected Joueur j;
	protected Image im;
	protected Ennemi(Vector2f pos, Joueur j) throws SlickException {
		super(pos, 16);
		this.j = j;
		im = new Image("Images/sw.png");
		// TODO Auto-generated constructor stub
	}
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
//		g.setColor(Color.red);
//		g.fillRect(pos.getX()-20, pos.getY()-20, 40, 40);
		
		g.drawImage(im, pos.getX()-30, pos.getY()-30);
	}
	public void update(GameContainer gc, int t) throws SlickException {
		super.update(gc, t);
		if(delta > DELAY) {
			assaut(j.getPos().copy(), new BalleEnnemi());
		}
		pos.add(j.getPos().copy().sub(pos).normalise().scale((float)t/20));
	}
	public void init(GameContainer gc) throws SlickException {
		super.init(gc);
		Random r = new Random();
		lives = 1 + r.nextInt(10);
		RADIUS_SQUARED = 400;
	}

}
