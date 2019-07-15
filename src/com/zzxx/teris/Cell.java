package com.zzxx.teris;

import java.awt.Image;

/**
 * @author 章旭东
 *
 */


public class Cell {
	//总共是20行10列
	private int row;
	private int col;
	private Image cellImg;
	
	public Cell() 
	{
	}
	
	public Cell(int row, int col)
	{
		super();
		this.row = row;
		this.col = col;
	}
	
	public void setCellImg(Image img) {
		this.cellImg = img;
	}
	public Image getCellImg() {
		return this.cellImg; 
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public void setRowCol(int row , int col) {
		this.row = row;
		this.col = col;
	}
	
	public void moveLeft() {
		this.col --;
	}
	
	public void moveRight() {
		this.col ++;
	}
	
	public void drop() {
		this.row ++;
	}
	
}
