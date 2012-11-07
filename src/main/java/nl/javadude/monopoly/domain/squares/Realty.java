package nl.javadude.monopoly.domain.squares;


/**
 * Station, Street or Utility company.
 */
@SuppressWarnings("serial")
public class Realty extends AbstractOwnableSquare {

	private int rent;

	public Realty(String name, int cost, int rent) {
		super(name, cost);
		this.rent = rent;
	}

	public long getRent() {
		return rent;
	}
}
