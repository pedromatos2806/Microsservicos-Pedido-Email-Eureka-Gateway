package com.pedidoms.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pedidoms.dtos.ItemDto;
import com.pedidoms.entities.Item;
import com.pedidoms.repositories.ItemRepository;
import com.pedidoms.validacoes.ValidacaoException;

import jakarta.validation.Valid;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Page<ItemDto> listarItens(Pageable pag){
		return itemRepository.findAll(pag).map(ItemDto::new);
	}
	
	public ResponseEntity<List<ItemDto>> listarItem(String nome) {
		List<Item> itens =  itemRepository.findAllByNome(nome);
		if(itens.isEmpty() || itens.equals(null))
			throw new ValidacaoException("Não há itens com o nome: " + nome);
		
		List<ItemDto> itensDto = itens.stream()
				.map(item -> new ItemDto(item.getNome()))
				.collect(Collectors.toList());
		return ResponseEntity.ok(itensDto);
	}
	
	public ResponseEntity<ItemDto> adicionarItem(@Valid ItemDto itemDto) {
		Item item = new Item(itemDto);
		var itemBuscado =  itemRepository.findAllByNome(itemDto.nome());
		if(!itemBuscado.isEmpty() || !itemBuscado.equals(null))
			throw new ValidacaoException("Já existe cadastro de item com o nome: " + itemDto.nome() +  " !");
		Item itemSalvo = itemRepository.save(item);
		
		return ResponseEntity.ok(new ItemDto(itemSalvo));
	}
	
	public ResponseEntity<List<ItemDto>> adicionarItens(@Valid List<ItemDto> itensDto){
		for (ItemDto itemDto : itensDto) {
			Item itemSalvar = new Item(itemDto);
			var itemBuscado =  itemRepository.findAllByNome(itemDto.nome());
			if(!itemBuscado.isEmpty() || !itemBuscado.equals(null))
				throw new ValidacaoException("Já existe cadastro de item com o nome: " + itemDto.nome() +  " !");
			itemRepository.save(itemSalvar);
		}
		return ResponseEntity.ok(itensDto);
		
	}
	
	public ResponseEntity<ItemDto> atualizarItem(Long id,@Valid ItemDto itemDto) {
		if(!itemRepository.existsById(id))
			throw new ValidacaoException("Não existe cadastro de item com esse id: " + id +  " !");
		Optional<Item> itemOp = itemRepository.findById(id);
		Item item = itemOp.get();
		item.setNome(itemDto.nome());
		itemRepository.save(item);
		return ResponseEntity.ok(itemDto);
	}
	
}
