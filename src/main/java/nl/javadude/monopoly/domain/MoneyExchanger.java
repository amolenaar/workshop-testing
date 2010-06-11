package nl.javadude.monopoly.domain;

public interface MoneyExchanger {
	
	void receiveMoney(long amount);
	
	void pay(long amount, MoneyExchanger receiver);

}
