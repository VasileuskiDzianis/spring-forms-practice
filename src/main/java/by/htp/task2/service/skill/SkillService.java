package by.htp.task2.service.skill;

import java.util.List;

import by.htp.task2.domain.Skill;

public interface SkillService {

	List<Skill> findAllSkills();

	void removeSkillsWithZeroId(List<Skill> skills);

	void appendAvailableSkills(List<Skill> skills);

	boolean isNoOneSkillSelected(List<Skill> skills);
}
