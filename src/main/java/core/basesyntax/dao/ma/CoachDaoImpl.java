package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            Query<Coach> withEnoughExperience = session.createQuery(
                    "FROM Coach "
                            + "WHERE experience >:experience", Coach.class);
            withEnoughExperience.setParameter("experience", years);
            return withEnoughExperience.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find coach with experience grater than " + years, e);
        }
    }
}
