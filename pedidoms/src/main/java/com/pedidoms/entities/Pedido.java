package com.pedidoms.entities;

import java.util.ArrayList;
import java.util.List;

import com.pedidoms.dtos.PedidoDto;
import com.pedidoms.enums.StatusPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_id", unique = true, nullable = false)
	private Long id;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@ManyToMany
	@JoinTable(name = "itens_pedidos"
				, joinColumns = @JoinColumn(name = "pedido_id")
				, inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<ItemPedido> itens = new ArrayList<>();

	private Long idCliente;

	
	public Pedido(PedidoDto dto) {
		setStatus(dto.status());
		setItens(dto.itens());
		setIdCliente(dto.idCliente());
	}
}
