package com.pedidoms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public List<PedidoDto> listarPedidos(){
		return pedidoService.listarPedidos();
	}
	@GetMapping(value="/{id}")
	public List<PedidoDto> listarPedidosDoCliente(@PathVariable Long idCliente){
		return pedidoService.listarPedidosDoCliente(idCliente);
	}
	@PutMapping(value="/{status}")
	public ResponseEntity<PedidoDto> atualizarStatus(@PathVariable StatusPedido status,@RequestBody Long idPedido){
		return pedidoService.atualizarStatus(status, idPedido);
	}
	@PostMapping
	public ResponseEntity<PedidoDto> criarPedido(@RequestBody PedidoDto dto){
		return pedidoService.criarPedido(dto);
	}
}
