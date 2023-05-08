package com.ajou_nice.with_pet.domain.entity;


import com.ajou_nice.with_pet.enums.DogSize;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class PetSitter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "petsitter_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="applicant_id",unique = true, nullable = false)
	private PetSitterApplicant applicant;

	@OneToMany(mappedBy = "petSitter")
	private List<PetSitterService> petSitterServiceList = new ArrayList<>();

	@OneToMany(mappedBy = "petSitter")
	private List<House> petSitterHouseList = new ArrayList<>();

	@OneToMany(mappedBy = "petSitter")
	private List<PetSitterHashTag> petSitterHashTagList = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private DogSize availableDogSize;

	@Lob
	private String introduction;
}
