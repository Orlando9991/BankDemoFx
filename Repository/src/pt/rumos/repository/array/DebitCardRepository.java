package pt.rumos.repository.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.rumos.contract.IDebitCardRepository;
import pt.rumos.exception.repository.ReposObjNotFoundException;
import pt.rumos.model.DebitCard;

public class DebitCardRepository implements IDebitCardRepository {

	DebitCard[] debitCards = new DebitCard[3];
	private static Long debitCardId = 1l;

	@Override
	public Optional<DebitCard> save(DebitCard card) {
		if (card.getId() == null) {
			return saveObj(card);
		}
		return updateObj(card);
	}

	@Override
	public Optional<DebitCard> saveObj(DebitCard card) {
		for (int i = 0; i < debitCards.length; i++) {
			if (debitCards[i] == null) {
				card.setId(debitCardId++);
				debitCards[i] = card;
				return Optional.of(card);
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<DebitCard> updateObj(DebitCard card) {
		for (int i = 0; i < debitCards.length; i++) {
			if (debitCards[i] == null) {
				continue;
			}
			if (card.getId().equals(debitCards[i].getId())) {
				debitCards[i] = card;
				return Optional.of(card);
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<DebitCard> findById(Long id) {
		for (int i = 0; i < debitCards.length; i++) {
			if (debitCards[i] == null) {
				continue;
			}
			if (debitCards[i].getId().equals(id)) {
				return Optional.of(debitCards[i]);
			}
		}
		return Optional.empty();
	}

	@Override
	public void deleteById(Long id) {
		for (int i = 0; i < debitCards.length; i++) {
			if (debitCards[i] == null) {
				continue;
			}
			if (debitCards[i].getId().equals(id)) {
				debitCards[i] = null;
				return;
			}
		}
		throw new ReposObjNotFoundException();

	}

	@Override
	public List<DebitCard> findAll() {
		List<DebitCard> debitCardsList = new ArrayList<DebitCard>();
		for (int i = 0; i < debitCards.length; i++) {
			if (debitCards[i] == null) {
				continue;
			}
			debitCardsList.add(debitCards[i]);
		}
		return debitCardsList;
	}

}
