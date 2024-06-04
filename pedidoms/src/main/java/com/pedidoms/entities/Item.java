package com.pedidoms.entities;

import com.pedidoms.dtos.ItemDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "itens")
@ToString
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOME", unique = false, nullable = true)
	private String nome;

	@Column(name = "ATIVO", unique = false, nullable = true)
	private Boolean ativo;

	public Item(ItemDto itemDto) {
		this.nome = itemDto.nome();
		if (this.ativo == null)
			this.ativo = true;
	}

}
