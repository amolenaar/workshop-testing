package nl.javadude.monopoly.fixtures;

class PlayerProperties {

	private String name;
	private long money;
	private String currentPosition;
	
	public PlayerProperties(String name) {
		this.name = name;
	}

	public void setMoney(long amount) {
		this.money = amount;
	}
	
	public long getMoney() {
		return money;
	}
	
	public String getName() {
		return name;
	}

	public void setCurrentPosition(String position) {
		this.currentPosition = position;
	}
	
	public String getCurrentPosition() {
		return currentPosition;
	}
}
