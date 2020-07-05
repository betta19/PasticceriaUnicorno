package it.dstech.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Ordinazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToMany
	private List<Dolce> dolce;

    private String dataConsegna;
	
    @Column(nullable = true)
	private double costoOrdinazione;
	
    @Column(nullable = true)
	private boolean completato;

	public boolean isCompletato() {
		return completato;
	}

	public void setCompletato(boolean completato) {
		this.completato = completato;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Dolce> getDolce() {
		return dolce;
	}

	public void setDolce(List<Dolce> dolce) {
		this.dolce = dolce;
	}

	
	public double getCostoOrdinazione() {
		return costoOrdinazione;
	}

	public void setCostoOrdinazione(double costoOrdinazione) {
		this.costoOrdinazione = costoOrdinazione;
	}

	public String getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	@Override
	public String toString() {
		return "Ordinazione [id=" + id + ", cliente=" + cliente + ", dolce=" + dolce + ", dataConsegna=" + dataConsegna
				+ ", costoOrdinazione=" + costoOrdinazione + ", completato=" + completato + "]";
	}
	
	
	
	

}
