package by.htp.spring_tags.dao.skill;

import static org.junit.Assert.*;

import java.beans.PropertyVetoException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import by.htp.spring_tags.domain.Skill;

public class SkillDaoImplTest {
	private BasicDataSource dataSource;
	private SkillDaoImpl skillDao;

	@Before
	public void setUp() throws PropertyVetoException {
		dataSource = new BasicDataSource();
		skillDao = new SkillDaoImpl();

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1/spring_tags");
		dataSource.setUsername("spring");
		dataSource.setPassword("qwerty");

		skillDao.setDataSource(dataSource);
	}

	@Test
	public void testGetSkillById() throws RuntimeException {
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
}
