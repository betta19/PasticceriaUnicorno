package it.dstech.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import it.dstech.models.Ricetta;


public interface RicettaRepository extends JpaRepository<Ricetta, Long>{

	public Ricetta findById(long id);
}
