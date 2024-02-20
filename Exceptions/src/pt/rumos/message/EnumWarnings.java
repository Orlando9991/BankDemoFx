package pt.rumos.message;

public enum EnumWarnings {
	
	REPOS_FULL("The database is full."),
	REPOS_ERROR("Error, Something went wrong."),
	REPOS_NOTFOUND("Not found"),
	NIF_FOUND("NIF already exists");

	private String repoString;

	EnumWarnings(String string) {
		this.repoString = string;
	}
	
	public String getString() {
		return repoString;
	}
	
	

}
