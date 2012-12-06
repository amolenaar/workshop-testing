package nl.javadude.monopoly.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;

public class GameTest {

    Game theVictim;
	// Test the following requirement:
	//  - The next player can only start playing after the current player finished his turn.
	//    Rolling dice is mandatory when it's a player's turn

    @Before
    public void startUp(){
        theVictim = new Game();
    }

	@Test
	public void playerShouldHaveRolledDiceBeforeNextPlayerCanPlay() {
	}

    @Test
    public void testGetSquares_MultipleSquares(){
        assertTrue(theVictim.getSquares().size() > 1);
    }

    @Test
    public void testAddPlayer(){
        Player myPlayer = new Player(theVictim.getBoard(), "player");
        theVictim.addPlayer(myPlayer);
        assertTrue(theVictim.getPlayers().contains(myPlayer));
    }

    @Test
    public void startPlay(){
        Player myPlayer1 = mock(Player.class);
        Player myPlayer2 = mock(Player.class);
        theVictim.addPlayer(myPlayer1);
        theVictim.addPlayer(myPlayer2);
        theVictim.startPlay();
        verify(myPlayer1).startTurn();
        verifyZeroInteractions(myPlayer2);
    }

    @Test
    public void testGetPlayers(){
        testAddPlayer();
    }

    @Test
    public void getCurrentPlayer(){
        Player myPlayer1 = new Player(theVictim.getBoard(), "player1");
        Player myPlayer2 = new Player(theVictim.getBoard(), "player2");
        theVictim.addPlayer(myPlayer1);
        theVictim.addPlayer(myPlayer2);
        theVictim.startPlay();
        theVictim.rollDice(1,2);
        theVictim.nextPlayer();
        assertEquals(theVictim.getCurrentPlayer(), myPlayer2);
    }

    @Test
    public void testRollDice(){
        //todo
    }

    @Test
    public void testCurrentPlayerBuy(){
        //todo
    }

    @Test
    public void testNextPlayer(){
        //todo
    }
}
