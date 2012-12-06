package nl.javadude.monopoly.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Special player that does not participate in the game, and has an unlimited amount of monopoly money.
 */
public class Bank implements MoneyExchanger {
	private static final Logger LOGGER = LoggerFactory.getLogger(Bank.class);
	public static final Bank BANK = new Bank();

	private Bank() {
	}

	public boolean pay(long amount, MoneyExchanger toPlayer) {
		LOGGER.info("Bank paid {} to {}", amount, toPlayer);
		toPlayer.receiveMoney(amount);
        return true;
	}

	public void receiveMoney(long amount) {
		LOGGER.info("Bank received {}", amount);
	}


}
