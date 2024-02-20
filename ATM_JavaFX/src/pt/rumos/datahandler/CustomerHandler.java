package pt.rumos.datahandler;

import pt.rumos.model.Customer;
import pt.rumos.service.CustomerService;

public abstract class CustomerHandler {
	
	private final static CustomerService customerService = new CustomerService();
	
	public static Customer getCustomer(String nif) {
		return customerService.getById(nif);
	}
	
}
