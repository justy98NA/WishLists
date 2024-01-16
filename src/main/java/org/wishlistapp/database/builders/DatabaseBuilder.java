//package org.wishlistapp.database.builders;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.hibernate.cfg.Configuration;
//
//public class DatabaseBuilder {
//
//    private EntityManagerFactory emf;
//
//    public DatabaseBuilder() {
//    }
//
//    // close the database
//    public void close() {
//        if (emf != null) {
//            emf.close();
//        }
//    }
//
//    // if tables exist, drop them and create new ones
//    public void dropAndCreateTables() {
//        Configuration config = new Configuration();
//        config.configure();
//        config.setProperty("hibernate.hbm2ddl.auto", "create");
//        emf = config.buildSessionFactory();
//    }
//}
