import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 3426940946811133635L;
	private static final int GRID_X = 25;
	private static final int GRID_Y = 25;
	private static final int INNER_CELL_SIZE = 70;
	private static final int TOTAL_COLUMNS = 9;
	private static final int TOTAL_ROWS =9;  
	private BufferedImage image;
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	public boolean[][] isMine = new boolean[TOTAL_COLUMNS][TOTAL_ROWS];
	
	public Color[][] colorArray = new Color[TOTAL_COLUMNS][TOTAL_ROWS];
	public MyPanel() {   //This is the constructor... this code runs first to initialize
		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //Top row
			colorArray[x][0] = Color.LIGHT_GRAY;
		}
		for (int y = 0; y < TOTAL_ROWS; y++) {   //Left column
			colorArray[0][y] = Color.LIGHT_GRAY;
		}
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				colorArray[x][y] = Color.WHITE;
				
			}
		}
		}	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Compute interior coordinates
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right ;
		int y2 = getHeight() - myInsets.bottom ;
		int width = x2 - x1;
		int height = y2 - y1;
		//Paint the background
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(x1, y1, width + 1, height + 1);

				
				//Draw grid
				g.setColor(Color.BLACK);
				for (int y = 0; y <= TOTAL_ROWS ; y++) {
					g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
				}
				for (int x = 0; x <= TOTAL_COLUMNS; x++) {
					g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y, x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS)));
				}

				
				//Paint cell colors
				for (int x = 0; x < TOTAL_COLUMNS; x++) {
					for (int y = 0; y < TOTAL_ROWS; y++) {
						
							Color c = colorArray[x][y];
							g.setColor(c);
							g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
							
					
						
					}
				}
			}


	// This method helps to find the adjacent boxes that don't have a mine.
	// It is partially implemented since the verify hasn't been discussed in class
	// Verify that the coordinates in the parameters are valid.
	// Also verifies if there are any mines around the x,y coordinate
		public void revealAdjacent(int x, int y){
			Graphics g=null;
		
			colorArray[x][y] = Color.GRAY;
		if (x+1 > 0 && colorArray[x-1][y]!= Color.GRAY && adjacentMines(x,y) == 0){
			revealAdjacent(x-1,y);
		}
		else if (x < 9 && colorArray[x+1][y]!= Color.GRAY && adjacentMines(x,y) == 0 && x != 9){
			revealAdjacent(x+1, y);
		}
		else if (y+1 > 0  && colorArray[x][y-1]!= Color.GRAY && adjacentMines(x,y) == 0){
			revealAdjacent(x,y-1);
		}
		else if (y < 9 && colorArray[x][y+1]!= Color.GRAY && adjacentMines(x,y) == 0 && y !=9){
			revealAdjacent(x, y+1);	
		}
		
		else if(x  < 0 || x >9|| y > 9 ||y < 0 || adjacentMines(x,y)>0 && colorArray[x][y]!= Color.GRAY){
			x = mouseDownGridX;
			y = mouseDownGridY;
			revealAdjacent(x,y);
		}
		
        }
		
		
		

		public int adjacentMines(int x, int y){
			Graphics g=null;
			int k=0;
			if(isMine[x-1][y] == true){
				k++;
			}
			if(x+1<10){
				if(isMine[x+1][y] == true){
					k++;
				}
			}
			if(isMine[x][y-1] == true){
				k++;
			}
			if(isMine[x][y+1] == true){
				k++;
			}
			if(isMine[x-1][y-1] == true){
				k++;
			}
			if(isMine[x-1][y+1] == true){
				k++;
			}
			if(x+1<10){
				if(isMine[x+1][y-1] == true){
					k++;
				}
			}
			if(x+1<10){
				if(isMine[x+1][y+1] == true){
					k++;
				}
			}
			
			Graphics g =null;
			switch(k){
				case 1:
				BufferedImage img1 = null;
				try {
					img1 = ImageIO.read(new File("number_1_blue.png"));
				} catch (IOException e2) {
					
					e2.printStackTrace();
				}
					g.drawImage(img1, x, y, WIDTH, HEIGHT, this);
					break;
				case 2:
				BufferedImage img2 = null;
				try {
					img2 = ImageIO.read(new File("images.png"));
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
					g.drawImage(img2, x, y, WIDTH, HEIGHT, this);
					break;
				case 3:
				BufferedImage img3 = null;
				try {
					img3 = ImageIO.read(new File("download.png"));
				} catch (IOException e) {
					
					e.printStackTrace();
				}
					g.drawImage(img3, x, y, WIDTH, HEIGHT, this);
					break;
			}
			return k;  
		}
		

	
		
	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return 1;
		}
		if (y < 0) {   //Above the grid
			return 1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return 1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		
		if (x < 0 || x > TOTAL_COLUMNS  -1|| y < 0 || y > TOTAL_ROWS-1 ) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X-1;
		y = y - y1 - GRID_Y-1;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}

}
