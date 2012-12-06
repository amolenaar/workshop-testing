package nl.javadude.monopoly.domain;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

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
	public void shouldHaveCorrectCurrentPlayer() {
        assertThat(theGame.getCurrentPlayer(), is(notNullValue()));
        assertThat(theGame.getCurrentPlayer().getName(), is("myPlayer"));
	}

    // Test the following requirement:
	//  - The next player can only start playing after the current player finished his turn.
	//    Rolling dice is mandatory when it's a player's turn

    @Test
	public void shouldNotAllowNextPlayerIfPlayerHasntMovedYet() {
        theGame.nextPlayer();
        assertThat(theGame.getCurrentPlayer().getName(), is(not("myOtherPlayer")));
	}

    @Test
	public void shouldAllowNextPlayerIfPlayerHasMoved() {
        theGame.move();
        theGame.nextPlayer();
        assertThat(theGame.getCurrentPlayer().getName(), is("myOtherPlayer"));
	}

    @Test
    public void shouldActivateCurrentPlayer() {
        assertThat(theGame.getCurrentPlayer().isActive(), is(true));
        theGame.move();
        theGame.nextPlayer();
        assertThat(theGame.getCurrentPlayer().isActive(), is(true));
    }


    @Test
    public void previousPlayerShouldHaveEndedTurnWhenNextPlayerComesOn() {
        final Player previousPlayer = theGame.getCurrentPlayer();
        theGame.move();
        theGame.nextPlayer();
        assertThat(previousPlayer.isActive(), is(false));
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
        theGame.move();
        theGame.nextPlayer();
        verifyCurrentPlayer(theSecondPlayer);
    }

    @Test
    public void nextPlayerMustEndTurnOfPreviousPlayer()
    {
        theGame.move();
        theGame.nextPlayer();
        assertFalse(theFirstPlayer.isActive());
    }

    @Test
    public void nextPlayerMustStartTurnOfNewPlayer()
    {
        theGame.move();
        theGame.nextPlayer();
        assertTrue(theSecondPlayer.isActive());
    }

    private void verifyCurrentPlayer(Player aPlayer)
    {
        assertEquals(aPlayer, theGame.getCurrentPlayer());
    }

}
