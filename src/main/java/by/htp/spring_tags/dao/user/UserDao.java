package by.htp.spring_tags.dao.user;

import by.htp.spring_tags.domain.User;

public interface UserDao {
	
	int saveUser(User user);

	User findUserById(int id);

	void deleteUser(User user);
}
