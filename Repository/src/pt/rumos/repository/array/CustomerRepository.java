package pt.rumos.repository.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.rumos.contract.ICustomerRepository;
import pt.rumos.exception.repository.ReposObjNotFoundException;
import pt.rumos.model.Customer;

public class CustomerRepository implements ICustomerRepository {
	// CRUD (CREATE, RETRIEVE, UPDATE, DELETE)

	private Customer[] customers = new Customer[1];
	private static Long id = 1L; // keep tracking of id value

	@Override
	public Optional<Customer> save(Customer customer) {
		// Costumer already exists
		if (customer.getId() == null) {
			return saveObj(customer);
		} else {
			return updateObj(customer);
		}
	}

	@Override
	public Optional<Customer> saveObj(Customer customer) {
		for (int i = 0; i < customers.length; i++) {
			if (customers[i] == null) {
				customer.setId(id++);
				customers[i] = customer;
				return Optional.of(customer);
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<Customer> updateObj(Customer customer) {
		for (int i = 0; i < customers.length; i++) {
			if (customers[i] == null) {
				continue;
			}

			Long customerID = customer.getId();
			Long currentCustomerID = customers[i].getId();

			if (customerID.equals(currentCustomerID)) {
				customers[i] = customer;
				return Optional.of(customer);
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<Customer> findById(String nif) {
		for (int i = 0; i < customers.length; i++) {

			if (customers[i] == null) {
				continue;
			}

			if (nif.equals(customers[i].getNif())) {
				return Optional.of(customers[i]);
			}
		}
		return Optional.empty();
	}

	@Override
	public void deleteById(String nif) {
		for (int i = 0; i < customers.length; i++) {

			if (customers[i] == null) {
				continue;
			}

			if (nif.equals(customers[i].getNif())) {
				customers[i] = null;
				return;
			}
		}
		throw new ReposObjNotFoundException();
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> customersList = new ArrayList<Customer>();
		for (int i = 0; i < customers.length; i++) {

			if (customers[i] == null) {
				continue;
			}

			customersList.add(customers[i]);
		}
		return customersList;
	}
}
