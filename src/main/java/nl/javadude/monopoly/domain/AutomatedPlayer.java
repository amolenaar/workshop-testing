package nl.javadude.monopoly.domain;

public class AutomatedPlayer extends Player
{

    public AutomatedPlayer(Board board, String name)
    {
        super(board, name);
    }

    public int rollDice()
    {
        return -1;
    }
}
