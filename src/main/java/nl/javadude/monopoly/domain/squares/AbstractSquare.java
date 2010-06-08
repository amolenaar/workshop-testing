package nl.javadude.monopoly.domain.squares;

import nl.javadude.monopoly.domain.ISquare;

/**
 *
 */
public abstract class AbstractSquare implements ISquare {
	protected String name;

	public AbstractSquare(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public boolean canBuy() {
		return false;
	}
}
