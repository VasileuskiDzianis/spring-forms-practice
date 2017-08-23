package by.htp.spring_tags.dao.user;

import static by.htp.spring_tags.dao.address.AddressDao.*;
import static by.htp.spring_tags.dao.skill.SkillDao.*;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.cj.api.jdbc.Statement;

import by.htp.spring_tags.domain.*;

@Repository
public class UserDaoImpl implements UserDao {
	private static String REQ_GET_USER_BY_ID = "SELECT user.*, skill.*, address.* FROM user "
			+ "LEFT JOIN user_skill ON user.userId=user_skill.user "
			+ "LEFT JOIN skill ON user_skill.skill=skill.skillId "
			+ "LEFT JOIN address ON user.address=address.addressId WHERE user.userId=?;";
	
	private static final String REQ_ADD_USER = "INSERT INTO user(login, password, address, locale) VALUES (?,?,?,?);";
	private static final String REQ_ADD_USER_TO_JOIN_TABLE = "INSERT INTO user_skill VALUES (?,?);";

	private static final String COLUMN_USER_ID = "userId";
	private static final String COLUMN_USER_LOGIN = "login";
	private static final String COLUMN_USER_PASSWORD = "password";
	private static final String COLUMN_USER_LOCALE = "locale";

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
				throw new RuntimeException("Error adding user to DB");
			}

			statementSkill = connection.prepareStatement(REQ_ADD_USER_TO_JOIN_TABLE);
			connection.setAutoCommit(false);

			for (Skill skill : user.getSkills()) {
				statementSkill.setInt(1, userId);
				statementSkill.setInt(2, skill.getId());
				statementSkill.addBatch();
			}

			statementSkill.executeBatch();
			connection.commit();

			return userId;
		} catch (SQLException e) {
			throw new RuntimeException("Error adding user to DB", e);
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
				throw new RuntimeException("Error resources closing", e);
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
				user.setId(resultSet.getInt(COLUMN_USER_ID));
				user.setLogin(resultSet.getString(COLUMN_USER_LOGIN));
				user.setPassword(resultSet.getString(COLUMN_USER_PASSWORD));
				user.setLocale(new Locale(resultSet.getString(COLUMN_USER_LOCALE)));

				address = new Address();
				address.setCity(resultSet.getString(COLUMN_ADDRESS_CITY));
				address.setId(resultSet.getInt(COLUMN_ADDRESS_ID));
				address.setCountry(resultSet.getString(COLUMN_ADDRESS_COUNTRY));
				user.setAddress(address);

				skill = new Skill();
				skill.setId(resultSet.getInt(COLUMN_SKILL_ID));
				skill.setSkillName(resultSet.getString(COLUMN_SKILL_NAME));

				skills = new ArrayList<Skill>();
				skills.add(skill);

				while (resultSet.next()) {
					skill = new Skill();
					skill.setId(resultSet.getInt(COLUMN_SKILL_ID));
					skill.setSkillName(resultSet.getString(COLUMN_SKILL_NAME));
					skills.add(skill);
				}

				user.setSkills(skills);

				return user;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error getting user by id", e);
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
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

}
