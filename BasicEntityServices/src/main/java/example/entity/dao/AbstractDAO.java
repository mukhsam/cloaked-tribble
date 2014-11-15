package example.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import example.entity.model.AbstractEntity;

/**
 * AbstracDAO which exposes the basic CRUD operation. Also using a Interface
 * since I am going to proxy this and inject em and transaction scopes
 * 
 * @author SamMukherjee
 * 
 * @param <T>
 */
public abstract class AbstractDAO<T extends AbstractEntity> implements IDAO<T> {

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

	public <ENTITY extends AbstractEntity> List<ENTITY> findAll(Class <ENTITY> entity,int startIndex, int totalCount) {
		final String entityName =entity.getSimpleName();
		//All table must have dateadded
		final String JPQL = "SELECT entityName FROM " +entityName+" entityName order by entityName.dateAdded";

		Query query = em.createQuery(JPQL); 
		query.setMaxResults(totalCount);
		query.setFirstResult(startIndex);

		// Now we have to sort since otherwise it might return you the same
		// results every time

		return (List<ENTITY>) query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	// We will use Java Dynamic Proxy to set the EntotyManager on the fly.
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
