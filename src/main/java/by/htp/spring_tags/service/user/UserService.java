package by.htp.spring_tags.service.user;

import by.htp.spring_tags.domain.User;

public interface UserService {
	
	void addUserAndSetId(User user);
	
	User getUserById(int id);
}
