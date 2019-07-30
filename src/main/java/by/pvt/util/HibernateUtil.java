package by.pvt.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static volatile HibernateUtil hibernateUtil;

    private SessionFactory sessionFactory;

    //private static HibernateUtil ourInstance = new HibernateUtil ( );



   /* public static HibernateUtil getInstance (){
        return ourInstance;
    }*/




    private HibernateUtil (){
        sessionFactory =
                new MetadataSources (
                        new StandardServiceRegistryBuilder ()
                                .configure()
                                .build()
                ).buildMetadata().buildSessionFactory();
    }

    public static synchronized HibernateUtil getInstance(){
        if (hibernateUtil == null) {
            hibernateUtil = new HibernateUtil ();
        }
        return hibernateUtil;
    }

    public Session getSession (){
        return sessionFactory.openSession ();
    }
}
