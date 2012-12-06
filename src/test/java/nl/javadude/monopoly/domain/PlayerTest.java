package nl.javadude.monopoly.domain;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.javadude.monopoly.domain.squares.Realty;

import org.apache.commons.lang.NotImplementedException;
import org.junit.*;

public class PlayerTest {

	private Player player;
    private Board theBoard;

    @Before
	public void before() {
        theBoard = new Board();
        player = new Player(theBoard, "John");
	}

	@Test
	public void shouldNotBeInDebt() {
		throw new NotImplementedException();
	}

	@Test
	public void shouldPayRent() {
		Player ownerPlayer = mock(Player.class);
		Realty realty = mock(Realty.class);
		when(realty.isUnowned()).thenReturn(false);
		when(realty.owner()).thenReturn(ownerPlayer);
		when(realty.getRent()).thenReturn(1000L);

		player.setCurrentPosition(realty);
		player.payRent();

		verify(ownerPlayer).receiveMoney(1000L);
		assertThat(player.getMoney(), is(500L));
	}

    @Test
    public void dummyAutomatedPlayer_tryToBuyEverything()
    {
        Controller myDummyController = new Controller()
        {
            public boolean shouldBuy(ISquare aCurrentPosition)
            {
                return true;
            }
        };
        setUpAndStartBot(myDummyController);

        assertFalse(player.getPossessions().isEmpty());
    }

    private void setUpAndStartBot(Controller aController)
    {
        player.setController(aController);
        player.setCurrentPosition(theBoard.findLocation("Baltic Avenue"));
        player.startTurn();
        player.move(Dice.INSTANCE);
    }

    @Test
    public void greedyAutomatedPlayer_neverBuy()
    {
        Controller myGreedyController = new Controller()
        {
            public boolean shouldBuy(ISquare aCurrentPosition)
            {
                return false;
            }
        };
        setUpAndStartBot(myGreedyController);

        assertTrue(player.getPossessions().isEmpty());
    }
}
