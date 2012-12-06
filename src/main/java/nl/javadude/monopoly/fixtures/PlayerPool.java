package nl.javadude.monopoly.fixtures;

import java.util.HashMap;
import java.util.Map;

import nl.javadude.monopoly.domain.*;

public class PlayerPool {
	public static final Map<String, PlayerProperties> PLAYER_POOL = new HashMap<String, PlayerProperties>();

	public static Player newPlayer(String name, Game game) {
		PlayerProperties pp = PLAYER_POOL.get(name);
		System.out.println("Creating player with name " + name + " " + pp.getMoney() + " " + pp.getCurrentPosition());
        Player p = new Player(game.getBoard(), "player");
        game.addPlayer(p);
		p.setMoney(pp.getMoney());
		p.setCurrentPosition(game.getBoard().findLocation(pp.getCurrentPosition()));
		return p;
	}
}
