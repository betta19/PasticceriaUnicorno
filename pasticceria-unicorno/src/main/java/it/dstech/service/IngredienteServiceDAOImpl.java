package it.dstech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.Cliente;
import it.dstech.models.Ingrediente;
import it.dstech.repository.IngredienteRepository;

@Service
public class IngredienteServiceDAOImpl implements IngredienteServiceDAO {

	@Autowired
	public IngredienteRepository ingredienteRepo;

	@Override
	public boolean modificaIngrediente(Ingrediente i) {
		// TODO Auto-generated method stub
		return false;
	}

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

}
