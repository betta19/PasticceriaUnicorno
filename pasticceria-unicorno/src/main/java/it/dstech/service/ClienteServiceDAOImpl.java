package it.dstech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.Cliente;
import it.dstech.repository.ClienteRepository;

@Service
public class ClienteServiceDAOImpl implements ClienteServiceDAO {

	@Autowired
	public ClienteRepository clienteRepo;

	@Override
	public boolean addCliente(Cliente c) {

		if (clienteRepo.existsById(c.getId())) {
			Cliente sovrascriviUtente = c;

			clienteRepo.save(sovrascriviUtente);
		}

		Cliente save = clienteRepo.save(c);
		return save != null;

	}

	public boolean deleteCliente(Long id) {
		Cliente cliente = clienteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		clienteRepo.delete(cliente);
		return true;
	}

	public List<Cliente> findAllClienti() {
		return clienteRepo.findAll();

	}

	@Override
	public Cliente findById(long id) {
		return clienteRepo.findById(id);
	}

	@Override
	public void save(Cliente cliente) {
		clienteRepo.save(cliente);
	}
}
