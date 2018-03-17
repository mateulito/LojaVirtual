package persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder(); 
			serviceRegistry.applySettings(cfg.getProperties());
			StandardServiceRegistry service = serviceRegistry.build();
			
			return cfg.buildSessionFactory(service);
		} catch (Throwable e){
			System.out.println("SessionFactory bricou na inicialização..." + e);
			throw new ExceptionInInitializerError(e);
		}
		
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
		
	}
	
}
