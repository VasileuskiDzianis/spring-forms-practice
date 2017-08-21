package by.htp.spring_tags.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.spring_tags.dao.user.UserDao;
import by.htp.spring_tags.domain.User;
import by.htp.spring_tags.service.address.AddressService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private AddressService addressService;

	@Override
	public void addUserAndSetId(User user) {
		if (user == null || user.getAddress() == null || user.getSkills() == null || user.getLocale() == null
				|| user.getLogin() == null || user.getPassword() == null) {

			throw new NullPointerException();
		}

		
		addressService.addAddressAndSetId(user.getAddress());

		int userId = userDao.addUser(user);
		user.setId(userId);
	}

	@Override
	public User getUserById(int id) {

		return userDao.getUserById(id);
	}

}
