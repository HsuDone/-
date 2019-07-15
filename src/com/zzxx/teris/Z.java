package com.zzxx.teris;

/**
 * @author ÕÂÐñ¶«
 *
 */
public class Z extends Tetromino {
	public Z() {
		this.cells[0] = new Cell(0,3);
		this.cells[1] = new Cell(0,4);
		this.cells[2] = new Cell(1,4);
		this.cells[3] = new Cell(1,5);
		this.cellImg = GameLauncher.cellZ;
		this.initialCellsImg();
	}
	@Override
	public void rotate() {
		switch (type%2) {
		case 0 :
			this.pivotRow = cells[1].getRow();
			this.pivotCol = cells[1].getCol();
			this.cells[0].setRowCol(pivotRow-1, pivotCol);
			this.cells[1].setRowCol(pivotRow, pivotCol);
			this.cells[2].setRowCol(pivotRow, pivotCol-1);
			this.cells[3].setRowCol(pivotRow+1, pivotCol-1);
			break;
		case 1 :
			this.pivotRow = cells[1].getRow();
			this.pivotCol = cells[1].getCol();
			this.cells[0].setRowCol(pivotRow-1,pivotCol-1 );
			this.cells[1].setRowCol(pivotRow, pivotCol);
			this.cells[2].setRowCol(pivotRow-1, pivotCol);
			this.cells[3].setRowCol(pivotRow, pivotCol+1);
			break;
		}
		++type;
	}

}
