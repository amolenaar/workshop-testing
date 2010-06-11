package nl.javadude.monopoly.domain;

import nl.javadude.monopoly.domain.squares.Start;
import nl.javadude.monopoly.domain.squares.AbstractSquare;
import nl.javadude.monopoly.domain.squares.PoliceAgent;
import nl.javadude.monopoly.domain.squares.Realty;
import nl.javadude.monopoly.domain.squares.DrawCard;
import nl.javadude.monopoly.domain.squares.FreeParking;
import nl.javadude.monopoly.domain.squares.Jail;
import nl.javadude.monopoly.domain.squares.TaxSquare;
import nl.javadude.monopoly.domain.squares.Station;
import nl.javadude.monopoly.domain.squares.Utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Representation of a monopoly board. We assume that in any game only one board
 * is used, and that any thread only deals with a single game.
 */
public class Board implements Serializable {
	
	public static final Start START = new Start();
	public static final Jail JAIL = new Jail();
	private static final List<ISquare> squares = new ArrayList<ISquare>();
	private static Stack<Card> communityChest = new Stack<Card>();
	private static Stack<Card> chance = new Stack<Card>();
    public static final Board BOARD = new Board();
    
	private Board() {
		add(START);
		add(new Realty("Mediterranean Avenue", 60, 2));
		add(new DrawCard("Community Chest", communityChest));
		add(new Realty("Baltic Avenue", 60, 4));
		add(new TaxSquare("Income Tax", 200));
		add(new Station("Reading Railroad"));
		add(new Realty("Oriental Avenue", 100, 6));
		add(new DrawCard("Chance", chance));
		add(new Realty("Vermont Avenue", 100, 6));
		add(new Realty("Conneticut Avenue", 120, 8));
		add(JAIL);
		add(new Realty("St. Charles Place", 140, 10));
		add(new Utility("Electric Company"));
		add(new Realty("States Avenue", 140, 10));
		add(new Realty("Virginia Avenue", 160, 12));
		add(new Station("Pennsylvania Railroad"));
		add(new Realty("St. James Place", 180, 14));
		add(new DrawCard("Community Chest", communityChest));
		add(new Realty("Tennessee Avenue", 180, 14));
		add(new Realty("New York Avenue", 200, 16));
		add(new FreeParking());
		add(new Realty("Kentucky Avenue", 220, 18));
		add(new DrawCard("Chance", chance));
		add(new Realty("Indiana Avenue", 220, 18));
		add(new Realty("Illinois Avenue", 240, 20));
		add(new Station("B&O Railroad"));
		add(new Realty("Atlantic Avenue", 260, 22));
		add(new Realty("Ventnor Avenue", 260, 22));
		add(new Utility("Water Works"));
		add(new Realty("Marvin Gardens", 280, 22));
		add(new PoliceAgent(JAIL));
		add(new Realty("Pacific Avenue", 300, 26));
		add(new Realty("North Carolina Avenue", 300, 26));
		add(new DrawCard("Community Chest", communityChest));
		add(new Realty("Pennsylvania Avenue", 320, 28));
		add(new Station("Short Line"));
		add(new DrawCard("Chance", chance));
		add(new Realty("Park Place", 350, 35));
		add(new TaxSquare("Luxury Tax", 100));
		add(new Realty("Boardwalk", 400, 50));

	}
	
	public ISquare findLocation(String locationName) {
		for(ISquare square : squares) {
			if (square.getName().equals(locationName)) {
				return square;
			}
		}
		return null;
	}

	private void add(AbstractSquare square) {
		squares.add(square);
	}

	public void move(Player player, int roll) {
		ISquare newSquare = squares.get(calculateNewPosition(player, roll));
		player.setCurrentPosition(newSquare);
		if (newSquare instanceof IAutomatic) {
		   ((IAutomatic) newSquare).execute(player);
		}
	}

	private int calculateNewPosition(Player player, int roll) {
		int oldPosition = squares.indexOf(player.getCurrentPosition());
		int sum = oldPosition + roll;
		if (sum >= squares.size()) {
			player.receiveMoney(200);
			return sum - squares.size();
		} else {
			return sum;
		}
	}
}
