package example.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author SamMukherjee
 * 
 */

/*
 * create table address ( 
id varchar (100), 
city varchar (100), 
state char (2),
zip int (5) ,
dateadded DATETIME,
datemodified DATETIME
) 
 */
@Entity
@Table(name = "address")
public class Address extends AbstractEntity {

	@Column(name = "city")
	private String city;
	// TODO Change this to Enum
	@Column(name = "state")
	private String state;
	@Column(name = "zip")
	private int zip;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

}
