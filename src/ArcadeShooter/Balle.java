package ArcadeShooter;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Balle {
	protected Vector2f pos;
	protected Vector2f vit;
	protected int lived = 0;
	protected boolean aktiv = true ;
	protected int MAX_LIFETIME = 2000;
	protected int RADIUS_SQUARED = 100;
	protected int DAMAGE = 5;
	public Balle(Vector2f pos, Vector2f vit) {
		this.pos = pos;
		this.vit = vit;
		vit.scale(20);
	}
	public Balle() {
		aktiv = false;
	}
	public void update(int t) throws SlickException{
		if(aktiv) {
			Vector2f vitreel = vit.copy();
			vitreel.scale(t/1000.0f);
			pos.add(vitreel);
			lived += t;
			if(lived > MAX_LIFETIME) {
				aktiv = false;
			}
		}
	}
	public void render(GameContainer gc, Graphics g) throws SlickException{
		if(aktiv) {
			g.setColor(Color.yellow);
			g.fillOval(pos.getX()+10, pos.getY()+10, 5, 5);
		}
	}
	public boolean isAktiv() {
		return aktiv;
	}
	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}
	public boolean collidewith(Vector2f otherpos, int otherRadiusSquared) {
		int dis = (int) otherpos.copy().sub(pos).lengthSquared();
		if(dis < (otherRadiusSquared+RADIUS_SQUARED)) {
			return true;
		}
		return false;
	}
	public Balle init(Vector2f pos, Vector2f vit) throws SlickException{
		this.pos = pos;
		this.vit = vit;
		vit.scale(500);
		setAktiv(true);
		return this;
	}
	public int getDamage() {
		return DAMAGE;
	}
}
