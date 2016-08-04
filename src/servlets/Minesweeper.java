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
import minesweeper.Clue;
import minesweeper.Mine;
import minesweeper.Tile;
import minesweeper.Tile.State;
import minesweeper.Field;
import minesweeper.GameState;

@WebServlet("/Minesweeper")
public class Minesweeper extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		HttpSession session = request.getSession();
		Field field = (Field) session.getAttribute("field");
		if (field == null) {
			field = new Field(10, 10, 5);
			session.setAttribute("field", field);
		}

		try {
			String newGame = request.getParameter("newGame");
			if (newGame != null) {
				field = new Field(10, 10, 5);
			}
			session.setAttribute("field", field);
			int row = Integer.parseInt(request.getParameter("row"));
			int column = Integer.parseInt(request.getParameter("column"));
			int fcn = Integer.parseInt(request.getParameter("function"));
			if (fcn == 1) {
				field.markTile(row, column);
			} else {
				field.openTile(row, column);
			}
		} catch (Exception e) {
		}

		String closed = "<td onClick='some(%d, %d, %d)' oncontextmenu='some2(%d, %d, %d)'><img src='imagesMinesweeper/closed.jpg'></img>";
		String marked = "<td oncontextmenu='some2(%d, %d, %d)'><img src='imagesMinesweeper/mark.jpg'></img>";
		String state = "";
		if (field.getState().equals(GameState.SOLVED)) {
			state = "Vyhral si!";
			closed = "<td><img src='imagesMinesweeper/closed.jpg'></img>";
			marked = "<td><img src='imagesMinesweeper/mark.jpg'></img>";
			if (!request.getParameter("nickName").toString().equals("")){
				new ScoreServiceJPA().addScore(new ScoreJpa(field.getPlayingSeconds(), new PlayerServiceJPA().checkPlayer(request.getParameter("nickName")), new GameServiceJPA().checkGame(request.getParameter("game"))));
			}
		} else if (field.getState().equals(GameState.FAILED)) {
			state = "Prehral si!";
			closed = "<td><img src='imagesMinesweeper/closed.jpg'></img>";
			marked = "<td><img src='imagesMinesweeper/mark.jpg'></img>";
		}

		out.println("<h3 class=\"minesweeper\">...MINESWEEPER...</h3>");
		out.println("<div class=\"minesweeper\">");
		out.println("<table oncontextmenu=\"return false;\"cellspacing=\"0\" cellpadding=\"0\">"); 
		for (int riadok = 0; riadok < field.getColumnCount(); riadok++) {
			out.println("<tr>");
			for (int stlpec = 0; stlpec < field.getRowCount(); stlpec++) {
				if (field.getTile(riadok, stlpec).getState() == State.CLOSED) {
					out.printf(
							closed, riadok, stlpec, 0, 
							riadok, stlpec, 1);
				} else if (field.getTile(riadok, stlpec).getState() == State.MARKED) {
					out.printf(marked, riadok, stlpec, 1);
				} else if (field.getTile(riadok, stlpec).getState() == State.OPEN) {
					if (field.getTile(riadok, stlpec) instanceof Clue) {
						Tile tempTile = field.getTile(riadok, stlpec);
						out.printf("<td><img src='imagesMinesweeper/%s.jpg'></img>", ((Clue) tempTile).getValue());
					} else if (field.getTile(riadok, stlpec) instanceof Mine) {
						out.printf("<td><img src='imagesMinesweeper/mine.jpg'></img>");
					}
				}
			}
		}
		out.println("</table>");
		out.println("</div>");
		out.printf("<h3 class=\"minesweeper\">Poèet zostávajúcich mín je %d.</h3>", field.getRemainingMineCount());
		
		String player = request.getParameter("nickName");
		String actGame = request.getParameter("game");
		String action = request.getParameter("action");
		
		out.println("<form method='get'>");
		out.println("<div class=\"minesweeper\">");
		out.printf("<a href=\"?action=%s&game=%s&nickName=%s&newGame=%s\" class=\"btn\">NEW GAME</a>",action, actGame , player, "newgame");
		out.println("</div>");
		out.println("</form>");
		out.printf("<h3 class=\"minesweeper\">%s</h3>", state);
		
		out.println("<script>");
		out.printf("function some(r, c, f) {window.location.href = ('?action=%s&game=%s&nickName=%s&row='+r+'&column='+c+'&function='+f)}", action, actGame , player);
		out.printf("function some2(r, c, f) {window.location.href = ('?action=%s&game=%s&nickName=%s&row='+r+'&column='+c+'&function='+f)}",action, actGame , player);
		out.println("</script>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
