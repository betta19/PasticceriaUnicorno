package it.dstech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dstech.models.Ordinazione;


public interface OrdinazioneRepository extends JpaRepository<Ordinazione, Long>{

	
	public List<Ordinazione> findByCompletatoTrue();

	public List<Ordinazione> findByCompletatoFalse();
	
}
