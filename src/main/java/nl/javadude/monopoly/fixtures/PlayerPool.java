package nl.javadude.monopoly.fixtures;

import java.util.HashMap;
import java.util.Map;

import nl.javadude.monopoly.domain.Game;
import nl.javadude.monopoly.domain.Player;

public class PlayerPool {
	public static final Map<String, PlayerProperties> PLAYER_POOL = new HashMap<String, PlayerProperties>();

	public static Player newPlayer(String name, Game game) {
		PlayerProperties pp = PLAYER_POOL.get(name);
		System.out.println("Creating player with name " + name + " " + pp.getMoney() + " " + pp.getCurrentPosition());
		Player p = game.addPlayer(name);
		p.setMoney(pp.getMoney());
		p.setCurrentPosition(game.getBoard().findLocation(pp.getCurrentPosition()));
		return p;
	}
}
