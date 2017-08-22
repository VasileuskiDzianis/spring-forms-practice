package by.htp.spring_tags.service.user;

import by.htp.spring_tags.domain.User;

public interface UserService {
	
	void saveUserAndSetId(User user);
	
	User findUserById(int id);
}
