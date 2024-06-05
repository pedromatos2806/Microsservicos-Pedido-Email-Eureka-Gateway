package com.pedidoms.dtos;

import com.pedidoms.entities.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDto(@NotBlank String nome,@Email String email) {

	public ClienteDto(Cliente cliente) {
		this(cliente.getNome(),cliente.getEmail());
	}
}
