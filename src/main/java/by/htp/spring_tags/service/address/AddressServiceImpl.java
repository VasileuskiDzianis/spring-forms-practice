package by.htp.spring_tags.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.spring_tags.dao.address.AddressDao;
import by.htp.spring_tags.domain.Address;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressDao addressDao;

	@Override
	public void addAddressAndSetId(Address address) {

		if (address == null || address.getCountry() == null || address.getCity() == null) {

			throw new IllegalArgumentException();
		}

		int addressId = addressDao.addAddress(address);
		address.setId(addressId);
	}
}
