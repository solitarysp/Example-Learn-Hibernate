package com.higgsup.test1;/*
  By Chi Can Em  26-01-2018
 */

import com.higgsup.test1.model.company;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;

import java.util.List;


public class Main {
    static SessionFactory sessionFactory = null;

    public static void main(String[] args) {
        Session session = null;
        Session session2 = null;

        Configuration configuration = new Configuration().configure("test1/hibernate.cfg.xml");

        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        //save(session);
        //selectCompanyWithHQL(session);
        //  selectCompanyWithHQL(session);
        //selectCompanySQL(session);
        // testgetAllNamedQuery(session);
        //addScalar(session);
        testCustomObj(session);
        // selectCompanyWithHQLJoin(session);
        //selectCompanyWithHQLAggregate(session);
        //selectCompanyWithGet(session);
        //selectCompanyWithLoad(session);
        //save(session);
        //testFirstCache(session);
        //testsecondCache(session, session2);
        //testsecondCache2(session, session2);
        // saveCurrentSession(session,sessionFactory);
        //testFirstCache(session);
    }

    private static void testCustomObj(Session session) {
        Query query=session.createQuery("SELECT sum(c.id) as total ,c from company as c");
        List<company> companies=query.getResultList();
        System.out.println("d");
    }

    private static void testsecondCache(Session session, Session session2) {
        company company = session.load(com.higgsup.test1.model.company.class, new Integer(1));
        System.out.println(company.getAddress());

        company company1 = session.load(com.higgsup.test1.model.company.class, new Integer(1));
        System.out.println(company1.getAddress());
        session2 = sessionFactory.openSession();
        session.close();

        company company2 = session2.load(com.higgsup.test1.model.company.class, new Integer(1));
        System.out.println(company2.getAddress());
        session2.close();


    }

    private static void testsecondCache2(Session session, Session session2) {
        Query query = session.createQuery("select c from company as c where c.id=1").setCacheable(true);

        query.getSingleResult();

        query = session.createQuery("select c from company as c where c.id=1").setCacheable(true);
        query.getSingleResult();
        session.close();
        session2 = sessionFactory.openSession();
        query = session2.createQuery("select c from company as c where c.id=1").setCacheable(true);
        query.getSingleResult();


        query = session2.createQuery("select c from company as c where c.id=1").setCacheable(true);
        query.getSingleResult();
/*
        company company1 = session.load(com.higgsup.test1.model.company.class, new Integer(1));
        System.out.println(company1.getAddress());
        session2 = sessionFactory.openSession();
        session.close();

        company company2 = session2.load(com.higgsup.test1.model.company.class, new Integer(1));
        System.out.println(company2.getAddress());
        session2.close();*/


    }


    public static void saveCurrentSession(Session session, SessionFactory sessionFactory) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            company company = new company();
            company.setId(1);
            company.setName("test11");
            company.setAddress("test add111");
            session.save(company);
            System.out.println(sessionFactory.getCurrentSession().getCacheMode());
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void addScalar(Session session) {
        String sql = "SELECT sum(idcompany) as total FROM company";
        Query query = session.createNativeQuery(sql).addScalar("total", IntegerType.INSTANCE);
        System.out.println(query.uniqueResult());
    }

    public static void save(Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            company company = new company();
            company.setId(1);
            company.setName("test11");
            company.setAddress("test add111");
            session.save(company);
            // session.persist(company);
            tx.commit();
        } catch (Exception e) {

        } finally {
            session.close();
        }
    }

    public static void savepersist(Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            company company = new company();
            company.setName("test");
            company.setAddress("test add111");
            session.persist(company);
            tx.commit();
        } catch (Exception e) {

        } finally {
            session.close();
        }
    }

    public static void selectCompanyWithCriteria(Session session) {

        Criteria cr = session.createCriteria(company.class);
        cr.add(Restrictions.gt("name", "test"));
        List<company> companyList = cr.list();
        for (company company : companyList
                ) {
            System.out.println(company.getName());
        }
    }


    public static void selectCompanyWithHQL(Session session) {
        try {
            Query query = session.createQuery("SELECT c FROM company as c");
            List<company> companyList = query.getResultList();
            for (company company : companyList
                    ) {
                System.out.println(company.getName());
            }
       /*     Transaction tx = null;

            tx = session.beginTransaction();
            Query query = session.createQuery("update company as c set c.name='test_update' where c.id=34");
            query.executeUpdate();
            tx.commit();*/
        } catch (Exception e) {
            System.out.println("d");
        } finally {
            session.close();
        }
    }

    public static void selectCompanyWithHQLJoin(Session session) {
        try {
            Query query = session.createQuery("SELECT c FROM company as c join c.employee as b where b.name='1'");
            List<company> companyList = query.getResultList();
            for (company company : companyList
                    ) {
                System.out.println(company.getName() + " " + company.getAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();

        }
    }

    public static void selectCompanyWithHQLAggregate(Session session) {
        try {
            Query query = session.createQuery("SELECT count(c.name) as cid FROM company as c");
            System.out.println(query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();

        }
    }

    public static void selectCompanyWithGet(Session session) {
        System.out.println(session.get(company.class, new Integer(2)).getAddress());

    }

    public static void selectCompanyWithLoad(Session session) {
        /*company company = session.load(company.class, new Integer(1));
        System.out.println(company.getAddress());*/
        company company = session.get(company.class, new Integer(1));
    }

    public static void selectCompanySQL(Session session) {
        try {
            Query query = session.createNativeQuery("SELECT * FROM  company", company.class);
//            SQLQuery query = session.createSQLQuery("SELECT * FROM  company");
//            query.addEntity(company.class);
            List<company> companyList = query.getResultList();
            for (company company : companyList
                    ) {
                System.out.println(company.getName());
            }
            System.out.println("=============");
            System.out.println("Named SQL ");
            String sql = "SELECT * FROM company WHERE idcompany=:id";
            query = session.createSQLQuery(sql);
           /* query.addEntity(company.class);*/
            query.setParameter("id", 1);
            List<company> companyList1 = query.getResultList();
            for (company company : companyList1
                    ) {
                System.out.println(company.getName());
            }

            System.out.println("=============");
/*
            sql = "SELECT idcompany,address FROM company WHERE idcompany=:id";
            query = session.createSQLQuery(sql);
            query.addEntity(company.class);
            query.setParameter("id", 1);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List companyList2 = query.getResultList();
            for (Object company : companyList2
                    ) {
                Map row = (Map) company;
                System.out.println(row.get("address"));
            }*/
        } catch (Exception e) {
            System.out.println();
        } finally {
            session.close();
        }
    }


    public static void testFirstCache(Session session) {

        company company = session.load(com.higgsup.test1.model.company.class, new Integer(1));
        System.out.println(company.getAddress());

        company company1 = session.load(com.higgsup.test1.model.company.class, new Integer(1));
        System.out.println(company1.getAddress());


        company company2 = session.load(com.higgsup.test1.model.company.class, new Integer(1));
        System.out.println(company2.getAddress());

        company company3 = session.load(com.higgsup.test1.model.company.class, new Integer(1));
        System.out.println(company3.getAddress());
        session.close();
    }

    public static void testgetAllNamedQuery(Session session) {
        try {
            Query query = session.getNamedQuery("getAll");
            List<company> companyList = query.getResultList();
            for (company company : companyList
                    ) {
                System.out.println(company.getName());
            }
        } catch (Exception e) {
            System.out.println("d");
        } finally {
            session.close();

        }
    }

}
