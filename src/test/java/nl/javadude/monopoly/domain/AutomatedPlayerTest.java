package nl.javadude.monopoly.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.*;

public class AutomatedPlayerTest
{

    public static final String BOT = "BOT";
    private AutomatedPlayer theAutomatedPlayer;

    @Before
    public void setUp()
    {
        Board theBoard = Mockito.mock(Board.class);
        theAutomatedPlayer = new AutomatedPlayer(theBoard, BOT);
    }

//    @Test
//    public void rollDice_SmallerThan12()
//    {
//        int myResult = theAutomatedPlayer.rollDice();
//        assertTrue(myResult <= 12);
//    }
//
    @Test
    public void rollDice_BiggerThan2()
    {
        int myResult = theAutomatedPlayer.rollDice();
        assertThat(myResult, greaterThanOrEqualTo(2));
    }
}
