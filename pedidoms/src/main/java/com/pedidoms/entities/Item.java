package com.pedidoms.entities;

import com.pedidoms.dtos.ItemDto;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "itens")
@EqualsAndHashCode(of = "id")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id", unique=true, nullable=false)
	private Long id;
	
	private String nome;
	
	public Item(ItemDto itemDto) {
		this.nome = itemDto.nome();
	}
}
