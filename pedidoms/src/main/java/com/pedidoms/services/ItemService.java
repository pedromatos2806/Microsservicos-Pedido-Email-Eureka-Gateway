package com.pedidoms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pedidoms.dtos.ItemDto;
import com.pedidoms.entities.Item;
import com.pedidoms.repositories.ItemRepository;

import jakarta.validation.Valid;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Page<ItemDto> listarItens(Pageable pag) {
		return itemRepository.findAll(pag).map(ItemDto::new);
	}

	public ResponseEntity<List<ItemDto>> listarItem(String nome) {
		List<Item> itens = itemRepository.findByNome(nome);
		if (itens.isEmpty())
			ResponseEntity.notFound().build();

		List<ItemDto> itensDto = itens.stream().map(item -> new ItemDto(item.getNome())).toList();
		return ResponseEntity.ok(itensDto);
	}

	public ResponseEntity<ItemDto> adicionarItem(ItemDto itemDto) {
		Item item = new Item(itemDto);
//		List<Item> itemBuscado = itemRepository.findByNome(itemDto.nome());
//		System.out.println(itemBuscado);
//		if (itemBuscado != null)
//			throw new ValidacaoException("Já existe cadastro de item com o nome: " + itemDto.nome() + " !");
		Item itemSalvo = itemRepository.save(item);

		return ResponseEntity.ok(new ItemDto(itemSalvo));
	}

	public ResponseEntity<List<ItemDto>> adicionarItens(List<ItemDto> itensDto) {
		for (ItemDto itemDto : itensDto) {
			Item itemSalvar = new Item(itemDto);
			List<Item> itemBuscado = itemRepository.findByNome(itemDto.nome());
			if (itemBuscado != null)
				ResponseEntity.notFound().build();
			itemRepository.save(itemSalvar);
		}
		return ResponseEntity.ok(itensDto);

	}

	public ResponseEntity<ItemDto> atualizarItem(Long id, @Valid ItemDto itemDto) {
		if (!itemRepository.existsById(id))
			ResponseEntity.notFound().build();
		Optional<Item> itemOp = itemRepository.findById(id);
		Item item = null;

		if (itemOp.isPresent()) {
			item = itemOp.get();
			item.setNome(itemDto.nome());

			itemRepository.save(item);
			return ResponseEntity.ok(itemDto);
		}
		return ResponseEntity.notFound().build();
	}

}
