package by.htp.spring_tags.dao.address;

import static org.junit.Assert.assertFalse;

import java.beans.PropertyVetoException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.spring_tags.domain.Address;

public class AddressDaoImplTest extends AddressDaoImpl {
	private AddressDao addressDao;
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws PropertyVetoException {

		context = new ClassPathXmlApplicationContext("test_spring_context.xml");
		addressDao = (AddressDao) context.getBean("addressDaoImpl");
	}

	@Test
	public void saveAddressTest() {
		Address address = new Address();
		address.setCountry("Germany");
		address.setCity("Berlin");

		int returnedId = addressDao.saveAddress(address);

		assertFalse(returnedId == 0);
	}

	@After
	public void destroyResources() {
		context.close();
	}
}
