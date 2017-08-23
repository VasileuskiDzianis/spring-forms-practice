package by.htp.spring_tags.dao.skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.spring_tags.domain.Skill;

@Repository
public class SkillDaoImpl implements SkillDao {
	private static String REQ_GET_SKILL_BY_ID = "SELECT * FROM skill WHERE skillId=?;";
	private static String REQ_GET_ALL_SKILLS = "SELECT * FROM skill;";
	
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void saveSkill(Skill skill) {
		// TODO Auto-generated method stub

	}

	@Override
	public Skill findSkillById(int id) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			prepStatement = connection.prepareStatement(REQ_GET_SKILL_BY_ID);
			prepStatement.setInt(1, id);
			resultSet = prepStatement.executeQuery();

			if (resultSet.next()) {
				Skill skill = new Skill();
				skill.setId(resultSet.getInt(COLUMN_SKILL_ID));
				skill.setSkillName(resultSet.getString(COLUMN_SKILL_NAME));

				return skill;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error getting skill by id", e);
		} finally {

			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepStatement != null) {
					prepStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("Error resources closing", e);
			}
		}

		return null;
	}

	@Override
	public void deleteSkill(Skill id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Skill> findAllSkills() {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;

		List<Skill> skills;

		try {
			connection = dataSource.getConnection();
			prepStatement = connection.prepareStatement(REQ_GET_ALL_SKILLS);
			resultSet = prepStatement.executeQuery();
			skills = new ArrayList<>();

			while (resultSet.next()) {
				Skill skill = new Skill();
				skill.setId(resultSet.getInt(COLUMN_SKILL_ID));
				skill.setSkillName(resultSet.getString(COLUMN_SKILL_NAME));
				skills.add(skill);
			}
			return skills;

		} catch (SQLException e) {

			throw new RuntimeException("Error skills list getting", e);
		} finally {

			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepStatement != null) {
					prepStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {

				throw new RuntimeException("Error resources closing", e);
			}
		}
	}
}
