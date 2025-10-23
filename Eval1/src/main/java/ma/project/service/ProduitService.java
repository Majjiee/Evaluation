package ma.project.service;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.project.classes.Produit;
import ma.project.classes.Categorie;
import ma.project.dao.IDao;

@Service
@Transactional
public class ProduitService implements IDao<Produit> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean create(Produit produit) {
		sessionFactory.getCurrentSession().save(produit);
		return true;
	}

	@Override
	public boolean delete(Produit produit) {
		sessionFactory.getCurrentSession().delete(produit);
		return true;
	}

	@Override
	public boolean update(Produit produit) {
		sessionFactory.getCurrentSession().update(produit);
		return true;
	}

	public List<Produit> findProduitsByCategorie(Categorie categorie) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Produit p where p.categorie = :categorie", Produit.class)
				.setParameter("categorie", categorie).list();
	}

	public List<Produit> findProduitsByCommande(int idCommande) {
		String hql = """
				    select p
				    from LigneCommandeProduit l
				    join l.produit p
				    join l.commande c
				    where c.id = :idCmd
				""";
		return sessionFactory.getCurrentSession().createQuery(hql, Produit.class).setParameter("idCmd", idCommande)
				.list();
	}

	public List<Produit> findProduitsCommandesEntreDates(Date dateDebut, Date dateFin) {
		String hql = """
				    select distinct p
				    from LigneCommandeProduit l
				    join l.produit p
				    join l.commande c
				    where c.date between :d1 and :d2
				""";
		return sessionFactory.getCurrentSession().createQuery(hql, Produit.class).setParameter("d1", dateDebut)
				.setParameter("d2", dateFin).list();
	}

	public int findQuantiteOfProduitsInLigne(int idProduit) {
		String hql = """
				    select l.quantite
				    from LigneCommandeProduit l
				    where l.produit.id = :idProduit
				""";
		return sessionFactory.getCurrentSession().createQuery(hql, Integer.class).setParameter("idProduit", idProduit)
				.uniqueResult();
	}

	@Override
	public Produit getById(int id) {
		return sessionFactory.getCurrentSession().get(Produit.class, id);
	}

	@Override
	public List<Produit> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Produit", Produit.class).list();
	}
}
