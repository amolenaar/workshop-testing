package nl.javadude.monopoly.fixtures;


public class SetUpUserFixture {

	private PlayerProperties user;

	public void createUser(String name) {
		user = new PlayerProperties(name);
		PlayerPool.PLAYER_POOL.put(name, user);
		System.out.println("Created new user " + name);
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
