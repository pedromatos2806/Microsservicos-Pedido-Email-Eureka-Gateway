package com.pedidoms.dtos;

import com.pedidoms.entities.Item;

import jakarta.validation.constraints.NotBlank;

public record ItemDto(@NotBlank String nome) {

	 public ItemDto(Item item) {
		 this(item.getNome());
	}
}
