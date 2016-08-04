package gamestudio.dto;

public class ScoresCount {

	private String game;
	private Long scoresCount;

	public ScoresCount(String game, Long scoresCount) {
		this.game = game;
		this.scoresCount = scoresCount;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public Long getScoresCount() {
		return scoresCount;
	}

	public void setScoresCount(Long scoresCount) {
		this.scoresCount = scoresCount;
	}

	@Override
	public String toString() {
		return "ScoresCount [game=" + game + ", scoresCount=" + scoresCount + "]";
	}
	
}
