package by.htp.spring_tags.service.address;

import org.junit.Before;
import org.junit.Test;

import by.htp.spring_tags.domain.Address;

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
