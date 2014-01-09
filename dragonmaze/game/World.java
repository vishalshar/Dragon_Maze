//name : Vishal Sharma
//A01789836
package cs5060.project.dragonmaze.game;


import java.io.*;
import java.util.*;

import cs5060.project.dragonmaze.actor.Dragon;
import cs5060.project.dragonmaze.actor.Hero;

public class World 
{

	private int maxTime;
	private int currentTime;
	private int level;
	
	private Grid grid;
	
	// constants to define number of walls in each level
	private int easy = 5;
	private int medium = 10;
	private int hard = 20;
	
	private int sleepTime = 500;
	
	private boolean exit = false;
	
	private Hero hero = new Hero('H');
	private Dragon dragon = new Dragon('D');
	
	private String move;

	private static String status;
	
	private ArrayList<Integer> x = new ArrayList<Integer>();
	private ArrayList<Integer> y = new ArrayList<Integer>();
	
	private int cost;
	
	Scanner in = new Scanner(System.in);
	
	World()
	{
		status = "******* Run Run Run *******"; 
		maxTime = 0;
		currentTime = 0;
		level = 0;
		grid = new Grid();
		exit = false;
	}
	
	// method to set the status message
	public static void setStatus(String stat)
	{
		status = stat;
	}
	
	
	// To check if the player crossed the time given
	public void checkTime()
	{
		if(currentTime >= maxTime)
		{
			status = "********   You Lose  *******";
			System.out.println("******  Out Of Time  ******");
			System.out.println("******  FAILURE  ******");
			displayWorld();
			System.exit(0);
		}
	}
	
	//It will display the game world
	public void displayWorld()
	{
		System.out.println();
		System.out.println("Time Limit : "+maxTime);
		System.out.println("Current Time : "+currentTime);
		System.out.println(status);
		grid.displayMaze();
	}
	
	
	
