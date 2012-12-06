package nl.javadude.monopoly.domain;

public interface MoneyExchanger {
	
	void receiveMoney(long amount);
	
	boolean pay(long amount, MoneyExchanger receiver);

}
