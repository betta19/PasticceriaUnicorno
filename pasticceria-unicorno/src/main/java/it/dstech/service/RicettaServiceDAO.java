package it.dstech.service;

import it.dstech.models.Ricetta;

public interface RicettaServiceDAO {

	boolean salvaRicetta(Ricetta r);

	void rimuoviRicetta(Ricetta t);

}
