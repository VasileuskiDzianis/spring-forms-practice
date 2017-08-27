package by.htp.spring_tags.dao.skill;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.htp.spring_tags.domain.Skill;

@Transactional
@Repository
public class SkillDaoImpl implements SkillDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional(readOnly=true)
	@Override
	public Skill findSkillById(int id) {

		return sessionFactory.getCurrentSession().get(Skill.class, id);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Skill> findAllSkills() {
		
		return sessionFactory.getCurrentSession().createQuery("from Skill", Skill.class).list();
	}
}
