package com.zzxx.teris;

/**
 * @author ÕÂÐñ¶«
 *
 */
public class S extends Tetromino {
	public S() {
		this.cells[0] = new Cell(0,3);
		this.cells[1] = new Cell(0,4);
		this.cells[2] = new Cell(1,2);
		this.cells[3] = new Cell(1,3);
		this.cellImg = GameLauncher.cellS;
		this.initialCellsImg();
	}
	@Override
	public void rotate() {
		switch (type%2) {
		case 0 :
			this.pivotRow = cells[0].getRow();
			this.pivotCol = cells[0].getCol();
			this.cells[0].setRowCol(pivotRow, pivotCol);
			this.cells[1].setRowCol(pivotRow-1, pivotCol-1);
			this.cells[2].setRowCol(pivotRow, pivotCol-1);
			this.cells[3].setRowCol(pivotRow+1, pivotCol);
			break;
		case 1 :
			this.pivotRow = cells[0].getRow();
			this.pivotCol = cells[0].getCol();
			this.cells[0].setRowCol(pivotRow,pivotCol );
			this.cells[1].setRowCol(pivotRow, pivotCol+1);
			this.cells[2].setRowCol(pivotRow+1, pivotCol);
			this.cells[3].setRowCol(pivotRow+1, pivotCol-1);
			break;
		}
		++type;
	}

}
