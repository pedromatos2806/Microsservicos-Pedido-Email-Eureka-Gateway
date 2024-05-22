package com.pedidoms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedidoms.dtos.ItemDto;
import com.pedidoms.services.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value="/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@Operation(description = "Consulta todos os itens")
	@ApiResponse(description = "traz um Page de ItemDto")
	@GetMapping
	public Page<ItemDto> listarItens(Pageable pag){
		return itemService.listarItens(pag);
	}
	
	@Operation(description = "Consulta todos os itens por nome sendo este enviado através do caminho da URI")
	@ApiResponse(description = "traz um ResponseEntity de uma Lista de ItemDto")
	@GetMapping(value = "/bynome/{nome}")
	public ResponseEntity<List<ItemDto>> listarItem(@PathVariable String nome){
		return itemService.listarItem(nome);
	}
	
	@Operation(description = "Adiciona um item passando um ItemDto")
	@ApiResponse(description = "traz uma ResponseEntity de ItemDto")
	@PostMapping
	public ResponseEntity<ItemDto> adicionarItem(@RequestBody ItemDto itemDto) {
		return itemService.adicionarItem(itemDto);
	}
	
	@Operation(description = "Adiciona uma lista passando uma Lista de ItemDto")
	@ApiResponse(description = "traz uma ResponseEntity da Lista de ItemDto")
	@PostMapping(value = "/adicionarItens")
	public ResponseEntity<List<ItemDto>> adicionarItens(@RequestBody List<ItemDto> itensDto){
		return itemService.adicionarItens(itensDto);
	}
	
	@Operation(description = "Atualiza um item na lista passando um id do item que deve ser modificado e o ItemDto que é como o novo elemento deverá ficar")
	@ApiResponse(description = "traz uma ResponseEntity de ItemDto")
	@PutMapping(value = "{id}")
	public ResponseEntity<ItemDto> atualizarItem(@PathVariable Long id,@RequestBody ItemDto itemDto){
		return itemService.atualizarItem(id, itemDto);
	}
	
}
