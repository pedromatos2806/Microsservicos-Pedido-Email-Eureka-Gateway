package com.pedidoms.controllers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.BDDMockito.then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pedidoms.dtos.ClienteDto;

@SpringBootTest
public class ClienteControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JacksonTester<ClienteDto> clienteDtoJson;

	@MockBean
	private ClienteController controller;

	void test() throws Exception {
		ClienteDto clienteDto = new ClienteDto("teste", "teste@hotmail.com");
		
		var json = clienteDtoJson.write(clienteDto).getJson();
		
		this.mockMvc.perform(post("/cliente").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
		
		then(this.controller).should().criarCliente(clienteDto);
	}
}
