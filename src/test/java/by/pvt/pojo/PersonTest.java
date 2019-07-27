package by.pvt.pojo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PersonTest {
    @Test
    public void testPerson(){

        SessionFactory sessionFactory =
                new MetadataSources (
                        new StandardServiceRegistryBuilder ()
                .configure()
                .build()
                ).buildMetadata().buildSessionFactory();
        assertNotNull ( sessionFactory );
    }

}