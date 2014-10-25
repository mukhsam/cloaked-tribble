package example.entity.dynamic.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import example.entity.annotiations.RequiresTransaction;
import example.entity.dao.AbstractDAO;
import example.entity.model.AbstractEntity;

/**
 * Instead of creating and ending transactions over and over again. We will use
 * that to set the em and begin tansaction if necessary
 * 
 * Ref : http://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html
 * 
 * @author SamMukherjee
 * 
 */
public class DynamicProxyForEM implements java.lang.reflect.InvocationHandler {

	private static final Logger logger = Logger.getLogger(DynamicProxyForEM.class.getName());

	private Object objectToBeProxied;

	private DynamicProxyForEM(final Object objectToBeProxied) {
		this.objectToBeProxied = objectToBeProxied;
	}

	public static Object getInstance(final Object objectToBeProxied) {
		return Proxy.newProxyInstance(objectToBeProxied.getClass().getClassLoader(), objectToBeProxied.getClass()
				.getInterfaces(), new DynamicProxyForEM(objectToBeProxied));
	}

	public Object invoke(Object arg0, Method method, Object[] parms) throws Throwable {

		Object returnObject = null;

		logger.info("Before calling the method = " + method.getName());

		boolean endtran = false;
		EntityManager em = null;

		try {

			if (objectToBeProxied instanceof AbstractDAO) {
				AbstractDAO<AbstractEntity> absDAO = (AbstractDAO<AbstractEntity>) objectToBeProxied;
				Method methodFromAbstractDAO = absDAO.getClass().getDeclaredMethod("setEm", EntityManager.class);
				methodFromAbstractDAO.setAccessible(true);

				EntityManagerFactory emf = Persistence.createEntityManagerFactory("basicPU");
				em = emf.createEntityManager();

				methodFromAbstractDAO.invoke(absDAO, em);

				RequiresTransaction reTran = method.getAnnotation(RequiresTransaction.class);

				if (null != reTran) {
					//Only start a Transaction if needed by the inteface contract
					em.getTransaction().begin(); 
					endtran = true;
				}
			}

			returnObject = method.invoke(objectToBeProxied, parms);

			if (null != em && endtran) {
				em.getTransaction().commit();

			}
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} catch (Exception e) {
			throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
		} finally {
			logger.info("Finishied calling the method = " + method.getName() + " of class "
					+ this.objectToBeProxied.getClass().getName());

		}

		return returnObject;
	}

}
