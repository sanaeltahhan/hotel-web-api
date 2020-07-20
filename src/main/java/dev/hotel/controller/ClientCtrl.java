package dev.hotel.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@RestController
@RequestMapping("clients")
public class ClientCtrl {
	
	private ClientRepository clientRipo;
			
	
	/** Constructeur
	 * @param clientRipo
	 */
	public ClientCtrl(ClientRepository clientRipo) {
		super();
		this.clientRipo = clientRipo;
	}





	@GetMapping
	// GET /clients?start=X&size=Y
	public Page<Client> getClient( @RequestParam Integer start,
			@RequestParam Integer size ) {
		
		
		
		Page<Client> listClient = clientRipo.findAll(PageRequest.of(start, size));
		return listClient;
	}
	

}
