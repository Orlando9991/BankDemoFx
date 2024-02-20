package pt.rumos.exception.repository;

import pt.rumos.message.EnumWarnings;

public class ReposFullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReposFullException() {
		super(EnumWarnings.REPOS_FULL.getString());
	}

	public ReposFullException(String message) {
		super(message);
	}
}
