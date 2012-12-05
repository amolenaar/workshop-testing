package nl.javadude.monopoly.domain;

import java.io.Serializable;

public interface ISquare extends Serializable {

	// Position for Ajax-item placement
	int getPosition();

	String getName();
	
	boolean forSale();

}