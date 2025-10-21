package ma.projet;

import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.service.EmployeService;
import ma.projet.service.EmployeTacheService;
import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private ProjetService projetService;

    @Autowired
    private TacheService tacheService;

    @Autowired
    private EmployeService employeService;

    @Autowired
    private EmployeTacheService employeTacheService;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void testCreationDonnees() throws Exception {
        System.out.println("\n=== Création des données de test ===\n");

        Employe emp1 = employeService.create(new Employe("Razin", "Mohamed", "0661234567"));
        Employe emp2 = employeService.create(new Employe("Bennani", "Fatima Zahra", "0662345678"));
        Employe emp3 = employeService.create(new Employe("El Idrissi", "Youssef", "0663456789"));
        Employe emp4 = employeService.create(new Employe("Benjelloun", "Samira", "0664567890"));
        Employe emp5 = employeService.create(new Employe("Tazi", "Omar", "0665678901"));

        Projet p1 = projetService.create(new Projet("Site E-commerce", sdf.parse("01/01/2013"), sdf.parse("31/12/2013")));
        Projet p2 = projetService.create(new Projet("Application Mobile", sdf.parse("01/02/2013"), sdf.parse("30/11/2013")));
        Projet p3 = projetService.create(new Projet("Système CRM", sdf.parse("15/03/2013"), sdf.parse("15/12/2013")));
        Projet p4 = projetService.create(new Projet("Gestion de stock", sdf.parse("14/01/2013"), sdf.parse("30/06/2013")));

        Tache t1 = tacheService.create(new Tache("Analyse", sdf.parse("05/01/2013"), sdf.parse("20/01/2013"), 1500.0, p1));
        Tache t2 = tacheService.create(new Tache("Conception", sdf.parse("21/01/2013"), sdf.parse("10/02/2013"), 2000.0, p1));
        Tache t3 = tacheService.create(new Tache("Développement", sdf.parse("11/02/2013"), sdf.parse("30/03/2013"), 5000.0, p1));

        Tache t4 = tacheService.create(new Tache("Analyse", sdf.parse("05/02/2013"), sdf.parse("25/02/2013"), 1200.0, p2));
        Tache t5 = tacheService.create(new Tache("Design UI", sdf.parse("26/02/2013"), sdf.parse("15/03/2013"), 1800.0, p2));
        Tache t6 = tacheService.create(new Tache("Développement Mobile", sdf.parse("16/03/2013"), sdf.parse("30/04/2013"), 4500.0, p2));

        Tache t7 = tacheService.create(new Tache("Analyse", sdf.parse("20/03/2013"), sdf.parse("05/04/2013"), 1600.0, p3));
        Tache t8 = tacheService.create(new Tache("Conception", sdf.parse("06/04/2013"), sdf.parse("25/04/2013"), 2200.0, p3));
        Tache t9 = tacheService.create(new Tache("Intégration", sdf.parse("26/04/2013"), sdf.parse("20/05/2013"), 3000.0, p3));

        Tache t10 = tacheService.create(new Tache("Analyse", sdf.parse("20/01/2013"), sdf.parse("05/02/2013"), 800.0, p4));
        Tache t11 = tacheService.create(new Tache("Conception", sdf.parse("06/02/2013"), sdf.parse("20/02/2013"), 950.0, p4));
        Tache t12 = tacheService.create(new Tache("Développement", sdf.parse("21/02/2013"), sdf.parse("25/03/2013"), 3500.0, p4));

        employeTacheService.create(new EmployeTache(emp1, t1, sdf.parse("05/01/2013"), sdf.parse("19/01/2013")));
        employeTacheService.create(new EmployeTache(emp2, t2, sdf.parse("22/01/2013"), sdf.parse("09/02/2013")));
        employeTacheService.create(new EmployeTache(emp3, t3, sdf.parse("12/02/2013"), sdf.parse("28/03/2013")));

        employeTacheService.create(new EmployeTache(emp1, t4, sdf.parse("06/02/2013"), sdf.parse("24/02/2013")));
        employeTacheService.create(new EmployeTache(emp4, t5, sdf.parse("27/02/2013"), sdf.parse("14/03/2013")));
        employeTacheService.create(new EmployeTache(emp5, t6, sdf.parse("17/03/2013"), sdf.parse("29/04/2013")));

        employeTacheService.create(new EmployeTache(emp2, t7, sdf.parse("21/03/2013"), sdf.parse("04/04/2013")));
        employeTacheService.create(new EmployeTache(emp3, t8, sdf.parse("07/04/2013"), sdf.parse("24/04/2013")));
        employeTacheService.create(new EmployeTache(emp4, t9, sdf.parse("27/04/2013"), sdf.parse("19/05/2013")));

        employeTacheService.create(new EmployeTache(emp5, t10, sdf.parse("21/01/2013"), sdf.parse("04/02/2013")));
        employeTacheService.create(new EmployeTache(emp1, t11, sdf.parse("07/02/2013"), sdf.parse("19/02/2013")));
        employeTacheService.create(new EmployeTache(emp2, t12, sdf.parse("10/02/2013"), sdf.parse("20/02/2013")));
        employeTacheService.create(new EmployeTache(emp3, t12, sdf.parse("10/03/2013"), sdf.parse("15/03/2013")));
        employeTacheService.create(new EmployeTache(emp4, t12, sdf.parse("10/04/2013"), sdf.parse("25/04/2013")));

        System.out.println("Données créées avec succès !\n");
    }

    @Test
    public void testAffichageTachesEmploye() throws Exception {
        testCreationDonnees();
        
        System.out.println("\n=== Tâches réalisées par l'employé Mohamed Razin ===\n");
        
        Employe emp = employeService.findById(1);
        List<Tache> taches = employeService.getTachesRealisees(emp.getId());
        
        for (Tache t : taches) {
            System.out.println("Tâche: " + t.getNom() + " - Projet: " + t.getProjet().getNom());
        }
    }

    @Test
    public void testAffichageProjetsEmploye() throws Exception {
        testCreationDonnees();
        
        System.out.println("\n=== Projets gérés par l'employé Fatima Zahra Bennani ===\n");
        
        Employe emp = employeService.findById(2);
        List<Projet> projets = employeService.getProjetsGeres(emp.getId());
        
        for (Projet p : projets) {
            System.out.println("Projet: " + p.getNom() + " - Début: " + sdf.format(p.getDateDebut()));
        }
    }

    @Test
    public void testAffichageTachesPlanifiees() throws Exception {
        testCreationDonnees();
        
        System.out.println("\n=== Tâches planifiées pour le projet 'Gestion de stock' ===\n");
        
        Projet projet = projetService.findById(4);
        List<Tache> taches = projetService.getTachesPlanifiees(projet.getId());
        
        System.out.println("Projet: " + projet.getId() + "\tNom: " + projet.getNom() + "\tDate début: " + sdf.format(projet.getDateDebut()));
        System.out.println("Liste des tâches:");
        for (Tache t : taches) {
            System.out.println("Num: " + t.getId() + "\tNom: " + t.getNom() + 
                             "\tDate Début: " + sdf.format(t.getDateDebut()) + 
                             "\tDate Fin: " + sdf.format(t.getDateFin()) +
                             "\tPrix: " + t.getPrix() + " DH");
        }
    }

    @Test
    public void testAffichageTachesRealisees() throws Exception {
        testCreationDonnees();
        
        System.out.println("\n=== Tâches réalisées pour le projet 'Gestion de stock' ===\n");
        
        Projet projet = projetService.findById(4);
        System.out.println("Projet : " + projet.getId() + "\tNom : " + projet.getNom() + 
                         "\tDate début : " + new SimpleDateFormat("dd MMMM yyyy").format(projet.getDateDebut()));
        
        System.out.println("Liste des tâches:");
        System.out.println("Num\tNom\t\tDate Début Réelle\tDate Fin Réelle");
        
        List<Object[]> results = projetService.getTachesRealisees(projet.getId());
        for (Object[] result : results) {
            Tache t = (Tache) result[0];
            EmployeTache et = (EmployeTache) result[1];
            System.out.println(t.getId() + "\t" + t.getNom() + "\t\t" + 
                             sdf.format(et.getDateDebutReelle()) + "\t\t" + 
                             sdf.format(et.getDateFinReelle()));
        }
    }

    @Test
    public void testTachesPrixSuperieur1000() throws Exception {
        testCreationDonnees();
        
        System.out.println("\n=== Tâches avec prix supérieur à 1000 DH ===\n");
        
        List<Tache> taches = tacheService.getTachesPrixSuperieur1000();
        
        System.out.println("Nombre de tâches trouvées: " + taches.size());
        for (Tache t : taches) {
            System.out.println("Tâche: " + t.getNom() + 
                             " - Projet: " + t.getProjet().getNom() + 
                             " - Prix: " + t.getPrix() + " DH");
        }
    }

    @Test
    public void testTachesRealiseesByDates() throws Exception {
        testCreationDonnees();
        
        System.out.println("\n=== Tâches réalisées entre le 01/03/2013 et le 31/03/2013 ===\n");
        
        Date debut = sdf.parse("01/03/2013");
        Date fin = sdf.parse("31/03/2013");
        
        List<Tache> taches = tacheService.getTachesRealiseesByDates(debut, fin);
        
        System.out.println("Nombre de tâches trouvées: " + taches.size());
        for (Tache t : taches) {
            System.out.println("Tâche: " + t.getNom() + 
                             " - Projet: " + t.getProjet().getNom());
        }
    }

    @Test
    public void testComplet() throws Exception {
        testCreationDonnees();
        testAffichageTachesEmploye();
        testAffichageProjetsEmploye();
        testAffichageTachesPlanifiees();
        testAffichageTachesRealisees();
        testTachesPrixSuperieur1000();
        testTachesRealiseesByDates();
    }
}
