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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.hotel.controller.ClientCtrl;
import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;
import dev.hotel.service.ClientService;

@WebMvcTest(ClientCtrl.class)
public class ClientCtrlTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ClientRepository clientRepo;
	
	@MockBean
	ClientService clientService;
	
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
	
	// Test find client by UUID
	@Test
	public void findClientByUuidTest () throws Exception {
		Mockito.when(clientRepo.findById(UUID.fromString("dcf129f1-a2f9-47dc-8265-1d844244b192")))
		.thenReturn(Optional.of(listeClients.get(0)));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/clients/dcf129f1-a2f9-47dc-8265-1d844244b192"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value("dcf129f1-a2f9-47dc-8265-1d844244b192"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Odd"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.prenoms").value("Ross"));

		}
	
	// Test post clients
	@Test
	public void creerClientValid() throws Exception {
		
		Client clientTest = new Client();
		clientTest.setNom("Dumoulin");
		clientTest.setPrenoms("Georges");
		clientTest.setUuid(UUID.fromString("ddd123d1-a1d1-12dd-1234-1d123456d123"));
		
		String jsonBody = "{ \"nom\": \"Dumoulin\", \"prenoms\": \"Georges\" }";
		
		Mockito.when(clientService.creerClient("Dumoulin", "Georges")).thenReturn(clientTest);
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/clients").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(jsonBody))
		
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value("ddd123d1-a1d1-12dd-1234-1d123456d123"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Dumoulin"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.prenoms").value("Georges"));
		
	}
	
	

}
