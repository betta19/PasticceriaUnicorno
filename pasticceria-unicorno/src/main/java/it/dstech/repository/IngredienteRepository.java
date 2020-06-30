package it.dstech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dstech.models.Ingrediente;


public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{

}
