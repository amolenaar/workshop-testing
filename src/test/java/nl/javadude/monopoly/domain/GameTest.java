package nl.javadude.monopoly.domain;

import static junit.framework.Assert.*;

import org.junit.*;

public class GameTest {

    private final Game theGame = new Game();
    private final Player theFirstPlayer = theGame.addPlayer("myPlayer");
    private final Player theSecondPlayer = theGame.addPlayer("myOtherPlayer");

    @Before
    public void setup()
    {
        theGame.startPlay();
    }

    @Test
    public void currentPlayerIsNotChangedWhenPlayerDidNotMove()
    {
        theGame.nextPlayer();
        verifyCurrentPlayer(theFirstPlayer);
    }

    @Test
    public void currentPlayerIsChangedWhenPlayerHasMoved()
    {
        theFirstPlayer.move();
        theGame.nextPlayer();
        verifyCurrentPlayer(theSecondPlayer);
    }

    @Test
    public void nextPlayerMustEndTurnOfPreviousPlayer()
    {
        theFirstPlayer.move();
        theGame.nextPlayer();
        assertTrue(theFirstPlayer.isFinishedTurn());
    }

    @Test
    public void nextPlayerMustStartTurnOfNewPlayer()
    {
        theFirstPlayer.move();
        theGame.nextPlayer();
        assertTrue(theSecondPlayer.isStartedTurn());
    }

    private void verifyCurrentPlayer(Player aPlayer)
    {
        assertEquals(aPlayer, theGame.getCurrentPlayer());
    }
}
