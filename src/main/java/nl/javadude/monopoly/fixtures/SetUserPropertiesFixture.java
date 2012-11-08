package nl.javadude.monopoly.fixtures;


public class SetUserPropertiesFixture {

	private PlayerProperties user;

	public void createUser(String name) {
		user = new PlayerProperties(name);
		PlayerPool.PLAYER_POOL.put(name, user);
		//save(user, constructFileName(name));
	}

	public void setBalance(long amount) {
		if (user != null) {
			user.setMoney(amount);
		}
	}

	public void setPosition(String position) {
		if (user != null) {
			user.setCurrentPosition(position);
		}
	}

}
