package example.entity.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * From the Spec: Mapped Superclasses An entity may inherit from a superclass
 * that provides persistent entity state and mapping information, but which is
 * not itself an entity. Typically, the purpose of such a mapped superclass is
 * to define state and mapping information that is common to multiple entity
 * classes.
 * 
 * @author SamMukherjee
 * 
 */
  

@MappedSuperclass
public abstract class AbstractEntity {
	@Id
	private String id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateadded")
	private Date dateAdded;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datemodified")
	private Date dateModified;


	@PrePersist
	protected void assignPK() {
		setId(UUID.randomUUID().toString());
		this.dateModified= new Date();
		this.dateAdded = new Date();
	}
	@PreUpdate
	protected void updateDate() { 
		this.dateModified= new Date(); 
	}
	

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public Date getDateModified() {
		return dateModified;
	}
}
