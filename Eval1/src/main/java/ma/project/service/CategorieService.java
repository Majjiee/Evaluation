package ma.project.service;

import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import ma.project.classes.Categorie;
import ma.project.dao.IDao;

@Service
@Transactional
public class CategorieService implements IDao<Categorie> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public boolean create(Categorie Categorie) {
		sessionFactory.getCurrentSession().save(Categorie);
		return true;
	}

	@Override
	public boolean delete(Categorie Categorie) {
		sessionFactory.getCurrentSession().delete(Categorie);
		return true;
	}

	@Override
	public boolean update(Categorie Categorie) {
		sessionFactory.getCurrentSession().update(Categorie);
		return true;
	}

	@Override
	public Categorie getById(int id) {
		return sessionFactory.getCurrentSession().get(Categorie.class, id);
	}

	@Override
	public List<Categorie> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Categorie", Categorie.class).list();
	}
}
