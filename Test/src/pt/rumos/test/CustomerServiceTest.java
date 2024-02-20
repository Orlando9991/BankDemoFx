package pt.rumos.test;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import pt.rumos.message.EnumWarnings;
import pt.rumos.model.Customer;
import pt.rumos.service.CustomerService;

public class CustomerServiceTest {
	
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
	public void testCreateWithSameNif() {
		Customer customer = customerExample;
		
		CustomerService customerService = new CustomerService();
		
		try {
			customerService.create(customerExample);
			customerService.create(customer);
		} catch (Exception e) {
			Assert.assertEquals(EnumWarnings.REPOS_NOTFOUND.getString(), e.getMessage());
		}
	}
}
