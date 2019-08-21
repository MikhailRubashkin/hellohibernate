package by.pvt.pojo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static by.pvt.util.HibernateUtil.getInstance;
import static org.junit.Assert.assertEquals;


public class PersonTest {

    private static Person createTestDate (){
        Person person = new Person ( );
        person.setId (1);
        person.setFirstName ("FirstName");
        person.setLastName ("LastName");
        person.setDateOfBirth (new Date ( ));
        person.setGender ('a');
       // person.setPassport (1);
        return person;
    }


    @Test
    public void testSavePerson (){
        Session session = getInstance ( ).getSession ( );
        Transaction tx = null;

        try {

            tx = session.beginTransaction ( );
            Person p = createTestDate ( );
            session.save (p);
            tx.commit ( );
            session.close ( );


            session = getInstance ( ).getSession ( );
            tx = session.beginTransaction ( );
            List<Person> personList =
                    session.createQuery ("from Person").list ( );

            assertEquals (1, personList.size ( ));
            Person p2 = personList.get (0);
            assertEquals (p, p2);
            tx.commit ( );
            session.close ( );

            session = getInstance ( ).getSession ( );
            tx = session.beginTransaction ( );
            Person loadedPerson = session.load (Person.class, 1);
            assertEquals(p.getId(), loadedPerson.getId());
           // assertEquals(p, loadedPerson);
            tx.commit ( );

        } catch (Exception e) {
            if (tx != null) tx.rollback ( );
            throw e;
        } finally {
            session.close ( );
        }
    }
}