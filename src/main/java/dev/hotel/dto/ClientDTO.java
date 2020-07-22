package dev.hotel.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClientDTO {
	
	/** nom */
	@NotNull
	@NotBlank
	private String nom;
	
	/** prenoms */
	@NotNull
	@NotBlank
	private String prenoms;

	/** Constructeur
	 * 
	 */
	public ClientDTO() {
	}

	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter
	 * @return the prenoms
	 */
	public String getPrenoms() {
		return prenoms;
	}

	/** Setter
	 * @param prenoms the prenoms to set
	 */
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}
	
	
	
	

}
