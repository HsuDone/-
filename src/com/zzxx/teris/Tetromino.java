package com.zzxx.teris;

import java.awt.image.BufferedImage;


/**
 * @author ÕÂÐñ¶«
 *
 */
public abstract class Tetromino {
		protected Cell[] cells = new Cell[4];
		protected BufferedImage cellImg = null; 
		protected int type = 0 ;
		protected int pivotRow;
		protected int pivotCol;
		public void moveLeft() {
			for (Cell c : cells) 
				c.moveLeft();
		}
		public void initialCellsImg() {
			for (int i = 0; i < cells.length; i++) {
				cells[i].setCellImg(cellImg);
			}
		}
		public void moveRight() {
			for (Cell c : cells) 
				c.moveRight();
		}
		public void drop() {
			for (Cell c : cells) 
				c.drop();
		}
		
		public static Tetromino getRandomTetro() {
			Tetromino[] t = { new I() , new J() , new L() , new O() , new S() , new T() , new Z() };
			int ran = (int)(Math.random()*7);
			return t[ran];
		}
		public int outOfLeftBounds() {
			int leftOut = -1 ;
			for (int i = 0; i < cells.length; i++) {
				int col = cells[i].getCol();
				if ( col <= 0) {
					int out = -col;
					if (out > leftOut) {
						leftOut = out;
					}
				}
			}return leftOut;
		}
		public int outOfRightBounds() {
			int rightOut = -1 ;
			for (int i = 0; i < cells.length; i++) {
				int col = cells[i].getCol();
				if ( col >= GameLauncher.COLS -1) {
					int out = col - (GameLauncher.COLS -1) ;
					if (out > rightOut) {
						rightOut = out;
					}
				}
			}return rightOut;
		}
		
		
		public abstract void rotate() ;
			
		
		
		
		
		
}
