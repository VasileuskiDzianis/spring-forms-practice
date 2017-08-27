package by.htp.spring_tags.dao.skill;

import java.util.List;

import by.htp.spring_tags.domain.Skill;

public interface SkillDao {

	Skill findSkillById(int id);

	List<Skill> findAllSkills();
}
