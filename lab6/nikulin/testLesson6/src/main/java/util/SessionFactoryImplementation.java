package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class SessionFactoryImplementation {

    private static SessionFactory sessionFactory;;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory getSessionFactory(){
        Configuration configuration=new Configuration();
        configuration.configure("/hibernate/hibernate.cfg.xml");
        if(serviceRegistry==null) {
            try {
                serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            } catch (Throwable x) {
                System.err.println(x);
            }
        }
        if(sessionFactory==null){
            try {
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);}
            catch (Throwable ex) {
                System.err.println(ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

}
