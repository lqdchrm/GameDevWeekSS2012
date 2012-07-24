package de.fhtrier.gdw2.sotf;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import de.fhtrier.gdw2.sotf.games.AbstractGameBase;
import de.fhtrier.gdw2.sotf.games.ClientGame;
import de.fhtrier.gdw2.sotf.games.ServerGame;

public class Starter {

	public static void main(String[] args) throws SlickException {
		try {
			selectGame(args);
		} catch(SlickException e) {
			e.printStackTrace();
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private static void selectGame(String[] args) throws SlickException {
		if (args.length < 1) {
			throwArgumentsError();
		} else {
			AbstractGameBase game = null;

			switch (args[0]) {
			case "client":
				game = new ClientGame(args);
				break;
			case "server":
				game = new ServerGame(args);
				break;
			default:
				throwArgumentsError();
			}

			if (game != null) {
				AppGameContainer app = new AppGameContainer(game);
				app.setTargetFrameRate(60);
				app.setVSync(true);
				app.setDisplayMode(800, 600, false);
				app.setAlwaysRender(true);
				app.start();
			}
		}
	}

	private static void throwArgumentsError() throws SlickException {
		throw new SlickException(
				"wrong number of parameters. Please use *.jar server|client [ip] [port]");
	}
}