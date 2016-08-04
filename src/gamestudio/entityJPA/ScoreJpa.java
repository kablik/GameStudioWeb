package gamestudio.entityJPA;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ScoreJpa {

	@Id
	@GeneratedValue
	private int ident;
	
	private int score;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
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
	
	public ScoreJpa(int score, PlayerJpa player, GameJpa game) {
		this.score = score;
		this.player = player;
		this.game = game;
	}
	
	public ScoreJpa() {
	}
	@Override
	public String toString() {
		return "ScoreJpa [ident=" + ident + ", score=" + score + ", player=" + player + ", game=" + game + "]";
	}
	
	
}
