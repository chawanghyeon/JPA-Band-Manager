package model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@SequenceGenerator(name = "MEMBER_SEQ_GEN", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 50)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GEN")
	private Long memberId;

	@Column(nullable=false, length=20)
	private String name;

	@Column(nullable=false, length=20)
	private String role;

	@Column(nullable=false, length=20)
	private String nationality;

	@Column(nullable=false, length=10)
	private String gender;

	@ManyToOne
	@JoinColumn(name="")
	private Band bandId;
}
