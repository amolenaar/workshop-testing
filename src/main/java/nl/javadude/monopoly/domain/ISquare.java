package nl.javadude.monopoly.domain;

import java.io.Serializable;

public interface ISquare extends Serializable {

	int getPosition();

	String getName();
	
	boolean canBuy();

}