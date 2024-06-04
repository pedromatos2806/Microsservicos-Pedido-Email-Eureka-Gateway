package com.pedidoms.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.pedidoms.entities.Cliente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ClienteRepositoryTests {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	@DisplayName("Deve devolver uma Lista com dois Pedros ")
	void TrazerTodosClientesComMesmoNomePedro() {
		cadastrarCliente("Pedro","pedrogabrielrochamatos@gmail.com");
		cadastrarCliente("Pedro","puppy.rocha.ssa@gmail.com");
		
		var listaClientes = clienteRepository.findAllByNome("Pedro");
		assertThat(listaClientes.stream()
				  .filter(cliente -> cliente.getEmail().equals("puppy.rocha.ssa@gmail.com") || cliente.getEmail().equals("pedrogabrielrochamatos@gmail.com"))
				  .count())
				  .isEqualTo(2);
		
	}
	
	@Test
	@DisplayName("Deve devolver uma Lista null ")
	void TrazerTodosClientesComMesmoNomeJoriskleiton() {
		var listaClientes = clienteRepository.findAllByNome("");
		assertThat(listaClientes).isNull();
	}
	
	private void cadastrarCliente(String nome, String email){
		em.persist(new Cliente(anyLong(),nome,email,null));
	}
	
	
	
}
