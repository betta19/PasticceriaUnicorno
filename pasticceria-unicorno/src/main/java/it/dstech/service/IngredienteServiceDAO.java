package it.dstech.service;

import java.util.List;


import it.dstech.models.Ingrediente;

public interface IngredienteServiceDAO {

	boolean modificaIngrediente(Ingrediente i);

	boolean rimuoviIngrediente(Long id);

	boolean addIngrediente(Ingrediente ingrediente);
	
	List<Ingrediente> findAllIngrediente();

}
