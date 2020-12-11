package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.domain.Band;
import model.domain.Member;
import util.PublicCommon;

public class MemberCRUD {

	// *CREATE : 신규 멤버 추가
	public static void createMember(EntityManager em, String name, String role, String nationality, String gender,
			long bandId) {
		System.out.println("=== Create ===");
	
		Band band = em.find(Band.class, bandId);
		
		if (band != null) {
			Member member = Member.builder().name(name).role(role)
									.nationality(nationality).gender(gender)
									.bandId(band).build();
			band.getMember().add(member);
			em.persist(member);
		} else {
			System.out.println("추가하실 정보에 오류가 있습니다. 다시 입력해 주세요.");
		}
	}

	// UPDATE1 : 밴드 아이디를 변경한다. 
	public static void updateMemberbandId(EntityManager em, Integer newbandId, int oldbandId) {
		String jqpl = "update Member set bandId = ? where bandId = ?";
		
		int result = em.createNativeQuery(jqpl).setParameter(1, newbandId)
				.setParameter(2, oldbandId).executeUpdate();
		
		if (result != 0) {
			System.out.println("ok");
		} else {
			System.out.println("ng");
		}
	}

	// * SELECT : 신규로 결성된 밴드의 멤버 확인
	public static void findMember(EntityManager em) {
		System.out.println("=== select ===");
		
		String jqpl = "select m from Member m where m.bandId = 3L";

		List<Member> resultList1 = em.createQuery(jqpl, Member.class).getResultList();
		
		if (resultList1 != null) {
			resultList1.forEach(v -> System.out.println("MEMBER SELECT" + v));
		} else {
			System.out.println("검색 요청한 멤버는 존재하지 않습니다");
		}
	}

	// *SELECT ALL : 전체 밴드 인원 확인하기
	public static void findMemberAll(EntityManager em) {
		System.out.println("=== select All ===");
		
		String jqpl = "select m from Member m";
		
		List<Member> resultList = em.createQuery(jqpl, Member.class).getResultList();
		
		if (resultList != null) {
			resultList.forEach(m -> System.out.println("MEMBER SELECT" + m));
		} else {
			System.out.println("검색 요청한 멤버는 존재하지 않습니다.");
		}
	}

	// * DELETE : 밴드아이디를 이용하여 멤버 삭제하기
	public static void deleteMember(EntityManager em, int bandId) {
		System.out.println("=== delete ===");
		
		String jqpl = "delete Member where bandId = ?";
		
		int result = em.createNativeQuery(jqpl).setParameter(1, bandId).executeUpdate();
		
		if (result != 0) {
			System.out.println("ok");
		} else {
			System.out.println("ng");
		}
	}

	@Test
	public void runningJPQL() {

		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			findMemberAll(em);// 밴드구성원 탐색
//			createMember(em, "이국주","보컬", "태국", "여성", 3L); //이국주를 3L 밴드에 추가
//			updateMemberbandId(em, 2, 1);
//			findMember(em); // 3L 멤버 탐색
			deleteMember(em, 3); // 3  멤버 삭제

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void main(String[] args) {

	}

}
