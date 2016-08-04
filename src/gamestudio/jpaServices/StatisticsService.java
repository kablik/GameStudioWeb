package gamestudio.jpaServices;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import gamestudio.JpaHelper;
import gamestudio.dto.KomentsCount;
import gamestudio.dto.RatingsCount;
import gamestudio.dto.ScoresCount;

public class StatisticsService {

	public int numOfPlayers() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(p) from PlayerJpa p");
		if (query.getResultList().isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(query.getResultList().get(0).toString());
		}
	}

	public int numOfGames() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(g) from GameJpa g");
		if (query.getResultList().isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(query.getResultList().get(0).toString());
		}
	}

	public List<String> mostPlayedGame() {
		List<String> games = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createNativeQuery("select game from (select g.game, count(*) as count from GAMEJPA g "
				+ "join SCOREJPA s on s.game_IDENT = g.IDENT group by g.GAME) where "
				+ "count = (select max(count) from (select g.game, count(*) as count from GAMEJPA g join SCOREJPA s on s.game_IDENT = g.IDENT group by g.GAME))");
		games = query.getResultList();
		return games;
	}

	public List<String> topPlayer() {
		List<String> players = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createNativeQuery("select player from (select p.player, count(*) as count from PLAYERJPA p "
				+ "join SCOREJPA s on s.player_IDENT = p.IDENT group by p.player) where "
				+ "count = (select max(count) from (select p.player, count(*) as count from PLAYERJPA p join SCOREJPA s on s.player_IDENT = p.IDENT group by p.player))");
		players = query.getResultList();
		return players;
	}

	public List<ScoresCount> scoresCountForGames(){
		List<ScoresCount> gameCountScores = new ArrayList<>();
		gameCountScores = JpaHelper.getEntityManager()
			.createQuery("SELECT NEW gamestudio.dto.ScoresCount(s.game.game, count(s.game.game)) FROM ScoreJpa s GROUP BY s.game.game ORDER BY count(s.game.game) DESC", ScoresCount.class)
			.getResultList();
		return gameCountScores;
	}
	
	public List<KomentsCount> komentsCountForGames(){
		List<KomentsCount> gameCountKoments = new ArrayList<>();
		gameCountKoments = JpaHelper.getEntityManager()
			.createQuery("SELECT NEW gamestudio.dto.KomentsCount(k.game.game, count(k.game.game)) FROM KomentJpa k GROUP BY k.game.game ORDER BY count(k.game.game) DESC", KomentsCount.class)
			.getResultList();
		return gameCountKoments;
	}
	
	public List<RatingsCount> ratingsCountForGames(){
		List<RatingsCount> gameCountRatings = new ArrayList<>();
		gameCountRatings = JpaHelper.getEntityManager()
			.createQuery("SELECT NEW gamestudio.dto.RatingsCount(r.game.game, count(r.game.game)) FROM RaitingJpa r GROUP BY r.game.game ORDER BY count(r.game.game) DESC", RatingsCount.class)
			.getResultList();
		return gameCountRatings;
	}

}
