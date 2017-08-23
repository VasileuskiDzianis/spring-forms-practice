package by.htp.spring_tags.dao.address;

import by.htp.spring_tags.domain.Address;

public interface AddressDao {
	String COLUMN_ADDRESS_ID = "addressId";
	String COLUMN_ADDRESS_COUNTRY = "country";
	String COLUMN_ADDRESS_CITY = "city";
	
	int saveAddress(Address address) throws RuntimeException;
}
