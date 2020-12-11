package model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
@Builder

@Entity
@SequenceGenerator(name = "MEMBER_SEQ_GEN", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GEN")
	private Long memberId;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false, length=30)
	private String role;

	@Column(nullable=false, length=30)
	private String nationality;

	@Column(nullable=false, length=30)
	private String gender;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bandId")
	private Band bandId;

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", name=" + name + ", role=" + role + ", nationality=" + nationality
				+ ", gender=" + gender + ", bandId=" + bandId.getBandId() + ", bname = " + bandId.getBname() + "]";
	}
	
	
	
	
}
