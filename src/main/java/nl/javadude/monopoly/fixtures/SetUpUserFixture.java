package nl.javadude.monopoly.fixtures;

import nl.javadude.monopoly.domain.Board;
import nl.javadude.monopoly.domain.Player;

public class SetUpUserFixture extends CommonDoFixtureCode {

	private Player user;
	
	public void createUser(String name) {
		user = new Player(name);
		save(user, constructFileName(name));
	}
	
	public void setBalance(long amount) {
		if (user != null) {
			user.setMoney(amount);
			save(user, constructFileName(user.getName()));
		}
	}
	
	public void setPosition(String position) {
		if (user != null) {
			user.setCurrentPosition(Board.BOARD.findLocation(position));
			save(user, constructFileName(user.getName()));
		}
	}
	
}
