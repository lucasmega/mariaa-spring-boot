package com.mariaa.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariaa.app.domain.Client;
import com.mariaa.app.repositories.ClientRepository;
import com.mariaa.app.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client find(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente de código: " + id + " não encontrado, Tipo: " + Client.class.getName()));
	}
}
