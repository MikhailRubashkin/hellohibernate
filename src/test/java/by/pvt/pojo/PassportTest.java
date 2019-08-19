package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PassportTest {

    static Passport createTestPassport2(String index) {
        Passport passport = new Passport ();
        passport.setNumber (index);
        passport.setSeries (index);
        passport.setId (2);
        return passport;
    }

    @Test
    public void testSaveOrUpdate() {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().getSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(createTestPassport2("123456789"));
            session.saveOrUpdate(createTestPassport2("22334455"));
            session.saveOrUpdate(createTestPassport2("12554"));

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            assertEquals(true, false);
        }
    }

    @Test
    public void testSavePassport() {
        Session session = HibernateUtil.getInstance().getSession();
        Transaction tx = null;
        try {
            //save
            tx = session.beginTransaction();
            Passport passport = createPassport(1);
            session.saveOrUpdate(passport);
            Passport passport2 = createPassport(2);
            session.saveOrUpdate(passport2);
            tx.commit();
            session.close();
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            List<Passport> PassportList = session.createQuery("from passport").list();
            assertEquals(2, PassportList.size());
            Passport Passport1 = PassportList.get(0);
            assertEquals(passport, Passport1);
            tx.commit();
            session.close();

            //delete
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            session.delete(passport2);
            List<Passport> deleteList = session.createQuery("from passport").list();
            assertEquals(1, deleteList.size());
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private static Passport createPassport(int index) {
        Passport passport = new Passport();
        passport.setId(index);
        passport.setNumber ("55555" + index);
        passport.setSeries ("jjhjh" + index);
        return passport;
    }


}
