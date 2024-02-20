package pt.rumos.model;

import pt.rumos.generic.Card;

public class CreditCard extends Card {

	private Double plafond;

	private CreditCard(Long cardNumb, BankAccount ba, Customer c, String pin) {
		super(cardNumb, ba, c, pin);
	}

	public CreditCard(Long cardNumb, BankAccount ba, Customer c, String pin, Double pf) {
		this(cardNumb, ba, c, pin);
		setPlafond(pf);
	}

	public Double getPlafond() {
		return plafond;
	}

	public void setPlafond(Double plafond) {
		this.plafond = plafond;
	}

	@Override
	public String toString() {
		return super.toString() + " getPlafond()=" + getPlafond() + "";
	}

}
