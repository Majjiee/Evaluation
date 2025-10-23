package ma.project.classes;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;
    private String libelle;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    private List<Produit> produits;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Categorie(String code, String libelle, List<Produit> produits) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.produits = produits;
	}

	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}

    public Categorie(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

	public Categorie(String libelle) {
		// TODO Auto-generated constructor stub
		this.libelle = libelle;
	}
    
    
}
