package pt.rumos.repository.database;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import pt.rumos.contract.ICustomerRepository;
import pt.rumos.database.Database;
import pt.rumos.model.Customer;
import pt.rumos.repository.database.commands.DbCommands;
import pt.rumos.repository.database.convert.DbConvert;

public class CustomerRepository implements ICustomerRepository {

	private Database database = Database.getInstance();

	@Override
	public Optional<Customer> save(Customer customer) {
		if (findById(customer.getNif()).isEmpty()) {
			return saveObj(customer);
		} else {
			return updateObj(customer);
		}
	}

	@Override
	public Optional<Customer> saveObj(Customer customer) {
		String command = DbCommands.insertCustomerCommand(customer);

		if (database.executeCommand(command)) {
			return Optional.of(customer);
		}

		return Optional.empty();
	}

	@Override
	public Optional<Customer> updateObj(Customer customer) {
		String command = DbCommands.updateCustomerCommand(customer);

		if (database.executeCommand(command)) {
			return Optional.of(customer);
		}

		return Optional.empty();
	}

	@Override
	public Optional<Customer> findById(String nif) {
		String query = DbCommands.findCustomerQuery(nif);
		HashMap<String, Object> rs = database.executeQuerySingleRow(query);

		return DbConvert.extractCustomerObject(rs);
	}

	@Override
	public void deleteById(String nif) {
		String query = DbCommands.deleteCustomerCommand(nif);
		database.executeCommand(query);
	}

	@Override
	public List<Customer> findAll() {
		String query = DbCommands.findAllCustomerCommand();
		List<HashMap<String, Object>> rs = database.executeQueryMultiRow(query);

		return DbConvert.extractCustomersObjectList(rs);
	}
}
