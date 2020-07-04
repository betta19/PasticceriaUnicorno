package it.dstech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.Cliente;
import it.dstech.models.Ingrediente;
import it.dstech.models.Ricetta;
import it.dstech.repository.IngredienteRepository;
import it.dstech.repository.RicettaRepository;

@Service
public class IngredienteServiceDAOImpl implements IngredienteServiceDAO {
	@Autowired
	public RicettaRepository ricettaRepo;

	@Autowired
	public IngredienteRepository ingredienteRepo;

	@Override
	public boolean rimuoviIngrediente(Long id) {
		Ingrediente ingrediente = ingredienteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid ingrediente Id:" + id));
		ingredienteRepo.delete(ingrediente);
		return true;

	}

	@Override
	public boolean addIngrediente(Ingrediente ingrediente) {
		ingrediente.setDisponibile(true);

		if (ingredienteRepo.existsById(ingrediente.getId())) {
			Ingrediente sovrascriviIngrediente = ingrediente;
			sovrascriviIngrediente.setId(ingrediente.getId());
			ingredienteRepo.save(sovrascriviIngrediente);
		}

		Ingrediente save = ingredienteRepo.save(ingrediente);
		return save != null;

	}

	public List<Ingrediente> findAllIngrediente() {
		return ingredienteRepo.findAll();

	}

	@Override
	public Ingrediente findById(long id) {

		return ingredienteRepo.findById(id);
	}

	@Override
	public void save(Ingrediente ingrediente) {
		ingredienteRepo.save(ingrediente);

	}

	@Override
	public Ingrediente aggiungiIngredienteARicetta(Long id, Ricetta ricetta) {
		
		Ingrediente ingrediente = ingredienteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid ingrediente Id:" + id));
		ingrediente.setId(id);
		ingrediente.getRicetta().add(ricetta);
		ingredienteRepo.save(ingrediente);
		return ingrediente;
	}

}
