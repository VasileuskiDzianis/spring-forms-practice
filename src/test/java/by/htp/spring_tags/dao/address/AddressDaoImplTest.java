package by.htp.spring_tags.dao.address;

import static org.junit.Assert.*;

import java.beans.PropertyVetoException;

import org.junit.Before;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import by.htp.spring_tags.dao.exception.DaoException;
import by.htp.spring_tags.domain.Address;

public class AddressDaoImplTest extends AddressDaoImpl {
	private ComboPooledDataSource dataSource;
	private AddressDaoImpl addressDao;

	@Before
	public void setUp() throws PropertyVetoException {
		dataSource = new ComboPooledDataSource();
		addressDao = new AddressDaoImpl();

		dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/spring_tags");
		dataSource.setUser("spring");
		dataSource.setPassword("qwerty");

		addressDao.setDataSource(dataSource);
	}

	@Test
	public void addAddressTest() throws DaoException {
		Address address = new Address();
		
		address.setCountry("United Kingdom");
		address.setCity("London");
		
		int returnedId = addressDao.addAddress(address);
		
		assertFalse(returnedId == 0);
	}

}
