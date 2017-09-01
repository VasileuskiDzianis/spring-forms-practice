package by.htp.task2.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.task2.dao.user.UserDao;
import by.htp.task2.domain.User;
import by.htp.task2.domain.UserStatus;

@Service
public class UserServiceImpl implements UserService {
	private static final int NOT_REGISTERED_USER_ID = 0;

	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		if (isUserNotValid(user)) {

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
		if (status == null) {

			throw new IllegalArgumentException();
		}
		return userDao.findAllByStatus(status);
	}

	@Override
	public void disableUser(User user) {
		if (isUserNotValid(user)) {

			throw new IllegalArgumentException();
		}
		user.setStatus(UserStatus.DISABLED);
		userDao.saveUser(user);
	}

	@Override
	public boolean isUserRegistered(User user) {

		return (user.getId() > NOT_REGISTERED_USER_ID) ? true : false;
	}

	private boolean isUserNotValid(User user) {
		if (user == null || user.getAddress() == null || user.getSkills() == null || user.getLocale() == null
				|| user.getLogin() == null || user.getPassword() == null || user.getStatus() == null) {

			return true;
		}
		return false;
	}
}
