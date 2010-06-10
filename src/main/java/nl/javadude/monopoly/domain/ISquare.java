package nl.javadude.monopoly.domain;

import java.io.Serializable;

public interface ISquare extends Serializable {

	String getName();
	
	boolean canBuy();

}