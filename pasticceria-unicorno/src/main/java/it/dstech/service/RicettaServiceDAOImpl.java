package it.dstech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.Dolce;
import it.dstech.models.Ingrediente;
import it.dstech.models.Ricetta;
import it.dstech.repository.DolceRepository;
import it.dstech.repository.IngredienteRepository;
import it.dstech.repository.RicettaRepository;

@Service
public class RicettaServiceDAOImpl implements RicettaServiceDAO{

	@Autowired 
	public RicettaRepository ricettaRepo;
	
	@Autowired
	public IngredienteRepository ingredienteRepo;
	 
	@Autowired
	public DolceRepository dolceRepo;

	@Override
	public boolean rimuoviRicetta(Long id) {
		Ricetta ricetta = ricettaRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		
		for (int i = 0; i < ricetta.getIngrediente().size(); i++) {
	
			Ingrediente ingrediente = ricetta.getIngrediente().get(i);
			ricetta.setIngrediente(null);
			ingrediente.setRicetta(null);
			ingredienteRepo.save(ingrediente);
			ricettaRepo.save(ricetta);
			
		}
		
		for (int i = 0; i < ricetta.getDolce().size(); i++) {
			Dolce dolce = ricetta.getDolce().get(i);
			ricetta.setDolce(null);
			dolce.setRicetta(null);
			dolceRepo.save(dolce);
			ricettaRepo.save(ricetta);
			
		}
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
	
	@Override
	public Ricetta costoRicetta (Ricetta r) {
		double costo = 0;
		for (int i = 0; i < r.getIngrediente().size(); i++) {
			costo += r.getIngrediente().get(i).getCostoIngrediente();
		} 
		r.setCostoRicetta10(costo + (costo * 0.1));
		return r;
	}
}
