package it.dstech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.Cliente;
import it.dstech.models.Dolce;
import it.dstech.models.Ingrediente;
import it.dstech.models.Ordinazione;
import it.dstech.models.Ricetta;
import it.dstech.repository.ClienteRepository;
import it.dstech.repository.DolceRepository;
import it.dstech.repository.OrdinazioneRepository;

@Service
public class OrdinazioneServiceDAOImpl implements OrdinazioneServiceDAO{

	@Autowired
	OrdinazioneRepository ordinazioneRepo;
	
	@Autowired
	ClienteRepository clienteRepo;
	
	@Autowired
	DolceRepository dolceRepo;
	
	@Override
	public List<Ordinazione> listaOrdiniCliente(long id) {
		
		return clienteRepo.findById(id).getOrdinazioni();
	}

	@Override
	public boolean addOrdinazione(Ordinazione ordinazione) {
		if (ordinazioneRepo.existsById(ordinazione.getId())) {
			Ordinazione sovrascriviOrdinazione = ordinazione;
			ordinazioneRepo.save(sovrascriviOrdinazione);
		}
		Ordinazione save = ordinazioneRepo.save(ordinazione);
		return save != null;
		
	}

	@Override
	public double costoOrdinazione (long id) {
		double costo = 0;
		costo += dolceRepo.findById(id).getCostoDolce20();
		return costo;
	}
	@Override
	public Ordinazione findById(long l) {
		
		return ordinazioneRepo.findById(l);
	}
}
