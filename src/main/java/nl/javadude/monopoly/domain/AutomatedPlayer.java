package nl.javadude.monopoly.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AutomatedPlayer extends Player
{
	Logger theLogger = LoggerFactory.getLogger(getClass());
    public AutomatedPlayer(Board board, String name)
    {
        super(board, name);
    }

    public int rollDice()
    {
    	return (int) (Math.random() * 12);
    }
}
