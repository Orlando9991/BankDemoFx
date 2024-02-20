package pt.rumos.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.rumos.console.dialog.EnumInsertFieldsDialog;
import pt.rumos.console.dialog.EnumMenuDialog;
import pt.rumos.exception.repository.ReposErrorException;
import pt.rumos.exception.repository.ReposFullException;
import pt.rumos.exception.repository.ReposObjNotFoundException;
import pt.rumos.model.BankAccount;
import pt.rumos.model.Customer;
import pt.rumos.service.BankAccountService;
import pt.rumos.service.CustomerService;

public class BankAccountManagementMenu {

	private Scanner scanner;
	private BankAccountService bankAccountService;
	private CustomerService customerService;

	public BankAccountManagementMenu(Scanner sc, BankAccountService bas, CustomerService cs) {
		this.scanner = sc;
		this.bankAccountService = bas;
		this.customerService = cs;
	}

	public void showMenu() {
		int choiceNumber = 0;

		do {
			displayMenu();
			choiceNumber = scanner.nextInt();

			switch (choiceNumber) {
			case 1:
				createBankAccount();
				break;
			case 2:
				updateBankAccount();
				break;
			case 3:
				getBankAccount();
				break;
			case 4:
				getAllBankAccounts();
				break;
			case 5:
				deleteBankAccount();
				break;
			case 0: // Exit Menu
				break;
			default:
				System.out.println(EnumMenuDialog.MENU_INVALID_OPTION.getString());
			}

		} while (choiceNumber != 0);

	}

	private void displayMenu() {
		System.out.println("(1) - " + EnumMenuDialog.BANKACC_MENU_ADD.getString());
		System.out.println("(2) - " + EnumMenuDialog.BANKACC_MENU_UPDATE.getString());
		System.out.println("(3) - " + EnumMenuDialog.BANKACC_MENU_FIND.getString());
		System.out.println("(4) - " + EnumMenuDialog.BANKACC_MENU_FINDALL.getString());
		System.out.println("(5) - " + EnumMenuDialog.BANKACC_MENU_DELETE.getString());
		System.out.println("(0) - " + EnumMenuDialog.MENU_EXIT.getString());
		System.out.println(EnumMenuDialog.MENU_CHOOSE.getString());
	}

	private void displayUpdateMenu() {
		System.out.println("(1) - " + EnumMenuDialog.BANKACC_UPD_MENU_OWNER.getString());
		System.out.println("(2) - " + EnumMenuDialog.BANKACC_UPD_MENU_SECOWNER.getString());
		System.out.println("(3) - " + EnumMenuDialog.BANKACC_UPD_MENU_BALC.getString());
		System.out.println("(0) - " + EnumMenuDialog.MENU_EXIT.getString());
		System.out.println(EnumMenuDialog.MENU_CHOOSE.getString());
	}

	private void createBankAccount() {
		System.out.println(EnumInsertFieldsDialog.BANKACCM_FIELD_ACCOUNTNUM.getString());
		Long accountNumb = scanner.nextLong();
		
		System.out.println(EnumInsertFieldsDialog.BANKACCM_FIELD_OWNER.getString());
		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_NIF.getString());
		String nif = scanner.next();
		
		Customer cOwner;
		try {
			cOwner = customerService.getById(nif);
		} catch (ReposObjNotFoundException e) {
			e.getMessage();
			return;
		}

		List<Customer> secOwnersList = getSecundaryOwners();
		Double balance = getBalanceValue();
		BankAccount bankAccount = new BankAccount(accountNumb,cOwner, secOwnersList, balance);

		try {
			bankAccountService.create(bankAccount);
		} catch (ReposErrorException | ReposFullException e) {
			e.getMessage();
		}
	}

	private void updateBankAccount() {
		System.out.println(EnumInsertFieldsDialog.BANKACCM_FIELD_ACCOUNTNUM.getString());
		Long accountNumber = scanner.nextLong();

		BankAccount ba;
		try {
			ba = bankAccountService.getById(accountNumber);
		} catch (ReposObjNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}

		int choiceNumber = 0;

		do {
			displayUpdateMenu();
			choiceNumber = scanner.nextInt();

			switch (choiceNumber) {
			case 1:
				Customer c = getOwner();
				if(c==null) {
					return;
				}
				ba.setOwner(c);
				break;
			case 2:
				List<Customer> secOwnersList = getSecundaryOwners();
				ba.setSecondaryOwners(secOwnersList);
				break;
			case 3:
				Double balance = getBalanceValue();
				ba.setBalance(balance);
				break;
			case 0:
				break;
			default:
			}
		} while (choiceNumber != 0);

		try {
			bankAccountService.update(ba);
		} catch (ReposErrorException | ReposFullException e) {
			System.out.println(e.getMessage());
		}
	}

	private void deleteBankAccount() {
		System.out.println(EnumInsertFieldsDialog.BANKACCM_FIELD_ACCOUNTNUM.getString());
		Long accountNumber = scanner.nextLong();

		try {
			bankAccountService.deleteById(accountNumber);
		} catch (ReposObjNotFoundException e) {
			e.getMessage();
		};
	}

	private void getBankAccount() {
		System.out.println(EnumInsertFieldsDialog.BANKACCM_FIELD_ACCOUNTNUM.getString());
		Long accountNumber = scanner.nextLong();

		BankAccount ba;
		try {
			ba = bankAccountService.getById(accountNumber);
			System.out.println(ba);
		} catch (ReposObjNotFoundException e) {
			e.getMessage();
		}
	}
	
	private void getAllBankAccounts() {
		List<BankAccount> bankAccountsList = bankAccountService.getAll();
		if (!bankAccountsList.isEmpty()) {
			bankAccountsList.forEach(System.out::println);
		}
	}

	private Double getBalanceValue() {
		Double balance = 0d;
		do {
			System.out.println(EnumInsertFieldsDialog.BANKACCM_FIELD_BALANCE.getString());
			balance = scanner.nextDouble();
		} while (balance < 50);
		return balance;
	}
	
	private Customer getOwner() {
		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_NIF.getString());
		String nif = scanner.next();
		Customer c = null;
		try {
			c = customerService.getById(nif);
		} catch (ReposObjNotFoundException e) {
			e.getMessage();
		}
		return c;
	}

	private List<Customer> getSecundaryOwners() {
		int secundaryOwnersSize = 0;
		do {
			System.out.println(EnumInsertFieldsDialog.BANKACCM_FIELD_SEC_OWNERSIZE.getString());
			secundaryOwnersSize = scanner.nextInt();
		} while (secundaryOwnersSize < 0 || secundaryOwnersSize > 4);

		List<Customer> secOwnersList = new ArrayList<Customer>();
		String nif = "";

		for (int i = 0; i < secundaryOwnersSize; i++) {
			System.out.println(EnumInsertFieldsDialog.BANKACCM_FIELD_SEC_OWNER.getString());
			System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_NIF.getString());
			nif = scanner.next();
			Customer secOwner;
			try {
				secOwner = customerService.getById(nif);
			} catch (ReposObjNotFoundException e) {
				e.getMessage();
				i--;
				continue;
			}
			secOwnersList.add(secOwner);
		}

		return secOwnersList;
	}
}
