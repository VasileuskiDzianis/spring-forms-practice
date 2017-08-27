package by.htp.spring_tags.service.user;

import java.util.List;

import by.htp.spring_tags.domain.User;
import by.htp.spring_tags.domain.UserStatus;

public interface UserService {
	
	void saveUser(User user);
	
	User findUserById(int id);
	
	List<User> findAllByStatus(UserStatus status);
	
	void disableUser(User user);
}
