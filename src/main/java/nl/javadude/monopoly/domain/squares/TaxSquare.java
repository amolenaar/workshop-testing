package nl.javadude.monopoly.domain.squares;

import nl.javadude.monopoly.domain.Bank;
import nl.javadude.monopoly.domain.IAutomatic;
import nl.javadude.monopoly.domain.Player;

/**
 *
 */
@SuppressWarnings("serial")
public class TaxSquare extends AbstractSquare implements IAutomatic {
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
