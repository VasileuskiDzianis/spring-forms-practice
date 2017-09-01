package by.htp.task2.service.skill;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.task2.domain.Skill;
import by.htp.task2.service.skill.SkillService;
import by.htp.task2.service.skill.SkillServiceImpl;

public class SkillServiceImplTest extends SkillServiceImpl {
	private static final String SKILL_NAME_ONE = "Java";
	private static final int SKILL_ID_ONE = 1;
	private static final String SKILL_NAME_TWO = "Python";
	private static final int SKILL_ID_TWO = 2;
	private static final int NUMBER_OF_SKILLS_IN_DB = 7;
	private SkillService skillService;
	private ClassPathXmlApplicationContext context;
	
	@Before
	public void setUp() {
		context = new ClassPathXmlApplicationContext("test_spring_context.xml");
		skillService = (SkillService) context.getBean("skillServiceImpl");
	}
	
	@Test
	public void isNoOneSkillSelectedTest() {
		Skill firstSkill = new Skill();
		firstSkill.setSkillName(SKILL_NAME_ONE);
		Skill secondSkill = new Skill();
		secondSkill.setSkillName(SKILL_NAME_TWO);
		List<Skill> skills = Arrays.asList(firstSkill, secondSkill);
		
		assertFalse(skillService.isNoOneSkillSelected(skills));
	}

	@Test
	public void appendAvailableSkillsTest() {
		Skill givenSkill = new Skill();
		givenSkill.setId(SKILL_ID_ONE);
		givenSkill.setSkillName(SKILL_NAME_ONE);
		List<Skill> skills = new ArrayList<>(Arrays.asList(givenSkill));
		Skill appendedSkill = new Skill();
		appendedSkill.setId(SKILL_ID_TWO);
		appendedSkill.setSkillName(SKILL_NAME_TWO);
		
		skillService.appendAvailableSkills(skills);
		
		assertTrue(skills.contains(appendedSkill));
		assertTrue(skills.size() == NUMBER_OF_SKILLS_IN_DB);
	}

	@Test
	public void removeSkillsWithZeroIdTest() {
		Skill normalSkill = new Skill();
		normalSkill.setId(SKILL_ID_ONE);
		normalSkill.setSkillName(SKILL_NAME_ONE);
		Skill zeroIdSkill = new Skill();
		zeroIdSkill.setSkillName(SKILL_NAME_TWO);
		List<Skill> skills = new ArrayList<>(Arrays.asList(normalSkill, zeroIdSkill));
		
		assertTrue(skills.contains(zeroIdSkill));
		
		skillService.removeSkillsWithZeroId(skills);
		
		assertTrue(skills.contains(normalSkill));
		assertFalse(skills.contains(zeroIdSkill));
	}

}
