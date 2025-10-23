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
import beans.Homme;
import beans.Mariage;
import dao.IDao;

@Service
@Transactional
public class HommeService implements IDao<Homme> {

	@Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(Homme h) {
        sessionFactory.getCurrentSession().save(h);
        return true;
    }

    @Override
    public boolean delete(Homme h) {
        sessionFactory.getCurrentSession().delete(h);
        return true;
    }

    @Override
    public boolean update(Homme h) {
        sessionFactory.getCurrentSession().update(h);
        return true;
    }

    @Override
    public Homme findById(int id) {
        return sessionFactory.getCurrentSession().get(Homme.class, id);
    }

    @Override
    public List<Homme> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Homme", Homme.class)
                .list();
    }


    public List<Femme> findEpouses(int hommeId) {
        String hql = """
            select f
            from Mariage m
            join m.femme f
            where m.homme.id = :hommeId
        """;
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Femme.class)
                .setParameter("hommeId", hommeId)
                .list();
    }


    public List<Object[]> findMariagesDetails(int hommeId) {
        String hql = """
            select f.nom, f.prenom, m.dateDebut, m.nbrEnfant
            from Mariage m
            join m.femme f
            where m.homme.id = :hommeId
            and m.dateFin = null
            order by m.dateDebut
        """;
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Object[].class)
                .setParameter("hommeId", hommeId)
                .list();
    }


    public List<Object[]>findMariagesEchoues (int hommeId) {
        String hql = """
            select f.nom, f.prenom, m.dateDebut, m.dateFin, m.nbrEnfant
            from Mariage m
            join m.femme f
            where m.homme.id = :hommeId
            and m.dateFin != null 
            order by m.dateDebut
        """;
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Object[].class)
                .setParameter("hommeId", hommeId)
                .list();
    }


    public List<Homme> countHommesMarieQuatreFemmesEntreDates(Date dateDebut, Date dateFin) {
        String hql = """
        select h
        from Homme h
        join h.mariages m
        where m.dateFin IS NULL
        group by h
        having count(m) = 4
    """;
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Homme.class)
                .list();
    }
}
