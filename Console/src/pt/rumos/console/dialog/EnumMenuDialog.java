package pt.rumos.console.dialog;

public enum EnumMenuDialog {

	//MAIN
	MANAGM_MENU_ACCOUNT("Account management"),
	MANAGM_MENU_BANKACCOUNT("Bank Account management"),
	MANAGM_MENU_CARDS("Cards management"),
	MASTER_MENU_MANAGEMENT ("Management"),
	MASTER_MENU_ATM ("ATM"),
	
	//CARDS
	CARD_MENU_CREDITC ("Credit Card management"),
	CARD_MENU_DEBITC ("Debit Card management"),
	
	CARD_MENU_ADD ("Create New Card"),
	CARD_MENU_UPDATE ("Update Card"),
	CARD_MENU_DELETE ("Delete by Card number"),
	CARD_MENU_FIND ("Find by Card number"),
	CARD_MENU_FINDALL("Find all Cards"),
	
	CARD_MENU_UPD_PIN ("Update Card PIN"),
	CARD_MENU_UPD_PLF ("Update Plafond"),
	
	
	//BANK ACCOUNT
	BANKACC_MENU_ADD ("Create New Bank Account"),
	BANKACC_MENU_UPDATE ("Update Bank Account by account number"),
	BANKACC_MENU_DELETE ("Delete Bank Account by account number"),
	BANKACC_MENU_FIND ("Find Bank Account by account number"),
	BANKACC_MENU_FINDALL ("Get all Bank Accounts"),
	
	BANKACC_UPD_MENU_OWNER ("Change Owner"),
	BANKACC_UPD_MENU_SECOWNER ("Change Secondary Owners"),
	BANKACC_UPD_MENU_BALC ("Change Balance"),
	
	//COSTUMER
	ACCMANG_MENU_ADD ("Add New Costumer"),
	ACCMANG_MENU_UPDATE ("Update Costumer by Nif"),
	ACCMANG_MENU_DELETE ("Delete Costumer by Nif"),
	ACCMANG_MENU_FIND ("Find Costumer by Nif"),
	ACCMANG_MENU_GETALL ("Get All Costumers"),
	
	ACCMANG_MENU_UPD_NAME ("Update Costumer Name"),
	ACCMANG_MENU_UPD_PSW ("Update Costumer Password"),
	AACCMANG_MENU_UPD_PHONE ("Update Costumer Email"),
	ACCMANG_MENU_UPD_MOBPHONE ("Update Costumer Phone"),
	ACCMANG_MENU_UPD_EMAIL ("Update Costumer Mobile Phone"),
	ACCMANG_MENU_UPD_OCCP ("Update Costumer Occupation"),
	
	//GENERAL
	MENU_INVALID_OPTION("Invalid option, please try again."),
	MENU_INVALID_INPUT("Invalid input, please try again."),
	MENU_EXIT("Exit"),
	MENU_EXIT_MESSAGE("Thank you, GoodBye."),
	MENU_CHOOSE("Choose a option: ");
	
	private String menuString;

	EnumMenuDialog(String string) {
		this.menuString = string;
	}
	
	public String getString() {
		return menuString;
	}
}
