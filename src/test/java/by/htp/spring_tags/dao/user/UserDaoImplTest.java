package by.htp.spring_tags.dao.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import by.htp.spring_tags.domain.Address;
import by.htp.spring_tags.domain.Skill;
import by.htp.spring_tags.domain.User;

public class UserDaoImplTest extends UserDaoImpl {
	private final static int USER_ID = 1;
	private final static String USER_EXPECTED_LOGIN = "Shield";
	private final static String USER_EXPECTED_PASSWORD = "qwerty";
	private final static int USER_EXPECTED_AGE = 54;
	private final static String USER_EXPECTED_CITY = "Dallas";
	private final static String USER_EXPECTED_LANGUAGE = "en";
	private final static int USER_EXPECTED_NUMBER_OF_SKILLS = 3;
	private final static int EXPECTED_SKILL_ID = 2;
	private final static String EXPECTED_SKILL_NAME = "Python";
	
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
	public void getUserById() {
		
		
		Skill testUserExpectedSkill = new Skill();
		testUserExpectedSkill.setId(EXPECTED_SKILL_ID);
		testUserExpectedSkill.setSkillName(EXPECTED_SKILL_NAME);
		
		User user = userDao.findUserById(USER_ID);
		
		assertEquals(USER_EXPECTED_LOGIN, user.getLogin());
		assertEquals(USER_EXPECTED_PASSWORD, user.getPassword());
		assertEquals(USER_EXPECTED_AGE, user.getAge());
		assertEquals(USER_EXPECTED_CITY, user.getAddress().getCity());
		assertEquals(USER_EXPECTED_LANGUAGE, user.getLocale().getLanguage());
		assertEquals(USER_EXPECTED_NUMBER_OF_SKILLS, user.getSkills().size());
		assertTrue(user.getSkills().contains(testUserExpectedSkill));
		
	}
	@Test
	public void addUserTest() {
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
