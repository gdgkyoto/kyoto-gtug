package menu.dao;

public class DataAccessException extends Exception {

	/** �V���A���o�[�W����ID. */
	private static final long serialVersionUID = 6827738492736446787L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}
	
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
