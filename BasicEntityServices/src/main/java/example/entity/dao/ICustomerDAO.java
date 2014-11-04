package example.entity.dao;

import example.entity.model.Customer;

public interface ICustomerDAO extends IDAO<Customer> {
	 Customer findByEmailID(String emailId);

}
