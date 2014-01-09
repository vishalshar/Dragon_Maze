
//name : Vishal Sharma
//A01789836
package cs5060.project.dragonmaze.game;


import java.util.*;
import cs5060.project.dragonmaze.actor.Actor;
import cs5060.project.dragonmaze.actor.Dragon;
import cs5060.project.dragonmaze.actor.Exit;
import cs5060.project.dragonmaze.actor.Hero;
import cs5060.project.dragonmaze.actor.Wall;

public class Grid 
{

	/**
	 *  Program to implement a grid 
	 *  which contains the maze
	 */
	
	
	private GridCell gridcell[][];  // with all location and actor
	public static int rowCount; 
	
	private int rows;
	private int cols;
	
	private GridCell heroLoc ; // location of Hero
	private GridCell exitLoc ; // location of exit
	private GridCell dragonLoc ; // dragon location
	private GridCell keyLoc; // Location of key
	private GridCell freezeLoc; // Freeze orb location
	

	Grid()
	{
		gridcell = new GridCell[0][0];
		rows=0;
		cols=0;
		rowCount = 0;
	}
	
	public GridCell getHero()
	{
		return heroLoc;
	}
	
	public GridCell getDragon()
	{
		return dragonLoc;
	}
	
	
	Grid(int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;
		gridcell = new GridCell[rows][cols];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
				gridcell[i][j] = new GridCell();
		}
	}
	
	public int getRows()
	{
		return rows;
	}
	
	public int getCols()
	{
		return cols;
	}
	
	// getter and setter for hero location
	public int getHeroLocX()
	{
		return heroLoc.getX();
	}
	
	public int getHeroLocY()
	{
		return heroLoc.getY();
	}
	
	public void setHeroLocX(int x)
	{
		heroLoc.setX(x);
	}
	
	public void setHeroLocY(int y)
	{
		heroLoc.setY(y);
	}
	
	// getter and setter for dragon location
	public int getDragonLocX()
	{
		return dragonLoc.getX();
	}
	
	public int getDragonLocY()
	{
		return dragonLoc.getY();
	}
	
	public void setDragonLocX(int x)
	{
		dragonLoc.setX(x);
	}
	
	public void setDragonLocY(int y)
	{
		dragonLoc.setY(y);
	}
	
	
	// to get the actor on a location
	public char getGridCellActor(int x , int y)
	{
		return gridcell[x][y].getActor(); 
	}
	
	// set actor on a location
	public void setGridCellActor(int x, int y , Actor actor)
	{
		gridcell[x][y].setActor(actor);
	}
	
	
	/*
	 *  Method to generate a random Maze
	 *  
	 *  Algorithm:
	 *  
	 *  1) Using a structure for defining a maze
	 *  	The end of maze is always a Wall (X)
	 *  
	 *  2) Generate random location for placing wall.
	 *  
	 *  3) Then call AssignActors to the generated grid 
	 */
	
	
	public void generateMaze(int walls) throws Exception
	{
		int count = 0;
		Actor wall = new Wall('X');
		
		int x1,y1;

		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				if(i==0 || j==0)
					gridcell[i][j].setActor(wall);
				
				if(i==rows-1 || j == cols-1)
					gridcell[i][j].setActor(wall);
			}
		}
		
		Random generator = new Random();
		
		for (int i = 0; count != walls; i++) 
		{
			
			if (count < walls) 
			{

				x1 = generator.nextInt(rows - 2);
				y1 = generator.nextInt(cols - 2);

				if ( x1 > 1 && y1 > 1 ) 
				{
					gridcell[x1][y1].setActor(wall);
					count++;
				}
			}
		}
		assignActor();
		
	}
	
	/*
	 * Generate random location for each actor 
	 * and assign the location that actor
	 * Do the same for Hero (H), exit (E), dragon (D), key(K), and freeze orb (F)
	 */
	
	public void assignActor() throws Exception
	{
		Actor hero = new Hero('H');
		Actor dragon = new Dragon('D');
		Actor exit = new Exit('E');
		Actor key = new Actor('K');
		Actor freeze = new Actor('F');

		int x1,y1;
		Random generator = new Random();
		
		boolean assign = false;
		while(assign != true)
		{
			x1 = generator.nextInt(rows - 2);
			y1 = generator.nextInt(cols - 2);
			if(gridcell[x1][y1].getActor()!= 'X')
			{
				gridcell[x1][y1].setActor(dragon);
				assign = true;
				dragonLoc = new GridCell(x1,y1);
			}
		}
		
		assign=false;
		while(assign != true)
		{
			x1 = generator.nextInt(rows - 2);
			y1 = generator.nextInt(cols - 2);
			if(gridcell[x1][y1].getActor()!= 'X' && gridcell[x1][y1].getActor()!= 'D')
			{
				gridcell[x1][y1].setActor(hero);
				assign = true;
				heroLoc = new GridCell(x1,y1);
			}
		}
		
		assign=false;
		while(assign != true)
		{
			x1 = generator.nextInt(rows - 2);
			y1 = generator.nextInt(cols - 2);
			if(gridcell[x1][y1].getActor()!= 'X' && gridcell[x1][y1].getActor()!= 'D' && gridcell[x1][y1].getActor()!= 'H')
			{
				gridcell[x1][y1].setActor(key);
				assign = true;
				keyLoc = new GridCell(x1,y1);
			}
		}
		
		assign=false;
		while(assign != true)
		{
			x1 = generator.nextInt(rows - 2);
			y1 = generator.nextInt(cols - 2);
			if(gridcell[x1][y1].getActor()!= 'X' 
					&& gridcell[x1][y1].getActor()!= 'D' 
					&& gridcell[x1][y1].getActor()!= 'H'
					&& gridcell[x1][y1].getActor()!= 'K')
			{
				gridcell[x1][y1].setActor(freeze);
				assign = true;
				freezeLoc = new GridCell(x1,y1);
			}
		}
		
		assign=false;
		while(assign != true)
		{
			x1 = generator.nextInt(rows-1);
			y1 = generator.nextInt(cols-1);
			if( (x1 > 0 && y1 == 0 ) || (y1 > 0 && x1==0) && gridcell[x1][y1].getActor() == 'X')
			{
				gridcell[x1][y1].setActor(exit);
				assign = true;
				exitLoc = new GridCell(x1,y1);
			}
		}
		
	}
	
	
	//Read from the file and load he gridCell
	// check for the actors and assign there location
	
	public void loadGridFromFile(ArrayList<Character> data)
	{
		
		for(int i=0;i<cols;i++)
		{
			Actor actor = new Actor(data.get(i));
			gridcell[rowCount][i].setActor(actor); 
			
			// define location of actors in the actors
			if(actor.getActor() == 'H')
			{
				heroLoc = new GridCell(rowCount,i);
			}
			if(actor.getActor() == 'D')
			{
				dragonLoc = new GridCell(rowCount,i);
			}
			if(actor.getActor() == 'E')
			{
				exitLoc = new GridCell(rowCount,i);
			}
			if(actor.getActor() == 'K')
			{
				keyLoc = new GridCell(rowCount,i);
			}
			if(actor.getActor() == 'F')
			{
				freezeLoc = new GridCell(rowCount,i);
			}
		}
		
	}
	
	
	// will display all actors of the grid cell
	public void displayMaze()
	{
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
				System.out.print(gridcell[i][j].getActor());
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
}
