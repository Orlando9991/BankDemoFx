package pt.rumos.repository.database.commands;

import pt.rumos.model.BankAccount;
import pt.rumos.model.CreditCard;
import pt.rumos.model.Customer;
import pt.rumos.model.DebitCard;

public abstract class DbCommands {
	
	public static String insertCustomerCommand(Customer customer) {
		return "INSERT INTO customers" 
				+ " (nif, password, name, dob, phone, mobile, email, occupation)"
				+ " VALUES (" 
				+ "'"+customer.getNif()+"',"
				+ " "+customer.getPassword()+","
				+ " '"+customer.getName()+"',"
				+ " '"+customer.getDob()+"',"
				+ " '"+customer.getPhone()+"',"
				+ " '"+customer.getMobile()+"',"
				+ " '"+customer.getEmail()+"',"
				+ " '"+customer.getOccupation()+"')";
	}
	
	public static String updateCustomerCommand(Customer customer) {
		return "UPDATE customers SET "
				+ "password='"+customer.getPassword()+"',"
				+ "name='"+customer.getName()+"',"
				+ "phone='"+customer.getPhone()+"',"
				+ "mobile='"+customer.getMobile()+"',"
				+ "email='"+customer.getEmail()+"',"
				+ "occupation='"+customer.getOccupation()+"'"
				+ " WHERE id="+customer.getId()+"";
	}
	
	public static String findCustomerQuery(String nif) {
		return "SELECT * FROM customers WHERE nif LIKE '"+ nif +"'";
	}
	
	public static String findCustomerQuery(Long id) {
		return "SELECT * FROM customers WHERE id LIKE '"+ id +"'";
	}
	
	public static String findAllCustomerCommand() {
		return "SELECT * FROM customers";
	}
		
	public static String deleteCustomerCommand(String nif) {
		return "DELETE FROM customers WHERE nif LIKE '"+ nif +"'";
	}
	
	public static String insertBankAccountCommand(Long accountNumb,Customer c, boolean isOwner, BankAccount bankAccount) {
		return "INSERT INTO bankaccount"
				+ "(account_number, customerId, owner, balance) "
				+ "VALUES "
				+ "("+accountNumb+","
				+ ""+c.getId()+","
				+ ""+isOwner+","
				+ ""+bankAccount.getBalance()+")";
	}
	
	public static String updateBankAccountCommand(Customer c, boolean isOwner, BankAccount bankAccount) {
		return "UPDATE bankaccount SET "
				+ "customerId="+c.getId()+", "
				+ "owner="+isOwner+", "
				+ "balance="+bankAccount.getBalance()+""
				+ " WHERE account_number="+bankAccount.getAccountNumber()+" AND customerId="+c.getId()+"";
	}
	
	public static String findBankAccountQuery(Long accountNumb) {
		return "SELECT * FROM bankaccount WHERE account_number="+ accountNumb +"";
	}
	
	public static String findAllBankAccountCommand() {
		return "SELECT * FROM bankaccount ORDER BY account_number ASC";
	}
	
	public static String deleteBankAccountCommand(Long id) {
		return "DELETE FROM bankaccount WHERE account_number="+ id +"";
	}
	
	
	public static String insertDebitCardCommand(DebitCard debitCard) {
		return "INSERT INTO debitcard "
				+ "(cardnumber, bankaccount_id, customer_id, pin) "
				+ "VALUES "
				+ "("+debitCard.getCardNumber()+","
				+ ""+debitCard.getBankAccount().getId()+","
				+ ""+debitCard.getOwnerCustomer().getId()+","
				+ ""+debitCard.getPin()+")";
	}
	
	public static String updateDebitCardCommand(DebitCard debitCard) {
		return "UPDATE debitcard SET"
				+ " pin="+debitCard.getPin()+" "
				+ "WHERE id ="+debitCard.getId()+"";
	}
	
	public static String findDebitCardQuery(Long id) {
		return "SELECT * FROM debitcard WHERE cardnumber="+ id +"";
	}
	
	public static String findAllDebitCardQuery() {
		return "SELECT * FROM debitcard";
	}
	
	public static String deleteDebitCardCommand(Long id) {
		return "DELETE FROM debitcard WHERE cardnumber="+ id +"";
	}
	

	public static String insertCreditCardCommand(CreditCard creditCard) {
		return "INSERT INTO creditcard"
				+ " (cardNumber, bankaccount_id, customer_id, pin, plafond)"
				+ " VALUES "
				+ "("+creditCard.getCardNumber()+","
				+ ""+creditCard.getBankAccount().getId()+","
				+ ""+creditCard.getOwnerCustomer().getId()+","
				+ ""+creditCard.getPin()+","
				+ ""+creditCard.getPlafond()+")";
	}
	
	public static String updateCreditCardCommand(CreditCard creditCard) {
		return "UPDATE creditcard SET"
				+" pin="+creditCard.getPin()+","
				+ "plafond="+creditCard.getPlafond()+""
				+ " WHERE id ="+creditCard.getId()+"";
	}
	
	public static String findCreditCardQuery(Long id) {
		return "SELECT * FROM creditcard WHERE cardNumber="+ id +"";
	}
	
	public static String findAllCreditCardQuery() {
		return "SELECT * FROM creditcard";
	}
	
	public static String deleteCreditCardCommand(Long id) {
		return "DELETE FROM creditcard WHERE cardnumber="+ id +"";
	}
}
