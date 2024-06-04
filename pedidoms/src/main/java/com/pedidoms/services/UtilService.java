package com.pedidoms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pedidoms.entities.Item;
import com.pedidoms.repositories.ItemRepository;

public class UtilService {
	
	@Autowired
	static ItemRepository itemRepository;
	 public static List<Item> copyProperties(List<String> nomesDosItens) {
	        List<Item> itens = new ArrayList<>();
	        for (String nome : nomesDosItens) {
				
	        	List<Item> listItem = itemRepository.findByNome(nome);

	            if (listItem != null) {
	            	listItem.forEach(itens::add);
	            } else {
	            	return null;
	            }
	        }
	        return itens;
	    }
	 
}
