package com.zzxx.teris;

/**
 * @author ÕÂÐñ¶«
 *
 */
public class I extends Tetromino {
	
	public I() {
		this.cells[0] = new Cell(0,3);
		this.cells[1] = new Cell(0,4);
		this.cells[2] = new Cell(0,5);
		this.cells[3] = new Cell(0,6);
		this.cellImg = GameLauncher.cellI;
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
			this.cells[2].setRowCol(pivotRow+1, pivotCol);
			this.cells[3].setRowCol(pivotRow+2, pivotCol);
			break;
		case 1 :
			this.pivotRow = cells[1].getRow();
			this.pivotCol = cells[1].getCol();
			this.cells[0].setRowCol(pivotRow,pivotCol-1 );
			this.cells[1].setRowCol(pivotRow, pivotCol);
			this.cells[2].setRowCol(pivotRow, pivotCol+1);
			this.cells[3].setRowCol(pivotRow, pivotCol+2);
			break;
		}
		++type;
	}

}
