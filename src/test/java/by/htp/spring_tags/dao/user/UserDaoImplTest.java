package by.htp.spring_tags.dao.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.spring_tags.domain.Address;
import by.htp.spring_tags.domain.Skill;
import by.htp.spring_tags.domain.User;
import by.htp.spring_tags.domain.UserStatus;

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

	private UserDao userDao;
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws PropertyVetoException {
		context = new ClassPathXmlApplicationContext("test_spring_context.xml");
		userDao = (UserDao) context.getBean("userDaoImpl");
	}

	@Test
	public void findUserById() {
		Skill testUserExpectedSkill = new Skill();
		testUserExpectedSkill.setId(EXPECTED_SKILL_ID);
		testUserExpectedSkill.setSkillName(EXPECTED_SKILL_NAME);

		User user = userDao.findUserById(USER_ID);

		assertEquals(USER_EXPECTED_LOGIN, user.getLogin());
		assertEquals(USER_EXPECTED_PASSWORD, user.getPassword());
		assertEquals(USER_EXPECTED_AGE, user.getAge());
		assertEquals(USER_EXPECTED_CITY, user.getAddress().getCity());
		assertEquals(USER_EXPECTED_LANGUAGE, user.getLocale());
		assertEquals(USER_EXPECTED_AGE, user.getAge());
		assertEquals(USER_EXPECTED_NUMBER_OF_SKILLS, user.getSkills().size());
		assertTrue(user.getSkills().contains(testUserExpectedSkill));

	}

	@Test
	public void saveUserTest() {
		User givenUser = new User();
		givenUser.setLogin("Fowler");
		givenUser.setPassword("asdasd");
		givenUser.setLocale("en");

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

	@Test
	public void findAllByStatus() {
		final int DISABLED_USER_ID = 3;
		final int ACT_USER_ID_1 = 1;
		final int ACT_USER_ID_2 = 2;
		
		final int NUMBER_OF_ACT_USERS = 2;
		final int NUMBER_OF_DIS_USERS = 0;
		
		
		List<User> findResult = userDao.findAllByStatus(UserStatus.ACTIVE);
		
		int actUserCounter = 0;
		int disUserCounter = 0;
		
		
		for (User user : findResult) {
			if (user.getId() == ACT_USER_ID_1) {
				actUserCounter++;
				assertEquals(USER_EXPECTED_LOGIN, user.getLogin());
				assertEquals(USER_EXPECTED_PASSWORD, user.getPassword());
				assertEquals(USER_EXPECTED_AGE, user.getAge());
				assertEquals(USER_EXPECTED_CITY, user.getAddress().getCity());
				assertEquals(USER_EXPECTED_LANGUAGE, user.getLocale());
				assertEquals(USER_EXPECTED_AGE, user.getAge());
				assertEquals(USER_EXPECTED_NUMBER_OF_SKILLS, user.getSkills().size());
				
			}
			if (user.getId() == ACT_USER_ID_2) actUserCounter++;
			if (user.getId() == DISABLED_USER_ID) disUserCounter++;
		}
		 
		assertTrue(NUMBER_OF_ACT_USERS == actUserCounter);
		assertTrue(NUMBER_OF_DIS_USERS == disUserCounter);
	}
	
	
	@After
	public void closeResources() {
		context.close();
	}

}
