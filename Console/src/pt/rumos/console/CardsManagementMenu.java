package pt.rumos.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.rumos.console.dialog.EnumInsertFieldsDialog;
import pt.rumos.console.dialog.EnumMenuDialog;
import pt.rumos.contract_serv.IBankAccountService;
import pt.rumos.contract_serv.ICreditCardService;
import pt.rumos.contract_serv.ICustomerService;
import pt.rumos.contract_serv.IDebitCardService;
import pt.rumos.exception.repository.ReposErrorException;
import pt.rumos.exception.repository.ReposFullException;
import pt.rumos.exception.repository.ReposObjNotFoundException;
import pt.rumos.model.BankAccount;
import pt.rumos.model.CreditCard;
import pt.rumos.model.Customer;
import pt.rumos.model.DebitCard;
import pt.rumos.service.BankAccountService;
import pt.rumos.service.CreditCardService;
import pt.rumos.service.CustomerService;
import pt.rumos.service.DebitCardService;

public class CardsManagementMenu {

	private Scanner scanner;
	private ICreditCardService creditCardService;
	private IDebitCardService debitCardService;
	private IBankAccountService bankAccountService;
	private ICustomerService customerService;
	boolean isDebitCard;

	public CardsManagementMenu(Scanner sc, BankAccountService bankAccServ, CustomerService custoServ,
			CreditCardService creditCardServ,DebitCardService debitCardServ) {

		this.scanner = sc;
		this.bankAccountService = bankAccServ;
		this.customerService = custoServ;
		this.creditCardService = creditCardServ;
		this.debitCardService = debitCardServ;
	}

	public void showMenuType() {
		int choiceNumber = 0;

		do {
			displayMenuType();
			choiceNumber = scanner.nextInt();

			switch (choiceNumber) {
			case 1:
				chooseCardClass(CreditCard.class);
				showMenuOptions();
				break;
			case 2:
				chooseCardClass(DebitCard.class);
				showMenuOptions();
				break;
			case 0: // Exit Menu
				break;
			default:
				displayInvalidOption();
			}

		} while (choiceNumber != 0);

	}

	private void showMenuOptions() {
		int choiceNumber = 0;
		do {
			displayMenuOptions();
			choiceNumber = scanner.nextInt();

			switch (choiceNumber) {
			case 1:
				createCard();
				break;
			case 2:
				updateCard();
				break;
			case 3:
				deleteCard();
				break;
			case 4:
				findCard();
				break;
			case 5:
				findAllCards();
				break;
			case 0: // Exit Menu
				break;
			default:
				displayInvalidOption();
			}

		} while (choiceNumber != 0);
	}

	private void displayMenuType() {
		System.out.println("(1) - " + EnumMenuDialog.CARD_MENU_CREDITC.getString());
		System.out.println("(2) - " + EnumMenuDialog.CARD_MENU_DEBITC.getString());
		System.out.println("(0) - " + EnumMenuDialog.MENU_EXIT.getString());
		System.out.println(EnumMenuDialog.MENU_CHOOSE.getString());
	}

	private void displayMenuOptions() {
		System.out.println("(1) - " + EnumMenuDialog.CARD_MENU_ADD.getString());
		System.out.println("(2) - " + EnumMenuDialog.CARD_MENU_UPDATE.getString());
		System.out.println("(3) - " + EnumMenuDialog.CARD_MENU_DELETE.getString());
		System.out.println("(4) - " + EnumMenuDialog.CARD_MENU_FIND.getString());
		System.out.println("(5) - " + EnumMenuDialog.CARD_MENU_FINDALL.getString());
		System.out.println("(0) - " + EnumMenuDialog.MENU_EXIT.getString());
		System.out.println(EnumMenuDialog.MENU_CHOOSE.getString());
	}
	
	private void displayMenuUpdateCreditCard() {
		System.out.println("(1) - " + EnumMenuDialog.CARD_MENU_UPD_PIN.getString());
		System.out.println("(2) - " + EnumMenuDialog.CARD_MENU_UPD_PLF.getString());
		System.out.println("(0) - " + EnumMenuDialog.MENU_EXIT.getString());
		System.out.println(EnumMenuDialog.MENU_CHOOSE.getString());
	}
	
	private void displayMenuUpdateDebitCard() {
		System.out.println("(1) - " + EnumMenuDialog.CARD_MENU_UPD_PIN.getString());
		System.out.println("(0) - " + EnumMenuDialog.MENU_EXIT.getString());
		System.out.println(EnumMenuDialog.MENU_CHOOSE.getString());
	}
	
	private void displayInvalidOption() {
		System.out.println(EnumMenuDialog.MENU_INVALID_OPTION.getString());
	}
	
	private void displayInvalidInput() {
		System.out.println(EnumMenuDialog.MENU_INVALID_INPUT.getString());
	}
	
	private void chooseCardClass(Class<?> c) {
		isDebitCard = c.equals(DebitCard.class);
	}

