package dev.hotel.controlleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.hotel.controller.ClientCtrl;
import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@WebMvcTest(ClientCtrl.class)
public class ClientCtrlTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ClientRepository clientRepo;
	
	private List<Client> listeClients = new ArrayList<Client>();
	
	@BeforeEach
	void Setup() {
		Client client = new Client("Odd", "Ross");
		client.setUuid(UUID.fromString("dcf129f1-a2f9-47dc-8265-1d844244b192"));
		listeClients.add(client);

		client = new Client("Don", "Duck");
		client.setUuid(UUID.fromString("f9a18170-9605-4fe6-83c8-d03a53e08bfe"));
		listeClients.add(client);
	}
	
	@Test
	public void findClientByUuidTest () throws Exception {
		Mockito.when(clientRepo.findById(UUID.fromString("dcf129f1-a2f9-47dc-8265-1d844244b192")))
		.thenReturn(Optional.of(listeClients.get(0)));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/clients/dcf129f1-a2f9-47dc-8265-1d844244b192"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value("dcf129f1-a2f9-47dc-8265-1d844244b192"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Odd"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.prenoms").value("Ross"));

		}
	
	

}
