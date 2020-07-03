package it.dstech.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany (fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Ricetta> ricetta;
	
	private String nome;
	
	private double costoIngrediente;
	
	boolean disponibile;

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

	public double getCostoIngrediente() {
		return costoIngrediente;
	}

	public void setCostoIngrediente(double costoIngrediente) {
		this.costoIngrediente = costoIngrediente;
	}

	public boolean isDisponibile() {
		return disponibile;
	}

	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}

	
	

	public List<Ricetta> getRicetta() {
		return ricetta;
	}

	public void setRicetta(List<Ricetta> ricetta) {
		this.ricetta = ricetta;
	}

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nome=" + nome + ", costoIngrediente=" + costoIngrediente + ", disponibile="
				+ disponibile + "]";
	}

	
	

}
