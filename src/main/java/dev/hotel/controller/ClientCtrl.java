package dev.hotel.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.ClientDTO;
import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;
import dev.hotel.service.ClientService;

@RestController
//@RequestMapping("clients")
public class ClientCtrl {
	
	private ClientRepository clientRipo;
	
	private ClientService clientService;
			
	
	/** Constructeur
	 * @param clientRipo
	 */
	public ClientCtrl(ClientRepository clientRipo, ClientService clientService) {
		super();
		this.clientRipo = clientRipo;
		this.clientService = clientService;
	}


	@GetMapping("clients")
	// GET /clients?start=X&size=Y
	public ResponseEntity<List<Client>> getClient( 
			@RequestParam Integer start,
			@RequestParam Integer size ) {
		if (start <0 || size <=0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		else {
			List<Client> listClient = clientRipo.findAll(PageRequest.of(start, size)).toList();
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(listClient) ;
		}
		
	}
	
	// GET /clients/UUID
	@GetMapping("clients/{uuid}")
	public ResponseEntity <Client> getClientByUUID(@PathVariable UUID uuid) {
		Optional <Client> client = clientRipo.findById(uuid);
		
		if( ! client.isPresent()) {
			return ResponseEntity.status(404).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(client.get());
		}
	}
	
	// POST /clients
	@PostMapping("/clients")
	public ResponseEntity<?> addNewClient(@RequestBody @Valid ClientDTO client, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Les champs nom et prenoms ne peuvent pas etre vide");
		}
		else {
			Client clientSauvegard = clientService.creerClient(client.getNom(), client.getPrenoms());
			return ResponseEntity.status(HttpStatus.OK).body(clientSauvegard);
		}
		
	    
	  }
	

}
