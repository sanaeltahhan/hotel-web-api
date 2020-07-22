package dev.hotel.exception;

public class ReservationException extends RuntimeException {

	/** Constructeur
	 * 
	 */
	public ReservationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** Constructeur
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ReservationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/** Constructeur
	 * @param message
	 * @param cause
	 */
	public ReservationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/** Constructeur
	 * @param message
	 */
	public ReservationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/** Constructeur
	 * @param cause
	 */
	public ReservationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
