package gamestudio.dto;

public class KomentsCount {
	
	private String game;
	private Long komentsCount;
	
	public KomentsCount(String game, Long komentsCount) {
		this.game = game;
		this.komentsCount = komentsCount;
	}
	
	public String getGame() {
		return game;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public Long getKomentsCount() {
		return komentsCount;
	}
	
	public void setKomentsCount(Long komentsCount) {
		this.komentsCount = komentsCount;
	}
	
	@Override
	public String toString() {
		return "KomentsCount [game=" + game + ", komentsCount=" + komentsCount + "]";
	}
	
}
