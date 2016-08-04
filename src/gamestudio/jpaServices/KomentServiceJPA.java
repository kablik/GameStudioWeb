package gamestudio.jpaServices;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import gamestudio.JpaHelper;
import gamestudio.entityJPA.GameJpa;
import gamestudio.entityJPA.KomentJpa;

public class KomentServiceJPA {
	
	public void addKoment(KomentJpa koment) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(koment);
		JpaHelper.commitTransaction();
	}

	public List<KomentJpa> showKoments(GameJpa game) {
		List<KomentJpa> Koments = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select k from KomentJpa k where k.game =:name");
		query.setParameter("name", game).setMaxResults(5);
		Koments = query.getResultList();
		return Koments;
	}

}
