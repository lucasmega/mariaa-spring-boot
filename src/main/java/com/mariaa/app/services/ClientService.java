package com.mariaa.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mariaa.app.domain.Address;
import com.mariaa.app.domain.City;
import com.mariaa.app.domain.Client;
import com.mariaa.app.domain.enums.CustomerType;
import com.mariaa.app.dto.ClientDTO;
import com.mariaa.app.dto.CostumerDTO;
import com.mariaa.app.repositories.AddressRepository;
import com.mariaa.app.repositories.ClientRepository;
import com.mariaa.app.services.exceptions.DataIntegrityException;
import com.mariaa.app.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

	public Client find(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente de código: " + id + " não encontrado, Tipo: " + Client.class.getName()));
	}

	@Transactional
	public Client insert(Client object) {
		object.setId(null);
		object = clientRepository.save(object);
		addressRepository.saveAll(object.getAdresses());
		return object;
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

	public Client fromDTO(ClientDTO dto) {
		return new Client(dto.getId(), dto.getName(), dto.getEmail(), null, null);
	}

	public Client fromDTO(CostumerDTO dto) {
		Client client = new Client(null, dto.getName(), dto.getEmail(), dto.getDocument(), CustomerType.toEnum(dto.getCustomerType()));
		City city = new City(dto.getCity(), null, null);
		Address address = new Address(null, dto.getStreet(), dto.getNumber(), dto.getComplement(), dto.getNeighborhood(), dto.getZipCode(), client, city);

		client.getAdresses().add(address);
		client.getPhones().add(dto.getCellphone());

		if (dto.getMainphone() != null) {
			client.getPhones().add(dto.getMainphone());
		}

		if (dto.getTelephone() != null) {
			client.getPhones().add(dto.getTelephone());
		}

		return client;
	}

	private void updateData(Client clientDB, Client request) {
		clientDB.setName(request.getName());
		clientDB.setEmail(request.getEmail());
	}
}

// 1 - Não é permitido deletar um cliente que contém pedidos, porém um cliente que contenha apenas endereço será permitido deleta-lo.
