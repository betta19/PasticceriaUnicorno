package it.dstech.service;

import it.dstech.models.Ingrediente;

public interface IngredienteServiceDAO {

	boolean modificaIngrediente(Ingrediente i);

	void rimuoviIngrediente(Ingrediente i);

}
