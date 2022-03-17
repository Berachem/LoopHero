package fr.iut.zen.game;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.List;

public class SimpleGameData {
	private final Cell[][] matrix;
	public final List<GridPosition> path = Arrays.asList(new GridPosition(4,4),new GridPosition(4,5),new GridPosition(4,6),new GridPosition(4,7),new GridPosition(4,8),new GridPosition(4,9),new GridPosition(4,10),new GridPosition(4,11),new GridPosition(4,12),new GridPosition(4,13),new GridPosition(4,14),new GridPosition(4,15),new GridPosition(4,16),new GridPosition(4,17),new GridPosition(4,18),new GridPosition(4,19),new GridPosition(5,19),new GridPosition(6,19),new GridPosition(6,18),new GridPosition(6,17),new GridPosition(6,16),new GridPosition(6,15),new GridPosition(6,14),new GridPosition(6,13),new GridPosition(6,12),new GridPosition(6,11),new GridPosition(6,10),new GridPosition(6,9),new GridPosition(6,8),new GridPosition(6,7),new GridPosition(6,6),new GridPosition(6,5),new GridPosition(6,4),new GridPosition(5,4));
	private final GridPosition FireCamp = path.get(0);
	private GridPosition selected;
	private GridPosition bob = path.get(0); // POSITION DE BOB AU DEPART
	

	public SimpleGameData(int nbLines, int nbColumns) {
		if (nbLines < 1 || nbColumns < 1) {
			throw new IllegalArgumentException("at least one line and column");
		}
		matrix = new Cell[nbLines][nbColumns];
		
	}

	/**
	 * The number of lines in the matrix contained in this GameData.
	 * 
	 * @return the number of lines in the matrix.
	 */
	public int nbLines() {
		return matrix.length;
	}

	/**
	 * The number of columns in the matrix contained in this GameData.
	 * 
	 * @return the number of columns in the matrix.
	 */
	public int nbColumns() {
		return matrix[0].length;
	}

	private void checkBoundsOrThrow(int line, int column) {
		Objects.checkIndex(line, matrix.length);
		Objects.checkIndex(column, matrix[0].length);
	}

	/**
	 * The color of the given cell.
	 * 
	 * @param line   the first coordinate of the cell.
	 * @param column the second coordinate of the cell.
	 * @return the number of columns in the matrix.
	 * @throws IndexOutOfBoundsException
	 */
	public Color cellColor(int line, int column) {
		checkBoundsOrThrow(line, column);
		return matrix[line][column].color();
	}

	/**
	 * The coordinates of the cell selected, if a cell is selected.
	 * 
	 * @return the coordinates of the selected cell; null otherwise.
	 */
	public GridPosition getSelected() {
		return selected;
	}

	public GridPosition bob() {
		return bob;
	}

	/**
	 * Tests if a cell is selected.
	 * 
	 * @return true if and only if at least one cell is selected; false otherwise.
	 */
	public boolean hasASelectedCell() {
		return selected != null;
	}

	/**
	 * Selects the cell identified by the specified coordinates.
	 * 
	 * @param line   the first coordinate of the cell.
	 * @param column the second coordinate of the cell.
	 * @throws IndexOutOfBoundsException
	 * @throws IllegalStateException     if a first cell is already selected.
	 */
	public void selectCell(int line, int column) {
		checkBoundsOrThrow(line, column);
		if (selected != null) {
			throw new IllegalStateException("First cell already selected");
		}
		selected = new GridPosition(line, column);
	}

	/**
	 * Selects the next cell in the same line, if it exists. If no cell is selected,
	 * start with the first cell.
	 */
	public void selectNextCellInLine() {
		if (selected == null) { // no cell is selected
			selectCell(0, 0);
			return;
		}
		int column = selected.column() + 1;
		if (column >= matrix[0].length) {
			selected = null;
		} else {
			selected = new GridPosition(selected.line(), column);
		}
	}
	
	public boolean coordInGridPosition(int line, int column) {
		return path.contains(new GridPosition(line,column));
	}
	

	/**
	 * Selects the next cell in the same line, if it exists. If no cell is selected,
	 * start with the first cell.
	 */
	public void moveBob() {
		/*
		int column = bob.column() + 1;
		int line = bob.line();
		if (column >= matrix[0].length) {
			column = 0;
			line++;
			if (line >= matrix.length) {
				line = 0;
			}
		}
		*/
		int index = path.indexOf(new GridPosition(bob.line(),bob.column()));
		if (index+1>path.size()-1) {
			bob = path.get(0);
		}else {
			bob = path.get(index+1);
		}
		
		
	}

	/**
	 * Unselects the cell (whether they is a selected cell or not).
	 */
	public void unselect() {
		selected = null;
	}

	/**
	 * Initialize a random grid
	 */
	public void setRandomMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = Cell.randomGameCell();
			}
		}
	}

	/**
	 * Updates the data contained in the GameData.
	 */
	public void updateData() {
		// update (attention traitement different si des cases sont
		// selectionnées ou non...)
	}
	
	/*
	public void spawnMobs() {
		
	}
	*/
}
