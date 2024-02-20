package pt.rumos.console.dialog;

public enum EnumInsertFieldsDialog {
	
	//COSTUMER
	COSTM_FIELD_NIF ("Insert Costumer Nif"),
	COSTM_FIELD_PSW ("Insert Costumer Password"),
	COSTM_FIELD_NAME ("Insert Costumer Name"),
	COSTM_FIELD_PHONE ("Insert Costumer Phone Number"),
	COSTM_FIELD_MOBPHONE ("Insert Costumer Mobile Phone Number"),
	COSTM_FIELD_EMAIL ("Insert Costumer Email"),
	COSTM_FIELD_DAY_DOB ("Insert Costumer Day of Date of Birth"),
	COSTM_FIELD_MONTH_DOB ("Insert Costumer Month of Date of Birth"),
	COSTM_FIELD_YEAR_DOB ("Insert Costumer Year of Date of Birth"),
	COSTM_FIELD_JOB ("Insert Costumer Job"),
	
	//BANK ACCOUNT
	BANKACCM_FIELD_OWNER ("Owner"),
	BANKACCM_FIELD_SEC_OWNER ("Secondary Owner"),
	BANKACCM_FIELD_SEC_OWNERSIZE ("How many Secondary Owners you like to insert (0-4)"),
	BANKACCM_FIELD_BALANCE ("Insert a initial deposit (min: 50€)"),
	BANKACCM_FIELD_ACCOUNTNUM ("Insert Account number"),
	
	//CARDS
	CARD_FIELD_OWNER ("Card Owner"),
	CARD_FIELD_PIN ("Insert 4 digits PIN"),
	CARD_FIELD_PLAF ("Insert Plafond"),
	CARD_FIELD_CARDNUM ("Insert Card number");

	private String dialogString;

	EnumInsertFieldsDialog(String string) {
		this.dialogString = string;
	}
	
	public String getString() {
		return dialogString;
	}

}
