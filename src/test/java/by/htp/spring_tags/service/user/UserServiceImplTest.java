package by.htp.spring_tags.service.user;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.spring_tags.domain.Address;
import by.htp.spring_tags.domain.Skill;
import by.htp.spring_tags.domain.User;
import by.htp.spring_tags.domain.UserStatus;

public class UserServiceImplTest extends UserServiceImpl {
	private final static String USER_FOR_DISABLING_LOGIN = "Johnson";
	private final static String USER_FOR_DISABLING_PWD = "qwasdaaa";
	private final static String USER_FOR_DISABLING_LOCALE = "en";
	private final static String USER_FOR_DISABLING_CITY = "Minsk";
	private final static String USER_FOR_DISABLING_COUNTRY = "BELARUS";
	private final static int USER_FOR_DISABLING_AGE = 33;
	private final static UserStatus USER_FOR_DISABLING_STAT = UserStatus.ACTIVE;
	
	private final static String USER_FOR_DISABLING_SKILL_NAME = "JavaScript";
	private final static int USER_FOR_DISABLING_SKILL_ID_ONE = 7;

	private UserService userService;
	private ClassPathXmlApplicationContext context;

	private User userForDisabling;

	@Before
	public void setUp() {
		context = new ClassPathXmlApplicationContext("test_spring_context.xml");
		userService = (UserService) context.getBean("userServiceImpl");

		userForDisabling = new User();
		userForDisabling.setLogin(USER_FOR_DISABLING_LOGIN);
		userForDisabling.setLocale(USER_FOR_DISABLING_LOCALE);
		userForDisabling.setAge(USER_FOR_DISABLING_AGE);
		userForDisabling.setPassword(USER_FOR_DISABLING_PWD);
		userForDisabling.setStatus(USER_FOR_DISABLING_STAT);
		Address userForDelAddr = new Address();
		userForDelAddr.setCity(USER_FOR_DISABLING_CITY);
		userForDelAddr.setCountry(USER_FOR_DISABLING_COUNTRY);
		userForDisabling.setAddress(userForDelAddr);
		Skill userForDelSkill = new Skill();
		userForDelSkill.setId(USER_FOR_DISABLING_SKILL_ID_ONE);
		userForDelSkill.setSkillName(USER_FOR_DISABLING_SKILL_NAME);

		userForDisabling.setSkills(Arrays.asList(userForDelSkill));
	}

	@Test
	public void disableUserTest() {
		userService.saveUser(userForDisabling);

		User storedUser = userService.findUserById(userForDisabling.getId());

		assertEquals(USER_FOR_DISABLING_STAT, storedUser.getStatus());

		userService.disableUser(userForDisabling);

		storedUser = userService.findUserById(userForDisabling.getId());

		assertEquals(UserStatus.DISABLED, userForDisabling.getStatus());
		assertEquals(UserStatus.DISABLED, storedUser.getStatus());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullLoginTest() {
		userForDisabling.setLogin(null);
		userService.saveUser(userForDisabling);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullPasswordTest() {
		userForDisabling.setPassword(null);
		userService.saveUser(userForDisabling);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullLocaleTest() {
		userForDisabling.setLocale(null);
		userService.saveUser(userForDisabling);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullAddressTest() {
		userForDisabling.setAddress(null);
		userService.saveUser(userForDisabling);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullSkillsTest() {
		userForDisabling.setSkills(null);
		userService.saveUser(userForDisabling);
	}

	@After
	public void tearDown() throws Exception {
		context.close();
	}

}
