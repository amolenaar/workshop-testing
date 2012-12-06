package nl.javadude.monopoly.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AutomatedPlayer
{
	Logger theLogger = LoggerFactory.getLogger(getClass());
    public AutomatedPlayer(String name)
    {

    }

    public int rollDice()
    {
    	return (int) (Math.random() * 12);
    }

    public String getName()
    {
        return null;
    }
}
