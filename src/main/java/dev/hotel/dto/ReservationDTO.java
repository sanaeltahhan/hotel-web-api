package dev.hotel.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;

public class ReservationDTO {
	
	private UUID uuid;
	
	@NotNull
	private LocalDate dateDebut;
	@NotNull
	
    private LocalDate dateFin;
	
	@NotNull
	private Client client;
	
	@NotNull
	private List<Chambre> chambres;

	/** Getter
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/** Setter
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/** Getter
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/** Setter
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/** Getter
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/** Setter
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/** Getter
	 * @return the chambres
	 */
	public List<Chambre> getChambres() {
		return chambres;
	}

	/** Setter
	 * @param chambres the chambres to set
	 */
	public void setChambres(List<Chambre> chambres) {
		this.chambres = chambres;
	}

	/** Getter
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	/** Setter
	 * @param uuid the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	
	
	

}
