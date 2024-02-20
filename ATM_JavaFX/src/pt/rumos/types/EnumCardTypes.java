package pt.rumos.types;

public enum EnumCardTypes {

	DEBITCARD("Debit Card"),
	CREDITCARD("Credit Card");


	private String cardString;

	EnumCardTypes(String string) {
		this.cardString = string;
	}
	
	public String getString() {
		return cardString;
	}
}
