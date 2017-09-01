package by.htp.task2.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.task2.dao.address.AddressDao;
import by.htp.task2.domain.Address;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressDao addressDao;

	@Override
	public void saveAddressAndSetId(Address address) {

		if (address == null || address.getCountry() == null || address.getCity() == null) {

			throw new IllegalArgumentException();
		}

		int addressId = addressDao.saveAddress(address);
		address.setId(addressId);
	}
}
