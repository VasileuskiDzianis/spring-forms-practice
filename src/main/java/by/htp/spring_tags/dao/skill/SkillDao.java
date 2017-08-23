package by.htp.spring_tags.dao.skill;

import java.util.List;

import by.htp.spring_tags.domain.Skill;

public interface SkillDao {
	String COLUMN_SKILL_ID = "skillId";
	String COLUMN_SKILL_NAME = "skillName";

	void saveSkill(Skill skill);

	Skill findSkillById(int id) throws RuntimeException;

	void deleteSkill(Skill id);

	List<Skill> findAllSkills();
}
