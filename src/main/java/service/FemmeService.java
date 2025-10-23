package service;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import beans.Femme;
import beans.Mariage;
import dao.IDao;

@Service
@Transactional
public class FemmeService implements IDao<Femme> {

	@Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(Femme f) {
        sessionFactory.getCurrentSession().save(f);
        return true;
    }

    @Override
    public boolean delete(Femme f) {
        sessionFactory.getCurrentSession().delete(f);
        return true;
    }

    @Override
    public boolean update(Femme f) {
        sessionFactory.getCurrentSession().update(f);
        return true;
    }

    @Override
    public Femme findById(int id) {
        return sessionFactory.getCurrentSession().get(Femme.class, id);
    }

    @Override
    public List<Femme> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Femme", Femme.class)
                .list();
    }


    public int countEnfantsEntreDates(int femmeId, Date dateDebut, Date dateFin) {
        Object result =  sessionFactory.getCurrentSession()
                .createNamedQuery("Femme.countEnfantsEntreDates")
                .setParameter("femmeId", femmeId)
                .setParameter("dateDebut", dateDebut)
                .setParameter("dateFin", dateFin)
                .getSingleResult();
        return result != null ? ((Number) result).intValue() : 0;
    }

    public List<Femme> findFemmesMarieesDeuxFois() {
        return sessionFactory.getCurrentSession()
                .createNamedQuery("Femme.findMarieesDeuxFois", Femme.class)
                .list();
    }


    public Femme findFemmeLaPlusAgee() {
        String hql = """
            from Femme f
            where f.dateNaissance = (
                select min(f2.dateNaissance) from Femme f2
            )
        """;
        List<Femme> results = sessionFactory.getCurrentSession()
                .createQuery(hql, Femme.class)
                .setMaxResults(1)
                .list();

        return results.isEmpty() ? null : results.get(0);
    }
}
