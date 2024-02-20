package pt.rumos.service;

import java.util.List;
import java.util.Optional;

import pt.rumos.exception.repository.*;
import pt.rumos.model.Customer;
import pt.rumos.contract.ICustomerRepository;
import pt.rumos.contract_serv.ICustomerService;
import pt.rumos.repository.database.CustomerRepository;

public class CustomerService implements ICustomerService {
	
	ICustomerRepository customerRepository = new CustomerRepository();

	@Override
	public Customer create(Customer customer) {
		// Check if NIF already exists
		try {
			String nif = customer.getNif();
			getById(nif);
		} catch (Exception e) {
			Optional<Customer> customerOpt = customerRepository.save(customer);
			return customerOpt.orElseThrow(ReposErrorException::new);
		}
		
		throw new ReposErrorException("NIF already taken");
	}
	
	@Override
	public Customer update(Customer customer) {
		Optional<Customer> customerOpt = customerRepository.save(customer);
		return customerOpt.orElseThrow(ReposErrorException::new);
	}

	@Override
	public Customer getById(String nif) {
		Optional<Customer> customerOpt = customerRepository.findById(nif);
		return customerOpt.orElseThrow(ReposObjNotFoundException::new);
	}
	
	@Override
	public void deleteById(String nif) throws ReposObjNotFoundException {
		customerRepository.deleteById(nif);
	}

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}	
}
