package nl.javadude.monopoly.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.javadude.monopoly.domain.squares.Realty;

import org.apache.commons.lang.NotImplementedException;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	private Player player;

	@Before
	public void before() {
		player = new Player(new Board(), "John");
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
}
