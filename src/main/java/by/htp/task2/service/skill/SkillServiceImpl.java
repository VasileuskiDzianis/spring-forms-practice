package by.htp.task2.service.skill;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.task2.dao.skill.SkillDao;
import by.htp.task2.domain.Skill;

@Service
public class SkillServiceImpl implements SkillService {
	@Autowired
	private SkillDao skillDao;

	@Override
	public List<Skill> findAllSkills() {

		return skillDao.findAllSkills();
	}

	@Override
	public boolean isNoOneSkillSelected(List<Skill> skills) {

		return skills.isEmpty();
	}

	@Override
	public void appendAvailableSkills(List<Skill> skills) {
		List<Skill> availableSkills = findAllSkills();
		availableSkills.removeAll(skills);
		skills.addAll(availableSkills);
	}

	@Override
	public void removeSkillsWithZeroId(List<Skill> skills) {
		Iterator<Skill> it = skills.iterator();
		while (it.hasNext()) {
			if (it.next().getId() == 0) {
				it.remove();
			}
		}
	}
}
