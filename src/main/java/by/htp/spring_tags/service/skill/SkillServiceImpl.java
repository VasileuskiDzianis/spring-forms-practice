package by.htp.spring_tags.service.skill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.spring_tags.dao.skill.SkillDao;
import by.htp.spring_tags.domain.Skill;

@Service
public class SkillServiceImpl implements SkillService {
	@Autowired
	private SkillDao skillDao;
	
	@Override
	public List<Skill> getAllSkills() {
		
		return skillDao.getAllSkills();
	}

}
