package example.entity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * We will pull address or customer based on the customer or Address Id
 * 
 * @author SamMukherjee
 * 
 */
/*
 * 
 * create table customer_to_address ( 
id varchar (100), 
address_id varchar(100), 
customer_id varchar (100),
dateadded DATETIME,
datemodified DATETIME )
 */

@NamedQueries({ @NamedQuery(name = "Customer.FindAddress", query = "SELECT c.address FROM CustomerToAddress c where c.customer.email = :email")

})
@Entity
@Table(name = "customer_to_address")
public class CustomerToAddress extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
