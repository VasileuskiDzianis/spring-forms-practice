package by.htp.spring_tags.dao.user;

import static org.junit.Assert.*;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import by.htp.spring_tags.dao.exception.DaoException;
import by.htp.spring_tags.domain.Address;
import by.htp.spring_tags.domain.Skill;
import by.htp.spring_tags.domain.User;

public class UserDaoImplTest extends UserDaoImpl {
	private ComboPooledDataSource dataSource;
	private UserDaoImpl userDao;

	@Before
	public void setUp() throws PropertyVetoException {
		dataSource = new ComboPooledDataSource();
		userDao = new UserDaoImpl();

		dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/spring_tags");
		dataSource.setUser("spring");
		dataSource.setPassword("qwerty");

		userDao.setDataSource(dataSource);
	}

	@Test
	public void getUserById() throws DaoException {
		int testUserId = 1;
		String testUserExpectedLogin = "Shield";
		String testUserExpectedPassword = "qwerty";
		String testUserExpectedCity = "Dallas";
		String testUserExpectedLanguage = "en";
		int testUserExpectedNumberOfSkills = 3;
		Skill testUserExpectedSkill = new Skill();
		testUserExpectedSkill.setId(2);
		testUserExpectedSkill.setSkillName("Python");
		
		User user = userDao.findUserById(testUserId);
		
		assertEquals(testUserExpectedLogin, user.getLogin());
		assertEquals(testUserExpectedPassword, user.getPassword());
		assertEquals(testUserExpectedCity, user.getAddress().getCity());
		assertEquals(testUserExpectedLanguage, user.getLocale().getLanguage());
		assertEquals(testUserExpectedNumberOfSkills, user.getSkills().size());
		assertTrue(user.getSkills().contains(testUserExpectedSkill));
		
	}
	@Test
	public void addUserTest() throws DaoException {
		User givenUser = new User();
		givenUser.setLogin("Fowler");
		givenUser.setPassword("asdasd");
		givenUser.setLocale(new Locale("en"));
		
		Skill skillOne = new Skill();
		skillOne.setId(3);
		skillOne.setSkillName("SQL");
		Skill skillTwo = new Skill();
		skillTwo.setId(4);
		skillTwo.setSkillName("C++");
		
		List<Skill> skills = new ArrayList<Skill>(Arrays.asList(skillOne, skillTwo));
		givenUser.setSkills(skills);
		
		Address address = new Address();
		address.setId(0);
		address.setCountry("United Kingdom");
		address.setCity("London");
		givenUser.setAddress(address);
		
		int storedUserId = userDao.saveUser(givenUser);
		
		User gotUser = userDao.findUserById(storedUserId);
		
		assertEquals(givenUser.getLogin(), gotUser.getLogin());
		assertEquals(givenUser.getPassword(), gotUser.getPassword());
		
		assertTrue(givenUser.getLocale().equals(gotUser.getLocale()));
		assertTrue(gotUser.getSkills().containsAll(skills));

	}
}
