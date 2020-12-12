package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;

import model.domain.Band;
import util.PublicCommon;

public class BandCRUD {

//	@Test
	public void createBand() {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();

		try {
			Band band = Band.builder().bname("카킹").genre("팝").guarantee(99999999).build();
			em.persist(band);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();

		} finally {
			em.close();

		}
	}

	public void updateBand() {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();

		try {
			Band band = em.find(Band.class, 4L);

			// before update
			System.out.println("update 전 : " + band);
			band.setGenre("록");
			em.getTransaction().commit();

			// after update
			System.out.println("update 후 : " + band);

		} catch (Exception e) {
			em.getTransaction().rollback();

		} finally {
			em.close();

		}
	}

	// select
//	@Test
	public void findBand() {
		EntityManager em = PublicCommon.getEntityManager();

		try {
			Band band = em.find(Band.class, 4L);
			if (band != null) {
				System.out.println("band ID = " + band.getBandId());
				System.out.println("band NAME = " + band.getBname());
				System.out.println("band SALARY = " + band.getGenre());
				System.out.println("band DESIGNATION = " + band.getGuarantee());
			} else {
				System.out.println("검색 요청한 밴드는 미존재합니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

//	@Test
	public void findAllBands() {
		EntityManager entitymanager = PublicCommon.getEntityManager();
		try {
			String jpql = "select * from Band";
			Query query = entitymanager.createNativeQuery(jpql, Band.class);

			List<Band> list = query.getResultList();
			list.forEach(v -> System.out.println(v.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entitymanager.close();
		}
	}

	// delete
//	@Test
	public void deleteBand() {
		EntityManager em = PublicCommon.getEntityManager();
		em.getTransaction().begin();

		try {
			String jpql = "UPDATE member SET bandid = null where bandid = 4L";
			em.createNativeQuery(jpql).executeUpdate();
			Band band = em.find(Band.class, 4L);
			em.remove(band);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();

		} finally {
			em.close();

		}
	}
}
