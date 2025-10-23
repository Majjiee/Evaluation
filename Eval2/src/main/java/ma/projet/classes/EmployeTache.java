package ma.projet.classes;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class EmployeTache {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dateDebutReelle;
    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;

    @ManyToOne
    private Employe employe;

    @ManyToOne
    private Tache tache;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDebutReelle() {
		return dateDebutReelle;
	}

	public void setDateDebutReelle(Date dateDebutReelle) {
		this.dateDebutReelle = dateDebutReelle;
	}

	public Date getDateFinReelle() {
		return dateFinReelle;
	}

	public void setDateFinReelle(Date dateFinReelle) {
		this.dateFinReelle = dateFinReelle;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public EmployeTache(Date dateDebutReelle, Date dateFinReelle, Employe employe, Tache tache) {
		super();
		this.dateDebutReelle = dateDebutReelle;
		this.dateFinReelle = dateFinReelle;
		this.employe = employe;
		this.tache = tache;
	}

	public EmployeTache() {
		super();
		// TODO Auto-generated constructor stub
	}
    

}
