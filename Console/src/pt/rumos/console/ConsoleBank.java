package pt.rumos.console;

import java.util.Scanner;

import pt.rumos.console.dialog.EnumMenuDialog;
import pt.rumos.service.*;

public class ConsoleBank {

	private static ConsoleBank consoleBank;

	Scanner scanner = new Scanner(System.in);
	
	private CustomerService customerService = new CustomerService();
	private BankAccountService bankAccountService = new BankAccountService();
	private CreditCardService creditCardService = new CreditCardService();
	private DebitCardService debitCardService = new DebitCardService();
	
	private AccountManagementMenu accountManagementeMenu = new AccountManagementMenu(scanner, customerService);
	
	private BankAccountManagementMenu bankAccountManagementMenu = new BankAccountManagementMenu(scanner,
			bankAccountService, customerService);
	
	private CardsManagementMenu cardsManagementMenu = new CardsManagementMenu(scanner, bankAccountService,
			customerService,creditCardService ,debitCardService);

	private ConsoleBank() {
		mainMenu();
	}
	
	public static ConsoleBank getInstance() {
		if(consoleBank==null) {
			consoleBank = new ConsoleBank();
		}
		return consoleBank;
	}


	private void mainMenu() {

		boolean exit = false;
		int choiceNumber = 0;

		do {

			displayMainMenu();
			choiceNumber = scanner.nextInt();

			switch (choiceNumber) {
			case 1: // Account Management
				managementMenu();

				break;
			case 2: // ATM
				break;

			case 0:
				exit = true;
				break;

			default:
				System.out.println(EnumMenuDialog.MENU_INVALID_OPTION.getString());
			}

		} while (!exit);

		System.out.println(EnumMenuDialog.MENU_EXIT_MESSAGE.getString());
	}

	private void managementMenu() {

		int choiceNumber = 0;
		
		do {
			displayManagementMenu();
			choiceNumber = scanner.nextInt();
			
			switch (choiceNumber) {
			case 1: 
				accountManagementeMenu.showMenu();
				break;
			case 2:  
				bankAccountManagementMenu.showMenu();
				break;
			case 3:
				cardsManagementMenu.showMenuType();
				break;
			case 0:	//Exit to Main Menu
				break;

			default:
				System.out.println(EnumMenuDialog.MENU_INVALID_OPTION.getString());
			}	
			
		} while (choiceNumber!=0);
	}
	
	private void displayMainMenu() {
		System.out.println("(1) - " + EnumMenuDialog.MASTER_MENU_MANAGEMENT.getString());
		System.out.println("(2) - " + EnumMenuDialog.MASTER_MENU_ATM.getString());
		System.out.println("(0) - " + EnumMenuDialog.MENU_EXIT.getString());
		System.out.println(EnumMenuDialog.MENU_CHOOSE.getString());
	}
	
	private void displayManagementMenu() {
		System.out.println("(1) - " + EnumMenuDialog.MANAGM_MENU_ACCOUNT.getString());
		System.out.println("(2) - " + EnumMenuDialog.MANAGM_MENU_BANKACCOUNT.getString());
		System.out.println("(3) - " + EnumMenuDialog.MANAGM_MENU_CARDS.getString());
		System.out.println("(0) - " + EnumMenuDialog.MENU_EXIT.getString());
		System.out.println(EnumMenuDialog.MENU_CHOOSE.getString());
	}
	

}
