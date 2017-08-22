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
	public void saveUserAndSetId(User user) {
		if (user == null || user.getAddress() == null || user.getSkills() == null || user.getLocale() == null
				|| user.getLogin() == null || user.getPassword() == null) {

			throw new IllegalArgumentException();
		}

		
		addressService.saveAddressAndSetId(user.getAddress());

		int userId = userDao.saveUser(user);
		user.setId(userId);
	}

	@Override
	public User findUserById(int id) {

		return userDao.findUserById(id);
	}

}
