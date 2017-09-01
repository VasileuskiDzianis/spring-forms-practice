package by.htp.task2.service.user;

import java.util.List;

import by.htp.task2.domain.User;
import by.htp.task2.domain.UserStatus;

public interface UserService {

	void saveUser(User user);

	User findUserById(int id);

	List<User> findAllByStatus(UserStatus status);

	void disableUser(User user);

	boolean isUserRegistered(User user);
}
