package com.pedidoms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pedidoms.clients.EmailClient;
import com.pedidoms.clients.EmailDto;
import com.pedidoms.dtos.ClienteDto;
import com.pedidoms.entities.Cliente;
import com.pedidoms.repositories.ClienteRepository;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EmailClient emailClient;

	public ResponseEntity<ClienteDto> criarCliente(@Valid ClienteDto clienteDto) {
		Cliente cliente = new Cliente(clienteDto);
		clienteRepository.save(cliente);
		emailClient.sendEmail(new EmailDto("202221007@gmail.com", clienteDto.email(), "Cliente Criado com Sucesso",
				"Parabéns o cliente foi criado com sucesso!"));
		return ResponseEntity.ok(clienteDto);
	}

	public ResponseEntity<ClienteDto> atualizarCliente(Long id, @Valid ClienteDto clienteDto) {
		var clienteOp = clienteRepository.findById(id);
		if (!clienteOp.isPresent())
			ResponseEntity.notFound().build();
		var cliente = clienteOp.get();
		ClienteDto clienteDtoAtualizado = new ClienteDto(cliente);

		return ResponseEntity.ok(clienteDtoAtualizado);
	}

	public Page<ClienteDto> consultarClientes(Pageable pag) {
		return clienteRepository.findAll(pag).map(ClienteDto::new);
	}

	public ResponseEntity<List<ClienteDto>> consultarCliente(String nome) {
		var listaNomes = clienteRepository.findAllByNome(nome);
		if (listaNomes.isEmpty())
			ResponseEntity.notFound().build();

		return ResponseEntity
				.ok(clienteRepository.findAllByNome(nome).stream().map(ClienteDto::new).collect(Collectors.toList()));
	}

	public ResponseEntity<ClienteDto> consultarCliente(Long id) {
		var clienteOp = clienteRepository.findById(id);
		if (!clienteOp.isPresent())
			ResponseEntity.notFound().build();
		var cliente = clienteOp.get();

		ClienteDto clienteDto = new ClienteDto(cliente);
		return ResponseEntity.ok(clienteDto);
	}
}
