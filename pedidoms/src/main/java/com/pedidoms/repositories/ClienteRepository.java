package com.pedidoms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedidoms.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public List<Cliente> findAllByNome(String nome);
}
