package pt.rumos.repository.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.rumos.contract.ICreditCardRepository;
import pt.rumos.exception.repository.ReposObjNotFoundException;
import pt.rumos.model.CreditCard;

public class CreditCardRepository implements ICreditCardRepository {
	CreditCard[] creditCards = new CreditCard[3];
	private static Long creditCardId = 1l;

	@Override
	public Optional<CreditCard> save(CreditCard card) {
		if (card.getId() == null) {
			return saveObj(card);
		}
		return updateObj(card);
	}

	@Override
	public Optional<CreditCard> saveObj(CreditCard card) {
		for (int i = 0; i < creditCards.length; i++) {
			if (creditCards[i] == null) {
				card.setId(creditCardId++);
				creditCards[i] = card;
				return Optional.of(card);
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<CreditCard> updateObj(CreditCard card) {
		for (int i = 0; i < creditCards.length; i++) {
			if (creditCards[i] == null) {
				continue;
			}
			if (card.getId().equals(creditCards[i].getId())) {
				creditCards[i] = card;
				return Optional.of(card);
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<CreditCard> findById(Long id) {
		for (int i = 0; i < creditCards.length; i++) {
			if (creditCards[i] == null) {
				continue;
			}
			if (creditCards[i].getId().equals(id)) {
				return Optional.of(creditCards[i]);
			}
		}
		return Optional.empty();
	}

	@Override
	public void deleteById(Long id) {
		for (int i = 0; i < creditCards.length; i++) {
			if (creditCards[i] == null) {
				continue;
			}
			if (creditCards[i].getId().equals(id)) {
				creditCards[i] = null;
				return;
			}
		}
		throw new ReposObjNotFoundException();
	}

	@Override
	public List<CreditCard> findAll() {
		List<CreditCard> creditCardsList = new ArrayList<CreditCard>();
		for (int i = 0; i < creditCards.length; i++) {
			if (creditCards[i] == null) {
				continue;
			}
			creditCardsList.add(creditCards[i]);
		}
		return creditCardsList;
	}
}
