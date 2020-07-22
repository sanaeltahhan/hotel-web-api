package dev.hotel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.CodeErreur;
import dev.hotel.dto.CreerReservationDTO;
import dev.hotel.dto.MessageErreurDto;
import dev.hotel.dto.ReservationDTO;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.exception.ReservationException;
import dev.hotel.repository.ReservationRepository;
import dev.hotel.service.ClientService;
import dev.hotel.service.ReservationService;

@RestController
public class ReservationCtrl {
	
	/** reservationService */
	private ReservationService reservationService;
	
	private ClientService clientService;
	
	private ReservationRepository resaRepo;

	/** Constructeur
	 * @param reservationService
	 */
	public ReservationCtrl(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	/*@GetMapping("/reservations")
	// GET /clients?start=X&size=Y
	public ResponseEntity<List<Reservation>> getReservation( 
			@RequestParam Integer start,
			@RequestParam Integer size ) {
		if (start <0 || size <=0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		else {
			List<Reservation> listResa = resaRepo.findAll(PageRequest.of(start, size)).toList();
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(listResa) ;
		}
		
	}*/
	
	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<String> onReservationException(ReservationException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	// POST /reservations
	@PostMapping("/reservations")
	public ResponseEntity<?> addNewResa(@RequestBody @Valid CreerReservationDTO resa, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErreurDto(CodeErreur.RESERVATION, "Error making reservation"));
		}
		// creation resa
		Reservation newResa = reservationService.creerResa(resa.getDateDebut(), resa.getDateFin(), resa.getClientUuid(), resa.getChambres());
		
		// transformation DTO
		ReservationDTO reservationDto = new ReservationDTO();
		reservationDto.setUuid(newResa.getUuid());
		reservationDto.setDateDebut(newResa.getDateDebut());
		reservationDto.setDateFin(newResa.getDateFin());
		reservationDto.setClient(newResa.getClient());
		reservationDto.setChambres(newResa.getChambres());
		
		return ResponseEntity.ok(reservationDto);
		}
	
	
	
	

	
}
