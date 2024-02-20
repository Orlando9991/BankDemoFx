package pt.rumos.test;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import pt.rumos.message.EnumWarnings;
import pt.rumos.model.Customer;
import pt.rumos.repository.array.CustomerRepository;

public class CustomerArrayRepositoryTest {
	
	private Customer customerExample = new Customer(
			"Orlando",
			"root",
			"123456789",
			"orlandocruz999@gmail.com",
			"programer",
			"+3519999999",
			"+3519999999",
			LocalDate.of(2022, 8, 20),
			"dev");

	@Test
	public void testUpdateCustomerNotExists() {
		CustomerRepository customerArrayRepository = new CustomerRepository();
		try {
			customerArrayRepository.updateObj(customerExample);
		} catch (Exception e) {
			Assert.assertEquals(EnumWarnings.REPOS_ERROR.getString(), e.getMessage());
		}	
	}

}
