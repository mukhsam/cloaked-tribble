package example.entity.dao;

import javax.persistence.EntityManager;

import example.entity.model.AbstractEntity;

/**
 * AbstracDAO which exposes the basic CRUD operation. Also using a Interface since I am going to 
 * proxy this and inject em and transaction scopes
 * 
 * @author SamMukherjee
 * 
 * @param <T>
 */
public class AbstractDAO<T extends AbstractEntity> implements IDAO <T>{

	private EntityManager em;

	
	public void insert(final T entity) {
		this.em.persist(entity);
	}
	public void update(final T entity) {
		this.em.persist(entity);

	}
	public void delete(final T entity) {
		this.em.remove(entity);
	}

	public EntityManager getEm() {
		return em;
	}
	
	//We will use Java Dynamic Proxy to set the EntotyManager on the fly. Do not make it public
	private void setEm(EntityManager em) {
		this.em = em;
	}

}
