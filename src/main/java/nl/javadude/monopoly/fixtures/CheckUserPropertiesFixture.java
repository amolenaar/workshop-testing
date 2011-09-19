package nl.javadude.monopoly.fixtures;

import org.apache.commons.lang.NotImplementedException;

import nl.javadude.monopoly.domain.Player;

public class CheckUserPropertiesFixture extends CommonDoFixtureCode {

	private Player user;

	public void getUser(String name) {
		//PlayerProperties p = PlayerPool.PLAYER_POOL.get(name);
		throw new NotImplementedException();
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
