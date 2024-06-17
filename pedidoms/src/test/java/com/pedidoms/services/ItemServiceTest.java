package com.pedidoms.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.pedidoms.dtos.ItemDto;
import com.pedidoms.entities.Item;
import com.pedidoms.repositories.ItemRepository;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

	@InjectMocks
	ItemService itemService;

	@Mock
	private ItemRepository itemRepository;

	@DisplayName("Cenário montado para adicionar dois itens a lista")
	List<Item> montandoCenarioComListaDeItensParaOMockito() {
		// ARRANGE
		Item item1 = new Item();
		item1.setNome("Item1");

		Item item2 = new Item();
		item2.setNome("Item2");

		List<Item> itens = List.of(item1, item2);
		return itens;
	}

	@DisplayName("Cenário montado para adicionar um itemDto")
	ItemDto montandoCenarioComItemDtoParaOMockito() {
		ItemDto itemDto = new ItemDto("Iphone");

		return itemDto;
	}

	@DisplayName("Verificando o método ListarItens ")
	@Test
	void testListarItens() {

		// Arrange (preparar)
		List<Item> itens = montandoCenarioComListaDeItensParaOMockito();
		Page<Item> page = new PageImpl<>(itens);
		Pageable pageable = PageRequest.of(0, 2);

		when(itemRepository.findAll(pageable)).thenReturn(page);
		// BDDMockito.given(itemRepository.findAll(pageable)).willReturn(page);

		// Act (agir)
		Page<ItemDto> result = itemService.listarItens(pageable);

		// Assert (assegurar)
		assertNotNull(result);
		assertEquals(2, result.getSize());
		assertEquals(2, result.getTotalElements());
	}

	@DisplayName("Verificando o método ListarItens por nome")
	@Test
	void testListarItem() {
		List<Item> itens = montandoCenarioComListaDeItensParaOMockito();

		when(itemRepository.findByNome("Item1")).thenReturn(itens);
	

		ResponseEntity<List<ItemDto>> result = itemService.listarItem("Item1");

		assertNotNull(result);
		List<ItemDto> body = result.getBody();

		assertNotNull(body);

		assertEquals("Item1", body.get(0).nome());
	}

	@Test
	void testAdicionarItem() {
		ItemDto itemDto = montandoCenarioComItemDtoParaOMockito();
		
		Item item = new Item(itemDto);
		
		when(itemRepository.save(item)).thenReturn(item);
		
		assertEquals(ResponseEntity.ok(new ItemDto(item)), itemService.adicionarItem(itemDto));
	}

	@Test
	void testAdicionarItens() {
		fail("Not yet implemented");
	}

	@Test
	void testAtualizarItem() {
		fail("Not yet implemented");
	}

}
