package com.mariaa.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mariaa.app.domain.Client;
import com.mariaa.app.dto.ClientDTO;
import com.mariaa.app.repositories.ClientRepository;
import com.mariaa.app.services.exceptions.DataIntegrityException;
import com.mariaa.app.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Client find(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente de código: " + id + " não encontrado, Tipo: " + Client.class.getName()));
	}

	public Client insert(Client object) {
		object.setId(null);
		return clientRepository.save(object);
	}

	public Client update(Client object) {
		Client clientDB = find(object.getId());
		updateData(clientDB, object);
		return clientRepository.save(clientDB);
	}
	
	// OBS: 1
	
	public void delete(Integer id) {
		find(id);
		try {
			clientRepository.deleteById(id);
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente por que há entidades relacionadas!");
		}
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public Page<Client> findPage(Integer page, Integer listPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, listPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO object) {
		return new Client(object.getId(), object.getName(), object.getEmail(), null, null);
	}
	
	private void updateData(Client clientDB, Client request) {
		clientDB.setName(request.getName());
		clientDB.setEmail(request.getEmail());
	}
}

// 1 - Não é permitido deletar um cliente que contém pedidos, porém um cliente que contenha apenas endereço será permitido deleta-lo.

