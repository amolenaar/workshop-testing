/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.javadude.monopoly.domain;

/**
 * Any square that implements this interface can react onto a player landing there.
 */
public interface IAutomatic extends ISquare {

	public void execute(Player player);
}
