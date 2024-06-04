package com.pedidoms.dtos;

import com.pedidoms.entities.Item;

public record ItemDto(String nome) {

	public ItemDto(Item item) {
		this(item.getNome());
	}
}
