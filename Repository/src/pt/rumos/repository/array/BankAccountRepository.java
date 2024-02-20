package pt.rumos.repository.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.rumos.contract.IBankAccountRepository;
import pt.rumos.exception.repository.ReposObjNotFoundException;
import pt.rumos.model.BankAccount;



public class BankAccountRepository implements IBankAccountRepository {

	private BankAccount[] bankAccountsArray = new BankAccount[3];
	private static Long accountNumber = 1L; // keep tracking of id value

	@Override
	public Optional<BankAccount> save(BankAccount bankAccount) {
		// Find if we have space on array

		if (bankAccount.getId() == null) {
			return saveObj(bankAccount);
		}

		return updateObj(bankAccount);
	}

	@Override
	public Optional<BankAccount> saveObj(BankAccount bankAccount) {
		for (int i = 0; i < bankAccountsArray.length; i++) {
			if (bankAccountsArray[i] == null) {
				bankAccount.setAccountNumber(accountNumber++);
				bankAccountsArray[i] = bankAccount;
				return Optional.of(bankAccount);
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<BankAccount> updateObj(BankAccount bankAccount) {
		for (int i = 0; i < bankAccountsArray.length; i++) {
			if (bankAccountsArray[i] == null) {
				continue;
			}

			Long accountNum = bankAccount.getAccountNumber();
			Long indexedAccountNum = bankAccountsArray[i].getAccountNumber();
			if (accountNum.equals(indexedAccountNum)) {
				bankAccountsArray[i] = bankAccount;
				return Optional.of(bankAccount);
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<BankAccount> findById(Long id) {
		for (int i = 0; i < bankAccountsArray.length; i++) {
			if (bankAccountsArray[i] == null) {
				continue;
			}

			Long currentAccountNumb = bankAccountsArray[i].getAccountNumber();
			if (currentAccountNumb.equals(id)) {
				return Optional.of(bankAccountsArray[i]);
			}
		}
		return Optional.empty();
	}

	@Override
	public void deleteById(Long id) {
		for (int i = 0; i < bankAccountsArray.length; i++) {
			if (bankAccountsArray[i] == null) {
				continue;
			}
			Long currentAccountNumb = bankAccountsArray[i].getAccountNumber();

			if (currentAccountNumb.equals(id)) {
				bankAccountsArray[i] = null;
				return;
			}
		}
		throw new ReposObjNotFoundException();
	}

	@Override
	public List<BankAccount> findAll() {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();

		for (int i = 0; i < bankAccountsArray.length; i++) {
			if (bankAccountsArray[i] == null) {
				continue;
			}
			bankAccounts.add(bankAccountsArray[i]);
		}
		return bankAccounts;
	}
}
