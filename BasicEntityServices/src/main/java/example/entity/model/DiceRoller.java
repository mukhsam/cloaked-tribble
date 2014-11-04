package example.entity.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import example.entity.dao.AbstractDAO;
import example.entity.dao.CustomerDAO;
import example.entity.dao.ICustomerDAO;
import example.entity.dao.IDAO;
import example.entity.dynamic.proxy.DynamicProxyForEM;

public class DiceRoller {

	private static DiceRoller test = new DiceRoller();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		insert();
		
		Customer customer = findByCustomerName("sam.mukherjee@abc.com");

		assert (null != customer);

	}

	private static Customer findByCustomerName(String email) {
		ICustomerDAO proxy = (ICustomerDAO) DynamicProxyForEM.getInstance(new CustomerDAO());
 
		return proxy.findByEmailID(email);

	}

	private static void insert() {
		// test.instantiateEntityManager();

		IDAO proxy = (IDAO) DynamicProxyForEM.getInstance(new CustomerDAO());

		System.out.println(proxy instanceof IDAO);

		Address address = new Address();
		address.setCity("Orlando");
		address.setState("FL");
		address.setZip(32822);

		proxy.insert(address);

		Customer customer = new Customer();
		customer.setEmail("sam.mukherjee@abbd.com");
		customer.setFirstName("SammyTo");
		customer.setLastName("Mukherjee");

		proxy.insert(customer);

		CustomerToAddress customerToAddress = new CustomerToAddress();
		customerToAddress.setAddress(address);
		customerToAddress.setCustomer(customer);

		proxy.insert(customerToAddress);
	}

	private void instantiateEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("basicPU");
		EntityManager em = emf.createEntityManager();

		Address address = new Address();
		address.setCity("Orlando");
		address.setState("FL");
		address.setZip(32822);

		em.getTransaction().begin();

		em.persist(address);
		em.getTransaction().commit();

	}

	/*
	 * No Persistence provider for EntityManager named This means you have not
	 * really provided a Provider Impl. Remeber JPA is just a spec you will
	 * always need a provider like EclipseLink, Hibernate Etc
	 * <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider> Also
	 * this can occur if you do not have your persistence.xml not strating
	 * properly. rememeber the valid forat is something like this :
	 * 
	 * <persistence xmlns="http://java.sun.com/xml/ns/persistence"
	 * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemalocation=
	 * "http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_5.xsd"
	 * version="2.5"> <persistence-unit name="basicPU">
	 * <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
	 * <transaction-type>RESOURCE_LOCAL</transaction-type>
	 * 
	 * <properties> <property name="com.acme.persistence.sql-logging" value="on"
	 * /> <!-- Turns on SQL Logging --> <property
	 * name="eclipselink.logging.level" value="FINE" /> <property
	 * name="javax.persistence.jdbc.user" value="root" /> <property
	 * name="javax.persistence.jdbc.password" value="" /> <property
	 * name="javax.persistence.jdbc.url"
	 * value="jdbc:mysql://localhost:3306/personal" /> <property
	 * name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
	 * 
	 * 
	 * </properties> </persistence-unit> </persistence>
	 */

}
