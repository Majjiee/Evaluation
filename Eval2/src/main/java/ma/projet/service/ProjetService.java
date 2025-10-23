package ma.projet.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;

@Service
@Transactional
public class ProjetService implements IDao<Projet> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean create(Projet p) {
		sessionFactory.getCurrentSession().save(p);
		return true;
	}

	@Override
	public boolean delete(Projet p) {
		sessionFactory.getCurrentSession().delete(p);
		return true;
	}

	@Override
	public boolean update(Projet p) {
		sessionFactory.getCurrentSession().update(p);
		return true;
	}

	public List<Tache> findTachesPlanifieesByProjet(int idProjet) {
		String hql = """
				    from Tache t
				    where t.projet.id = :idProjet
				    and t.dateDebut is not null
				    and t.dateFin is not null
				""";
		return sessionFactory.getCurrentSession().createQuery(hql, Tache.class).setParameter("idProjet", idProjet)
				.list();
	}

	public List<Tache> findTachesRealiseesByProjet(int idProjet) {
		String hql = """
				    select distinct t
				    from Tache t
				    join t.employeTaches et
				    where t.projet.id = :idProjet
				    and et.dateFinReelle is not null
				""";
		return sessionFactory.getCurrentSession().createQuery(hql, Tache.class).setParameter("idProjet", idProjet)
				.list();
	}

	@Override
	public Projet getById(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Projet.class, id);
	}

	@Override
	public List<Projet> getAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Projet", Projet.class).list();
	}


}
