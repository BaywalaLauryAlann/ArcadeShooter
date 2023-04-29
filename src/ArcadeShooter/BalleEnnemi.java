package ArcadeShooter;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class BalleEnnemi extends Balle {
	public BalleEnnemi(Vector2f pos, Vector2f vit) {
		super(pos, vit);
		Random r = new Random();
		MAX_LIFETIME = 2000 + r.nextInt(2000);
		vit.scale(50+r.nextInt(450));
	}
	public BalleEnnemi init(Vector2f pos, Vector2f vit) throws SlickException{
		this.pos = pos;
		this.vit = vit;
		Random r = new Random();
		MAX_LIFETIME = 2000 + r.nextInt(2000);
		vit.scale(50+r.nextInt(450));
		setAktiv(true);
		return this;
	}
	public BalleEnnemi() {
		super();
		DAMAGE = 1;
	}
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(aktiv) {
			super.render(gc, g);
		}
	}
	public void update(int t) throws SlickException{
		// TODO Auto-generated method stub
		if(aktiv) {
			super.update(t);
		}
	}
	
}
