package pt.rumos.service;

import java.util.List;
import java.util.Optional;

import pt.rumos.exception.repository.ReposErrorException;
import pt.rumos.exception.repository.ReposObjNotFoundException;
import pt.rumos.model.CreditCard;
import pt.rumos.contract.ICreditCardRepository;
import pt.rumos.contract_serv.ICreditCardService;
import pt.rumos.repository.database.CreditCardRepository;

public class CreditCardService implements ICreditCardService {
	ICreditCardRepository creditCardRepository = new CreditCardRepository();

	@Override
	public CreditCard create(CreditCard creditCard) {
		Optional<CreditCard> creditCardOpt = creditCardRepository.save(creditCard);
		return creditCardOpt.orElseThrow(ReposErrorException::new);
	}

	@Override
	public CreditCard update(CreditCard creditCard) {
		Optional<CreditCard> creditCardOpt = creditCardRepository.save(creditCard);
		return creditCardOpt.orElseThrow(ReposErrorException::new);
	}

	@Override
	public CreditCard getById(Long id) {
		Optional<CreditCard> creditCardOpt = creditCardRepository.findById(id);
		return creditCardOpt.orElseThrow(ReposObjNotFoundException::new);
	}

	@Override
	public void deleteById(Long id) {
		creditCardRepository.deleteById(id);
	}

	@Override
	public List<CreditCard> getAll() {
		return creditCardRepository.findAll();
	}

}
