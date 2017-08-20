package by.htp.spring_tags.dao.skill;

import java.util.List;

import by.htp.spring_tags.dao.exception.DaoException;
import by.htp.spring_tags.domain.Skill;

public interface SkillDao {

	void addSkill(Skill skill);

	Skill getSkillById(int id) throws DaoException;

	void deleteSkill(Skill id);

	List<Skill> getAllSkills();
}
