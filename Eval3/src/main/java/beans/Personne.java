package beans;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String nom;
    protected String prenom;
    protected String telephone;
    protected String adresse;

    @Temporal(TemporalType.DATE)
    protected Date dateNaissance;

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Personne(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.adresse = adresse;
		this.dateNaissance = dateNaissance;
	}

	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}


}
