package by.htp.spring_tags.dao.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.cj.api.jdbc.Statement;

import by.htp.spring_tags.dao.exception.DaoException;
import by.htp.spring_tags.domain.Address;

public class AddressDaoImpl implements AddressDao {
	private static String REQ_ADD_ADDRESS = "INSERT INTO address (country, city) VALUES (?, ?);";

	@Autowired
	DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int addAddress(Address address) throws DaoException {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			prepStatement = connection.prepareStatement(REQ_ADD_ADDRESS, Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, address.getCountry());
			prepStatement.setString(2, address.getCity());
			prepStatement.execute();
			resultSet = prepStatement.getGeneratedKeys();

			if (resultSet.next()) {

				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new DaoException("Error getting skill by id", e);
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

		return 0;
	}
}
