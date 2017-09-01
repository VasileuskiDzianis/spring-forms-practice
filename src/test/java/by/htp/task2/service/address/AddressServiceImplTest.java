package by.htp.task2.service.address;

import org.junit.Before;
import org.junit.Test;

import by.htp.task2.domain.Address;
import by.htp.task2.service.address.AddressService;
import by.htp.task2.service.address.AddressServiceImpl;

public class AddressServiceImplTest extends AddressServiceImpl {
	private AddressService addressService;
	
	
	@Before
	public void setUp() throws Exception {
		
		addressService = new AddressServiceImpl();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullCityTest() {
		Address address = new Address();
		address.setCountry("China");
		
		addressService.saveAddressAndSetId(address);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullCountryTest() {
		Address address = new Address();
		address.setCity("Bejing");
		
		addressService.saveAddressAndSetId(address);
	}
}
