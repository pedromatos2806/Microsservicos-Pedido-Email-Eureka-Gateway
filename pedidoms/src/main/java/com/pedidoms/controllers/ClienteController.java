package com.pedidoms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedidoms.dtos.ClienteDto;
import com.pedidoms.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Operation(description = "Consulta todos os clientes")
	@ApiResponse(description = "traz um Page de ClienteDto")
	@GetMapping
	public Page<ClienteDto> consultarClientes(Pageable pag) {
		return clienteService.consultarClientes(pag);
	}

	@Operation(description = "Consulta todos os clientes por nome")
	@ApiResponse(description = "traz um ResponseEntity da Lista de ClienteDto")
	@GetMapping(value = "/byNome/{nome}")
	public ResponseEntity<List<ClienteDto>> consultarCliente(@PathVariable String nome) {
		return clienteService.consultarCliente(nome);
	}

	@Operation(description = "Consulta o cliente por id")
	@ApiResponse(description = "traz um ResponseEntity de ClienteDto")
	@GetMapping(value = "/byId/{id}")
	public ResponseEntity<ClienteDto> consultarCliente(@PathVariable Long id) {
		return clienteService.consultarCliente(id);
	}
	
	@Operation(description = "Atualiza um cliente já existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o ClienteDto"),
			@ApiResponse(responseCode = "400", description = "não há nenhum cliente com o id solicitado") })
	@PutMapping(value = "/{id}" )
	public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
		return clienteService.atualizarCliente(id, clienteDto);
	}

	@Operation(description = "Cria um cliente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o ClienteDto"),
			@ApiResponse(responseCode = "404", description = "Houve algum erro na hora de inserir no SGBD") })
	@PostMapping
	public ResponseEntity<ClienteDto> criarCliente(@RequestBody ClienteDto clienteDto) {
		return clienteService.criarCliente(clienteDto);
	}

}
