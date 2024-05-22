package com.pedidoms.entities;

import com.pedidoms.dtos.ClienteDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "clientes")
@EqualsAndHashCode(of = "id")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id", unique=true, nullable=false)
	private Long id;
	private String nome;
	private String email;
	
	public Cliente(ClienteDto clienteDto) {
		setNome(clienteDto.nome());
		setEmail(clienteDto.email());
	}
}
