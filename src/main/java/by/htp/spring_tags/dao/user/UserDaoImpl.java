package by.htp.spring_tags.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.cj.api.jdbc.Statement;

import by.htp.spring_tags.dao.exception.DaoException;
import by.htp.spring_tags.domain.Address;
import by.htp.spring_tags.domain.Skill;
import by.htp.spring_tags.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	private static String REQ_GET_USER_BY_ID = "SELECT user.*, skill.*, address.* FROM user "
			+ "LEFT JOIN user_skill ON user.id=user_skill.user " 
			+ "LEFT JOIN skill ON user_skill.skill=skill.id "
			+ "LEFT JOIN address ON user.address=address.id WHERE user.id=?;";
	private static final String REQ_ADD_USER = "INSERT INTO user(login, password, address, locale) VALUES (?,?,?,?);";
	private static final String REQ_ADD_USER_TO_JOIN_TABLE = "INSERT INTO user_skill VALUES (?,?);";

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int saveUser(User user) {
		Connection connection = null;
		PreparedStatement statementUser = null;
		PreparedStatement statementSkill = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			statementUser = connection.prepareStatement(REQ_ADD_USER, Statement.RETURN_GENERATED_KEYS);
			statementUser.setString(1, user.getLogin());
			statementUser.setString(2, user.getPassword());
			statementUser.setInt(3, user.getAddress().getId());
			statementUser.setString(4, user.getLocale().getLanguage());
			statementUser.execute();
			resultSet = statementUser.getGeneratedKeys();

			int userId;
			
			if (resultSet.next()) {
				userId = resultSet.getInt(1);
			} else {
				throw new DaoException("Error adding user to DB");
			}
					
			statementSkill = connection.prepareStatement(REQ_ADD_USER_TO_JOIN_TABLE);
			connection.setAutoCommit(false);
			
			for(Skill skill : user.getSkills()) {
				statementSkill.setInt(1, userId);
				statementSkill.setInt(2, skill.getId());
				statementSkill.addBatch();
			}
			
			statementSkill.executeBatch();
			connection.commit();
			
			return userId;
		} catch (SQLException e) {
			throw new DaoException("Error adding user to DB", e);
		} finally {

			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statementUser != null) {
					statementUser.close();
				}
				if (statementSkill != null) {
					statementSkill.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Error resources closing", e);
			}
		}
	}

	@Override
	public User findUserById(int id) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			prepStatement = connection.prepareStatement(REQ_GET_USER_BY_ID);
			prepStatement.setInt(1, id);
			resultSet = prepStatement.executeQuery();
			User user;
			Address address;
			Skill skill;
			List<Skill> skills;

			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setLogin(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setLocale(new Locale(resultSet.getString(5)));

				address = new Address();
				address.setCity(resultSet.getString(10));
				address.setId(resultSet.getInt(8));
				address.setCountry(resultSet.getString(9));
				user.setAddress(address);

				skill = new Skill();
				skill.setId(resultSet.getInt(6));
				skill.setSkillName(resultSet.getString(7));

				skills = new ArrayList<Skill>();
				skills.add(skill);

				while (resultSet.next()) {
					skill = new Skill();
					skill.setId(resultSet.getInt(6));
					skill.setSkillName(resultSet.getString(7));
					skills.add(skill);
				}

				user.setSkills(skills);

				return user;
			}
		} catch (SQLException e) {
			throw new DaoException("Error getting user by id", e);
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
				throw new DaoException("Error resources closing", e);
			}
		}

		return null;
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

}
