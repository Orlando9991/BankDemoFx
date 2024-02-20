package pt.rumos.datahandler;

import pt.rumos.generic.Card;
import pt.rumos.model.BankAccount;
import pt.rumos.model.CreditCard;
import pt.rumos.model.DebitCard;
import pt.rumos.service.CreditCardService;
import pt.rumos.service.DebitCardService;

public abstract class CardHandler {
	
	private final static CreditCardService creditCardService = new CreditCardService();
	private final static DebitCardService debitCardService = new DebitCardService();
	private static Card card;
	
	
	public static Card cardCheckValidation(Long id, Class<?> cardClass) {
			
		if (cardClass.equals(DebitCard.class)) {
			card = debitCardService.getById(id);
		} else {
			card = creditCardService.getById(id);
		}
		return card;
	}
	
	public static Card getCard() {
		return CardHandler.card;
	}
	
	public static void setNewPin(String newPin,  Class<?> cardClass) {
		card.setPin(newPin);
		
		if (cardClass.equals(DebitCard.class)) {
			card = debitCardService.update((DebitCard)card);
		} else {
			card = creditCardService.update((CreditCard)card);
		}
	}

	public static void withdrawAmount(double amount, Class<?> cardClass) {

		if (cardClass.equals(DebitCard.class)) {
			BankAccount bankAccount = card.getBankAccount();
			double currentBalance = bankAccount.getBalance();
			currentBalance -= amount;
			if (currentBalance >= 0) {
				bankAccount.setBalance(currentBalance);
				BankAccountHandler.updateBankAccount(bankAccount);
			} else {
				throw new RuntimeException("Amount not permited");
			}
		} else {
			double currentPlafond = ((CreditCard) card).getPlafond();
			currentPlafond -= amount;
			if (currentPlafond >= 0) {
				((CreditCard) card).setPlafond(currentPlafond);
				card = creditCardService.update((CreditCard) card);
			} else {
				throw new RuntimeException("Amount not permited");
			}
		}
	}
	
	public static void transferAmount(double amount, BankAccount externalBankAccount, Class<?> cardClass) {
		
		BankAccount bankAccount = card.getBankAccount();
		double currentBalance;
		if (cardClass.equals(DebitCard.class)) {
			currentBalance = bankAccount.getBalance();	
		}else {
			currentBalance = ((CreditCard)card).getPlafond();
		}
		currentBalance -= amount;
		if(bankAccount.getAccountNumber().equals(externalBankAccount.getAccountNumber())) {
			throw new RuntimeException("Transfer to the Same Account");
		}
		if (currentBalance >= 0) {
			bankAccount.setBalance(currentBalance);
			double externalBaBalance = externalBankAccount.getBalance();
			externalBaBalance +=amount;
			externalBankAccount.setBalance(externalBaBalance);
			BankAccountHandler.updateBankAccount(bankAccount);
			BankAccountHandler.updateBankAccount(externalBankAccount);
		} else {
			throw new RuntimeException("Amount not permited");
		}
	}

	
}
