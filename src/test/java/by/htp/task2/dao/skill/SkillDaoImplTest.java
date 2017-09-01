package by.htp.task2.dao.skill;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.task2.dao.skill.SkillDao;
import by.htp.task2.domain.Skill;

public class SkillDaoImplTest {
	private SkillDao skillDao;
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() {
		context = new ClassPathXmlApplicationContext("test_spring_context.xml");
		skillDao = (SkillDao) context.getBean("skillDaoImpl");
	}

	@Test
	public void testGetSkillById() {
		int skillId = 2;
		String expectedSkillName = "Python";
		Skill skill = skillDao.findSkillById(skillId);

		assertEquals(expectedSkillName, skill.getSkillName());
	}

	@Test
	public void getAllSkillsTest() {
		int skillIdFirst = 1;
		String expectedSkillNameFirst = "Java";
		Skill skillFirst = new Skill();
		skillFirst.setId(skillIdFirst);
		skillFirst.setSkillName(expectedSkillNameFirst);
		int skillIdSecond = 2;
		String expectedSkillNameSecond = "Python";
		Skill skillSecond = new Skill();
		skillSecond.setId(skillIdSecond);
		skillSecond.setSkillName(expectedSkillNameSecond);
	
		List<Skill> expectedSkills = Arrays.asList(skillFirst, skillSecond);
		List<Skill> gotSkills = skillDao.findAllSkills();

		assertTrue(gotSkills.containsAll(expectedSkills));
	}

	@After
	public void closeResources() {
		context.close();
	}
}
