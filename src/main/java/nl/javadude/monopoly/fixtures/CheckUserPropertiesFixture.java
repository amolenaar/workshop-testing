package nl.javadude.monopoly.fixtures;

import nl.javadude.monopoly.domain.Player;

public class CheckUserPropertiesFixture extends CommonDoFixtureCode {

	private Player user;

	public void getUser(String name) {
		user = (Player) restore(name + ".data");
	}

	public long balance() {
		return user.getMoney();
	}

	public String position() {
		return user.getCurrentPosition().getName();
	}
	
	public boolean ownsStreet(String name) {
		return user.owns(name);
	}
}
