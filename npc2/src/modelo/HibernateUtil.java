package modelo;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory = configuredSessionFactory();
	private static ServiceRegistry serviceRegistry;
	  
	public static SessionFactory configuredSessionFactory() throws HibernateException {  
	    Configuration configuration = new Configuration();  
	    configuration.configure();  
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();          
	    return configuration.buildSessionFactory(serviceRegistry);  
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
