package pt.rumos.model;

import java.util.List;

public class BankAccount {
	private Long id;
	private Long accountNumber;
	private Customer owner;
	private List<Customer> secondaryOwners;
	private Double balance;

	public BankAccount(Long accountnumb,Customer owner, List<Customer> secOwners, Double balance) {
		setAccountNumber(accountnumb);
		setOwner(owner);
		setSecondaryOwners(secOwners);
		setBalance(balance);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public List<Customer> getSecondaryOwners() {
		return secondaryOwners;
	}

	public void setSecondaryOwners(List<Customer> secondaryOwners) {
		this.secondaryOwners = secondaryOwners;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", Owner=" + owner + ", secondaryOwners="
				+ secondaryOwners + ", balance=" + balance + "]";
	}
}
