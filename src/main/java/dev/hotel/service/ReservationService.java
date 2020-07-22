package dev.hotel.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.exception.ReservationException;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ReservationRepository;

@Service
public class ReservationService {

	/** resaRepo */
	private ReservationRepository resaRepo;
	
	/** chambreRepo */
	private ChambreRepository chambreRepo;

	/** clientService */
	private ClientService clientService;

	/** Constructeur
	 * @param resaRepo
	 * @param chambreRepo
	 * @param clientService
	 */
	public ReservationService(ReservationRepository resaRepo, ChambreRepository chambreRepo,
			ClientService clientService) {
		super();
		this.resaRepo = resaRepo;
		this.chambreRepo = chambreRepo;
		this.clientService = clientService;
	}
	
	
	/**Methode qui créer une reservation
	 * @param dateDebut
	 * @param dateFin
	 * @param uuidClient
	 * @param chambres
	 * @return
	 */
	@Transactional
	public Reservation creerResa (LocalDate dateDebut, LocalDate dateFin, UUID uuidClient, List<UUID> chambres) {
		// Recup uuid client
		Client client = clientService.findClientByUuid(uuidClient)
				.orElseThrow(()-> new ReservationException("UUID du client non trouvé"));
		
		// Liste chambre
		List<Chambre> listChambre = new ArrayList<>();
		
		for(UUID uuid : chambres) {
			Chambre chambre = chambreRepo.findById(uuid)
					.orElseThrow(() -> new ReservationException("La chambre n'existe pas"));
			listChambre.add(chambre);
		}
		
		Reservation newResa = new Reservation();
		newResa.setDateDebut(dateDebut);
		newResa.setDateFin(dateFin);
		newResa.setClient(client);
		newResa.setChambres(listChambre);
		
		return resaRepo.save(newResa);
	}
}
