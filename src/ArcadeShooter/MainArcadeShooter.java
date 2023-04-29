package ArcadeShooter;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainArcadeShooter {

	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		AppGameContainer app = new AppGameContainer(new ArcadeShooter("Arcade Shooter"));
		app.setTargetFrameRate(60);
		app.setShowFPS(false);
		app.setDisplayMode(1280, 720, false);
		app.start();
	}

}
