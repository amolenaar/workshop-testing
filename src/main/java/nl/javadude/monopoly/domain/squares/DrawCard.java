package nl.javadude.monopoly.domain.squares;

import nl.javadude.monopoly.domain.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 */
public class DrawCard extends AbstractSquare implements IAutomatic {

	private Stack<Card> stack;
	private List<Card> drawn = new ArrayList<Card>();
	private double unifier = Math.random() * 1000;

	public DrawCard(String name, Stack<Card> stack) {
		super(name);
		this.stack = stack;
	}

	public void execute(Player player) {
		//throw new UnsupportedOperationException("Don't know how to draw card");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof DrawCard)) {
			return false;
		}
		DrawCard card = (DrawCard) obj;
		return (card.getName() != null && card.getName().equals(getName()) && unifier == card.getUnifier());
	}

	public double getUnifier() {
		return unifier;
	}
	
}
