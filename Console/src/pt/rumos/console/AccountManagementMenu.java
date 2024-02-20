package pt.rumos.console;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import pt.rumos.console.dialog.EnumInsertFieldsDialog;
import pt.rumos.console.dialog.EnumMenuDialog;
import pt.rumos.exception.repository.ReposErrorException;
import pt.rumos.exception.repository.ReposFullException;
import pt.rumos.exception.repository.ReposObjNotFoundException;
import pt.rumos.model.Customer;
import pt.rumos.service.CustomerService;

public class AccountManagementMenu {

	private Scanner scanner;
	private CustomerService customerService;

	public AccountManagementMenu(Scanner sc, CustomerService customerServ) {
		this.scanner = sc;
		this.customerService = customerServ;
	}

	public void showMenu() {
		int choiceNumber = 0;

		do {
			displayMenu();
			choiceNumber = scanner.nextInt();

			switch (choiceNumber) {
			case 1:
				createCostumer();
				break;
			case 2:
				updateCostumer();
				break;
			case 3:
				findCostumerbyNif();
				break;
			case 4:
				deleteCostumerbyNif();
				break;
			case 5:
				findAllCostumers();
				break;
			case 0: // Exit Menu
				break;

			default:
				System.out.println(EnumMenuDialog.MENU_INVALID_OPTION.getString());
			}

		} while (choiceNumber != 0);

	}

	private void displayMenu() {
		System.out.println("(1) - " + EnumMenuDialog.ACCMANG_MENU_ADD.getString());
		System.out.println("(2) - " + EnumMenuDialog.ACCMANG_MENU_UPDATE.getString());
		System.out.println("(3) - " + EnumMenuDialog.ACCMANG_MENU_FIND.getString());
		System.out.println("(4) - " + EnumMenuDialog.ACCMANG_MENU_DELETE.getString());
		System.out.println("(5) - " + EnumMenuDialog.ACCMANG_MENU_GETALL.getString());
		System.out.println("(0) - " + EnumMenuDialog.MENU_EXIT.getString());
		System.out.println(EnumMenuDialog.MENU_CHOOSE.getString());
	}

	private void displayUpdateMenu() {
		System.out.println("(1) - " + EnumMenuDialog.ACCMANG_MENU_UPD_NAME.getString());
		System.out.println("(2) - " + EnumMenuDialog.ACCMANG_MENU_UPD_PSW.getString());
		System.out.println("(3) - " + EnumMenuDialog.AACCMANG_MENU_UPD_PHONE.getString());
		System.out.println("(4) - " + EnumMenuDialog.ACCMANG_MENU_UPD_MOBPHONE.getString());
		System.out.println("(5) - " + EnumMenuDialog.ACCMANG_MENU_UPD_EMAIL.getString());
		System.out.println("(5) - " + EnumMenuDialog.ACCMANG_MENU_UPD_OCCP.getString());
		System.out.println("(0) - " + EnumMenuDialog.MENU_EXIT.getString());
		System.out.println(EnumMenuDialog.MENU_CHOOSE.getString());
	}

	private void createCostumer() {

		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_NIF.getString());
		String nif = scanner.next();

		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_PSW.getString());
		String password = scanner.next();

		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_NAME.getString());
		String name = scanner.next();

		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_DAY_DOB.getString());
		int dayDbo = scanner.nextInt();
		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_MONTH_DOB.getString());
		int monthDbo = scanner.nextInt();
		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_YEAR_DOB.getString());
		int yearDbo = scanner.nextInt();
		LocalDate dbo = LocalDate.of(yearDbo, monthDbo, dayDbo);

		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_PHONE.getString());
		String phone = scanner.next();

		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_MOBPHONE.getString());
		String mobile = scanner.next();

		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_JOB.getString());
		String occupation = scanner.next();

		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_EMAIL.getString());
		String email = scanner.next();

		Customer newCostumer = new Customer(
				name,
				password,
				nif,
				email,
				occupation,
				phone,
				mobile,
				dbo,
				occupation);

		try {
			customerService.create(newCostumer);
		} catch (ReposErrorException | ReposFullException e) {
			System.out.println(e.getMessage());
		}

	}

	private void findAllCostumers() {
		List<Customer> customersList = customerService.getAll();

		if (!customersList.isEmpty()) {
			customersList.forEach(System.out::println);
		}
	}

	private void findCostumerbyNif() {

		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_NIF.getString());
		String nif = scanner.next();

		Customer customer;
		try {
			customer = customerService.getById(nif);
			System.out.println(customer);
		} catch (ReposObjNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	private void deleteCostumerbyNif() {

		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_NIF.getString());
		String nif = scanner.next();

		try {
			customerService.deleteById(nif);
		} catch (ReposObjNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void updateCostumer() {
		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_NIF.getString());
		String nif = scanner.next();

		Customer customer;
		try {
			customer = customerService.getById(nif);
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
				System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_NAME.getString());
				String name = scanner.next();
				customer.setName(name);
				break;
			case 2:
				System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_PSW.getString());
				String password = scanner.next();
				customer.setPassword(password);
				break;
			case 3:
				System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_EMAIL.getString());
				String email = scanner.next();
				customer.setEmail(email);
				break;
			case 4:
				System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_PHONE.getString());
				String phone = scanner.next();
				customer.setPhone(phone);
				break;
			case 5:
				System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_MOBPHONE.getString());
				String mobile = scanner.next();
				customer.setMobile(mobile);
				break;
			case 6:
				System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_JOB.getString());
				String occupation = scanner.next();
				customer.setOccupation(occupation);
				break;
			case 0:
				break;

			default:
			}

		} while (choiceNumber != 0);

		try {
			customerService.update(customer);
		} catch (ReposErrorException | ReposFullException e) {
			System.out.println(e.getMessage());
		}
	}

}
