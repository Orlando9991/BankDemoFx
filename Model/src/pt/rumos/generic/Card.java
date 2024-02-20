package pt.rumos.generic;

import java.util.List;

import pt.rumos.model.BankAccount;
import pt.rumos.model.Customer;
import pt.rumos.model.DayStatus;

public abstract class Card {
	
	private Long id;
	private Long cardNumber;
	private BankAccount bankAccount;
	private Customer ownerCustomer;
	private String pin;
	private List<DayStatus> dayStatus;
	
	public Card(Long cardNumber,BankAccount ba, Customer c, String pin) {
		setCardNumber(cardNumber);
		setBankAccount(ba);
		setOwnerCustomer(c);
		setPin(pin);
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Customer getOwnerCustomer() {
		return ownerCustomer;
	}

	public void setOwnerCustomer(Customer ownerCustomer) {
		this.ownerCustomer = ownerCustomer;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
	
	public List<DayStatus> getDayStatus() {
		return dayStatus;
	}

	public void setDayStatus(List<DayStatus> dayStatus) {
		this.dayStatus = dayStatus;
	}


	@Override
	public String toString() {
		return "Card [id=" + id + ", cardNumber=" + cardNumber + ", bankAccount=" + bankAccount + ", ownerCustomer="
				+ ownerCustomer + ", pin=" + pin + ", dayStatus=" + dayStatus + "]";
	}
}
