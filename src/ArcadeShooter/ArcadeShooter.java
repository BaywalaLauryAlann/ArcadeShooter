package ArcadeShooter;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class ArcadeShooter extends BasicGame {
	private Joueur j;
	private LinkedList<Ennemi> en;
	private Ennemi e ;
	//Cette variable permet de definir la frequence d'apparition des ennemis
	float SPAWN_CHANCE = 8;
	private Random r;
	Music mu ;
	Music mu1 ;
	//Les variables pour la progression du joueur
	private int score ;
	private float multi ;
	private long time;
	Image[] tab = new Image[3];
	public ArcadeShooter(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		j.render(gc, g);
		for(Ennemi e : en) {
			e.render(gc, g);
		}
//Ici on efface manuellement l'ecran avec un noir transparent pour obtenir un meilleur rendu des déplacements  
		g.setColor(new Color(0, 0, 0, 50));
		g.fillRect(0, 0, 1281, 720);
		
//Ici on définit le format d'affichage des variables
		NumberFormat nf = NumberFormat.getInstance();
		//Cette méthode permet de combler l'affichage au n-ième chiffre après la virgule
		nf.setMinimumFractionDigits(2);
		
		//Cette méthode permet de limiter l'affichage au n-ième chiffre après la virgule
		nf.setMaximumFractionDigits(2);
		
//Affichage des variables à l'écran
		g.setColor(Color.white);
		g.drawString("Score :" +score, 10, 5);
		g.drawString("Multiplicateur :" +nf.format(multi), 600, 5);
		g.drawString("Temps :" +nf.format((double)time/1000), 1080, 5);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		mu = new Music("Music/pes6  (1).ogg");
		mu.play(1.0f, 1.0f);
		mu.loop();
		j = new Joueur(new Vector2f(500, 540));
		en = new LinkedList<Ennemi>();
		r = new Random();
		
//Cette méthode permet d'indiquer si on doit effacer l'écran après chaque image;		
		gc.setClearEachFrame(false);
		mu1 = new Music("Music/Aura-Burst.ogg");
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		// TODO Auto-generated method stub
		j.update(gc, t);
		Iterator<Ennemi> i = en.iterator();
		while(i.hasNext()) {
			Ennemi b = i.next();
			b.update(gc, t);
			
//Ici on supprime les ennemis qui sont morts ou qui sont à la position du joueur
			if(!b.isAlive()) {
				score += 10*multi ;
				i.remove();
			}else if(b.getPos().copy().sub(j.getPos()).lengthSquared()<5) {
				i.remove();
			}
			j.verif_balles_collision(b.getBalles());
			b.verif_balles_collision(j.getBalles());
		}
		
//Définit une chance d'apparition maximale
		if(SPAWN_CHANCE<1) {
			SPAWN_CHANCE = 1;
		}
		
//Devrions nous engendrer un ennemi ?		
		if(r.nextInt((int)(SPAWN_CHANCE*t))==0) {
//Ici on engendre un ennemi à une position aléatoire			
			e = new Ennemi(new Vector2f((int)(r.nextInt(1280)), (int)(r.nextInt(720))), j);
			e.init(gc);
			en.add(e);
		}
//Rendre le jeu plus difficile à chaque appel de mise à jour si nous sommes morts		
		if(!j.isAlive()) {
			SPAWN_CHANCE -= 0.1 ;
			return ;
		}else {
			time += t;
			multi +=(float)t/5000;
		}
		if(gc.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			System.out.println(gc.getInput().getMouseX()+" "+gc.getInput().getMouseY());
		}
	}
	public String toString() {
		return "Game" ;
	}

}
