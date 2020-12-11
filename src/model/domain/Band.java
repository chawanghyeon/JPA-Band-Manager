package model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@SequenceGenerator(name = "BAND_SEQ_GEN", sequenceName = "band_id_seq", initialValue = 1)
public class Band {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BAND_SEQ_GEN")
	@Column(name = "band_id")
	private long bandId;
	@Column(nullable = false, length = 20)
	private String bname;
	@Column(nullable = false, length = 20)
	private String genre;
	@Column(nullable = false, length = 20)
	private int guarantee;

	@OneToMany(mappedBy = "bandId")
	private List<Member> member;
}
