package nl.javadude.monopoly.domain.squares;

import nl.javadude.monopoly.domain.ISquare;

/**
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractSquare implements ISquare {
	protected String name;
	private int position;

	public AbstractSquare(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean canBuy() {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AbstractSquare)) {
			return false;
		}
		AbstractSquare sq = (AbstractSquare) obj;
		return (sq.getName() != null && sq.getName().equals(getName()));
	}


}
