package by.htp.spring_tags.dao.user;

import java.util.List;

import by.htp.spring_tags.domain.User;
import by.htp.spring_tags.domain.UserStatus;

public interface UserDao {
	
	int saveUser(User user);

	User findUserById(int id);

	void deleteUser(User user);

	List<User> findAllByStatus(UserStatus status);
}
