package nl.javadude.monopoly.resources;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nl.javadude.monopoly.domain.Game;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class SmokeTest {

	private Resources resources;

	HttpServletRequest request;
	HttpSession session;
	Game game;


	@Before
	public void before() {
		resources = new Resources();

		request = mock(HttpServletRequest.class);
		session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);

		doAnswer(extractGame()).when(session).setAttribute(eq("game"), isA(Game.class));

		doAnswer(provideGame()).when(session).getAttribute(eq("game"));

		resources.setRequest(request);
	}

	@Test
	public void shouldGoThroughBasicFlow() {

		resources.newPlayer("Arjan");
		resources.newPlayer("Iwein");
		resources.startGame();
		resources.rollDice(2, 4);
		resources.buy();
		resources.nextPlayer();
		resources.rollDice(2, 4);

		assertThat(resources.nextPlayer(), hasName("Arjan"));
		assertThat(game.findPlayer("Arjan").getMoney(), lessThan(1500L));
		assertThat(game.findPlayer("Iwein").getMoney(), lessThan(1500L));
	}

	private Answer<Object> provideGame() {
		return new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return game;
			}};
	}

	private Answer<Object> extractGame() {
		return new Answer<Object>() {
	        public Object answer(InvocationOnMock invocation) {
	            game = (Game) invocation.getArguments()[1];
	            return null;
	        }
	    };
	}

	private Matcher<String> hasName(final String name) {
		return new  TypeSafeMatcher<String>() {

			public void describeTo(Description description) {
				description.appendText("Not the right player: " + snippet() + " not found");
			}

			@Override
			protected boolean matchesSafely(String json) {
				return json.contains(snippet());
			}

			private String snippet() {
				return "\"name\":\"" + name + "\"";
			}
		};
	}

}
