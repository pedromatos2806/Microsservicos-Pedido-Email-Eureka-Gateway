package com.pedidoms.entities;

import com.pedidoms.dtos.ClienteDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENTE_ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOME", unique = false, nullable = false)
	private String nome;

	@Column(name = "EMAIL", unique = false, nullable = false)
	@Email
	private String email;

	@Column(name = "Ativo", unique = false, nullable = true)
	private Boolean ativo;

	public Cliente(ClienteDto clienteDto) {
		setNome(clienteDto.nome());
		setEmail(clienteDto.email());
		if (getAtivo() == null)
			setAtivo(true);
	}
}
