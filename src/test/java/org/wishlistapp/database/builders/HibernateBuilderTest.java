package org.wishlistapp.database.builders;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.wishlistapp.model.Gift;
import org.wishlistapp.model.WLUser;
import org.wishlistapp.model.WishList;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class HibernateBuilderTest {

    private EntityManagerFactory emf;

    @BeforeEach
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try {
            emf = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.out.println("PROBLEM!!!!! <-----------");
            System.out.println(e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @AfterEach
    protected void tearDown() throws Exception {
        if (emf != null) {
            emf.close();
        }
    }

//    @Test
//    void saveMyFIrstObjectToTheDB() {
//        WLUser WLUser = new WLUser("Derek", "drake98");
//        Gift gift = new Gift("Teddy Bear", "www.somewhereovertheraindbow.pl", "www.imageofteddybear.pl");
//        EntityManager session = emf.createEntityManager();
//        session.getTransaction().begin();
//
//        session.persist(WLUser);
//        session.persist(gift);
//
//        session.getTransaction().commit();
//
//    }
//
//    @Test
//    void hqlFetchUser() {
//        EntityManager session = emf.createEntityManager();
//        session.getTransaction().begin();
//        List<WLUser> users = session.createQuery("select u from WLUser u", WLUser.class)
//                .getResultList();
//        users.forEach(System.out::println);
//
//        session.getTransaction().commit();
//    }
//
//    @Test
//    void saveSomeRelations() {
//        WLUser user1 = new WLUser("Justyna", "justa");
//        WLUser user2 = new WLUser("Bartek", "orzech");
//
//        WishList wl1 = new WishList("Birthday");
//        WishList wl2 = new WishList("Christmas");
//        WishList wl3 = new WishList("urodziny");
//        WishList wl4 = new WishList("swieta");
//
//        Gift g1 = new Gift("teddy", "url1", "url_imag1");
//        Gift g2 = new Gift("Barbie", "url2", "image2");
//        Set<Gift> gifts = new HashSet<>();
//        gifts.add(g1);
//        gifts.add(g2);
//        wl1.setGifts(gifts);
//        g1.setOwnerList(wl1);
//        g2.setOwnerList(wl1);
//
//        user1.getWishLists().add(wl1);
//        wl1.setOwner(user1);
//        user1.getWishLists().add(wl2);
//        wl2.setOwner(user1);
//        EntityManager session = emf.createEntityManager();
//        session.getTransaction().begin();
//
//        session.persist(user1);
//
//        session.getTransaction().commit();
//    }
}
