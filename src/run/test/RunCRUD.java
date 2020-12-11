package run.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import util.PublicCommon;

public class RunCRUD {

	@Test
	public void run() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		tx.commit();
		em.close();
	}
}
