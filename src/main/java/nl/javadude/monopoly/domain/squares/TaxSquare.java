package nl.javadude.monopoly.domain.squares;

import nl.javadude.monopoly.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class TaxSquare extends AbstractSquare implements IAutomatic {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaxSquare.class);
	private long tax;

	public TaxSquare(String name, long tax) {
		super(name);
		this.tax = tax;
	}

	/**
	 * Pay tax to the bank.
	 * @param player The player that has to pay.
	 */
	public void execute(Player player) {
		player.pay(tax, Bank.BANK);
	}
}
