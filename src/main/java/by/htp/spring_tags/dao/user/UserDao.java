package by.htp.spring_tags.dao.user;

import by.htp.spring_tags.domain.User;

public interface UserDao {
	
	int addUser(User user);

	User getUserById(int id);

	void deleteUser(User user);
}
