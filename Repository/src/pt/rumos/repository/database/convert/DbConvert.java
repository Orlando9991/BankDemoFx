package pt.rumos.repository.database.convert;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import pt.rumos.database.Database;
import pt.rumos.exception.repository.ReposObjNotFoundException;
import pt.rumos.generic.Card;
import pt.rumos.model.*;
import pt.rumos.repository.database.commands.DbCommands;

public abstract class DbConvert {

	public static Optional<CreditCard> extractCreditCardObject(HashMap<String, Object> row) {
		CreditCard creditCard = (CreditCard) extractCardObject(CreditCard.class, row);
		return Optional.ofNullable(creditCard);
	}

	public static Optional<DebitCard> extractDebitCardObject(HashMap<String, Object> row) {
		DebitCard debitCard = (DebitCard) extractCardObject(DebitCard.class, row);
		return Optional.ofNullable(debitCard);
	}

	private static Card extractCardObject(Class<?> card, HashMap<String, Object> row) {
		if (row.isEmpty()) {
			return null;
		}
		boolean isCreditCard = card.equals(CreditCard.class) ? true : false;
		
		Long id = (Long) row.get("id");
		Long cardNumber = (Long) row.get("cardnumber");
		Long bankAccountId = (Long) row.get("bankaccount_id");
		Long customer_id = (Long) row.get("customer_id");
		String pin = (String) row.get("pin");

		Double plafond = null;
		if (isCreditCard) {
			plafond = (Double) row.get("plafond");
		}

		String findBankAccountQuery = DbCommands.findBankAccountQuery(bankAccountId);
		String findCustomerQuery = DbCommands.findCustomerQuery(customer_id);

		List<HashMap<String, Object>> bankAccountData = Database.getInstance()
				.executeQueryMultiRow(findBankAccountQuery);
		HashMap<String, Object> customerData = Database.getInstance().executeQuerySingleRow(findCustomerQuery);

		BankAccount ba = extractBankAccountObject(bankAccountData).orElseThrow(ReposObjNotFoundException::new);
		Customer c = extractCustomerObject(customerData).orElseThrow(ReposObjNotFoundException::new);

		if (isCreditCard) {
			CreditCard creditCard = new CreditCard(cardNumber, ba, c, pin, plafond);
			creditCard.setId(id);
			return creditCard;
		}
		DebitCard debitCard = new DebitCard(cardNumber, ba, c, pin);
		debitCard.setId(id);
		return debitCard;
	}

	public static Optional<Customer> extractCustomerObject(HashMap<String, Object> row) {

		if (row.isEmpty()) {
			return Optional.empty();
		}

		Long id = (Long) row.get("id");
		String nif = (String) row.get("nif");
		String password = (String) row.get("password");
		String name = (String) row.get("name");
		LocalDate dob = (Date.valueOf(row.get("dob").toString()).toLocalDate());
		String phone = (String) row.get("phone");
		String mobile = (String) row.get("mobile");
		String email = (String) row.get("email");
		String occupation = (String) row.get("occupation");

		Customer c = new Customer(name, password, nif, email, mobile, phone, mobile, dob, occupation);

		c.setId(id);
		return Optional.of(c);
	}

	public static Optional<BankAccount> extractBankAccountObject(List<HashMap<String, Object>> rowsList) {
		if (rowsList.isEmpty()) {
			return Optional.empty();
		}

		Long accountNumber = (Long) (rowsList.get(0).get("account_number"));
		Double balance = (Double) (rowsList.get(0).get("balance"));

		Customer owner = null;
		Customer secOwner;
		List<Customer> secOwnersList = new ArrayList<>();

		for (HashMap<String, Object> row : rowsList) {
			Long customerId = (Long) row.get("customerId");
			Boolean isOwner = (Boolean) row.get("owner");

			String findCustomerQuery = DbCommands.findCustomerQuery(customerId);
			HashMap<String, Object> customerData = Database.getInstance().executeQuerySingleRow(findCustomerQuery);

			if (isOwner) {
				owner = extractCustomerObject(customerData).orElseThrow(ReposObjNotFoundException::new);
			} else {
				secOwner = extractCustomerObject(customerData).orElseThrow(ReposObjNotFoundException::new);
				secOwnersList.add(secOwner);
			}
		}

		BankAccount bankAccount = new BankAccount(accountNumber, owner, secOwnersList, balance);
		Long id = (Long) rowsList.get(0).get("id");
		bankAccount.setId(id);
		return Optional.of(bankAccount);
	}

	// List
	public static List<Customer> extractCustomersObjectList(List<HashMap<String, Object>> rs) {
		List<Customer> customersList = new ArrayList<>();
		for (HashMap<String, Object> row : rs) {
			customersList.add(extractCustomerObject(row).get());
		}
		return customersList;
	}

	public static List<CreditCard> extractCreditCardObjectList(List<HashMap<String, Object>> rs) {
		List<CreditCard> creditList = new ArrayList<>();
		for (HashMap<String, Object> row : rs) {
			creditList.add(extractCreditCardObject(row).get());
		}
		return creditList;
	}

	public static List<DebitCard> extractDebitCardObjectList(List<HashMap<String, Object>> rs) {
		List<DebitCard> debitCardList = new ArrayList<>();
		for (HashMap<String, Object> row : rs) {
			debitCardList.add(extractDebitCardObject(row).get());
		}
		return debitCardList;
	}

	public static List<BankAccount> extractBankAccountObjectList(List<HashMap<String, Object>> rs) {
		List<BankAccount> bankAccountList = new ArrayList<>();
		List<HashMap<String, Object>> bankAccountRef = new ArrayList<>();
		Long accountNumb = null;

		for (HashMap<String, Object> rowMap : rs) {
			Long currentaccNumb = (Long) (rowMap.get("account_number"));
			accountNumb = accountNumb == null ? currentaccNumb : accountNumb;

			if (accountNumb.equals(currentaccNumb)) {
				bankAccountRef.add(rowMap);
			} else {
				Optional<BankAccount> ba = extractBankAccountObject(bankAccountRef);
				bankAccountList.add(ba.get());
				bankAccountRef.clear();
				accountNumb = currentaccNumb;
				bankAccountRef.add(rowMap);
			}
		}

		// Get the last bank account if exists
		if (!bankAccountRef.isEmpty()) {
			Optional<BankAccount> ba = extractBankAccountObject(bankAccountRef);
			bankAccountList.add(ba.get());
		}

		return bankAccountList;
	}
}
