package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import util.HibernateUtil;
import service.*;
import beans.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TestApp {

    public static void main(String[] args) throws Exception {

        // 🔹 Load Spring configuration from HibernateUtil
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateUtil.class);

        // 🔹 Get service beans
        HommeService hommeService = context.getBean(HommeService.class);
        FemmeService femmeService = context.getBean(FemmeService.class);
        MariageService mariageService = context.getBean(MariageService.class);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // ───────────────────────────────
        // 🔹 Création des données
        // ───────────────────────────────
        Homme h = new Homme();
        h.setNom("SAFI");
        h.setPrenom("SAID");
        hommeService.create(h);

        Femme f1 = new Femme("SALIMA", "RAMI");
        Femme f2 = new Femme("AMAL", "ALI");
        Femme f3 = new Femme("WAFA", "ALAOUI");
        Femme f4 = new Femme("KARIMA", "ALAMI");

        femmeService.create(f1);
        femmeService.create(f2);
        femmeService.create(f3);
        femmeService.create(f4);

        // ───────────────────────────────
        // 🔹 Création des mariages
        // ───────────────────────────────
        Mariage m1 = new Mariage();
        m1.setHomme(h);
        m1.setFemme(f1);
        m1.setDateDebut(sdf.parse("03/09/1990"));
        m1.setNbrEnfant(4);
        mariageService.create(m1);

        Mariage m2 = new Mariage();
        m2.setHomme(h);
        m2.setFemme(f2);
        m2.setDateDebut(sdf.parse("03/09/1995"));
        m2.setNbrEnfant(2);
        mariageService.create(m2);

        Mariage m3 = new Mariage();
        m3.setHomme(h);
        m3.setFemme(f3);
        m3.setDateDebut(sdf.parse("04/11/2000"));
        m3.setNbrEnfant(3);
        mariageService.create(m3);

        Mariage m4 = new Mariage();
        m4.setHomme(h);
        m4.setFemme(f4);
        m4.setDateDebut(sdf.parse("03/09/1989"));
        m4.setDateFin(sdf.parse("03/09/1990"));
        m4.setNbrEnfant(0);
        mariageService.create(m4);

        // ───────────────────────────────
        // 🔹 AFFICHAGE FORMATÉ
        // ───────────────────────────────
        System.out.println("Nom : " + h.getNom() + " " + h.getPrenom());
        System.out.println("Mariages En Cours :");

        List<Mariage> mariages = mariageService.findAll();
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");

        int i = 1;
        for (Mariage m : mariages) {
            if (m.getHomme().getId() == h.getId() && m.getDateFin() == null) {
                System.out.printf("%d. Femme : %-10s %-10s Date Début : %-10s Nbr Enfants : %d%n",
                        i++, m.getFemme().getPrenom(), m.getFemme().getNom(),
                        out.format(m.getDateDebut()), m.getNbrEnfant());
            }
        }

        System.out.println("\nMariages échoués :");
        i = 1;
        for (Mariage m : mariages) {
            if (m.getHomme().getId() == h.getId() && m.getDateFin() != null) {
                System.out.printf("%d. Femme : %-10s %-10s Date Début : %-10s%n",
                        i++, m.getFemme().getPrenom(), m.getFemme().getNom(),
                        out.format(m.getDateDebut()));
                System.out.printf("   Date Fin : %-10s Nbr Enfants : %d%n",
                        out.format(m.getDateFin()), m.getNbrEnfant());
            }
        }

        // 🔹 Close context
        context.close();
    }
}
