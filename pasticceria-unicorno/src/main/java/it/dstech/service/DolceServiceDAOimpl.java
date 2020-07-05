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
import it.dstech.repository.RicettaRepository;

@Service
public class DolceServiceDAOimpl implements DolceServiceDAO{
	@Autowired
	RicettaRepository ricettaRepo;
	
	@Autowired
	DolceRepository dolceRepo;
	
	@Autowired
	OrdinazioneRepository ordinazioneRepo;
	
	@Autowired
	ClienteRepository clienteRepo;
	
	@Autowired
	ClienteServiceDAO clienteService;
	
	@Autowired
	OrdinazioneServiceDAO ordinazioneService;

	@Override
	public boolean modificaDolce(Dolce d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rimuoviDolce(Long id) {
		
		Dolce dolce = dolceRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		dolceRepo.delete(dolce);
		return true;
		
	}

	@Override
	public boolean addDolce(Dolce dolce, long id) {
		Ricetta r = ricettaRepo.findById(id);
		dolce.setRicetta(r);
		r.getDolce().add(dolce);
		if (dolceRepo.existsById(dolce.getId())) {
			Dolce sovrascriviDolce = dolce;
			dolceRepo.save(sovrascriviDolce);
			r.setId(id);
			ricettaRepo.save(r);
		}

		Dolce save = dolceRepo.save(dolce);
		r.setId(id);
		ricettaRepo.save(r);
		return save != null;
	}

	@Override
	public List<Dolce> findAllDolci() {
		return dolceRepo.findAll();
	}

	@Override
	public Dolce findById(long l) {
		
		return dolceRepo.findById(l);
	}

	@Override
	public void aggiungiOrdinazioneADolce(Dolce dolce, Ordinazione ordinazione, Cliente cliente) {
		
	    	dolceRepo.save(dolce);
	    	ordinazione.getDolce().add(dolce.getNome());
	    	ordinazioneService.addOrdinazione(ordinazione); 
			clienteService.findById(cliente.getId()).getOrdinazioni().add(ordinazione);
			clienteService.addCliente(clienteService.findById(cliente.getId()));

		}
	}

