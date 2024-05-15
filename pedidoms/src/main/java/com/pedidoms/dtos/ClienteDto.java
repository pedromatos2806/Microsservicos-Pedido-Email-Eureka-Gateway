package com.pedidoms.dtos;

import com.pedidoms.entities.Cliente;

public record ClienteDto(String nome, String email) {

	public ClienteDto(Cliente cliente) {
		this(cliente.getNome(),cliente.getEmail());
	}
}
