package ma.projet.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.projet.classes.EmployeTache;
import ma.projet.dao.IDao;

@Service
@Transactional
public class EmployeTacheService implements IDao<EmployeTache> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean create(EmployeTache et) {
		sessionFactory.getCurrentSession().save(et);
		return true;
	}

	@Override
	public boolean delete(EmployeTache et) {
		sessionFactory.getCurrentSession().delete(et);
		return true;
	}

	@Override
	public boolean update(EmployeTache et) {
		sessionFactory.getCurrentSession().update(et);
		return true;
	}

	// Example: find assignments by employee
	public List<EmployeTache> findByEmployeId(int idEmploye) {
		String hql = "from EmployeTache et where et.employe.id = :idEmploye";
		return sessionFactory.getCurrentSession().createQuery(hql, EmployeTache.class)
				.setParameter("idEmploye", idEmploye).list();
	}

	// Example: find assignments by task
	public List<EmployeTache> findByTacheId(int idTache) {
		String hql = "from EmployeTache et where et.tache.id = :idTache";
		return sessionFactory.getCurrentSession().createQuery(hql, EmployeTache.class).setParameter("idTache", idTache)
				.list();
	}

	@Override
	public EmployeTache getById(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(EmployeTache.class, id);
	}

	@Override
	public List<EmployeTache> getAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from EmployeTache", EmployeTache.class).list();
	}
}
