package it.dstech.service;

import java.util.List;


import it.dstech.models.Ricetta;

public interface RicettaServiceDAO {

	boolean rimuoviRicetta(Long id);

	List<Ricetta> findAllRicette();

	boolean addRicetta(Ricetta ricetta);

	Ricetta findById(long id);

	Ricetta costoRicetta (Ricetta ricetta);
}
