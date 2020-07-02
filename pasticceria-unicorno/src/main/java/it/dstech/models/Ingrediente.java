package it.dstech.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
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

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nome=" + nome + ", costoIngrediente=" + costoIngrediente + ", disponibile="
				+ disponibile + "]";
	}

	
	

}
