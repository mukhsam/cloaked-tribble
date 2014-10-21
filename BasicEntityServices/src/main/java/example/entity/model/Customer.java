package example.entity.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.UuidGenerator;
/**
 * 
 * @author SamMukherjee
 *
 */

/*
 *  
create table customer (
id VARCHAR (100),
firstname VARCHAR (200),
lastname  VARCHAR (200),
email VARCHAR (200)
)
 */

@Entity
@Table(name="customer")
public class Customer {
	@UuidGenerator(name="uuidGen")
	@Id
	@Column(name="id")
	private String id;
	@Column(name="firstName")
	private String firstName;
	@Column(name="lastName")
	private String lastName;
	@Column(name="email")
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}