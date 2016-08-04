package gamestudio.jpaServices;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import gamestudio.JpaHelper;
import gamestudio.entityJPA.PlayerJpa;

public class PlayerServiceJPA {

	private PlayerJpa getPlayer(int ident) {
		EntityManager em = JpaHelper.getEntityManager();
		return em.find(PlayerJpa.class, ident);
	}

	public PlayerJpa checkPlayer(String name) {
		int idcko = playerId(name);
		return getPlayer(idcko);
	}
	
	public PlayerJpa checkPlayerLogin(String name, String pass) {
		int idcko = playerIdLogin(name, pass);
		if (idcko != 0) {
			return getPlayer(idcko);
		} else {
			return null;
		}
	}
	
	private int playerIdLogin(String name, String pass){
		int id = 0;
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select ident from PlayerJpa p where p.player = :name and p.password = :pass");
		query.setParameter("name", name);
		query.setParameter("pass", pass);
		if (query.getResultList().isEmpty()) {
			id = 0;
		} else {
			id = (int) query.getResultList().get(0);
		}
		return id;
	}

	private int playerId(String playerName) {
		int id = 0;
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select ident from PlayerJpa p where p.player = :name");
		query.setParameter("name", playerName);
		if (query.getResultList().isEmpty()) {
			id = 0;
		} else {
			id = (int) query.getResultList().get(0);
		}
		return id;
	}

	private void insertPlayer(PlayerJpa player) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(player);
		JpaHelper.commitTransaction();
	}

	
	public int playerCheckReg(String playerName) {
		return playerId(playerName);
	}
	
	public PlayerJpa playerReg(String name, String pass) {
		PlayerJpa playerForDb = new PlayerJpa(name, pass);
		insertPlayer(playerForDb);
		return playerForDb;
	}


}
