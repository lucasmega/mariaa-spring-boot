package com.mariaa.app.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mariaa.app.domain.enums.CustomerType;
import com.mariaa.app.dto.CostumerDTO;
import com.mariaa.app.repositories.ClientRepository;
import com.mariaa.app.resources.exception.FieldMessage;
import com.mariaa.app.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, CostumerDTO> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) { }

	@Override
	public boolean isValid(CostumerDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getCustomerType().equals(CustomerType.PERSON.getCode()) && !BR.isValidCPF(dto.getDocument())) {
			list.add(new FieldMessage("document", "CPF inválido!"));
		}

		if (dto.getCustomerType().equals(CustomerType.LEGAL_PERSON.getCode()) && !BR.isValidCNPJ(dto.getDocument())) {
			list.add(new FieldMessage("document", "CNPJ inválido!"));
		}
		
		if (clientRepository.findByEmail(dto.getEmail()) != null) {
			list.add(new FieldMessage("email", "E-mail já existe!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
