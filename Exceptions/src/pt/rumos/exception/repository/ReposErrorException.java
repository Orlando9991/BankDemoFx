package pt.rumos.exception.repository;

import pt.rumos.message.EnumWarnings;

public class ReposErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReposErrorException() {
		super(EnumWarnings.REPOS_ERROR.getString());
	}

	public ReposErrorException(String message) {
		super(message);
	}
}
