package nl.javadude.monopoly.fixtures;

import nl.javadude.monopoly.domain.Board;
import nl.javadude.monopoly.domain.Player;

public class SetUserPropertiesFixture extends CommonFixtureCode {

	private Player user;
	
	public void createUser(String name) {
		user = new Player(name);
		save(user, name+".data");
	}
	
	public void setBalance(long amount) {
		if (user != null) {
			user.setMoney(amount);
			save(user, user.getName()+".data");
		}
	}
	
	public void setPosition(String position) {
		if (user != null) {
			user.setCurrentPosition(Board.findLocation(position));
			save(user, user.getName()+".data");
		}
	}
	
}
