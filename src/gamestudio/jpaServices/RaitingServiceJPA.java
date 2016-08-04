package gamestudio.jpaServices;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import gamestudio.JpaHelper;
import gamestudio.entityJPA.RaitingJpa;


public class RaitingServiceJPA {

	public void addRaiting(RaitingJpa raiting) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		int id = getIdOfRaiting(raiting.getGame().getGame(), raiting.getPlayer().getPlayer());
		if (id == 0){
			em.persist(raiting);
		} else {
			RaitingJpa raitingObj = em.find(RaitingJpa.class, id);
			raitingObj.setRaiting(raiting.getRaiting());
		}
		JpaHelper.commitTransaction();
	}

	public int numbersOfRaitings(String game) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(r) from RaitingJpa r WHERE r.game=:game");
		query.setParameter("game", new GameServiceJPA().checkGame(game));
		return Integer.parseInt(query.getSingleResult().toString());
	}

	public double averageOfRaitings(String game) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select avg(r.raiting) from RaitingJpa r WHERE r.game=:game");
		query.setParameter("game", new GameServiceJPA().checkGame(game));
		Double avg = (Double) query.getSingleResult();
		if (avg == null) {
			return 0;
		} else {
			return avg;
		}
	}
	
	private int getIdOfRaiting(String gameName, String playerName){
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select ident from RaitingJpa r where r.player =:playerId and r.game =:gameId");
		query.setParameter("playerId", new PlayerServiceJPA().checkPlayer(playerName));
		query.setParameter("gameId", new GameServiceJPA().checkGame(gameName));
		if(query.getResultList().isEmpty()){
			return 0;
		} else {
			return (int) query.getResultList().get(0);
		}
	}
	
//	public int countOfRatingGames(String name){
//		EntityManager em = JpaHelper.getEntityManager();
//		Query query = em.createNativeQuery("select count(*) from RaitingJpa r join GAMEJPA g on r.game_IDENT = g.IDENT where g.game like :name group by g.game");
//		query.setParameter("name", name);
//		if (query.getResultList().isEmpty()){
//			return 0;
//		} else {
//			return Integer.parseInt(query.getResultList().get(0).toString());
//		}
//	}

}
