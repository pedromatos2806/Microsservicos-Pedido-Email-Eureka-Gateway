package com.pedidoms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedidoms.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	public List<Item> findAllByNome(String nome);
}
