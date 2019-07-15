package com.zzxx.teris;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author 章旭东
 *
 */
public class GameLauncher extends JPanel 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static BufferedImage cellI ;
	protected static BufferedImage cellJ ;
	protected static BufferedImage cellZ ;
	protected static BufferedImage cellS ;
	protected static BufferedImage cellT ;
	protected static BufferedImage cellO ;
	protected static BufferedImage cellL ;
	protected static BufferedImage gameOver ;
	protected static BufferedImage pause ;
	protected static BufferedImage tetrisUI ;
	private static int WIDTH ;
	private static int HEIGHT ;
	
	private final static int READY = 0;
	private final static int RUNNING = 1;
	private final static int PAUSE = 2;
	private final static int GAMEOVER = 3;
	private  static int CURRENT_STATE = READY;
	
	
	public static final int ROWS ;
	public static final int COLS ;
	private Cell[][] cells= new Cell[ROWS][COLS];
	private Tetromino droping ;
	private Tetromino nextdroping = Tetromino.getRandomTetro();
	private boolean Islanded = true;
	private static final int CELL_SIZE ;
	private boolean IsControled = false;
	
	/** 销毁行数 */
	private int lines = 0;
	/** 得分 */
	private int score = 0;
	/**难度等级*/
	private int level = 1;
	/**速度*/
	private int speed = 10;
	/*index % speed == 0 时*/
	int index = 0 ;
	
	static {
			try {
				cellI = ImageIO.read(GameLauncher.class.getResource("I.png"));
				cellJ = ImageIO.read(GameLauncher.class.getResource("J.png"));
				cellO = ImageIO.read(GameLauncher.class.getResource("O.png"));
				cellZ = ImageIO.read(GameLauncher.class.getResource("Z.png"));
				cellS = ImageIO.read(GameLauncher.class.getResource("S.png"));
				cellL = ImageIO.read(GameLauncher.class.getResource("L.png"));
				cellT = ImageIO.read(GameLauncher.class.getResource("T.png"));
				gameOver = ImageIO.read(GameLauncher.class.getResource("game-over.png"));
				pause = ImageIO.read(GameLauncher.class.getResource("pause.png"));
				tetrisUI = ImageIO.read(GameLauncher.class.getResource("tetris.png"));
				WIDTH  = tetrisUI.getWidth();
				HEIGHT = tetrisUI.getHeight();
				
				}catch (IOException e) {
					System.out.println(e);
				}
			CELL_SIZE = cellI.getWidth();
			COLS = 9;
			ROWS = 18;
	}
	public void ShowMe()
	{
		JFrame window = new JFrame();
		window.setSize(WIDTH+5,HEIGHT+5);
		window.setLocationRelativeTo(null);//默认居中
//		window.setAlwaysOnTop(true);//置顶
		window.setResizable(false);
		window.setUndecorated(true);//去除边框
		window.add(this);		
		window.setVisible(true);
		this.action();
		getNewDroping();
	}
	
	public static void main(String[] args) 
	{
		GameLauncher gamePanel = new GameLauncher();
		gamePanel.ShowMe();
	}

	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		paintStates(g);
		
		g.translate(15, 15);
		paintDroping(g);
		paintNextDroping(g);
		paintCellsLanded(g);
		
		g.translate(-15, -15);
	}
	public void paintDroping(Graphics g) {
		if(droping == null || CURRENT_STATE != RUNNING)
			return;
		int row , col;
		Cell c = null;
		for(int i = 0 ; i < droping.cells.length ; i ++)
		{
			c = droping.cells[i];
			row = c.getRow();
			col = c.getCol();
			g.drawImage(droping.cellImg, col*CELL_SIZE, row*CELL_SIZE, null);
		}
	}
	public void paintNextDroping(Graphics g) {
		if(nextdroping == null || CURRENT_STATE != RUNNING)
			return;
		int row , col;
		Cell c = null;
		for(int i = 0 ; i < nextdroping.cells.length ; i ++)
		{
			c = nextdroping.cells[i];
			row = c.getRow();
			col = c.getCol()+9;
			g.drawImage(nextdroping.cellImg, col*CELL_SIZE, row*CELL_SIZE, null);
		}
	}
	public void paintInfo(Graphics g) {
		int x = 300;
		int y = 170;
		g.setColor(Color.GRAY);
		Font font = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 25);
		g.setFont(font);
		g.drawString("得分:" + score, x, y);
		y += 60;
		g.drawString("消行数:" + lines, x, y);
		y += 60;
		g.drawString("难度等级:" + level, x, y);
		
		
	}

	public void paintStates(Graphics g) {
		switch (CURRENT_STATE) {
		case READY:
			g.drawImage(tetrisUI, 0,0,null);
			break;
		case RUNNING:
			g.drawImage(tetrisUI, 0,0,null);
			paintInfo(g);
			break;
		case PAUSE :
			g.drawImage(pause, 0, 0, null);
			break;
		case GAMEOVER :
			g.drawImage(gameOver, 0, 0, null);
			JOptionPane.showMessageDialog(null,"您共消除："+lines+"行,"+ "总得分："+score,"游戏结束",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		repaint();
	}
	
	public void action() {
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch (key) {
				case KeyEvent.VK_S:
					if(CURRENT_STATE == READY)
					CURRENT_STATE = RUNNING;
					else if (CURRENT_STATE == GAMEOVER) {
						CURRENT_STATE = READY;
						cells = new Cell[ROWS][COLS];
						level = 1 ;
						score = 0 ;
						lines = 0 ;
						speed = 10;
						index = 0 ;
					}
					break;
				case KeyEvent.VK_P :
					System.out.println("P");
					CURRENT_STATE = PAUSE;
					break;
				case KeyEvent.VK_C :
					System.out.println("C");
					if(CURRENT_STATE == PAUSE) {
						CURRENT_STATE = RUNNING;
						index = 0;
					}
					break;
				case KeyEvent.VK_Q :
					System.out.println("Q");
					System.exit(0);
					break;
				case KeyEvent.VK_UP:
					if(IsRunning())
					rotateAction();
					break;
				case KeyEvent.VK_LEFT:
					moveLeftAction();
					break;
				case KeyEvent.VK_RIGHT:
					moveRightAction();
					break;
				case KeyEvent.VK_DOWN:
					if(IsRunning()) {
						IsControled = true;
						hardDropAction();
					}
					break;
				}
				repaint();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) 
					IsControled = false;
				
			}
			public void moveLeftAction()
			{
				if(IsRunning() && checkLeft()) {
					droping.moveLeft();
				}
			}
			
			public void moveRightAction() {
				if(IsRunning()&&checkRight()) {			
					droping.moveRight();
				}
			}
			
			public void hardDropAction() {
				checkIsLanded();
				if( ! Islanded && IsControled )
				dropAction();
			}
		
			public void rotateAction() {
				droping.rotate();
				int left=droping.outOfLeftBounds();
				int right =droping.outOfRightBounds();
				if (left != -1) 
				{
					while(left -- > 0) 
						droping.moveRight();
				}else if (right != -1) 
				{
					while(right -- > 0)
						droping.moveLeft();
				}
			}
		});

		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(CURRENT_STATE == RUNNING)
				{
					checkGameOver();
					checkIsLanded();
					if ( ! IsControled)
						AutodropAction();
					elinimateAction();
					getNewDroping();
					repaint();
				}
			}
		}, 1000,100);
		this.requestFocus();
	}
	
	public boolean checkRight() 
	{
		int co , ro;
		if ( droping.outOfRightBounds() != -1)
			return false;
		for( Cell c : droping.cells)
		{
			co = c.getCol();
			ro = c.getRow();
			if(ro >= 0 && cells[ro][co+1] != null)
			{
				return false;
			}
		}
		return true;
	}
	public boolean checkLeft() 
	{
		int co , ro;
		if ( droping.outOfLeftBounds() != -1)
			return false;
		for( Cell c : droping.cells)
		{
			co = c.getCol();
			ro = c.getRow();
			if(ro >= 0 &&cells[ro][co-1] != null)
			{
				return false;
			}
		}
		return true;
	}
	public void checkIsLanded() {
		if(CURRENT_STATE == RUNNING)
		for (int i = 0; i < droping.cells.length; i++) {
			Cell c = droping.cells[i];
			if (c.getRow() >= ROWS-1 || cells[c.getRow()+1][c.getCol()] != null) {
				Islanded = true;
				return;
			}
		}
	}
	public void dropAction() {
		if ( ! Islanded) 
		droping.drop();
		else landAction();
	}
	
	public void AutodropAction() {
		if ( ! Islanded) { 
			if(++index %speed == 0)
			{
				droping.drop();
			}
		}
		else landAction();
	}
	public void landAction() {
		if (Islanded) {
			SetCells();
			for ( Cell ce : droping.cells) {
				int r = ce.getRow();
				int c = ce.getCol();
				cells[r][c] = ce; 
				SetCells();
			}
		}
	}
	
	//判断是否满足删除一行的条件
	public boolean checkElinimate(int row) {
		if ( ! Islanded || IsControled) {
			return false;
		}
			for (int col = 0; col < cells[row].length; col++) {
				if (cells[row][col] == null) 
					return false;
			}
		return true;
	}
	//删除动作
	public void elinimateAction() {
		if(Islanded)
		for (int i = 0; i < cells.length; i++) {
			if (checkElinimate(i)) {
				/*倒着将每一行的上一行内容拷贝下来*/
				for (int j = i; j >= 1; j--)
					System.arraycopy(cells[j - 1], 0, cells[j], 0, 9);
				
				Arrays.fill(cells[0], null);// fill填充
				++lines;
				if(lines == level*8) {
					++level;
					speed = speed > 0 ? speed - 1 : 1;
				}
				score = lines * 20;
			}
		}
	}
	public void checkGameOver() {
		for(int col = 0 ; col < cells[0].length ; col++)
		{	
			if(cells[0][col] != null) {
				CURRENT_STATE = GAMEOVER;
				repaint();
				return ;
			}
		}
	}
	public void paintCellsLanded(Graphics g) 
	{
		if(CURRENT_STATE == RUNNING)
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				Cell ce = cells[i][j];
				if (ce != null) 
					g.drawImage(ce.getCellImg(), j*CELL_SIZE, i*CELL_SIZE, null);
			}
		}
	}
	public void SetCells() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j] != null) {
				cells[i][j].setRow(i);
				cells[i][j].setCol(j);
				}
			}
		}
	}
	public void getNewDroping() {
		
		if (Islanded && ! IsControled) {
			if(droping == null)
				droping = Tetromino.getRandomTetro();
			else
				droping = nextdroping;
			nextdroping = Tetromino.getRandomTetro();
			Islanded = false;
		}
	}
	
	public boolean IsRunning() {
		return CURRENT_STATE == RUNNING;
	}

	
	
	
}
