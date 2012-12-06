package nl.javadude.monopoly.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@SuppressWarnings("serial")
public class Player implements Serializable, MoneyExchanger {
	private String name;
	private TurnState turnState;
	private ISquare currentPosition;
	private long money;
	private List<IOwnable> possessions = new ArrayList<IOwnable>();
	private Board board;

    private Controller theController = null;

	public Player(Board board, String name) {
		this.name = name;
		this.board = board;
		turnState = TurnState.END_TURN;
		// Add the base money to the player.
        Bank.BANK.pay(1500, this);
		setCurrentPosition(board.START);
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

	public boolean isTurnAction() {
		return turnState == TurnState.ROLLED_SAME_ONCE
				|| turnState == TurnState.ROLLED_SAME_TWICE
				|| turnState == TurnState.TURN_ACTION;
	}

	public boolean buy() {
		if (isTurnAction() && currentPosition instanceof IOwnable) {
			IOwnable ownable = (IOwnable) currentPosition;
			if (ownable.canBuy()) {
				pay(ownable.getCost(), Bank.BANK);
				ownable.setOwned(this);
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

	void payRent() {
		if (currentPosition instanceof IOwnable) {
			IOwnable ownable = (IOwnable) currentPosition;
			if (!ownable.isUnowned()) {
				pay(ownable.getRent(), ownable.owner());
			}
		}
	}

	public void move(Dice dice) {
		if (!isRollAllowed()) {
			return;
		}

		turnState = turnState.transition(this);
		if (isJailed()) {
			currentPosition = board.JAIL;
		} else {
			board.move(this, dice.view());
		}
        if (isControlled())
        {
            if (theController.shouldBuy(currentPosition))
            {
                buy();
                forceTurnFinish();
            }
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

	public boolean isFinishedTurn() {
		return turnState == TurnState.END_TURN;
	}

	public boolean isRollAllowed() {
		return turnState == TurnState.START_TURN
				|| turnState == TurnState.ROLLED_SAME_ONCE
				|| turnState == TurnState.ROLLED_SAME_TWICE;
	}

	public void forceTurnFinish() {
		turnState = TurnState.END_TURN;
	}

	public boolean owns(String name) {
		ISquare sq = board.findLocation(name);
		return possessions.contains(sq);
	}

    public Controller getController()
    {
        return theController;
    }

    public void setController(Controller aController)
    {
        theController = aController;
    }

    public boolean isControlled()
    {
        return theController != null;
    }
}
