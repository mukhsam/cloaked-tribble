package example.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author SamMukherjee
 * 
 */

/*
 * 
 * create table customer ( id VARCHAR (100), firstname VARCHAR (200), lastname
 * VARCHAR (200), email VARCHAR (200) ,
 * dateadded DATETIME,
 * datemodified DATETIME)
 */

@NamedQueries({ 
    @NamedQuery(name="Country.findByName",
                query="SELECT c FROM Customer c WHERE c.email = :email"),
}) 

@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity {

	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "email")
	private String email;

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
