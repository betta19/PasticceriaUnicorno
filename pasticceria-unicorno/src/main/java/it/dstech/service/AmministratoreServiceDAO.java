package it.dstech.service;

import java.util.List;

import it.dstech.models.Dolce;
import it.dstech.models.Ingrediente;
import it.dstech.models.Ordinazione;
import it.dstech.models.Ricetta;

public interface AmministratoreServiceDAO {

	boolean addIngrediente(Ingrediente i);

	boolean addDolce(Dolce d);

	boolean addRicetta(Ricetta r);

	List<Ordinazione> listaOrdinazioniPassate();

	List<Ordinazione> listaOrdinazioniNuove();
	

}
