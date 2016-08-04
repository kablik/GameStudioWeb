package gamestudio.dto;

public class RatingsCount {
	
	private String game;
	private Long ratingsCount;
	
	public RatingsCount(String game, Long ratingsCount) {
		this.game = game;
		this.ratingsCount = ratingsCount;
	}
	
	public String getGame() {
		return game;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public Long getRatingsCount() {
		return ratingsCount;
	}
	
	public void setRatingsCount(Long ratingsCount) {
		this.ratingsCount = ratingsCount;
	}
	
	@Override
	public String toString() {
		return "RatingsCount [game=" + game + ", ratingsCount=" + ratingsCount + "]";
	}
	
}
