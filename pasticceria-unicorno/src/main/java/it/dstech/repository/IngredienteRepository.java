package it.dstech.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import it.dstech.models.Ingrediente;


public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{

	public Ingrediente findById(long id);

	public boolean existsIngredienteByNome(String nome);


}
