package it.dstech.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ricetta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	
	private String tempoDiRealizzazione;
	
	private int difficolta;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Dolce> dolce;
	
	@OneToMany
	private List<Ingrediente> ingrediente;
	
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

	public int getDifficolta() {
		return difficolta;
	}

	public void setDifficolta(int difficolta) {
		this.difficolta = difficolta;
	}

	public List<Ingrediente> getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(List<Ingrediente> ingrediente) {
		this.ingrediente = ingrediente;
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

	public List<Dolce> getDolce() {
		return dolce;
	}

	public void setDolce(List<Dolce> dolce) {
		this.dolce = dolce;
	}

	@Override
	public String toString() {
		return "Ricetta [id=" + id + ", nome=" + nome + ", tempoDiRealizzazione=" + tempoDiRealizzazione
				+ ", difficolta=" + difficolta + ", dolce=" + dolce + ", ingrediente=" + ingrediente + ", descrizione="
				+ descrizione + ", costoRicetta10=" + costoRicetta10 + "]";
	}

	

}
