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

import com.pedidoms.dtos.PedidoDto;
import com.pedidoms.enums.StatusPedido;
import com.pedidoms.services.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Operation(description = "Lista todos os pedidos")
	@ApiResponse(description = "traz uma Page de PedidoDto")
	@GetMapping
	public Page<PedidoDto> listarPedidos(Pageable pag){
		return pedidoService.listarPedidos(pag);
	}
	
	@Operation(description = "Lista os pedidos de um determinado cliente passando o id do Cliente")
	@ApiResponse(description = "traz uma Lista de PedidoDto")
	@GetMapping(value="/{id}")
	public ResponseEntity<List<PedidoDto>> listarPedidosDoCliente(@PathVariable Long idCliente){
		return pedidoService.listarPedidosDoCliente(idCliente);
	}
	
	@Operation(description = "Atualiza um pedido passando um Status e o id do Pedido")
	@ApiResponse(description = "traz uma ResponseEntity do PedidoDto")
	@PutMapping(value="/{status}")
	public ResponseEntity<PedidoDto> atualizarStatus(@PathVariable StatusPedido status, @RequestBody Long idPedido){
		return pedidoService.atualizarStatus(status, idPedido);
	}
	
	@Operation(description = "Cria um pedido passando um PedidoDto")
	@ApiResponse(description = "traz uma ResponseEntity do PedidoDto")
	@PostMapping
	public ResponseEntity<PedidoDto> criarPedido(@RequestBody PedidoDto dto){
		return pedidoService.criarPedido(dto);
	}
}
