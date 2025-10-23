package ma.projet.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;

@Service
@Transactional
public class EmployeService implements IDao<Employe> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean create(Employe e) {
		sessionFactory.getCurrentSession().save(e);
		return true;
	}

	@Override
	public boolean delete(Employe e) {
		sessionFactory.getCurrentSession().delete(e);
		return true;
	}

	@Override
	public boolean update(Employe e) {
		sessionFactory.getCurrentSession().update(e);
		return true;
	}

	public List<Tache> findTachesRealiseesByEmploye(int idEmploye) {
		String hql = """
				    select distinct t
				    from Tache t
				    join t.employeTaches et
				    where et.employe.id = :idEmploye
				    and et.dateFinReelle is not null
				""";
		return sessionFactory.getCurrentSession().createQuery(hql, Tache.class).setParameter("idEmploye", idEmploye)
				.list();
	}

	public List<Projet> findProjetsGeresByEmploye(int idEmploye) {
		String hql = "from Projet p where p.chef.id = :idEmploye";
		return sessionFactory.getCurrentSession().createQuery(hql, Projet.class).setParameter("idEmploye", idEmploye)
				.list();
	}

	@Override
	public Employe getById(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Employe.class, id);
	}

	@Override
	public List<Employe> getAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Employe", Employe.class).list();
	}
}
