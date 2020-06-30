package it.dstech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dstech.models.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
