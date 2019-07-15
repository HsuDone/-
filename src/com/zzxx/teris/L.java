package com.zzxx.teris;

/**
 * @author ÕÂÐñ¶«
 *
 */
public class L extends Tetromino {

	public L() 
	{
		this.cells[0] = new Cell(0, 3);
		this.cells[1] = new Cell(1, 3);
		this.cells[2] = new Cell(2, 3);
		this.cells[3] = new Cell(2, 4);
		this.cellImg = GameLauncher.cellL;
		this.initialCellsImg();
	}
	@Override
	public void rotate() {
		switch (type%4) {
		case 0 :
			this.pivotRow = cells[2].getRow();
			this.pivotCol = cells[2].getCol();
			this.cells[0].setRowCol(pivotRow, pivotCol +1);
			this.cells[1].setRowCol(pivotRow, pivotCol +2);
			this.cells[2].setRowCol(pivotRow, pivotCol);
			this.cells[3].setRowCol(pivotRow+1, pivotCol);
			
			break;
		case 1 :
			this.pivotRow = cells[2].getRow();
			this.pivotCol = cells[2].getCol();
			this.cells[0].setRowCol(pivotRow, pivotCol-1);
			this.cells[1].setRowCol(pivotRow+1, pivotCol);
			this.cells[2].setRowCol(pivotRow, pivotCol);
			this.cells[3].setRowCol(pivotRow+2, pivotCol);
			break;
		case 2:
			this.pivotRow = cells[2].getRow();
			this.pivotCol = cells[2].getCol();
			this.cells[0].setRowCol(pivotRow-1, pivotCol );
			this.cells[1].setRowCol(pivotRow, pivotCol -1);
			this.cells[2].setRowCol(pivotRow, pivotCol );
			this.cells[3].setRowCol(pivotRow, pivotCol-2 );
			break;
		case 3:
			this.pivotRow = cells[2].getRow();
			this.pivotCol = cells[2].getCol();
			this.cells[0].setRowCol(pivotRow-2, pivotCol);
			this.cells[1].setRowCol(pivotRow-1, pivotCol );
			this.cells[2].setRowCol(pivotRow, pivotCol );
			this.cells[3].setRowCol(pivotRow, pivotCol+1 );
			break;
		}
		++type;
	}

}
