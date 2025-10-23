package ma.project.service;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.project.classes.Commande;
import ma.project.dao.IDao;

@Service
@Transactional
public class CommandeService implements IDao<Commande> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean create(Commande c) {
		sessionFactory.getCurrentSession().save(c);
		return true;
	}

	@Override
	public boolean delete(Commande c) {
		sessionFactory.getCurrentSession().delete(c);
		return true;
	}

	@Override
	public boolean update(Commande c) {
		sessionFactory.getCurrentSession().update(c);
		return true;
	}

	@Override
	public Commande getById(int id) {
		return sessionFactory.getCurrentSession().get(Commande.class, id);
	}

	@Override
	public List<Commande> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Commande", Commande.class).list();
	}
}
