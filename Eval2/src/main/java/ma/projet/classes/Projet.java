package ma.projet.classes;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @ManyToOne
    private Employe chefProjet;

    @OneToMany(mappedBy = "projet")
    private List<Tache> taches;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Employe getChefProjet() {
		return chefProjet;
	}

	public void setChefProjet(Employe chefProjet) {
		this.chefProjet = chefProjet;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public Projet(String nom, Date dateDebut, Date dateFin, Employe chefProjet, List<Tache> taches) {
		super();
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.chefProjet = chefProjet;
		this.taches = taches;
	}

	public Projet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setChef(Employe e1) {
		// TODO Auto-generated method stub
		this.chefProjet=e1;
		
	}
    


}
