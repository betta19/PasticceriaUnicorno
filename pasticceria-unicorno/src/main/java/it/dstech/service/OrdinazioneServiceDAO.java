package it.dstech.service;

import java.util.List;

import it.dstech.models.Ordinazione;

public interface OrdinazioneServiceDAO {

	List<Ordinazione> listaOrdiniCliente(long id);

	boolean addOrdinazione(Ordinazione ordinazione);

	double costoOrdinazione(long id);

	Ordinazione findById(long l);
}
