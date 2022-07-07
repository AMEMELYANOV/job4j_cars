package ru.job4j.cars.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Advert;

import java.util.List;
import java.util.function.Function;

@Repository
public class AdRepository implements AdvertRep {

    private final SessionFactory sf;

    public AdRepository(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T execute(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Advert> findAllAdverts() {
        return this.execute(session -> session.createQuery(
                        "select a from Advert a "
                                + "join fetch a.car "
                                + "join fetch a.user order by a.created desc",
                        Advert.class)
                .list());
    }

    @Override
    public Advert save(Advert advert) {
        return this.execute(
                session -> {
                    session.save(advert);
                    return advert;
                }
        );
    }

    @Override
    public Advert findAdvertById(Integer advertId) {
        return this.execute(
                session -> {
                    Query query = session.createQuery("from Advert where id = :advertId");
                    query.setParameter("advertId", advertId);
                    return (Advert) query.uniqueResult();
                }
        );
    }

    @Override
    public List<Advert> findAdvertsByUserId(int id) {
        return this.execute(session -> {
                    Query query = session.createQuery("from Advert a "
                            + "join fetch a.car "
                            + "join fetch a.user "
                            + "where users_id = : id  order by a.created desc");
                    query.setParameter("id", id);
                    return query.list();
                }
        );
    }

    @Override
    public boolean deleteAdvertById(Integer advertId) {
        return this.execute(
                session -> session.createQuery("delete from Advert where id = :advertId")
                        .setParameter("advertId", advertId).executeUpdate() > 0);
    }

    @Override
    public Advert update(Advert advert) {
        return this.execute(
                session -> {
                    session.update(advert);
                    return advert;
                }
        );
    }

    public List<Advert> filterAdverts(String bodyType, String transmissionname,
                                      String drive, String fuel) {
        return this.execute(session -> {
                    Criteria criteria = session.createCriteria(Advert.class, "Advert");
                    criteria.createCriteria("Advert.car", "car");
                    criteria.createCriteria("Advert.car.engine", "engine");
                    criteria.createCriteria("Advert.car.transmission", "transmission");
                    criteria.createCriteria("Advert.car.bodyType", "bodyType");
                    if (!bodyType.isBlank()) {
                        criteria.add(Restrictions.eq("bodyType.bodytypename", bodyType));
                    }
                    if (!transmissionname.isBlank()) {
                        criteria.add(Restrictions.eq("transmission.transmissionname",
                                transmissionname));
                    }
                    if (!drive.isBlank()) {
                        criteria.add(Restrictions.eq("car.drive", drive));
                    }
                    if (!fuel.isBlank()) {
                        criteria.add(Restrictions.eq("engine.fuel", fuel));
                    }
                    criteria.addOrder(Order.desc("Advert.created"));
                    return criteria.list();
                }
        );
    }
}
