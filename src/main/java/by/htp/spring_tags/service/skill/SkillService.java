package by.htp.spring_tags.service.skill;

import java.util.List;

import by.htp.spring_tags.domain.Skill;

public interface SkillService {

	List<Skill> findAllSkills();

	void removeSkillsWithZeroId(List<Skill> skills);

	void appendAvailableSkills(List<Skill> skills);

	boolean isNoOneSkillSelected(List<Skill> skills);
}
