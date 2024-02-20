package pt.rumos.service;


import java.util.List;
import java.util.Optional;

import pt.rumos.exception.repository.ReposErrorException;
import pt.rumos.exception.repository.ReposObjNotFoundException;
import pt.rumos.model.DebitCard;
import pt.rumos.contract.IDebitCardRepository;
import pt.rumos.contract_serv.IDebitCardService;
import pt.rumos.repository.database.DebitCardRepository;

public class DebitCardService implements IDebitCardService {

	IDebitCardRepository debitCardRepository = new DebitCardRepository();

	@Override
	public DebitCard create(DebitCard debitCard) {
		Optional<DebitCard> debitCardOpt = debitCardRepository.save(debitCard);
		return debitCardOpt.orElseThrow(ReposErrorException::new);
	}

	@Override
	public DebitCard update(DebitCard debitCard) {
		Optional<DebitCard> debitCardOpt = debitCardRepository.save(debitCard);
		return debitCardOpt.orElseThrow(ReposErrorException::new);
	}

	@Override
	public DebitCard getById(Long id) {
		Optional<DebitCard> debitCardOpt = debitCardRepository.findById(id);
		return debitCardOpt.orElseThrow(ReposObjNotFoundException::new);
	}

	@Override
	public void deleteById(Long id) {
		debitCardRepository.deleteById(id);
	}

	@Override
	public List<DebitCard> getAll() {
		return debitCardRepository.findAll();
	}

}
