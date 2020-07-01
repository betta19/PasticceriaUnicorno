package it.dstech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.Cliente;
import it.dstech.models.Ingrediente;
import it.dstech.repository.IngredienteRepository;

@Service
public class IngredienteServiceDAOImpl implements IngredienteServiceDAO{
	
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
		if (ingredienteRepo.existsById(ingrediente.getId())) {
			Ingrediente sovrascriviUtente = ingrediente;

			ingredienteRepo.save(sovrascriviUtente);
		}

		Ingrediente save = ingredienteRepo.save(ingrediente);
		return save != null;
		
	}

}
