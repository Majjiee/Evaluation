package ma.projet.service;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;

@Service
@Transactional
public class TacheService implements IDao<Tache> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean create(Tache t) {
		sessionFactory.getCurrentSession().save(t);
		return true;
	}

	@Override
	public boolean delete(Tache t) {
		sessionFactory.getCurrentSession().delete(t);
		return true;
	}

	@Override
	public boolean update(Tache t) {
		sessionFactory.getCurrentSession().update(t);
		return true;
	}

	public List<Tache> findTachesPrixSup1000() {
		return sessionFactory.getCurrentSession().createNamedQuery("Tache.findPrixSup1000", Tache.class).list();
	}

	public List<Tache> findTachesRealiseesEntreDates(Date dateDebut, Date dateFin) {
		String hql = """
				    select distinct t
				    from Tache t
				    join t.employeTaches et
				    where et.dateFinReelle between :d1 and :d2
				""";
		return sessionFactory.getCurrentSession().createQuery(hql, Tache.class).setParameter("d1", dateDebut)
				.setParameter("d2", dateFin).list();
	}

	@Override
	public Tache getById(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Tache.class, id);
	}

	@Override
	public List<Tache> getAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Tache", Tache.class).list();
	}
}
