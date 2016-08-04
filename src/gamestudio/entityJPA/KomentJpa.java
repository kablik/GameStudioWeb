package gamestudio.entityJPA;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class KomentJpa {
	
	@Id
	@GeneratedValue
	private int ident;
	
	private String koment;
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
	public String getKoment() {
		return koment;
	}
	public void setKoment(String koment) {
		this.koment = koment;
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
	public KomentJpa(String koment, PlayerJpa player, GameJpa game) {
		this.koment = koment;
		this.player = player;
		this.game = game;
	}
	
	public KomentJpa(){
	}
	@Override
	public String toString() {
		return "KomentJpa [ident=" + ident + ", koment=" + koment + ", player=" + player + ", game=" + game + "]";
	}
	
	
	
	

}
