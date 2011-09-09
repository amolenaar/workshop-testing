package nl.javadude.monopoly.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Player implements Serializable, MoneyExchanger {
	private String name;
	private TurnState turnState;
	private ISquare currentPosition;
	private long money;
	private List<IOwnable> possessions = new ArrayList<IOwnable>();

	public Player(String name) {
		this.name = name;
		turnState = TurnState.END_TURN;
		// Add the base money to the player.
        Bank.BANK.pay(1500, this);
		setCurrentPosition(Board.START);
	}

	public void setCurrentPosition(ISquare currentPosition) {
		this.currentPosition = currentPosition;
	}

	public ISquare getCurrentPosition() {
		return currentPosition;
	}

	public void pay(long amount, MoneyExchanger toPlayer) {
		toPlayer.receiveMoney(amount);
		// TODO payed money needs to be withdrawn also!!
		money -= amount;
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

	public boolean buy() {
		if (currentPosition instanceof IOwnable) {
			IOwnable ownable = (IOwnable) currentPosition;
			if (ownable.canBuy()) {
				pay(ownable.getCost(), Bank.BANK);
				ownable.setOwner(this);
				addPossession(ownable);
				return true;
			}
		}
		return false;
	}

	public void move() {
		Dice dice = Dice.getInstance();
		dice.roll();
		move(dice);
		payRent();
	}

	private void payRent() {
		if (currentPosition instanceof IOwnable) {
			IOwnable ownable = (IOwnable) currentPosition;
			if (!ownable.isUnowned()) {
				pay(ownable.getRent(), ownable.getOwner());
			}
		}		
	}
	
	public void move(Dice dice) {
		turnState = turnState.transition(this);
		if (isJailed()) {
			currentPosition = Board.JAIL;
		} else {
			Board.BOARD.move(this, dice.view());
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Player)) {
			return false;
		}
		Player that = (Player) obj;
		return (that.getName() != null && that.getName().equals(getName()));
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
	
	public boolean owns(String name) {
		ISquare sq = Board.BOARD.findLocation(name);
		if (sq instanceof IOwnable) {
			IOwnable ownable = (IOwnable) sq;
			if (!ownable.isUnowned()) {
				Player owner = ownable.getOwner();
				return this.equals(owner);
			}
		}
		return false;
	}
	
	
}
