package pt.rumos.exception.repository;

import pt.rumos.message.EnumWarnings;

public class ReposObjNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReposObjNotFoundException() {
		super(EnumWarnings.REPOS_NOTFOUND.getString());
	}

	public ReposObjNotFoundException(String message) {
		super(message);
	}
}
