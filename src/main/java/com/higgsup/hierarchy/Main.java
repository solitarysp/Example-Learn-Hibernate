package com.higgsup.hierarchy;/*
  By Chi Can Em  26-01-2018
 */


import com.higgsup.hierarchy.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class Main {
    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {
        Session session = null;
        Configuration configuration = new Configuration().configure("hierarchy/hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();

        getAll(session);

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

        Session session12 = sessionFactory.getCurrentSession();
        System.out.println(session12.getCacheMode());
    }

    public static void save(Session session) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Company companyName = new Company();
        companyName.setName("thanh111");
        //session.save(companyName);

        Set<ConstraintViolation<Company>> constraintViolations =
                validator.validate(companyName);
        constraintViolations.size();

    }
}
