package dev.hotel.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@Service
public class ClientService {
	
	private ClientRepository clientRepo;
	
	/**
	 * Constructeur
	 * 
	 * @param repo
	 */
	public ClientService(ClientRepository clientRepo) {
		super();
		this.clientRepo = clientRepo;
	} 

	@Transactional
	public Client creerClient (String nom, String prenoms) {
		Client newClient = new Client();		
		newClient.setNom(nom);
		newClient.setPrenoms(prenoms);
		
		return clientRepo.save(newClient);
	}
	
	/** Affiche le client selon son uuid
	 * @param uuid
	 * @return optional de client
	 */
	public Optional<Client> findClientByUuid (UUID uuid) {
		return clientRepo.findById(uuid);
		
	}
	
}
