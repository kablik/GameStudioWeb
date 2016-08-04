package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gamestudio.entityJPA.GameJpa;
import gamestudio.entityJPA.KomentJpa;
import gamestudio.entityJPA.PlayerJpa;
import gamestudio.entityJPA.RaitingJpa;
import gamestudio.jpaServices.GameServiceJPA;
import gamestudio.jpaServices.KomentServiceJPA;
import gamestudio.jpaServices.PlayerServiceJPA;
import gamestudio.jpaServices.RaitingServiceJPA;
import gamestudio.jpaServices.ScoreServiceJPA;
import gamestudio.jpaServices.StatisticsService;

@WebServlet("/GameStudio")
public class GameStudio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlayerJpa actualPlayer;
	private GameJpa actualGame;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String actionForm = request.getParameter("actionForm");

		
		if ("registration".equals(action)){
			if (!request.getParameter("nickNameReg").trim().equals("") || !request.getParameter("passwordReg").trim().equals("") || !request.getParameter("passwordCheck").trim().equals("")){
				if (new PlayerServiceJPA().playerCheckReg(request.getParameter("nickNameReg")) != 0){
					System.out.println(new PlayerServiceJPA().playerCheckReg(request.getParameter("nickNameReg")));
//					request.setAttribute("usedNickName", "visible");
					request.setAttribute("registerWrong", "registerWrong();");
					request.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(request, response);
				} else {
					actualPlayer = new PlayerServiceJPA().playerReg(request.getParameter("nickNameReg"), request.getParameter("passwordReg"));
					request.setAttribute("player", actualPlayer);
					request.setAttribute("back", "hidden");
					request.setAttribute("games", new GameServiceJPA().getGames());
					statisticsData(request);
					request.getRequestDispatcher("/WEB-INF/jsp/GamesChoice.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("registerWrong", "registerWrong();");
				request.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(request, response);
			}
			
		} else if ("setNickName".equals(action)){
			if (!request.getParameter("nickName").trim().equals("") || !request.getParameter("password").trim().equals("")){
				if (new PlayerServiceJPA().checkPlayerLogin(request.getParameter("nickName"), request.getParameter("password")) == null){
//					request.setAttribute("wrongNamePass", "visible");
					request.setAttribute("loginWrong", "loginWrong();");
					request.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(request, response);
				} else {
					actualPlayer = new PlayerServiceJPA().checkPlayerLogin(request.getParameter("nickName"), request.getParameter("password"));
					request.setAttribute("player", actualPlayer);
					request.setAttribute("back", "hidden");
					request.setAttribute("games", new GameServiceJPA().getGames());
					statisticsData(request);
					request.getRequestDispatcher("/WEB-INF/jsp/GamesChoice.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("loginWrong", "loginWrong();");
				request.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(request, response);
			}
			
			
		} else if ("hratBezPrihlasenia".equals(action)){
			request.setAttribute("formular", "hidden");
			request.setAttribute("games", new GameServiceJPA().getGames());

			statisticsData(request);
			request.getRequestDispatcher("/WEB-INF/jsp/GamesChoice.jsp").forward(request, response);
		} else if ("play".equals(action) && request.getParameter("game") != null) {
//			statisticsData(request);
			request.setAttribute("games", new GameServiceJPA().getGames());
//			request.setAttribute("player", actualPlayer);

			if (!request.getParameter("nickName").equals("")){
				request.setAttribute("player", actualPlayer);
				request.setAttribute("back", "hidden");
				request.setAttribute("statistics", "hidden");
				// show scores and koments
				String gamePlayed = request.getParameter("game");
				request.setAttribute("gamePlayed", gamePlayed);
				actualGame = new GameServiceJPA().checkGame(gamePlayed);

					if ("addKoment".equals(actionForm)) {
						String koment = request.getParameter("koment");
						if (!koment.trim().isEmpty()) {
							new KomentServiceJPA().addKoment(new KomentJpa(koment, actualPlayer, actualGame));
						}
					}
					if ("addRating".equals(actionForm)) {
						int rating = Integer.parseInt(request.getParameter("rating"));
						if (Integer.parseInt(request.getParameter("rating")) != 0) {
							new RaitingServiceJPA().addRaiting(new RaitingJpa(rating, actualPlayer, actualGame));
						}
					}
				
				//stuffAboutGames
				statisticsAboutGames(request, gamePlayed);
				request.getRequestDispatcher("/WEB-INF/jsp/GameStudio.jsp").forward(request, response);
				
			} else {
//				request.setAttribute("player", actualPlayer);

				request.setAttribute("formular", "hidden");
				request.setAttribute("statistics", "hidden");
				// show scores and koments
				String gamePlayed = request.getParameter("game");
				request.setAttribute("gamePlayed", gamePlayed);
				actualGame = new GameServiceJPA().checkGame(gamePlayed);

				statisticsAboutGames(request, gamePlayed);
				request.getRequestDispatcher("/WEB-INF/jsp/GameStudio.jsp").forward(request, response);
			}
		} else if ("mainMenu".equals(action)){
			if (!request.getParameter("nickName").equals("")) {
				request.setAttribute("player", actualPlayer);
				request.setAttribute("back", "hidden");
				request.setAttribute("games", new GameServiceJPA().getGames());
				statisticsData(request);
				request.getRequestDispatcher("/WEB-INF/jsp/GamesChoice.jsp").forward(request, response);
			} else {
				request.setAttribute("games", new GameServiceJPA().getGames());
				request.setAttribute("formular", "hidden");
				statisticsData(request);
				request.getRequestDispatcher("/WEB-INF/jsp/GamesChoice.jsp").forward(request, response);
			}
		} else if ("loginMenu".equals(action)){
			request.setAttribute("usedNickName", "hidden");
			request.setAttribute("wrongNamePass", "hidden");
			request.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(request, response);
		} else if ("logOut".equals(action)){
			request.setAttribute("usedNickName", "hidden");
			request.setAttribute("wrongNamePass", "hidden");
			request.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
		/**
		if (request.getParameter("nickName") != null && request.getParameter("password") != null) {
			request.setAttribute("games", new GameServiceJPA().getGames());
			request.setAttribute("numbersOfPlayers", new StatisticsService().numOfPlayers());
			request.setAttribute("numbersOfGames", new StatisticsService().numOfGames());
			request.setAttribute("mostPlayedGames", new StatisticsService().mostPlayedGame());
			request.setAttribute("topPlayer", new StatisticsService().topPlayer());
			request.setAttribute("numOfScoresForGames", new StatisticsService().scoresCountForGames());
			request.setAttribute("numOfKomentsForGames", new StatisticsService().komentsCountForGames());
			request.setAttribute("numOfRatingsForGames", new StatisticsService().ratingsCountForGames());

			if (!request.getParameter("nickName").equals("")) {
				actualPlayer = new PlayerServiceJPA().checkPlayer(request.getParameter("nickName"));
				request.setAttribute("player", actualPlayer);
				request.setAttribute("back", "hidden");
			} else {
				request.setAttribute("formular", "hidden");
			}
			if (request.getParameter("game") != null) {
				request.setAttribute("statistics", "hidden");
				// show scores and koments
				String gamePlayed = request.getParameter("game");
				request.setAttribute("gamePlayed", gamePlayed);
				actualGame = new GameServiceJPA().checkGame(gamePlayed);

				// show avg and num Of Ratings

				if (!request.getParameter("nickName").equals("")) {
					// adding koment and rating
					if ("addKoment".equals(action)) {
						String koment = request.getParameter("koment");
						if (!koment.trim().isEmpty()) {
							new KomentServiceJPA().addKoment(new KomentJpa(koment, actualPlayer, actualGame));
						}
					}
					if ("addRating".equals(action)) {
						int rating = Integer.parseInt(request.getParameter("rating"));
						if (Integer.parseInt(request.getParameter("rating")) != 0) {
							new RaitingServiceJPA().addRaiting(new RaitingJpa(rating, actualPlayer, actualGame));
						}
					}
				}
				int numOfRatings = new RaitingServiceJPA().numbersOfRaitings(gamePlayed);
				request.setAttribute("numOfRatings", numOfRatings);
				double avgOfRatings = new RaitingServiceJPA().averageOfRaitings(gamePlayed);
				request.setAttribute("avgOfRatings", avgOfRatings);

				if (new ScoreServiceJPA().showScore(actualGame).isEmpty()) {
					request.setAttribute("scoresTable", "hidden");
				} else {
					request.setAttribute("scores", new ScoreServiceJPA().showScore(actualGame));
				}

				if (new KomentServiceJPA().showKoments(actualGame).isEmpty()) {
					request.setAttribute("komentsTable", "hidden");
				} else {
					request.setAttribute("koments", new KomentServiceJPA().showKoments(actualGame));
				}

				request.getRequestDispatcher("/WEB-INF/jsp/GameStudio.jsp").forward(request, response);
			} else if ("mainMenu".equals(action) && request.getParameter("nickName") != null) {
				request.getRequestDispatcher("/WEB-INF/jsp/GamesChoice.jsp").forward(request, response);
			}  else if ("loginPage".equals(action)) {
				request.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/jsp/GamesChoice.jsp").forward(request, response);
			}
		} else if ("logOut".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(request, response);

		}*/
		
		
		
		else {
			request.setAttribute("wrongNamePass", "hidden");
			request.setAttribute("usedNickName", "hidden");
//			new GameServiceJPA().checkGame("Minesweeper");
//			new GameServiceJPA().checkGame("Stones");
//			new GameServiceJPA().checkGame("GuessTheNumber");
//			new PlayerServiceJPA().playerReg("Janko", "123456");
//			new PlayerServiceJPA().playerReg("Ferko", "456789");
//			new ScoreServiceJPA().addScore(new ScoreJpa(52, new PlayerServiceJPA().checkPlayer("Janko"), new GameServiceJPA().checkGame("GuessTheNumber")));
//			new ScoreServiceJPA().addScore(new ScoreJpa(20, new PlayerServiceJPA().checkPlayer("Janko"), new GameServiceJPA().checkGame("Minesweeper")));
//			new ScoreServiceJPA().addScore(new ScoreJpa(13, new PlayerServiceJPA().checkPlayer("Janko"), new GameServiceJPA().checkGame("Stones")));
//			new ScoreServiceJPA().addScore(new ScoreJpa(45, new PlayerServiceJPA().checkPlayer("Ferko"), new GameServiceJPA().checkGame("GuessTheNumber")));
//			new ScoreServiceJPA().addScore(new ScoreJpa(68, new PlayerServiceJPA().checkPlayer("Ferko"), new GameServiceJPA().checkGame("Minesweeper")));
//			new ScoreServiceJPA().addScore(new ScoreJpa(8, new PlayerServiceJPA().checkPlayer("Ferko"), new GameServiceJPA().checkGame("Stones")));
//			new RaitingServiceJPA().addRaiting(new RaitingJpa(1, new PlayerServiceJPA().checkPlayer("Janko"), new GameServiceJPA().checkGame("GuessTheNumber")));
//			new RaitingServiceJPA().addRaiting(new RaitingJpa(2, new PlayerServiceJPA().checkPlayer("Janko"), new GameServiceJPA().checkGame("Minesweeper")));
//			new RaitingServiceJPA().addRaiting(new RaitingJpa(5, new PlayerServiceJPA().checkPlayer("Janko"), new GameServiceJPA().checkGame("Stones")));
//			new RaitingServiceJPA().addRaiting(new RaitingJpa(3, new PlayerServiceJPA().checkPlayer("Ferko"), new GameServiceJPA().checkGame("GuessTheNumber")));
//			new RaitingServiceJPA().addRaiting(new RaitingJpa(4, new PlayerServiceJPA().checkPlayer("Ferko"), new GameServiceJPA().checkGame("Minesweeper")));
//			new RaitingServiceJPA().addRaiting(new RaitingJpa(4, new PlayerServiceJPA().checkPlayer("Ferko"), new GameServiceJPA().checkGame("Stones")));
//			new KomentServiceJPA().addKoment(new KomentJpa("koment 1", new PlayerServiceJPA().checkPlayer("Janko"), new GameServiceJPA().checkGame("GuessTheNumber")));
//			new KomentServiceJPA().addKoment(new KomentJpa("koment 2", new PlayerServiceJPA().checkPlayer("Janko"), new GameServiceJPA().checkGame("Minesweeper")));
//			new KomentServiceJPA().addKoment(new KomentJpa("koment 3", new PlayerServiceJPA().checkPlayer("Janko"), new GameServiceJPA().checkGame("Stones")));
//			new KomentServiceJPA().addKoment(new KomentJpa("koment 4", new PlayerServiceJPA().checkPlayer("Ferko"), new GameServiceJPA().checkGame("GuessTheNumber")));
//			new KomentServiceJPA().addKoment(new KomentJpa("koment 5", new PlayerServiceJPA().checkPlayer("Ferko"), new GameServiceJPA().checkGame("Minesweeper")));
//			new KomentServiceJPA().addKoment(new KomentJpa("koment 6", new PlayerServiceJPA().checkPlayer("Ferko"), new GameServiceJPA().checkGame("Stones")));
			request.getRequestDispatcher("/WEB-INF/jsp/LoginPage.jsp").forward(request, response);
		}
	}

	private void statisticsAboutGames(HttpServletRequest request, String gamePlayed) {
		int numOfRatings = new RaitingServiceJPA().numbersOfRaitings(gamePlayed);
		request.setAttribute("numOfRatings", numOfRatings);
		double avgOfRatings = new RaitingServiceJPA().averageOfRaitings(gamePlayed);
		request.setAttribute("avgOfRatings", avgOfRatings);

		if (new ScoreServiceJPA().showScore(actualGame).isEmpty()) {
			request.setAttribute("scoresTable", "hidden");
		} else {
			request.setAttribute("scores", new ScoreServiceJPA().showScore(actualGame));
		}

		if (new KomentServiceJPA().showKoments(actualGame).isEmpty()) {
			request.setAttribute("komentsTable", "hidden");
		} else {
			request.setAttribute("koments", new KomentServiceJPA().showKoments(actualGame));
		}
	}

	private void statisticsData(HttpServletRequest request) {
		request.setAttribute("numbersOfPlayers", new StatisticsService().numOfPlayers());
		request.setAttribute("numbersOfGames", new StatisticsService().numOfGames());
		request.setAttribute("mostPlayedGames", new StatisticsService().mostPlayedGame());
		request.setAttribute("topPlayer", new StatisticsService().topPlayer());
		request.setAttribute("numOfScoresForGames", new StatisticsService().scoresCountForGames());
		request.setAttribute("numOfKomentsForGames", new StatisticsService().komentsCountForGames());
		request.setAttribute("numOfRatingsForGames", new StatisticsService().ratingsCountForGames());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/GameStudio.jsp").forward(request, response);
	}

}