package nl.javadude.monopoly.domain;

import java.util.Random;

public class Dice {

	private int die1;
	private int die2;
	private Random generator = new Random();

	public static final Dice INSTANCE = new Dice();

	// Private constructor prevents instantiation from other classes
	private Dice() {
	}

	public static Dice getInstance() {
		return INSTANCE;
	}

	public void roll() {

		die1 = rollSingleDie();
		die2 = rollSingleDie();
	}

	private int rollSingleDie() {
		return generator.nextInt(6) + 1;
	}

	public int view() {
		return die1 + die2;
	}

	public boolean isSameEyes() {
		return (die1 == die2) ? true : false;
	}

	public void setDiceValues(int die1, int die2) {
		this.die1 = die1;
		this.die2 = die2;
	}
}
