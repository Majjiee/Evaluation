package ma.project.classes;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommandeProduit> lignes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<LigneCommandeProduit> getLignes() {
		return lignes;
	}

	public void setLignes(List<LigneCommandeProduit> lignes) {
		this.lignes = lignes;
	}

	public Commande(Date date, List<LigneCommandeProduit> lignes) {
		super();
		this.date = date;
		this.lignes = lignes;
	}

	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commande(Date date2) {
		// TODO Auto-generated constructor stub
		this.date = date2;
	}
    
}
