package nl.javadude.monopoly.domain.squares;


/**
 *
 */
public class Station extends AbstractOwnableSquare {

	public Station(String name) {
		super(name, 200);
	}

	public long getRent() {
		return 25;
	}
}
