package it.dstech.service;

import java.util.List;

import it.dstech.models.Cliente;

public interface ClienteServiceDAO {

	boolean addCliente (Cliente c);
	
	boolean deleteCliente (Long id);
	
	List<Cliente> findAllClienti();

	Cliente findById(long id);

	void save(Cliente cliente);
}
