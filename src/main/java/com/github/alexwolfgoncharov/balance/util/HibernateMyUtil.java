package com.github.alexwolfgoncharov.balance.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HibernateMyUtil {
	@Autowired
	   private static  SessionFactory sessionFactory = buildSessionFactory();
//	    private static ServiceRegistry serviceRegistry;
	 
	    private static SessionFactory buildSessionFactory() {
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();

			}

			return sessionFactory;
		}
	 
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	 
	    public static void shutdown() {
	        // Чистит кеш и закрывает соединение с БД
	        getSessionFactory().close();
	    }
	    
}
