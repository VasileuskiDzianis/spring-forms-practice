package by.htp.spring_tags.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.spring_tags.dao.user.UserDao;
import by.htp.spring_tags.domain.User;
import by.htp.spring_tags.domain.UserStatus;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		if (user == null || user.getAddress() == null || user.getSkills() == null || user.getLocale() == null
				|| user.getLogin() == null || user.getPassword() == null) {

			throw new IllegalArgumentException();
		}
		userDao.saveUser(user);
	}

	@Override
	public User findUserById(int id) {

		return userDao.findUserById(id);
	}

	@Override
	public List<User> findAllByStatus(UserStatus status) {
		
		return userDao.findAllByStatus(status);
	}
}
