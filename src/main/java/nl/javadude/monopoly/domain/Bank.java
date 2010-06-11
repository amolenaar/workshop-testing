package nl.javadude.monopoly.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Special player that does not participate in the game, and has an unlimited amount of monopoly money.
 */
public class Bank extends Player {
	private static final Logger LOGGER = LoggerFactory.getLogger(Bank.class);
	public static final Bank BANK = new Bank();

	private Bank() {
		super("BANK");
		setMoney(1500000);
	}

	@Override
	public ISquare getCurrentPosition() {
		return null;
	}

	@Override
	public void pay(long amount, Player toPlayer) {
		LOGGER.info("Bank paid {} to {}", amount, toPlayer);
		toPlayer.receiveMoney(amount);
	}

	@Override
	public void receiveMoney(long amount) {
		LOGGER.info("Bank received {}", amount);
		return;
	}


}
