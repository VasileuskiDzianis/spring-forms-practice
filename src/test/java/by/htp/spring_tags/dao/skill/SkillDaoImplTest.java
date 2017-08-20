package by.htp.spring_tags.dao.skill;

import static org.junit.Assert.*;

import java.beans.PropertyVetoException;

import org.junit.Before;
import org.junit.Test;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import by.htp.spring_tags.dao.exception.DaoException;
import by.htp.spring_tags.domain.Skill;

public class SkillDaoImplTest {
	private ComboPooledDataSource dataSource;
	private SkillDaoImpl skillDao;

	@Before
	public void setUp() throws PropertyVetoException {
		dataSource = new ComboPooledDataSource();
		skillDao = new SkillDaoImpl();

		dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/spring_tags");
		dataSource.setUser("spring");
		dataSource.setPassword("qwerty");

		skillDao.setDataSource(dataSource);
	}

	@Test
	public void testGetSkillById() throws DaoException {
		int skillId = 2;
		String expectedSkillName = "Python";

		Skill skill = skillDao.getSkillById(skillId);

		assertEquals(expectedSkillName, skill.getSkillName());
	}
}
