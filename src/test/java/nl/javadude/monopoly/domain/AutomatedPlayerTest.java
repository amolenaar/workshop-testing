package nl.javadude.monopoly.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.hamcrest.*;
import org.junit.*;

import com.google.common.collect.*;

public class AutomatedPlayerTest
{

    public static final String NAME = "NAME";
    private AutomatedPlayer theAutomatedPlayer;
    private Board theBoard;
    private Game theGame;

    @Before
    public void setUp()
    {
        theGame = new Game();
        theBoard = new Board();
        theAutomatedPlayer = new AutomatedPlayer(NAME);
    }

    @Test
    public void rollDice_SmallerThan12()
    {
        int myResult = theAutomatedPlayer.rollDice();
        assertThat(myResult, lessThanOrEqualTo(12));
    }

    @Test
    public void rollDice_BiggerThan2()
    {
        int myResult = theAutomatedPlayer.rollDice();
        assertThat(myResult, greaterThanOrEqualTo(2));
    }

    @Ignore("not finsihed yet")
    @Test
    public void rollDice100Times_notAlwaysTheSame()
    {
        Collection<Integer> myList = Lists.newArrayList();
        boolean isSame = true;
        for (int myIndex = 0; myIndex < 100; myIndex++)
        {
            int myResult = theAutomatedPlayer.rollDice();
            if (!myList.isEmpty())
            {
                int myTmp = myList.iterator().next();
                if (! (myTmp == myResult))
                {
                    isSame = false;
                    break;
                }
            }
            myList.add(myIndex);
        }
        assertThat(isSame, is(false));
    }


    @Test
    public void newPlayer_hasName()
    {
        assertTrue(theAutomatedPlayer.getName().equals(NAME));
    }
//
// @Test
//    public void onStartPosition_notARealty_cannotBuy()
//    {
//        ISquare myGO = theBoard.findLocation("GO");
//        theAutomatedPlayer.setCurrentPosition(myGO);
//        theAutomatedPlayer.buy();
//        assertThat(theAutomatedPlayer, hasNoPosession());
//    }

//    @Test
//    public void noMoney_cannotBuy()
//    {
//        theGame.startPlay();
//        theGame.setCurrentPlayer(theAutomatedPlayer);
//        ISquare myPlace = theBoard.findLocation("Baltic Avenue");
//        theAutomatedPlayer.setMoney(0);
//        theAutomatedPlayer.setCurrentPosition(myPlace);
//        theAutomatedPlayer.buy();
//        assertThat(theAutomatedPlayer, hasNoPosession());
//    }

//    private Matcher<AutomatedPlayer> hasNoPosession()
//    {
//        return new TypeSafeMatcher<AutomatedPlayer>()
//        {
//            @Override
//            protected boolean matchesSafely(AutomatedPlayer aAutomatedPlayer)
//            {
//                return theAutomatedPlayer.getPossessions().isEmpty();
//            }
//
//            public void describeTo(Description aDescription)
//            {
//                aDescription.appendText("supposed to be empty");
//            }
//        };
//    }
}
