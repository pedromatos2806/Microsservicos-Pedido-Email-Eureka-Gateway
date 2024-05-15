package com.pedidoms.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pedidoms.dtos.PedidoDto;
import com.pedidoms.entities.Pedido;
import com.pedidoms.enums.StatusPedido;
import com.pedidoms.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	public List<PedidoDto> listarPedidos(){
		return pedidoRepository.findAll().stream().map(PedidoDto::new).collect(Collectors.toList());
	}
	
	public List<PedidoDto> listarPedidosDoCliente(Long idCliente){
		return pedidoRepository.findAllByIdCliente(idCliente).stream().map(PedidoDto::new).collect(Collectors.toList());
	}
	
	public ResponseEntity<PedidoDto> atualizarStatus(StatusPedido status, Long idPedido) {
		if(!status.equals(StatusPedido.Solicitado) || !status.equals(StatusPedido.Cancelado) || !status.equals(StatusPedido.Transito) || !status.equals(StatusPedido.Recebido))
			return ResponseEntity.notFound().build();
		
		Pedido pedido = pedidoRepository.findById(idPedido).get();
		
		if(pedido == null)
			return ResponseEntity.notFound().build();
		
		pedido.setStatus(status);
		Pedido pedidoAtualizado = pedidoRepository.save(pedido);
		PedidoDto pedidoAtualizadoDto = new PedidoDto(pedidoAtualizado);
		return ResponseEntity.ok(pedidoAtualizadoDto);
	}
	
	public ResponseEntity<PedidoDto> criarPedido(PedidoDto dto) {
		Pedido pedido = new Pedido(dto);
		var ped = pedidoRepository.save(pedido);
		
		if(ped == null)
			ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(dto);
	}
}
