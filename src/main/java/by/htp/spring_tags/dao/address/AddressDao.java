package by.htp.spring_tags.dao.address;

import by.htp.spring_tags.dao.exception.DaoException;
import by.htp.spring_tags.domain.Address;

public interface AddressDao {
	
	int saveAddress(Address address) throws DaoException;
}
