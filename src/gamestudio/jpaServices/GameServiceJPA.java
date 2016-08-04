package gamestudio.jpaServices;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import gamestudio.JpaHelper;
import gamestudio.entityJPA.GameJpa;

public class GameServiceJPA {

	private GameJpa getGame(int ident) {
		EntityManager em = JpaHelper.getEntityManager();
		return em.find(GameJpa.class, ident);
	}

	public GameJpa checkGame(String gameName) {
		int idcko = gameId(gameName);
		if (idcko != 0) {
			return getGame(idcko);
		} else {
			GameJpa gameForDb = new GameJpa(gameName);
			insertGame(gameForDb);
			return gameForDb;
		}
	}

	private int gameId(String gameName) {
		int id = 0;
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select ident from GameJpa g where g.game = :name");
		query.setParameter("name", gameName);
		if (query.getResultList().isEmpty()) {
			id = 0;
		} else {
			id = (int) query.getResultList().get(0);
		}
		return id;
	}

	private void insertGame(GameJpa game) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(game);
		JpaHelper.commitTransaction();
	}
	
	public List<GameJpa> getGames(){
		List<GameJpa> Games = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select g from GameJpa g");
		Games = query.getResultList();
		return Games;
	}
	
}
