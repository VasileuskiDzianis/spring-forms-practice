package by.htp.spring_tags.dao.exception;

public class DaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Exception e) {
		super(message, e);
	}

	public DaoException(Exception e) {
		super(e);
	}

}
