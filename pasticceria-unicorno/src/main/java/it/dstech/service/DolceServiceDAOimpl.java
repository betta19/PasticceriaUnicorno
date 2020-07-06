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
	public boolean rimuoviDolce(Long id) {
		
		Dolce dolce = dolceRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		if (dolce.getOrdinazione() != null) {
			return false;
		}
		dolce.getRicetta().setIngrediente(null);
		dolce.setRicetta(null);
		dolceRepo.save(dolce);
		dolceRepo.delete(dolce);
		return true;
		
	}

	@Override
	public boolean addDolce(Dolce dolce, long id) {
		Ricetta r = ricettaRepo.findById(id);
		dolce.setRicetta(r);
		r.getDolce().add(dolce);
		dolce.setCostoDolce20(costoDolce(dolce));
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
	public Dolce aggiungiOrdinazioneADolce(Long id, Ordinazione ordinazione) {
		
			Dolce dolce = dolceRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid dolce Id:" + id));
			dolce.setId(id);
			dolce.getOrdinazione().add(ordinazione);
			dolceRepo.save(dolce);
			return dolce;
	    	
		}
	@Override
	public double costoDolce (Dolce d) {
		double costo = 0;
	costo += d.getRicetta().getCostoRicetta10();
	 
	return costo +(costo * 0.2);
	}

	}

