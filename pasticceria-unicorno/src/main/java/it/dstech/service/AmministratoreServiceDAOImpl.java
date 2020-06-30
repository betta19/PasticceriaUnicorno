package it.dstech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.dstech.models.Dolce;
import it.dstech.models.Ingrediente;
import it.dstech.models.Ordinazione;
import it.dstech.models.Ricetta;
import it.dstech.repository.AmministratoreRepository;

public class AmministratoreServiceDAOImpl implements AmministratoreServiceDAO {
	
	@Autowired
	public AmministratoreRepository amministratoreRepo;
	

	public List<Ordinazione> listaOrdinazioniPassate() {
		return amministratoreRepo.findByCompletatoTrue();
	}
	
	public List<Ordinazione> listaOrdinazioniNuove() {
		return amministratoreRepo.findByCompletatoFalse();
	}

	@Override
	public boolean addIngrediente(Ingrediente i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addDolce(Dolce d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRicetta(Ricetta r) {
		// TODO Auto-generated method stub
		return false;
	}
}