	private void createCard() {
		System.out.println(EnumInsertFieldsDialog.CARD_FIELD_CARDNUM.getString());
		Long cardNumber = scanner.nextLong();
		
		System.out.println(EnumInsertFieldsDialog.BANKACCM_FIELD_ACCOUNTNUM.getString());
		Long accountNumber = scanner.nextLong();
		
		BankAccount ba;
		try {
			ba = bankAccountService.getById(accountNumber);
		} catch (ReposObjNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("[" + EnumInsertFieldsDialog.CARD_FIELD_OWNER.getString() + "]");
		System.out.println(EnumInsertFieldsDialog.COSTM_FIELD_NIF.getString());
		String nif = scanner.next();
		Customer cOwner;
		try {
			cOwner = customerService.getById(nif);
		} catch (ReposObjNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}

		String pin = insertPin();

		if (isDebitCard) {
			DebitCard debitCard = new DebitCard(cardNumber, ba, cOwner, pin);
			try {
				debitCardService.create(debitCard);
			} catch (ReposErrorException | ReposFullException e) {
				System.out.println(e.getMessage());
				return;
			}
		} else {
			double plafond = getPlafond();
			CreditCard creditCard = new CreditCard(cardNumber, ba, cOwner, pin, plafond);
			
			try {
				creditCardService.create(creditCard);
			} catch (ReposErrorException | ReposFullException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
	}

	private Long getId() {
		System.out.println(EnumInsertFieldsDialog.CARD_FIELD_CARDNUM.getString());
		Long cardId = scanner.nextLong();
		return cardId;
	}

	private void deleteCard() {
		Long cardId = getId();
		if (isDebitCard) {
			try {
				debitCardService.deleteById(cardId);
			} catch (ReposObjNotFoundException e) {
				System.out.println(e.getMessage());
				return;
			}
		} else {
			try {
				creditCardService.deleteById(cardId);
			} catch (ReposObjNotFoundException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
	}

	private void findCard() {

		Long cardId = getId();
		if (isDebitCard) {
			DebitCard dc;
			try {
				dc = debitCardService.getById(cardId);
			} catch (ReposObjNotFoundException e) {
				System.out.println(e.getMessage());
				return;
			}
			if (dc != null) {
				System.out.println(dc);
			}
		} else {
			CreditCard cc;
			try {
				cc = creditCardService.getById(cardId);
			} catch (ReposObjNotFoundException e) {
				System.out.println(e.getMessage());
				return;
			}
			if (cc != null) {
				System.out.println(cc);
			}
		}
	}
	
	private void findAllCards() {
		if (isDebitCard) {
			List<DebitCard> debitCardList = new ArrayList<>();
			try {
				debitCardList = debitCardService.getAll();
			} catch (ReposObjNotFoundException e) {
				System.out.println(e.getMessage());
				return;
			}
			if (!debitCardList.isEmpty()) {
				debitCardList.forEach(System.out::println);
			}
		} else {
			List<CreditCard> creditCardList = new ArrayList<>();
			try {
				creditCardList = creditCardService.getAll();
			} catch (ReposObjNotFoundException e) {
				System.out.println(e.getMessage());
				return;
			}
			if (!creditCardList.isEmpty()) {
				creditCardList.forEach(System.out::println);
			}
		}
	}
	
	private void updateCard() {

		Long cardId = getId();
		DebitCard dc = null;
		CreditCard cc = null;

		int choiceNumber = 0;

		do {
			if (isDebitCard) {
				try {
					dc = debitCardService.getById(cardId);
				} catch (ReposObjNotFoundException e) {
					System.out.println(e.getMessage());
					return;
				}
				displayMenuUpdateDebitCard();
			} else {
				try {
					cc = creditCardService.getById(cardId);
				} catch (ReposObjNotFoundException e) {
					System.out.println(e.getMessage());
					return;
				}
				displayMenuUpdateCreditCard();
			}

			choiceNumber = scanner.nextInt();

			switch (choiceNumber) {
			case 1:
				String newPin = insertPin();
				if (isDebitCard) {
					dc.setPin(newPin);
				} else {
					cc.setPin(newPin);
				}
				break;
			case 2:
				if (!isDebitCard) {
					double plafond = getPlafond();
					cc.setPlafond(plafond);
				} else {
					displayInvalidOption();
				}
				break;
			case 0: // Exit Menu
				break;
			default:
				displayInvalidOption();
			}

		} while (choiceNumber != 0);

		if (isDebitCard) {
			try {
				debitCardService.update(dc);
			} catch (ReposErrorException | ReposFullException e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {
				creditCardService.update(cc);
			} catch (ReposErrorException | ReposFullException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private Double getPlafond() {
		System.out.println(EnumInsertFieldsDialog.CARD_FIELD_PLAF.toString());
		double plafond = scanner.nextDouble();
		return plafond;
	}
	
	private String insertPin() {
		String pin;
		while (true) {
			System.out.println(EnumInsertFieldsDialog.CARD_FIELD_PIN.getString());
			pin = scanner.next();
			
			if (pin.length() == 4) {
				int value = Integer.parseInt(pin);
				if(value>-1 && value<10000) {
					break;
				}
			}
			displayInvalidInput();
		}
		return pin;
	}
}
