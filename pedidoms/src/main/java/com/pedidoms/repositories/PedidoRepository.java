package com.pedidoms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedidoms.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Long>{
	
	public List<Pedido> findAllByIdCliente(Long idCliente);

}
