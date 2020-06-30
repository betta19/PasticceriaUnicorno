package it.dstech.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ricetta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	private String tempoDiRealizzazione;
	
	private int difficoltà;
	
	@ManyToOne
	private List<Ingrediente> ingredienti;
	
	private String descrizione;
	
	private double costoRicetta10;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTempoDiRealizzazione() {
		return tempoDiRealizzazione;
	}

	public void setTempoDiRealizzazione(String tempoDiRealizzazione) {
		this.tempoDiRealizzazione = tempoDiRealizzazione;
	}

	public int getDifficoltà() {
		return difficoltà;
	}

	public void setDifficoltà(int difficoltà) {
		this.difficoltà = difficoltà;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getCostoRicetta10() {
		return costoRicetta10;
	}

	public void setCostoRicetta10(double costoRicetta10) {
		this.costoRicetta10 = costoRicetta10;
	}



	
	
	

}
