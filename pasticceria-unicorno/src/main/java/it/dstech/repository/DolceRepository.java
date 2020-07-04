package it.dstech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dstech.models.Dolce;
import it.dstech.models.Ingrediente;

public interface DolceRepository extends JpaRepository<Dolce, Long>{

	public Dolce findById(long id);
}
