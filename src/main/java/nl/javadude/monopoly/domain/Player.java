package nl.javadude.monopoly.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Player implements Serializable {
	private String name;
	private TurnState turnState;
	private ISquare currentPosition;
	private Board board;
	private long money;
	private List<IOwnable> possessions = new ArrayList<IOwnable>();

	public Player(String name) {
		this.name = name;
		turnState = TurnState.END_TURN;
	}

	public void setCurrentPosition(ISquare currentPosition) {
		this.currentPosition = currentPosition;
	}

	public ISquare getCurrentPosition() {
		return currentPosition;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void pay(long amount, Player toPlayer) {
		toPlayer.receiveMoney(amount);
	}

	public void receiveMoney(long amount) {
		money += amount;
	}

	public long getMoney() {
		return money;
	}
	
	public void setMoney(long money) {
		this.money = money;
	}

	public List<IOwnable> getPossessions() {
		return possessions;
	}

	public void addPossession(IOwnable ownable) {
		this.possessions.add(ownable);
	}

	public boolean isJailed() {
		return turnState == TurnState.JAILED;
	}

	public void buy() {
		if (currentPosition instanceof IOwnable) {
			IOwnable ownable = (IOwnable) currentPosition;
			pay(ownable.getCost(), Bank.BANK);
			ownable.setOwner(this);
		}
	}

	public void move() {
		Dice dice = Dice.getInstance();
		dice.roll();
		move(dice);
	}

	public void move(Dice dice) {
		turnState = turnState.transition(this);
		if (isJailed()) {
			currentPosition = Board.JAIL;
		} else {
			board.move(this, dice.view());
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Player)) {
			return false;
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public void startTurn() {
		turnState = TurnState.START_TURN;
	}

	public boolean finishedTurn() {
		return turnState == TurnState.END_TURN;
	}

	public void forceTurnFinish() {
		turnState = TurnState.END_TURN;
	}
}
