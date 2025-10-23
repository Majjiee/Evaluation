package beans;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Homme extends Personne {

	@OneToMany(mappedBy = "homme", cascade = CascadeType.ALL)
	private List<Mariage> mariages;

	public List<Mariage> getMariages() {
		return mariages;
	}

	public void setMariages(List<Mariage> mariages) {
		this.mariages = mariages;
	}
}
