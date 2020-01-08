package org.project.Messenger.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Creating the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(org.project.Messenger.model.Message.class);
			configuration.addAnnotatedClass(org.project.Messenger.model.Comment.class);
			
			return configuration.configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	public static void shutdown() {
		// Close caches and connection pools
		sessionFactory.close();
	}
	 
}
