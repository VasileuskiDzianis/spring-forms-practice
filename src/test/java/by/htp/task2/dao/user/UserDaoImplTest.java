package by.htp.task2.dao.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyVetoException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.task2.dao.user.UserDao;
import by.htp.task2.dao.user.UserDaoImpl;
import by.htp.task2.domain.Address;
import by.htp.task2.domain.Skill;
import by.htp.task2.domain.User;
import by.htp.task2.domain.UserStatus;

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

	private final static String USER_FOR_DELETION_LOGIN = "Johnson";
	private final static String USER_FOR_DELETION_PWD = "qwasdaaa";
	private final static String USER_FOR_DELETION_LOCALE = "en";
	private final static String USER_FOR_DELETION_CITY = "Minsk";
	private final static String USER_FOR_DELETION_COUNTRY = "BELARUS";
	private final static int USER_FOR_DELETION_AGE = 33;
	private final static UserStatus USER_FOR_DELETION_STAT = UserStatus.ACTIVE;
	private final static String USER_FOR_DELETION_SKILL_NAME = "JavaScript";
	private final static int USER_FOR_DELETION_SKILL_ID = 7;

	private final static String NEW_USER_LOGIN = "Fowler";
	private final static String NEW_USER_PWD = "asdasd";
	private final static String NEW_USER_LOCALE = "en";
	private final static String NEW_USER_CITY = "London";
	private final static String NEW_USER_COUNTRY = "United Kingdom";
	private final static int NEW_USER_AGE = 33;
	private final static UserStatus NEW_USER_STAT = UserStatus.ACTIVE;
	private final static String NEW_USER_SKILL_1_NAME = "SQL";
	private final static int NEW_USER_SKILL_1_ID = 3;
	private final static String NEW_USER_SKILL_2_NAME = "C++";
	private final static int NEW_USER_SKILL_2_ID = 4;

	private final static int DISABLED_USER_ID = 3;
	private final static int ACT_USER_ID_1 = 1;
	private final static int ACT_USER_ID_2 = 2;

	private final static int NUMBER_OF_ACT_USERS = 2;
	private final static int NUMBER_OF_DIS_USERS = 0;

	private User userForDeletion;
	private User userForSaving;

	private UserDao userDao;
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws PropertyVetoException {
		context = new ClassPathXmlApplicationContext("test_spring_context.xml");
		userDao = (UserDao) context.getBean("userDaoImpl");

		userForDeletion = new User();
		userForDeletion.setLogin(USER_FOR_DELETION_LOGIN);
		userForDeletion.setLocale(USER_FOR_DELETION_LOCALE);
		userForDeletion.setAge(USER_FOR_DELETION_AGE);
		userForDeletion.setPassword(USER_FOR_DELETION_PWD);
		userForDeletion.setStatus(USER_FOR_DELETION_STAT);
		Address userForDelAddr = new Address();
		userForDelAddr.setCity(USER_FOR_DELETION_CITY);
		userForDelAddr.setCountry(USER_FOR_DELETION_COUNTRY);
		userForDeletion.setAddress(userForDelAddr);
		Skill userForDelSkill = new Skill();
		userForDelSkill.setId(USER_FOR_DELETION_SKILL_ID);
		userForDelSkill.setSkillName(USER_FOR_DELETION_SKILL_NAME);

		userForDeletion.setSkills(Arrays.asList(userForDelSkill));

		userForSaving = new User();
		userForSaving.setLogin(NEW_USER_LOGIN);
		userForSaving.setPassword(NEW_USER_PWD);
		userForSaving.setLocale(NEW_USER_LOCALE);
		userForSaving.setStatus(NEW_USER_STAT);
		userForSaving.setAge(NEW_USER_AGE);

		Skill skillOne = new Skill();
		skillOne.setId(NEW_USER_SKILL_1_ID);
		skillOne.setSkillName(NEW_USER_SKILL_1_NAME);
		Skill skillTwo = new Skill();
		skillTwo.setId(NEW_USER_SKILL_2_ID);
		skillTwo.setSkillName(NEW_USER_SKILL_2_NAME);

		userForSaving.setSkills(Arrays.asList(skillOne, skillTwo));

		Address address = new Address();
		address.setCountry(NEW_USER_COUNTRY);
		address.setCity(NEW_USER_CITY);
		userForSaving.setAddress(address);

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
		int storedUserId = userDao.saveUser(userForSaving);
		User gotUser = userDao.findUserById(storedUserId);

		assertEquals(userForSaving.getLogin(), gotUser.getLogin());
		assertEquals(userForSaving.getAddress().getCity(), gotUser.getAddress().getCity());
		assertTrue(gotUser.getSkills().containsAll(userForSaving.getSkills()));
	}

	@Test
	public void findAllByStatus() {
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
			if (user.getId() == ACT_USER_ID_2) {
				actUserCounter++;
			}
			if (user.getId() == DISABLED_USER_ID) {
				disUserCounter++;
			}
		}
		assertTrue(NUMBER_OF_ACT_USERS == actUserCounter);
		assertTrue(NUMBER_OF_DIS_USERS == disUserCounter);
	}

	@Test
	public void deleteUserTest() {
		userDao.saveUser(userForDeletion);
		User storedUser = userDao.findUserById(userForDeletion.getId());

		assertEquals(USER_FOR_DELETION_LOGIN, storedUser.getLogin());
		assertEquals(USER_FOR_DELETION_COUNTRY, storedUser.getAddress().getCountry());
		assertTrue(userForDeletion.getSkills().containsAll(storedUser.getSkills()));

		userDao.deleteUser(storedUser);

		assertNull(userDao.findUserById(storedUser.getId()));
	}

	@After
	public void closeResources() {
		context.close();
	}

}
