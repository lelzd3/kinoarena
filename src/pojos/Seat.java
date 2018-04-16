package pojos;

import com.sun.media.sound.InvalidDataException;

public class Seat {

	private int row;
	private int column;
	

	public Seat(int row, int column) {

		try {
			setRow(row);
			setColumn(column);
		} catch (InvalidDataException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) throws InvalidDataException {
		if (row < 0 || row > 10) {
			throw new InvalidDataException("Not a valid row");
		}
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) throws InvalidDataException {
		if (column < 0 || column > 10) {
			throw new InvalidDataException("Not a valid column");
		}
		this.column = column;
	}


}
