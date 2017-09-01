package by.htp.task2.dao.skill;

import java.util.List;

import by.htp.task2.domain.Skill;

public interface SkillDao {

	Skill findSkillById(int id);

	List<Skill> findAllSkills();
}
