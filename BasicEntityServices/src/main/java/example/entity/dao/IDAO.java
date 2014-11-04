package example.entity.dao;

import example.entity.annotiations.RequiresTransaction;
import example.entity.model.AbstractEntity;

public interface IDAO<T extends AbstractEntity> {
	@RequiresTransaction
	public void insert(final T entity);

	@RequiresTransaction
	public void update(final T entity);

	@RequiresTransaction
	public void delete(final T entity);

}
