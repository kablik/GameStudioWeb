package gamestudio.entityJPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GameJpa {
	
	@Id
	@GeneratedValue
	private int ident;
	
	private String game;
	
	public GameJpa(String game) {
		this.game = game;
	}
	
	public GameJpa() {
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return "GameJpa [ident=" + ident + ", game=" + game + "]";
	}

}
