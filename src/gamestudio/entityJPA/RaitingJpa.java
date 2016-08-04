package gamestudio.entityJPA;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RaitingJpa {

	@Id
	@GeneratedValue
	private int ident;
	
	private int raiting;
	@ManyToOne(cascade = CascadeType.ALL)
	private PlayerJpa player;
	@ManyToOne(cascade = CascadeType.ALL)
	private GameJpa game;
	public int getIdent() {
		return ident;
	}
	public void setIdent(int ident) {
		this.ident = ident;
	}
	public int getRaiting() {
		return raiting;
	}
	public void setRaiting(int raiting) {
		this.raiting = raiting;
	}
	public PlayerJpa getPlayer() {
		return player;
	}
	public void setPlayer(PlayerJpa player) {
		this.player = player;
	}
	public GameJpa getGame() {
		return game;
	}
	public void setGame(GameJpa game) {
		this.game = game;
	}
	public RaitingJpa(int raiting, PlayerJpa player, GameJpa game) {
		this.raiting = raiting;
		this.player = player;
		this.game = game;
	}
	
	public RaitingJpa() {
	}
	@Override
	public String toString() {
		return "RaitingJpa [ident=" + ident + ", raiting=" + raiting + ", player=" + player + ", game=" + game + "]";
	}
	
	
}
