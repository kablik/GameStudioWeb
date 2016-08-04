package gamestudio.jpaServices;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import gamestudio.JpaHelper;
import gamestudio.entityJPA.GameJpa;
import gamestudio.entityJPA.ScoreJpa;

public class ScoreServiceJPA {

	public void addScore(ScoreJpa score) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(score);
		JpaHelper.commitTransaction();
	}

	public List<ScoreJpa> showScore(GameJpa game) {
		List<ScoreJpa> Scores = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select s from ScoreJpa s where s.game = :name order by s.score");
		query.setParameter("name", game).setMaxResults(5);
		Scores = query.getResultList();
		return Scores;
	}

}
