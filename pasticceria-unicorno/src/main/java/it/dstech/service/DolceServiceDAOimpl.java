package it.dstech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.Dolce;
import it.dstech.models.Ricetta;
import it.dstech.repository.DolceRepository;

@Service
public class DolceServiceDAOimpl implements DolceServiceDAO{
	
	@Autowired
	DolceRepository dolceRepo;

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
	public boolean addDolce(Dolce dolce, Ricetta ricetta) {
		dolce.setRicetta(ricetta);
		if (dolceRepo.existsById(dolce.getId())) {
			Dolce sovrascriviDolce = dolce;
			dolceRepo.save(sovrascriviDolce);
		}

		Dolce save = dolceRepo.save(dolce);
		return save != null;
	}

	@Override
	public List<Dolce> findAllDolci() {
		return dolceRepo.findAll();
	}

}
