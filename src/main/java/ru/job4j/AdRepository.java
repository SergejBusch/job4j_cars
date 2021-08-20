package ru.job4j;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import ru.job4j.model.Advertisement;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AdRepository implements AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public List<Advertisement> lastDaysAds() {
        List<Advertisement> result = new ArrayList<>();
        try (var session = sf.openSession()) {
            session.getTransaction();
            Timestamp ts = Timestamp.from(Instant.now().minus(1, ChronoUnit.DAYS));
            result = session.createQuery(
                    "from Advertisement a where a.created > :date")
                    .setParameter("date", ts)
                    .list();

            if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
                session.getTransaction().commit();
            }
            return result;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Advertisement> adsWitsPhoto() {
        List<Advertisement> result = new ArrayList<>();
        try (var session = sf.openSession()) {
            session.getTransaction();
            result = session.createQuery(
                    "from Advertisement a where a.image.size IS NOT NULL")
                    .list();

            if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
                session.getTransaction().commit();
            }
            return result;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Advertisement> getAdsByBrandName(String brand) {
        List<Advertisement> result = new ArrayList<>();
        try (var session = sf.openSession()) {
            session.getTransaction();
            result = session.createQuery(
                    "from Advertisement a where a.brand = :name")
                    .setParameter("name", brand)
                    .list();

            if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
                session.getTransaction().commit();
            }
            return result;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
