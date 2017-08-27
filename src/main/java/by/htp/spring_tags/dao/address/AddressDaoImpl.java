package by.htp.spring_tags.dao.address;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.htp.spring_tags.domain.Address;

@Transactional
@Repository
public class AddressDaoImpl implements AddressDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public int saveAddress(Address address) {
		
		sessionFactory.getCurrentSession().save(address);

		return address.getId();
	}
}
