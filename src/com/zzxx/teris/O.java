package com.zzxx.teris;

/**
 * @author ÕÂÐñ¶«
 *
 */
public class O extends Tetromino {
	public O() {
		this.cells[0] = new Cell(0,3);
		this.cells[1] = new Cell(0,4);
		this.cells[2] = new Cell(1,3);
		this.cells[3] = new Cell(1,4);
		this.cellImg = GameLauncher.cellO;
		this.initialCellsImg();
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
		
	}
	

}
