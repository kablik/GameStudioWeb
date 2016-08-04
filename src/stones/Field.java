package stones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Field implements Serializable {
	public static final int EMPTY_CELL = -1;

	private final int[][] tiles;

	private final int rowCount;

	private final int columnCount;

	private long startMillis;

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new int[rowCount][columnCount];
		generate();
		startMillis = System.currentTimeMillis();
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getValueAt(int row, int column) {
		return tiles[row][column];
	}

	private void generate() {
		int count = rowCount * columnCount;
		List<Integer> values = new ArrayList<>(count);
		for (int i = 1; i < count; i++) {
			values.add(i);
		}
		values.add(EMPTY_CELL);
		int index = 0;
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				tiles[row][column] = values.get(index);
				index++;
			}
		}
		shufflefield();
	}

	private Position getPositionOf(int value) {
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				if (tiles[row][column] == value) {
					return new Position(row, column);
				}
			}
		}
		return null;
	}

	private Position getEmptyPosition() {
		return getPositionOf(EMPTY_CELL);
	}

	private void move(int dr, int dc) {
		Position emptyPosition = getEmptyPosition();
		int er = emptyPosition.getRow();
		int ec = emptyPosition.getColumn();
		if (er + dr >= 0 && er + dr < rowCount && ec + dc >= 0 && ec + dc < columnCount) {
			tiles[er][ec] = tiles[er + dr][ec + dc];
			tiles[er + dr][ec + dc] = EMPTY_CELL;
		}
	}

	private void shufflefield() {
		for (int pocetTahov = 0; pocetTahov < rowCount*columnCount*10; pocetTahov++) {
			int typeOfMove = randomGenerate();
			switch (typeOfMove) {
			case 1:
				moveDown();
				break;
			case 2:
				moveUp();
				break;
			case 3:
				moveLeft();
				break;
			case 4:
				moveRight();
				break;
			}
		}
	}

	private int randomGenerate() {
		int RN = (int) (Math.random() * 4 + 1);
		return RN;
	}

	public void moveDown() {
		move(-1, 0);
	}

	public void moveUp() {
		move(1, 0);
	}

	public void moveRight() {
		move(0, -1);
	}

	public void moveLeft() {
		move(0, 1);
	}

	public void move(int value) {
		Position pos = getPositionOf(value);
		if (pos != null) {
			Position emptyPosition = getEmptyPosition();
			int dr = pos.getRow() - emptyPosition.getRow();
			int dc = pos.getColumn() - emptyPosition.getColumn();

			if ((Math.abs(dr) == 1 && dc == 0) || (dr == 0 && Math.abs(dc) == 1)) {
				move(dr, dc);
			}
		}
	}

	public boolean isSolved() {
		int value = 1;
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				if (tiles[row][column] != value) {
					return false;
				}
				value++;
				if (value == rowCount * columnCount) {
					return true;
				}
			}
		}
		throw new StonesException("This should never happen");
	}

	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}
}
