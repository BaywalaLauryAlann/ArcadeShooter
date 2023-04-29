package ArcadeShooter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Vaisseau {
	protected int DELAY = 100;
	protected int delta = 0;
	protected Vector2f pos;
	protected Balle[] balles ;
	protected int current = 0;
	protected int RADIUS_SQUARED = 3600;
	protected int lives = 5;
	protected boolean alive = true;
	
	protected Vaisseau(Vector2f pos, int ballesMax) throws SlickException {
		this.pos = pos;
		balles = new Balle[ballesMax];
		for(int i = 0; i < balles.length; i++) {
			balles[i] = new Balle();
		}
	}
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for(Balle b : balles) {
			b.render(gc, g);
		}
		
	}
	public void update(GameContainer gc, int t) throws SlickException{
		delta += t;
		for(Balle b : balles) {
			b.update(t);
		}
	}
	public void assaut(Vector2f vec, Balle b) throws SlickException {
		delta = 0;
//On soustrait au vecteur passé en paramètre le vecteur position du vaisseau pour que les balles 
//ennemies puissent etre tirées en direction du joueur
		vec.sub(pos);
//On normalise le vecteur en paramètre afin d'afficher clairement le déplacement des balles		
		vec.normalise();
		balles[current] = b.init(pos.copy(), vec);
		current++ ;
		if(current >= balles.length) {
			current = 0;
		}
	}
	public void init(GameContainer gc) throws SlickException{
		
	}
	public Balle[] getBalles() {
		return balles;
	}
	public Vector2f getPos() {
		return pos;
	}
	public void verif_balles_collision(Balle[] autresballes) {
		for(Balle b : autresballes) {
			if(b.isAktiv() && b.collidewith(pos, RADIUS_SQUARED)) {
				b.setAktiv(false);
				lives -= b.getDamage();
				if(lives < 1 && alive) {
					die();
				}
			}
		}
		
	}
	public void die() {
		alive = false;
	}
	public boolean isAlive() {
		return alive;
	}
}
