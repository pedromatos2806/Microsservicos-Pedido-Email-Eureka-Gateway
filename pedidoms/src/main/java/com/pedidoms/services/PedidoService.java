package com.pedidoms.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pedidoms.dtos.PedidoDto;
import com.pedidoms.entities.Pedido;
import com.pedidoms.enums.StatusPedido;
import com.pedidoms.repositories.ItemRepository;
import com.pedidoms.repositories.PedidoRepository;

import com.pedidoms.validacoes.ValidacaoStatusPedido;

import jakarta.validation.Valid;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ItemRepository itemRepository;

	public Page<PedidoDto> listarPedidos(Pageable pag) {
		return pedidoRepository.findAll(pag).map(PedidoDto::new);
	}

	public ResponseEntity<List<PedidoDto>> listarPedidosDoCliente(Long idCliente) {
		if (!pedidoRepository.existsById(idCliente))
			ResponseEntity.notFound().build();
		return ResponseEntity.ok(pedidoRepository.findAllByIdCliente(idCliente).stream().map(PedidoDto::new)
				.collect(Collectors.toList()));
	}

	public ResponseEntity<PedidoDto> atualizarStatus(StatusPedido status, Long idPedido) {
		if (!ValidacaoStatusPedido.verificarStatusPedido(status))
			return ResponseEntity.notFound().build();

		Optional<Pedido> pedidoOp = pedidoRepository.findById(idPedido);

		if (!pedidoOp.isPresent())
			ResponseEntity.notFound().build();

		Pedido pedido = pedidoOp.get();

		pedido.setStatus(status);
		Pedido pedidoAtualizado = pedidoRepository.save(pedido);
		PedidoDto pedidoAtualizadoDto = new PedidoDto(pedidoAtualizado);
		return ResponseEntity.ok(pedidoAtualizadoDto);
	}

	public ResponseEntity<PedidoDto> criarPedido(@Valid PedidoDto pedidoDto) {
		verificarNomesItens(pedidoDto.nomesDosItens());

		Pedido pedido = new Pedido(pedidoDto);

		pedidoRepository.save(pedido);

		return ResponseEntity.ok(pedidoDto);
	}

	public void verificarNomesItens(List<String> listaDeNomes) {
		for (String nome : listaDeNomes) {
			var listaNomes = itemRepository.findByNome(nome);
			if (!listaNomes.isEmpty())
				ResponseEntity.notFound().build();
		}

	}

}
