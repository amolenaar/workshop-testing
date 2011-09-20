package nl.javadude.monopoly.resources;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import nl.javadude.monopoly.domain.Board;
import nl.javadude.monopoly.domain.Dice;
import nl.javadude.monopoly.domain.Game;
import nl.javadude.monopoly.domain.Player;

import org.json.JSONArray;
import org.json.JSONObject;

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

    private Game game() {
    	Game game = (Game) request.getSession().getAttribute("game");
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
    	return toJson(game().getBoard().getSquares());
    }
    
    @POST
    @Path("/player")
    public void newPlayer(@FormParam("name") final String name) {
    	game().addPlayer(name);
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
    	Dice dice = Dice.INSTANCE;
    	dice.setDiceValues(d1, d2);
    	String player = player();
    	game().getCurrentPlayer().move(dice);
    	return player();
    }
    
    @POST
    @Path("/player/buy")
    public String buy() {
    	return toJson(game().getCurrentPlayer().buy());
    }
    
    @SuppressWarnings("rawtypes")
	private static String toJson(List o) {
		JSONArray a = new JSONArray();
		for (Object e: (List) o) {
			a.put(new JSONObject(e, true));
		}
		return a.toString();
    }
    
    private static String toJson(Object o) {
    	return new JSONObject(o, true).toString();
    }

}