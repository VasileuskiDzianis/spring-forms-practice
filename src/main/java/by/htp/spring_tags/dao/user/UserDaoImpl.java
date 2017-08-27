package by.htp.spring_tags.dao.user;

import java.util.List;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.htp.spring_tags.domain.*;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public int saveUser(User user) {
		sessionFactory.getCurrentSession().save(user);

		return user.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public User findUserById(int id) {

		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> findAllByStatus(UserStatus stat) {

		return sessionFactory.getCurrentSession().createQuery("from User u where status = :stat", User.class)
				.setParameter("stat", stat).list();
	}
}
