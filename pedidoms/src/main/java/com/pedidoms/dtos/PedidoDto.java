package com.pedidoms.dtos;

import java.util.List;

import com.pedidoms.entities.ItemPedido;
import com.pedidoms.entities.Pedido;
import com.pedidoms.enums.StatusPedido;

public record PedidoDto(StatusPedido status, List<ItemPedido> itens, Long idCliente) {

	public PedidoDto(Pedido pedido) {
		this(pedido.getStatus(),pedido.getItens(), pedido.getIdCliente());
	}
}
