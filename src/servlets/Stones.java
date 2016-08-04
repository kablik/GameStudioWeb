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
import stones.Field;

@WebServlet("/Stones")
public class Stones extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		Field pole = (Field) session.getAttribute("pole");
		if (pole == null) {
			pole = new Field(4, 4);
			session.setAttribute("pole", pole);
		}
		try {
			String newGame = request.getParameter("newGame");
			if (newGame != null){
				pole = new Field(4, 4);
				session.setAttribute("pole", pole);
			}
			int value = Integer.parseInt(request.getParameter("value"));
			pole.move(value);
		} catch (Exception e) {
		}

		String nickName = request.getParameter("nickName");
		String gameSelected = request.getParameter("game");
		String action = request.getParameter("action");

		out.println("<h3 class=\"stones\">...KAMENE...</h3>");
		out.println("<div class=\"stones\">");
		out.println("<table border='1'>");
		for (int row = 0; row < pole.getRowCount(); row++) {
			out.println("<tr>");
			for (int column = 0; column < pole.getColumnCount(); column++) {
				int value = pole.getValueAt(row, column);
				if (value == Field.EMPTY_CELL) {
					out.printf("<td><img src='imagesStones/00.jpg'>");
				} else {
					if (!pole.isSolved()){
						out.printf("<td><a href='?action=%s&game=%s&nickName=%s&value=%d'><img src='imagesStones/%s.jpg'></img></a>",action,  gameSelected ,nickName, value, value);
					} else {
						out.printf("<td><img src='imagesStones/%s.jpg'></img></a>", value);
					}
				}
			}
		}
		out.println("</table>");
		out.println("</div>");
		out.println("<div class=\"stones\"><br>");
		out.println("<form method='get'>");
		out.printf("<a href=\"?action=%s&game=%s&nickName=%s&newGame=%s\" class=\"btn\">NEW GAME</a>",action, gameSelected , nickName, "newgame");
		out.println("</form>");
		out.println("</div>");
		
		if (pole.isSolved()) {
			if (!request.getParameter("nickName").toString().equals("")){
				new ScoreServiceJPA().addScore(new ScoreJpa(pole.getPlayingSeconds(), new PlayerServiceJPA().checkPlayer(request.getParameter("nickName")), new GameServiceJPA().checkGame(request.getParameter("game"))));
			}
			out.println("<h3 class=\"stones\">Vyhral si!</h3>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
