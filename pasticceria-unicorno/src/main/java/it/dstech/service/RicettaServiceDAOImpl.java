package it.dstech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.Ingrediente;
import it.dstech.models.Ricetta;
import it.dstech.repository.RicettaRepository;

@Service
public class RicettaServiceDAOImpl implements RicettaServiceDAO{

	@Autowired 
	public RicettaRepository ricettaRepo;

	@Override
	public boolean rimuoviRicetta(Long id) {
		Ricetta ricetta = ricettaRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		ricettaRepo.delete(ricetta);
		return true;
		
	}
	
	@Override
	public Ricetta findById(long id) {
		
		return ricettaRepo.findById(id);
	}

	@Override
	public List<Ricetta> findAllRicette() {
		
		return ricettaRepo.findAll();
	}

	@Override
	public boolean addRicetta(Ricetta ricetta) {

		
		if (ricettaRepo.existsById(ricetta.getId())) {
			Ricetta sovrascriviRicetta = ricetta;
			ricettaRepo.save(sovrascriviRicetta);
		}

		Ricetta save = ricettaRepo.save(ricetta);
		return save != null;

		
	}

}
