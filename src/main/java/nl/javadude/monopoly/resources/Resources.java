package nl.javadude.monopoly.resources;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import nl.javadude.monopoly.domain.Board;
import nl.javadude.monopoly.domain.Game;
import nl.javadude.monopoly.domain.Player;

import com.google.gson.Gson;

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
    @Produces("text/plain")
    public String newGame() {
    	request.getSession().setAttribute("game", new Game());
    	return "";
    }
    
    @GET
    @Path("/startgame")
    @Produces("text/plain")
    public String startGame() {
    	game().startPlay();
    	return "";
    }
    
    private Game game() {
    	return (Game) request.getSession().getAttribute("game");
    }
    
    @GET
    @Path("/board")
    @Produces(JSON)
    public String board() {
    	return new Gson().toJson(Board.BOARD.getSquares());
    }
    
    @POST
    @Path("/player/{name}")
    public void newPlayer(@FormParam("name") final String name) {
    	game().add(new Player(name));
    }
    
    @GET
    @Path("/player")
    public String player() {
    	return new Gson().toJson(game().getCurrentPlayer());
    }

    @GET
    @Path("/player/list")
    public String playerList() {
    	return new Gson().toJson(game().getPlayers());
    }

    @POST
    @Path("/rolldice/{d1}{d2}")
    public String rollDice(@FormParam("d1") final String dice1, final String dice2) {
    	return "";
    }
}