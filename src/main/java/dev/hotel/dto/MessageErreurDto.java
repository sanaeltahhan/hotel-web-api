package dev.hotel.dto;

public class MessageErreurDto {
	
	private CodeErreur code;
	
	private String message;

	/** Constructeur
	 * @param code
	 * @param message
	 */
	public MessageErreurDto(CodeErreur code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/** Getter
	 * @return the code
	 */
	public CodeErreur getCode() {
		return code;
	}

	/** Setter
	 * @param code the code to set
	 */
	public void setCode(CodeErreur code) {
		this.code = code;
	}

	/** Getter
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/** Setter
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
