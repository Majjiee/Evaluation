package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestApp {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        EmployeService employeService = context.getBean(EmployeService.class);
        ProjetService projetService = context.getBean(ProjetService.class);
        TacheService tacheService = context.getBean(TacheService.class);
        EmployeTacheService employeTacheService = context.getBean(EmployeTacheService.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 🧑‍💼 Employés
        Employe e1 = new Employe("Sabri", "Majdouline", "0600000000");
        Employe e2 = new Employe("Raj", "Zik", "060000000");
        employeService.create(e1);
        employeService.create(e2);

        // 📁 Projet
        Projet projet = new Projet();
        projet.setNom("Gestion de stock");
        projet.setDateDebut(sdf.parse("2013-01-14"));
        projet.setChef(e1);
        projetService.create(projet);

        // 📝 Tâches
        Tache t1 = new Tache("Analyse", sdf.parse("2013-02-10"), sdf.parse("2013-02-20"), 1000f, projet);
        Tache t2 = new Tache("Conception", sdf.parse("2013-03-10"), sdf.parse("2013-03-15"), 1500f, projet);
        Tache t3 = new Tache("Développement", sdf.parse("2013-04-10"), sdf.parse("2013-04-25"), 2000f, projet);
        tacheService.create(t1);
        tacheService.create(t2);
        tacheService.create(t3);

        // 🤝 Assignation des tâches
        employeTacheService.create(new EmployeTache(sdf.parse("2013-02-10"), sdf.parse("2013-02-20"), e2, t1));
        employeTacheService.create(new EmployeTache(sdf.parse("2013-03-10"), sdf.parse("2013-03-15"), e2, t2));
        employeTacheService.create(new EmployeTache(sdf.parse("2013-04-10"), sdf.parse("2013-04-25"), e2, t3));

        // 🖨️ Affichage du projet formaté
        afficherProjet(projet, employeTacheService);

        ((AnnotationConfigApplicationContext) context).close();
    }

    private static void afficherProjet(Projet projet, EmployeTacheService employeTacheService) {
        SimpleDateFormat frenchDate = new SimpleDateFormat("d MMMM yyyy", Locale.FRENCH);
        String dateDebut = frenchDate.format(projet.getDateDebut());

        System.out.printf("Projet : %-3d   Nom : %-20s   Date début : %s%n",
                projet.getId(), projet.getNom(), dateDebut);
        System.out.println("Liste des tâches :");
        System.out.printf("%-5s %-15s %-20s %-20s%n",
                "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");

        List<EmployeTache> lignes = employeTacheService.getAll();
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");

        for (EmployeTache et : lignes) {
            if (et.getTache().getProjet().getId() == projet.getId()) {
                System.out.printf("%-5d %-15s %-20s %-20s%n",
                        et.getTache().getId(),
                        et.getTache().getNom(),
                        simple.format(et.getDateDebutReelle()),
                        simple.format(et.getDateFinReelle()));
            }
        }
        System.out.println();
    }
}
