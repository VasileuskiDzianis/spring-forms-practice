package by.htp.task2.dao.user;

import java.util.List;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.htp.task2.domain.User;
import by.htp.task2.domain.UserStatus;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public int saveUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);

		return user.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public User findUserById(int id) {

		return sessionFactory.getCurrentSession().get(User.class, id);
	}
	@Transactional
	@Override
	public void deleteUser(User user) {
		
		sessionFactory.getCurrentSession().delete(user);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<User> findAllByStatus(UserStatus stat) {

		return sessionFactory.getCurrentSession().createQuery("from User u where status = :stat", User.class)
				.setParameter("stat", stat).list();
	}
}
