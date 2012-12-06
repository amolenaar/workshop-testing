package nl.javadude.monopoly.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AutomatedPlayer
{
	Logger theLogger = LoggerFactory.getLogger(getClass());

    private String theName;

    private long theBalance;

    public AutomatedPlayer(String name)
    {
    	theName = name;
        theBalance = 15000;
    }

    public int rollDice()
    {
    	return (int) (Math.random() * 12);
    }

    public String getName()
    {
        return theName;
    }

    public void setBalance(long aBalance)
    {
        theBalance = aBalance;
    }

    public long getBalance()
    {
        return theBalance;
    }

    public boolean buy(IOwnable aOwnable)
    {
        return theBalance > (0.7 * aOwnable.getCost()) ;
    }
}
