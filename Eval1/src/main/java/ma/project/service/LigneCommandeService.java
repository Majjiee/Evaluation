package ma.project.service;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.project.classes.LigneCommandeProduit;
import ma.project.dao.IDao;

@Service
@Transactional
public class LigneCommandeService implements IDao<LigneCommandeProduit> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean create(LigneCommandeProduit ligne) {
		sessionFactory.getCurrentSession().save(ligne);
		return true;
	}

	@Override
	public boolean delete(LigneCommandeProduit ligne) {
		sessionFactory.getCurrentSession().delete(ligne);
		return true;
	}

	@Override
	public boolean update(LigneCommandeProduit ligne) {
		sessionFactory.getCurrentSession().update(ligne);
		return true;
	}

	@Override
	public LigneCommandeProduit getById(int id) {
		return sessionFactory.getCurrentSession().get(LigneCommandeProduit.class, id);
	}

	@Override
	public List<LigneCommandeProduit> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from LigneCommandeProduit", LigneCommandeProduit.class)
				.list();
	}
}
