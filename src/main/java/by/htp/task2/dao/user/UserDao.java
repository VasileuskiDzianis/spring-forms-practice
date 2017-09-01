package by.htp.task2.dao.user;

import java.util.List;

import by.htp.task2.domain.User;
import by.htp.task2.domain.UserStatus;

public interface UserDao {
	
	int saveUser(User user);

	User findUserById(int id);

	void deleteUser(User user);

	List<User> findAllByStatus(UserStatus status);
}
