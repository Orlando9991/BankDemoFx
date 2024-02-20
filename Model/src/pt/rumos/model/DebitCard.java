package pt.rumos.model;

import pt.rumos.generic.Card;

public class DebitCard extends Card {

	public DebitCard(Long cardNumb, BankAccount ba, Customer c, String pin) {
		super(cardNumb, ba, c, pin);
	}
}
