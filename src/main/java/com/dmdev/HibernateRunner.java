package com.dmdev;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.Birthday;
import com.dmdev.entity.Company;
import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.sql.SQLException;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Slf4j
public class HibernateRunner {


    public static void main(String[] args) throws SQLException {
        debugMethod();
    }

    public static void debugMethod(){
/*
        User user = User.builder()
                .userName("ivan@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Petr")
                        .lastName("Petrov")
                        .birthDate(new Birthday(LocalDate.of(2000,1,2)))
                        .build())
                .company(company)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session1 = sessionFactory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();

                session1.save(user);

                session1.getTransaction().commit();
            }
        }*/
    }

    public static void lesson23(){
        Company company = Company.builder()
                .name("Amazon")
                .build();

        User user = User.builder()
                .userName("ivan@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Petr")
                        .lastName("Petrov")
                        .birthDate(new Birthday(LocalDate.of(2000,1,2)))
                        .build())
                .company(company)
                .build();
        log.info("User in transient state, object = {}", user);
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session1 = sessionFactory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();

                session1.save(user);

                session1.getTransaction().commit();
            }
        }


    }

   /* public static void lesson_14() {
        User user = User.builder()
                .userName("Ivan@gmail.com")
                .firstName("Ivan")
                .lastName("Ivanov")
                .build();
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();
                session1.saveOrUpdate(user);

                session1.getTransaction().commit();
            }

            try (Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();
                user.setFirstName("Sveta");
//                session2.delete(user);
//                refresh
                User freshUser = session2.get(User.class, user.getUserName());
//                user.setFirstName(freshUser.getFirstName());
//                user.setLastName(freshUser.getLastName());
//                session2.refresh(user);
//                merge
                Object merge = session2.merge(user);
                session2.getTransaction().commit();
            }
        }
    }*/

    public static void lesson15_22() {
        User user = User.builder()
                .userName("petr2@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Petr")
                        .lastName("Petrov")
                        .birthDate(new Birthday(LocalDate.of(2000,1,2)))
                        .build())
                .build();
        log.info("User in transient state, object = {}", user);
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session1 = sessionFactory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();
                log.trace("Transaction begin, transaction: {}", transaction);

                session1.saveOrUpdate(user);

                log.trace("User in persistance state, user:{}, session: {}", user, session1);
                session1.getTransaction().commit();
                log.warn("User in detached state, user:{}, session closed: {}", user, session1);
                try (Session session = sessionFactory.openSession()){
                    PersonalInfo key = PersonalInfo.builder()
                            .firstName("Petr")
                            .lastName("Petrov")
                            .birthDate(new Birthday(LocalDate.of(2000, 1, 2)))
                            .build();
                    User user1 = session.get(User.class, key);
                    System.out.println();
                }
            } catch (Exception exception) {
                log.error("Some Exception", exception);
                throw exception;
            }

        }


    }

    /*public void lesson1_13() {
        // BlockingQueue<Connection> pool = null
        // Connection connection = DriverManager
        //        .getConnection("db.url", "db.username", "db.password");
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
//            User user = User.builder()
//                    .userName("ivan9@gmail.com")
//                    .firstName("Ivan1")
//                    .lastName("Ivanov")
//                    .birthDate(new Birthday(LocalDate.of(2000, 1, 18)))
//                    .role(Role.ADMIN)
//                    .info("""
//                            {
//                                "name":"Ivan",
//                                "id": 25
//                            }
//                            """)
//                    .build();
//            session.delete(user);
            User user = session.get(User.class, "ivan@gmail.com");

            session.getTransaction().commit();
        }
    }*/
}
