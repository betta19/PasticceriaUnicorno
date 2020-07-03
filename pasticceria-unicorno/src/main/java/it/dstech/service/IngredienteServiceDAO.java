package it.dstech.service;

import java.util.List;

import it.dstech.models.Cliente;
import it.dstech.models.Ingrediente;
import it.dstech.models.Ricetta;

public interface IngredienteServiceDAO {

	boolean rimuoviIngrediente(Long id);
	
	Ingrediente aggiungiIngredienteARicetta(Long idIngredienti, Ricetta ricetta);

	boolean addIngrediente(Ingrediente ingrediente);
	
	List<Ingrediente> findAllIngrediente();
	
	Ingrediente findById(long id);
	
	void save(Ingrediente ingrediente);

}