	// it will check for the movement of player and dragon
	// and call respective methods
	public void movement() throws Exception
	{
		// player movement
		// check method to check the finishing line or dragon killing him
		// dragon movement
		// find path to the hero. shortest minimum path
		
		while(exit != true)
		{
			System.out.println();
			System.out.println("        Its your turn.. ");
			
			move = in.nextLine();
			move = move.toLowerCase();
			
			int row , col;
			
			row = grid.getHeroLocX();
			col = grid.getHeroLocY();
			
			switch(move)
			{
			
				case ("l") :
				{
					// check if left movement is wall or exit with key not with you
					if(grid.getGridCellActor(row,col-1) == 'E' && hero.getKey() == false)
					{
						World.setStatus("********   Get the Key Before Exit *******");
					}
					
					if(!(grid.getGridCellActor(row,col-1) == 'X' || (grid.getGridCellActor(row,col-1) == 'E' && hero.getKey() == false)))
					{
						hero.checkMovementHero(row,col-1,grid,dragon);
						hero.moveHero(row,col-1,grid);
					}
					else
					{
						System.out.println(" **** You wasted a MOVE **** ");
					}
					
					// increase the time and check fot the time limit
					currentTime++;
					checkTime();
//					displayWorld();
					
					// move dragon and display world
					dragon.checkDragonMovement(grid);
					displayWorld();
					
					
					
					break;
				}
				case ("r") :
				{
					if(grid.getGridCellActor(row,col-1) == 'E' && hero.getKey() == false)
					{
						World.setStatus("********   Get the Key Before Exit *******");
					}
					// check if left movement is wall or exit with key not with you
					if(!(grid.getGridCellActor(row,col+1) == 'X' || (grid.getGridCellActor(row,col+1) == 'E' && hero.getKey() == false)))
					{
						hero.checkMovementHero(row,col+1,grid,dragon);
						hero.moveHero(row,col+1,grid);
					}
					else
					{
						System.out.println(" **** You wasted a MOVE **** ");
					}
					
					// increase the time and check for the time limit
					currentTime++;
					checkTime();
//					displayWorld();
								
					// move dragon and display world
					dragon.checkDragonMovement(grid);
					displayWorld();
				
					break;
				}
				case ("u"):
				{
					if(grid.getGridCellActor(row,col-1) == 'E' && hero.getKey() == false)
					{
						World.setStatus("********   Get the Key Before Exit *******");
					}
					// check if left movement is wall or exit with key not with you
					if(!(grid.getGridCellActor(row-1,col) == 'X' || (grid.getGridCellActor(row-1,col) == 'E' && hero.getKey() == false)))
					{
						hero.checkMovementHero(row-1,col,grid,dragon);
						hero.moveHero(row-1,col,grid);
					}
					else
					{
						System.out.println(" **** You wasted a MOVE **** ");
					}
					
//					increase the time and check for the time limit
					currentTime++;
					checkTime();
//					displayWorld();
					
					// move dragon and display world
					dragon.checkDragonMovement(grid);
					displayWorld();
					break;
				}
				case ("d"):
				{
					if(grid.getGridCellActor(row,col-1) == 'E' && hero.getKey() == false)
					{
						World.setStatus("********   Get the Key Before Exit *******");
					}
					// check if left movement is wall or exit with key not with you
					if(!(grid.getGridCellActor(row+1,col) == 'X' || (grid.getGridCellActor(row+1,col) == 'E' && hero.getKey() == false)))
					{
						hero.checkMovementHero(row+1,col,grid,dragon);
						hero.moveHero(row+1,col,grid);
					}
					else
					{
						System.out.println(" **** You wasted a MOVE **** ");
					}
					
					//increase the time and check for the time limit
					currentTime++;
					checkTime();
//					displayWorld();
					
					// move dragon and display world
					dragon.checkDragonMovement(grid);
					displayWorld();
					break;
				}
				case ("w"):
				{
					//increase the time and check for the time limit
					currentTime++;
					checkTime();
//					displayWorld();
					
					// move dragon and display world
					dragon.checkDragonMovement(grid);
					displayWorld();
					break;
				}
				case ("q!"):
				{
					// quit the game
					exit = true;
					break;
				}
				default:
				{
					System.out.println("Invalid input.. Please Try Again..!!");
					break;
				}
			}
		}
		
	}
	
	
	// It will load the grid from the map
	public void loadGrid() throws Exception
	{
		Grid.rowCount = 0;
		System.out.println("Enter the file name: ");
		
		//load maps from files
		System.out.println();
		String file = in.nextLine();

		
		ArrayList<Character> data = new ArrayList<Character>();
		
		// defining location of file
		file = "src\\cs5060\\project\\dragonmaze\\maps\\"+file;
//		System.out.println(file);
		String line;
			
		
		FileInputStream filestream = new FileInputStream(file);
		DataInputStream inp = new DataInputStream(filestream);
		BufferedReader br = new BufferedReader(new InputStreamReader(inp));

		// read first line
		
		line = br.readLine();
		
		String lineSplit[] = line.split(" ");
		 
//		get rows and columns and max time
		
		int rows = Integer.parseInt(lineSplit[0]);
		int cols = Integer.parseInt(lineSplit[1]);
		maxTime = Integer.parseInt(lineSplit[2]);
		
		// initialize the grid
		grid = new Grid(rows,cols);
		
		while ((line = br.readLine()) != null) 
		{

			for (int i = 0; i < line.length(); i++) 
			{
				data.add(line.charAt(i));
			}
			// load grid 
			grid.loadGridFromFile(data);
			data.clear();
			Grid.rowCount++;
		}
		
		displayWorld();
	    movement();
	}
	
	
	// it will take the input from the user about the level of the game
	// and will generate the maze according to that
	public void gameLevel(World world) throws Exception
	{
		
		System.out.println();
		System.out.println("	***************************************************");
		System.out.println("			Choose a game level");
		System.out.println();
		System.out.println("			1 - Easy (Size : 7*11 )");
		System.out.println("			2 - Medium (Size : 10*18)");
		System.out.println("			3 - Hard (Size : 12*22)");
		System.out.println();
		System.out.println("	***************************************************");
		while (!(level == 1 || level == 2 || level == 3)) 
		{
			level = in.nextInt();
			in.nextLine();
			switch (level) 
			{
				case 1: 
				{
					// grid define row amd column size
					// generate random maze 
					// assign actor
					// 7*11
					// 5 walls
					maxTime = easy *10;
					grid = new Grid(7,11);
					grid.generateMaze(easy);

					displayWorld();
					movement();
					
					break;
				}
				case 2: 
				{
					// grid define row amd column size
					// generate random maze 
					// assign actor
					// 10*18
					// 10 walls
					maxTime = medium*10;
					grid = new Grid(10,18);
					grid.generateMaze(medium);
					
					displayWorld();
					movement();
					
					break;
				}
				case 3: 
				{
					// grid define row amd column size
					// generate random maze 
					// assign actor
					// 12*22
					// 20 walls
					maxTime = hard*10;
					grid = new Grid(12,22);
					grid.generateMaze(hard);
					
					displayWorld();
					movement();
					
					break;
				}
				default: 
				{
					System.out.println("Invalid input.. TRY AGAIN..!!");
				}
			}
		}
	}
}
