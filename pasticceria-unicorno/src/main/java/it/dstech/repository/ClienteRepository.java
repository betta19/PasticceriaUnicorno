package it.dstech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dstech.models.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	public Cliente findById(long id);

}
