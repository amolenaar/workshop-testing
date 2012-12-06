package nl.javadude.monopoly.domain;

import static org.junit.Assert.*;

import org.apache.commons.lang.NotImplementedException;
import org.junit.*;

public class GameTest {
    private Game theGame;

    // Test the following requirement:
	//  - The next player can only start playing after the current player finished his turn.
	//    Rolling dice is mandatory when it's a player's turn


    @Before
    public void setUp()
    {
        theGame = new Game();
        theGame.addPlayer("Bot");
        theGame.addPlayer("Human");
    }

    @Test
    public void aTest()
    {
        theGame.findPlayer("Bot").setController(new Controller()
        {
            public boolean shouldBuy(ISquare aCurrentPosition)
            {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        theGame.startPlay();
        assertEquals(theGame.getCurrentPlayer(), theGame.findPlayer("Human"));
    }
}
