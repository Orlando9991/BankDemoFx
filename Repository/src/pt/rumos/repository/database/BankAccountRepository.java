package pt.rumos.repository.database;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import pt.rumos.contract.IBankAccountRepository;
import pt.rumos.database.Database;
import pt.rumos.model.BankAccount;
import pt.rumos.model.Customer;
import pt.rumos.repository.database.commands.DbCommands;
import pt.rumos.repository.database.convert.DbConvert;

public class BankAccountRepository implements IBankAccountRepository {
	
	private Database database = Database.getInstance();
	
	@Override
	public Optional<BankAccount> save(BankAccount bankAccount) {
		if(findById(bankAccount.getAccountNumber()).isEmpty()) {
			return saveObj(bankAccount);
		}
		else {
			return updateObj(bankAccount);
		}
	}
	
	@Override
	public Optional<BankAccount> saveObj(BankAccount bankAccount) {
		
		List<Customer> secundaryOwners = bankAccount.getSecondaryOwners();
		Customer owner = bankAccount.getOwner();
		
		if(database.executeCommand(DbCommands.insertBankAccountCommand(
				bankAccount.getAccountNumber(),
				owner,
				true,
				bankAccount))){
			
			for (Customer secOwner : secundaryOwners) {
				database.executeCommand(DbCommands.insertBankAccountCommand(
						bankAccount.getAccountNumber(),
						secOwner,
						false,
						bankAccount));
			}
			return Optional.of(bankAccount);
		}

		return Optional.empty();
	}
	
	@Override
	public Optional<BankAccount> updateObj(BankAccount bankAccount) {
		List<Customer> secundaryOwners = bankAccount.getSecondaryOwners();
		Customer owner = bankAccount.getOwner();
		if(database.executeCommand(DbCommands.updateBankAccountCommand(owner, true, bankAccount))){
			for (Customer secOwner : secundaryOwners) {
				database.executeCommand(DbCommands.updateBankAccountCommand(secOwner, false, bankAccount));
			}
			return Optional.of(bankAccount);
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<BankAccount> findById(Long id) {
		String query = DbCommands.findBankAccountQuery(id);
		List<HashMap<String,Object>> rs = database.executeQueryMultiRow(query);
		
		return DbConvert.extractBankAccountObject(rs);
	}
	
	@Override
	public void deleteById(Long id) {
		String query = DbCommands.deleteBankAccountCommand(id);
		database.executeCommand(query);
	}
	
	@Override
	public List<BankAccount> findAll() {
		String query = DbCommands.findAllBankAccountCommand();
		List<HashMap<String,Object>> rs = database.executeQueryMultiRow(query);
		List<BankAccount> bankAccountsList = DbConvert.extractBankAccountObjectList(rs);
		
		return bankAccountsList;
	}
}
