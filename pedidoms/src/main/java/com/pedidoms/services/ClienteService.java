package com.pedidoms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pedidoms.clients.EmailClient;
import com.pedidoms.clients.EmailDto;
import com.pedidoms.dtos.ClienteDto;
import com.pedidoms.entities.Cliente;
import com.pedidoms.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EmailClient emailClient;
	
	public ResponseEntity<ClienteDto> criarCliente(ClienteDto clienteDto){
		Cliente cliente = new Cliente(clienteDto);
		var clienteSave = clienteRepository.save(cliente);
		if(clienteSave == null)
			return ResponseEntity.notFound().build();
		emailClient.sendEmail(new EmailDto("202221007@gmail.com"
											, clienteDto.email()
											,"Cliente Criado com Sucesso"
											, "Parabéns o cliente foi criado com sucesso!"));
		return ResponseEntity.ok(clienteDto);
	}
	
	public ResponseEntity<ClienteDto> atualizarCliente(Long id, ClienteDto clienteDto){
		Cliente cliente = clienteRepository.findById(id).get();
		if(cliente == null)
			return ResponseEntity.badRequest().build();
		
		return ResponseEntity.ok(null);
	}
	
	public List<ClienteDto> consultarClientes(){
		return clienteRepository.findAll().stream().map(ClienteDto::new).collect(Collectors.toList());
	}
	
	public List<ClienteDto> consultarCliente(String nome){
		return clienteRepository.findAllByNome(nome).stream().map(ClienteDto::new).collect(Collectors.toList());
	}
	
	public ClienteDto consultarCliente(Long id){
		var cliente =  clienteRepository.findById(id).get();
		ClienteDto clienteDto = new ClienteDto(cliente);
		return clienteDto;
	}
}
