package it.dstech.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Dolce {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	private int quantita;
	
	@ManyToOne (fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn (name="ricetta")
	private Ricetta ricetta;
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	private List<Ordinazione> ordinazione;
	
	public List<Ordinazione> getOrdinazione() {
		return ordinazione;
	}

	public void setOrdinazione(List<Ordinazione> ordinazione) {
		this.ordinazione = ordinazione;
	}

	private double costoDolce20;

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

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public Ricetta getRicetta() {
		return ricetta;
	}

	public void setRicetta(Ricetta ricetta) {
		this.ricetta = ricetta;
	}

	public double getCostoDolce20() {
		return costoDolce20;
	}

	public void setCostoDolce20(double costoDolce20) {
		this.costoDolce20 = costoDolce20;
	}
	
	

	
	
	

}
