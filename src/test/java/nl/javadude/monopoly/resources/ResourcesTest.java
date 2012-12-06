package nl.javadude.monopoly.resources;

import static org.mockito.Mockito.*;

import javax.servlet.http.*;

import org.junit.*;

public class ResourcesTest
{

    public static final String FIRST_PLAYER = "FIRST_PLAYER";
    private static final String SECOND_PLAYER ="SECOND_PLAYER" ;

    @Test
    public void addTwoPlayers_rollDicesOnce_FirstPlayerMove()
    {
        Resources myResources = new Resources();
        HttpServletRequest myRequest= mock(HttpServletRequest.class, RETURNS_MOCKS);
        myResources.request = myRequest;
        when(myRequest.getSession()).thenReturn(new DummyHttpSession());
        myResources._newGame();
        myResources.newPlayer(FIRST_PLAYER);
        myResources.newPlayer(SECOND_PLAYER);
        myResources.startGame();
        String myString = myResources.rollDice(1, 1);
        System.out.println(myString);
        myResources.nextPlayer();
        myString = myResources.rollDice(4, 5);
        System.out.println(myString);
    }
}
