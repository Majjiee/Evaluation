package beans;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Femme extends Personne {

	@OneToMany(mappedBy = "femme", cascade = CascadeType.ALL)
	private List<Mariage> mariages;

	public Femme() {
		super();
	}

	public Femme(String prenom, String nom) {
		super();
		this.prenom = prenom;
		this.nom = nom;
	}

	public Femme(String nom, String prenom, String telephone, String adresse, java.util.Date dateNaissance) {
		super(nom, prenom, telephone, adresse, dateNaissance);
	}

	public List<Mariage> getMariages() {
		return mariages;
	}

	public void setMariages(List<Mariage> mariages) {
		this.mariages = mariages;
	}
}
