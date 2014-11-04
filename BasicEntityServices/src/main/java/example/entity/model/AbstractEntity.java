package example.entity.model;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

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
	 

	@PrePersist
	protected void assignPK() {
		setId(UUID.randomUUID().toString());
	}

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
