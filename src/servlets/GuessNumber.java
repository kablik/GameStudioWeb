package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gamestudio.entityJPA.ScoreJpa;
import gamestudio.jpaServices.GameServiceJPA;
import gamestudio.jpaServices.PlayerServiceJPA;
import gamestudio.jpaServices.ScoreServiceJPA;
import guessthenumber.GuessTheNumber;

@WebServlet("/GuessTheNumber")
public class GuessNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int gtnScore;
	private String readonly;
	private String inputValue;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession();

		int inputNumber = -1;
		try {
			inputNumber = Integer.parseInt(request.getParameter("cislo"));
		} catch (Exception e) {
		}

		GuessTheNumber hra = (GuessTheNumber) session.getAttribute("hra");
		String newGame = request.getParameter("newGame");
		
		if (hra == null || newGame != null) {
			gtnScore = 1;
			hra = new GuessTheNumber();
			session.setAttribute("hra", hra);
			readonly = "";
			inputValue = "";
		}

		String nickName = request.getParameter("nickName");
		String gameSelected = request.getParameter("game");
		String action = request.getParameter("action");
		
		out.println("<div id=\"guessTheNumber\">");
		out.println("<h3 class=\"gtnH\">...UHÁDNI ÈÍSLO...</h3>");
		out.println("<form method='get'>");
		if (request.getParameter("cislo") != null && inputNumber != -1) {
			switch (hra.compareNumbers(inputNumber).toString()) {
			case "bigger":
				out.printf("<h3 class=\"gtnH\">Hádané èíslo je väèšie.</h3>");
				gtnScore++;
				break;
			case "smaller":
				out.printf("<h3 class=\"gtnH\">Hádané èíslo je menšie.</h3>");
				gtnScore++;
				break;
			case "bingo":
				readonly = "readonly";
				inputValue = "value='"+inputNumber+"'";
				if (!request.getParameter("nickName").toString().equals("")){
					new ScoreServiceJPA().addScore(new ScoreJpa(gtnScore, new PlayerServiceJPA().checkPlayer(request.getParameter("nickName")), new GameServiceJPA().checkGame(request.getParameter("game"))));
				}
				out.printf("<h3 class=\"gtnH\">Uhádol si èíslo.</h3>");
				break;
			} 
		} else {
			out.printf("<h3 class=\"gtnH\">Hádaj.</h3>");
		}
		out.println("<div  class=\"gtnH\">");
		out.printf("<input type='hidden' name='game' value='%s'><br>", gameSelected);
		out.printf("<input type='hidden' name='nickName' value='%s'><br>", nickName);
		out.printf("<input type='hidden' name='action' value='%s'><br>", action);
		out.printf("<input type='text' name='cislo' %s %s autofocus><br>", inputValue, readonly);
		out.printf("<h3 class=\"gtnH\">...Stlaè ENTER...</h3>");
		out.println("<input type='submit' style='display: none'><br>");
		out.printf("<a href=\"?action=%s&game=%s&nickName=%s&newGame=%s\" class=\"btn\">NEW GAME</a>",action, gameSelected , nickName, "newgame");
		out.println("</div>");
		out.println("</form>");
		out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
