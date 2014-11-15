package example.entity.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import example.entity.model.Customer;
import example.entity.model.Customer_;

public class CustomerDAO extends AbstractDAO<Customer> implements ICustomerDAO {

	/**
	 * <a href="http://docs.oracle.com/javaee/6/tutorial/doc/gjivm.html">
	 * Reference </a>
	 */
	// Regular JPA Criteria Style with MetaModel
	public Customer findByEmailID(String emailId) {
		if (StringUtils.isBlank(emailId)) {
			throw new IllegalArgumentException("EmailID cannot be Null/Empty");
		}

		final CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
		final CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		// Get the root from the Entity object
		final Root<Customer> root = cq.from(Customer.class);
		Predicate predicate =cb.equal(root.get(Customer_.email), emailId);
		
		cq.where( predicate);

		final TypedQuery<Customer> typedQuery = this.getEm().createQuery(cq);
		//TODO Put a constrant
		List<Customer> list = typedQuery.getResultList();
		final Customer result = CollectionUtils.isNotEmpty(list) ?list .get(0) :null;

		return result;
	}
 
}
