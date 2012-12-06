package nl.javadude.monopoly.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class GameTest {
    private Game game;

    @Before
    public void setupGame() {
        game = new Game();
        game.addPlayer("First");
        game.addPlayer("Second");
        game.startPlay();
    }

	@Test
	public void shouldHaveCorrectCurrentPlayer() {
        assertThat(game.getCurrentPlayer(), is(notNullValue()));
        assertThat(game.getCurrentPlayer().getName(), is("First"));
	}

    // Test the following requirement:
	//  - The next player can only start playing after the current player finished his turn.
	//    Rolling dice is mandatory when it's a player's turn

    @Test
	public void shouldNotAllowNextPlayerIfPlayerHasntMovedYet() {
        game.nextPlayer();
        assertThat(game.getCurrentPlayer().getName(), is(not("Second")));
	}

    @Test
	public void shouldAllowNextPlayerIfPlayerHasMoved() {
        game.move();
        game.nextPlayer();
        assertThat(game.getCurrentPlayer().getName(), is("Second"));
	}

    @Test
    public void shouldActivateCurrentPlayer() {
        assertThat(game.getCurrentPlayer().isActive(), is(true));
        game.move();
        game.nextPlayer();
        assertThat(game.getCurrentPlayer().isActive(), is(true));
    }


}
