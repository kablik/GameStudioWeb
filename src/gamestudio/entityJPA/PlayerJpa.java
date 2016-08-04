package gamestudio.entityJPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PlayerJpa {
	
	@Id
	@GeneratedValue
	private int ident;
	
	private String player;
	
	private String password;
	
	public PlayerJpa(String player, String password) {
		this.player = player;
		this.password = password;
	}
	
	public PlayerJpa(String player) {
		this.player = player;
	}
	
	public PlayerJpa() {
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "PlayerJpa [ident=" + ident + ", player=" + player + ", password=" + password + "]";
	}
	
}
