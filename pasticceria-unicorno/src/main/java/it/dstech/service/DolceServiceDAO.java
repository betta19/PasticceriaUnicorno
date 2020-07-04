package it.dstech.service;

import java.util.List;

import it.dstech.models.Dolce;
import it.dstech.models.Ordinazione;
import it.dstech.models.Ricetta;

public interface DolceServiceDAO {

	boolean modificaDolce(Dolce d);

	boolean rimuoviDolce(Long id);

	boolean addDolce(Dolce dolce, long id);

	List<Dolce> findAllDolci();

	Dolce findById(long l);

	Dolce aggiungiOrdinazioneADolce(Long l, Ordinazione ordinazione);


}
