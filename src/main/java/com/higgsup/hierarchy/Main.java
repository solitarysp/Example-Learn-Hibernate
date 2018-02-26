package com.higgsup.hierarchy;/*
  By Chi Can Em  26-01-2018
 */


import com.higgsup.hierarchy.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {
    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {
        Session session = null;
        Configuration configuration = new Configuration().configure("hierarchy/hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();

        getALlSessionQuery(session);

    }

    public static void getAll(Session session) {
    /*    Query query = session.createQuery("SELECT c FROM Company as c where c.id=2");
        query.setCacheable(true);*/
      /*  Company company = (Company) query.getSingleResult();*/
        for (int i = 0; i < 10; i++) {
            Company company1 = session.load(Company.class, new Integer(2));
            System.out.println(company1.getAddress());

        }
        session.close();
        Session session1 = sessionFactory.openSession();
        Company company1 = session1.load(Company.class, new Integer(2));
        System.out.println(company1.getAddress());
        System.out.println("===========");
        Session session12 = sessionFactory.getCurrentSession();
        Transaction tx = session12.beginTransaction();
        Company company12 = session12.load(Company.class, new Integer(2));
        System.out.println(company12.getAddress());
    }

    public static void save(Session session) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Company companyName = new Company();


        Set<ConstraintViolation<Company>> constraintViolations =
                validator.validate(companyName);
        Integer numberError = constraintViolations.size();
        if (numberError == 0) {
            System.out.println("có thể thêm ");
            Transaction tx = session.beginTransaction();
            session.save(companyName);
            tx.commit();
        } else {
            System.out.println("không thể thêm có " + numberError + " lỗi");


            for (Iterator<ConstraintViolation<Company>> it = constraintViolations.iterator(); it.hasNext(); ) {
                ConstraintViolation<Company> violation = it.next();
                System.out.println(violation.getPropertyPath() + violation.getMessage());
            }
        }

    }


    public static void getALlSessionQuery(Session session) {
        Query query = session.createQuery("select c from Company as c ").setCacheable(true);
        List<Company> companys = query.getResultList();
        for (Company company : companys
                ) {
            System.out.println(company.getAddress());
        }
        System.out.println("-=================");
        Session session1=sessionFactory.getCurrentSession();
        Transaction tx=session1.beginTransaction();
        Query query1 = session1.createQuery("select c from Company as c ").setCacheable(true);
        List<Company> companys1 = query1.getResultList();
        for (Company company : companys1
                ) {
            System.out.println(company.getAddress());
        }
    }
}
