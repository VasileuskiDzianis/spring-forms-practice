package by.htp.spring_tags.dao.address;

import static org.junit.Assert.assertFalse;

import java.beans.PropertyVetoException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import by.htp.spring_tags.domain.Address;

public class AddressDaoImplTest extends AddressDaoImpl {
	private BasicDataSource dataSource;
	private AddressDaoImpl addressDao;

	@Before
	public void setUp() throws PropertyVetoException {
		dataSource = new BasicDataSource();
		addressDao = new AddressDaoImpl();

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1/spring_tags");
		dataSource.setUsername("spring");
		dataSource.setPassword("qwerty");

		addressDao.setDataSource(dataSource);
	}

	@Test
	public void addAddressTest() {
		Address address = new Address();

		address.setCountry("United Kingdom");
		address.setCity("London");

		int returnedId = addressDao.saveAddress(address);

		assertFalse(returnedId == 0);
	}

}
