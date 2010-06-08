/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.javadude.monopoly.domain;

/**
 * A Square that implements Ownable can be bought.
 */
public interface IOwnable extends ISquare {

	long getCost();

	long getRent();

	boolean isUnowned();

	void setOwner(Player player);
}
