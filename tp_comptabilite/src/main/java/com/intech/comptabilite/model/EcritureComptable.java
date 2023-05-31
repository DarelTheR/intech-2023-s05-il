package com.intech.comptabilite.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ecriture_comptable")
public class EcritureComptable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "journalCode")
	@NotNull
	private JournalComptable journal;

	@Pattern(regexp = "\\d{1,5}-\\d{4}/\\d{5}")
	private String reference;

	@NotNull
	private Date date;

	@NotNull
	@Size(min = 1, max = 200)
	private String libelle;

	@OneToMany(
			cascade = CascadeType.ALL, 
			orphanRemoval = true, 
			fetch = FetchType.EAGER)
	@JoinColumn(name = "ecritureId")
	@Valid
	@Size(min = 2)
	private final List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer pId) {
		id = pId;
	}

	public JournalComptable getJournal() {
		return journal;
	}

	public void setJournal(JournalComptable pJournal) {
		journal = pJournal;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String pReference) {
		reference = pReference;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date pDate) {
		date = pDate;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String pLibelle) {
		libelle = pLibelle;
	}

	public List<LigneEcritureComptable> getListLigneEcriture() {
		return listLigneEcriture;
	}
}
