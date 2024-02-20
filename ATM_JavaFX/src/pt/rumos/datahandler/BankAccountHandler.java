package pt.rumos.datahandler;

import pt.rumos.model.BankAccount;
import pt.rumos.service.BankAccountService;

public abstract class BankAccountHandler {
	private static final BankAccountService bankAccountService = new BankAccountService();
	
	public static void updateBankAccount(BankAccount bankAccount) {
		bankAccountService.update(bankAccount);
	}
	
	public static BankAccount getBankAccount(Long id) {
		return bankAccountService.getById(id);
	}

}
