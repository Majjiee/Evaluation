package ma.project.test;

import ma.project.classes.*;
import ma.project.service.*;
import ma.project.util.HibernateConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestDB {

	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

		ProduitService produitService = context.getBean(ProduitService.class);
		CommandeService commandeService = context.getBean(CommandeService.class);
		LigneCommandeService ligneService = context.getBean(LigneCommandeService.class);
		CategorieService categorieService = context.getBean(CategorieService.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Categorie cat1 = new Categorie();
		cat1.setCode("C1");
		cat1.setLibelle("Catégorie 1");

		Categorie cat2 = new Categorie();
		cat2.setCode("C2");
		cat2.setLibelle("Catégorie 2");
		categorieService.create(cat1);
		categorieService.create(cat2);

		Produit p1 = new Produit("ES12", 120f, cat1);
		Produit p2 = new Produit("ZR85", 100f, cat1);
		Produit p3 = new Produit("EE85", 200f, cat2);
		produitService.create(p1);
		produitService.create(p2);
		produitService.create(p3);

		Commande c1 = new Commande();
		c1.setDate(sdf.parse("2013-03-14")); // Example: 14 Mars 2013
		commandeService.create(c1);

		Commande c2 = new Commande();
		c2.setDate(sdf.parse("2025-10-10"));
		commandeService.create(c2);

		ligneService.create(new LigneCommandeProduit(p1, c1, 7));
		ligneService.create(new LigneCommandeProduit(p2, c1, 14));
		ligneService.create(new LigneCommandeProduit(p3, c1, 5));

		System.out.println();
		afficherCommande(c1, ligneService);

		context.close();
	}

	private static void afficherCommande(Commande commande, LigneCommandeService ligneService) {

		SimpleDateFormat frenchDate = new SimpleDateFormat("d MMMM yyyy", Locale.FRENCH);
		String formattedDate = frenchDate.format(commande.getDate());

		System.out.printf("Commande : %-3d   Date : %s%n", commande.getId(), formattedDate);
		System.out.println("Liste des produits :");
		System.out.printf("%-10s %-10s %-10s%n", "Référence", "Prix", "Quantité");

		List<LigneCommandeProduit> lignes = ligneService.getAll();
		for (LigneCommandeProduit l : lignes) {
			if (l.getCommande().getId() == commande.getId()) {
				System.out.printf("%-10s %-8.0fDH %-10d%n", l.getProduit().getReference(), l.getProduit().getPrix(),
						l.getQuantite());
			}
		}
		System.out.println();
	}
}
