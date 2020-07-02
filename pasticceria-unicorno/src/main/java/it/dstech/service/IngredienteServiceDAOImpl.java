package it.dstech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.Cliente;
import it.dstech.models.Ingrediente;
import it.dstech.models.Ricetta;
import it.dstech.repository.IngredienteRepository;

@Service
public class IngredienteServiceDAOImpl implements IngredienteServiceDAO {

	@Autowired
	public IngredienteRepository ingredienteRepo;


	@Override
	public boolean rimuoviIngrediente(Long id) {
		Ingrediente ingrediente = ingredienteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		ingredienteRepo.delete(ingrediente);
		return true;

	}

	@Override
	public boolean addIngrediente(Ingrediente ingrediente) {
		ingrediente.setDisponibile(true);
		
		if (ingredienteRepo.existsById(ingrediente.getId())) {
			Ingrediente sovrascriviIngrediente = ingrediente;
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
	public boolean aggiungiIngredienteARicetta(Long id, Ricetta ricetta) {
		 Ingrediente ingrediente = ingredienteRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		ricetta.getIngrediente().add(ingrediente);
		return true;
	}

}
