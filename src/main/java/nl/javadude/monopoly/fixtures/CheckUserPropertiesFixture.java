package nl.javadude.monopoly.fixtures;

import org.apache.commons.lang.NotImplementedException;

public class CheckUserPropertiesFixture {

	private static PlayerProperties user;

	public synchronized void getUser(String name) {
	    user = PlayerPool.PLAYER_POOL.get(name);
	}

	public long balance() {
		return user.getMoney();
	}

	public String position() {
		return user.getCurrentPosition();
	}

	public boolean ownsStreet(String name) {
	    throw new NotImplementedException();
	}
}
