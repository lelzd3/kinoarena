package pojos;

import exceptions.InvalidDataException;

public class Seat {

	private int row;
	private int column;
	

	public Seat(int row, int column) throws InvalidDataException {
		setRow(row);
		setColumn(column);
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
