package it.dstech.service;

import it.dstech.models.Dolce;
import it.dstech.models.Ingrediente;
import it.dstech.models.Ricetta;

public interface AmministratoreServiceDAO {

	boolean addIngrediente(Ingrediente i);

	boolean addDolce(Dolce d);

	boolean addRicetta(Ricetta r);

}
