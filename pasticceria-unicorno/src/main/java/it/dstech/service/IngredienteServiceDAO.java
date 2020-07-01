package it.dstech.service;

import it.dstech.models.Ingrediente;

public interface IngredienteServiceDAO {

	boolean modificaIngrediente(Ingrediente i);

	boolean rimuoviIngrediente(Long id);

	boolean addIngrediente(Ingrediente ingrediente);

}
