package by.htp.spring_tags.dao.address;

import java.sql.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.cj.api.jdbc.Statement;

import by.htp.spring_tags.domain.Address;

@Repository
public class AddressDaoImpl implements AddressDao {
	private static String REQ_ADD_ADDRESS = "INSERT INTO address (country, city) VALUES (?, ?);";

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int saveAddress(Address address) throws RuntimeException {
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

		return 0;
	}
}
