package pt.rumos.service;

import java.util.List;
import java.util.Optional;

import pt.rumos.contract.IBankAccountRepository;
import pt.rumos.contract_serv.IBankAccountService;
import pt.rumos.exception.repository.ReposErrorException;
import pt.rumos.exception.repository.ReposObjNotFoundException;
import pt.rumos.model.BankAccount;
import pt.rumos.repository.database.BankAccountRepository;

public class BankAccountService implements IBankAccountService {
	
	private IBankAccountRepository bankAccountArrayRepository = new BankAccountRepository();

	@Override
	public BankAccount create(BankAccount bankAccount) {
		Optional<BankAccount> bankAccountOpt = bankAccountArrayRepository.save(bankAccount);
		return bankAccountOpt.orElseThrow(ReposErrorException::new);
	}

	@Override
	public BankAccount update(BankAccount bankAccount) {
		Optional<BankAccount> bankAccountOpt = bankAccountArrayRepository.save(bankAccount);
		return bankAccountOpt.orElseThrow(ReposErrorException::new);
	}

	@Override
	public BankAccount getById(Long id) {
		Optional<BankAccount> bankAccountOpt = bankAccountArrayRepository.findById(id);
		return bankAccountOpt.orElseThrow(ReposObjNotFoundException::new);
	}

	@Override
	public void deleteById(Long id) {
		bankAccountArrayRepository.deleteById(id);
	}

	@Override
	public List<BankAccount> getAll() {
		return bankAccountArrayRepository.findAll();
	}
}
