package nl.javadude.monopoly.resources;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.json.*;

import nl.javadude.monopoly.domain.*;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/")
public class Resources {

	public static final String JSON = "application/json";


	@Context
	HttpServletRequest request;

    @GET
    public InputStream getIndexHtml() {
    	return getClass().getClassLoader().getResourceAsStream("index.html");
    }

    @GET
    @Path("/jquery.js")
    @Produces("text/javascript")
    public InputStream getJQueryJs() {
    	return getClass().getClassLoader().getResourceAsStream("jquery-1.6.2.min.js");
    }


    @GET
    @Path("/monopoly.js")
    @Produces("text/javascript")
    public InputStream getMonopolyJs() {
    	return getClass().getClassLoader().getResourceAsStream("monopoly.js");
    }

    @GET
    @Path("/monopoly.css")
    @Produces("text/css")
    public InputStream getMonopolyCss() {
    	return getClass().getClassLoader().getResourceAsStream("monopoly.css");
    }



    @GET
    @Path("/newgame")
    public InputStream _newGame() {
    	newGame();
    	return getIndexHtml();
    }

    //@Produces("text/plain")
    private Game newGame() {
    	Game game = new Game();
    	request.getSession().setAttribute("game", game);
    	return game;
    }

    private IGame game() {
    	IGame game = (IGame) request.getSession().getAttribute("game");
    	if (game == null) {
    		return newGame();
    	}
    	return game;
    }

    @POST
    @Path("/startgame")
    @Produces("text/plain")
    public String startGame() {
    	game().startPlay();
    	return "";
    }

    @GET
    @Path("/board")
    @Produces(JSON)
    public String board() {
    	return toJson(game().getSquares());
    }

    @POST
    @Path("/player")
    public void newPlayer(@FormParam("name") final String name) {
    	game().addPlayer(new Player(game().getBoard(), name));
    }

    @GET
    @Path("/player")
    public String player() {
    	return toJson(game().getCurrentPlayer());
    }

    @GET
    @Path("/player/next")
    public String nextPlayer() {
    	game().nextPlayer();
    	return toJson(game().getCurrentPlayer());
    }


    @GET
    @Path("/players")
    public String playerList() {
    	return toJson(game().getPlayers());
    }

    @POST
    @Path("/rolldice/{d1}/{d2}")
    public String rollDice(@PathParam("d1") final int d1, @PathParam("d2") final int d2) {
        return toJson(game().rollDice(d1, d2));
    }

    @POST
    @Path("/player/buy")
    public String buy() {
    	return toJson(game().currentPlayerBuy());
    }

    @SuppressWarnings("rawtypes")
	private static String toJson(List o) {
		JSONArray a = new JSONArray();
		for (Object e: o) {
			a.put(new JSONObject(e, true));
		}
		return a.toString();
    }

    private static String toJson(Object o) {
    	return new JSONObject(o, true).toString();
    }

    /**
     * Test fixture.
     *
     * @param request
     */
    void setRequest(HttpServletRequest request) {
    		this.request = request;
    }
}