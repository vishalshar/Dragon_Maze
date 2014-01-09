//name : Vishal Sharma
//A01789836
package cs5060.project.dragonmaze.actor;

import cs5060.project.dragonmaze.game.Grid;
import cs5060.project.dragonmaze.game.World;


public class Dragon extends Actor
{

	private static int cost = 0;
	protected  boolean breakWall = false;
	protected  char pre_act;
	protected  int counterWall = 0;
	protected  int counterFreeze = 0;
	protected  boolean freeze = false;
	
	public Dragon(char ch) 
	{
		super(ch);
		pre_act = '.';
		breakWall = false;
		counterWall = 1;
		counterFreeze = 0;
		freeze = false;
	}


	// Algorithm to find the path for dragon towards hero
	public  void findPath(Grid grid) 
	{

		int xD = grid.getDragonLocX();
		int yD = grid.getDragonLocY();
		
		// Hero location
		int xH = grid.getHeroLocX();
		int yH = grid.getHeroLocY();
		
		int xValue = xD - xH;
		int yValue = yD - yH;
		
		/*
		 * 
		 * Algorithm: Use coordinate system to locate hero
		 * 
		 * 
		 * Suppose location of Dragon is (xD,yD) and location of Hero is (xH,yH)
		 * then if we consider location of dragon as origin 
		 * then, changing the location of Hero (xD-xH, yD-yH) with respect to dragon location
		 * 
		 * Dragon can locate hero using the below co-ordinate system
		 * 
		 * 
		 *  		H(x,y)			|		H(x, -y)
		 *  						|
		 *  			1st			|			2nd
		 *  						|
		 *  						|
		 *  	____________________|____________________
		 *  						|
		 *  						|			
		 *  		  4th			|		  3rd
		 *  						| 		
		 *  			H(-x,y)		|		H(-x,-y)
		 *  						|
		 *  
		 *  then using the respective coordinate we can define the move of the dragon.
		 *  
		 *  
		 *  Suppose,
		 *   
		 *  If the hero is in 1st coordinate then dragon can move up or left to be closure to hero.
		 *  If the hero is in 2nd coordinate then dragon can move up or right to be closure to hero.
		 *  If the hero is in 3rd coordinate then dragon can move down or left to be closure to hero.
		 *  If the hero is in 4th coordinate then dragon can move up or right to be closure to hero.
		 *  
		 *  
		 */
		if(xValue > 0)
		{
			if(yValue > 0)
			{
				cost = 0;
				int cost1 = calculatePath(xD,yD,xH,yH,'u',grid);
				cost = 0;
				int cost2 = calculatePath(xD,yD,xH,yH,'l',grid);
				
				if(cost1 > cost2)
				{
					moveDragon(xD,yD-1,grid);
				}
				else
				{
					moveDragon(xD-1,yD,grid);
				}
			}
			else if(yValue == 0)
			{
				moveDragon(xD-1,yD,grid);
			}
			else
			{
				cost = 0;
				int cost1 = calculatePath(xD,yD,xH,yH,'u',grid);
				cost = 0;
				int cost2 = calculatePath(xD,yD,xH,yH,'r',grid);
				
				if(cost1 > cost2)
				{
					moveDragon(xD,yD+1,grid);
				}
				else
				{
					moveDragon(xD-1,yD,grid);
				}
			}
		}
		
		
		if(xValue == 0)
		{
			if(yValue > 0)
			{
				moveDragon(xD,yD-1,grid);
			}
			if (yValue < 0)
			{
				moveDragon(xD,yD+1,grid);
			}
		}
		
		
		if(xValue < 0)
		{
			if(yValue < 0)
			{
				cost = 0;
				int cost1 = calculatePath(xD,yD,xH,yH,'d',grid);
				cost = 0;
				int cost2 = calculatePath(xD,yD,xH,yH,'r',grid);
				
				if(cost1 > cost2)
				{
					moveDragon(xD,yD+1,grid);
				}
				else
				{
					moveDragon(xD+1,yD,grid);
				}
			}
			else if(yValue == 0)
			{
				cost = 0;
				int cost1 = calculatePath(xD,yD,xH,yH,'d',grid);
				moveDragon(xD+1,yD,grid);
			}
			else
			{
				cost = 0;
				int cost1 = calculatePath(xD,yD,xH,yH,'d',grid);
				cost = 0;
				int cost2 = calculatePath(xD,yD,xH,yH,'l',grid);
				
				if(cost1 > cost2)
				{
					moveDragon(xD,yD-1,grid);
				}
				else
				{
					moveDragon(xD+1,yD,grid);
				}
			}
		}
	}
	
	
	
	// method used to find the cost of the path to the hero
	public  int calculatePath(int xD,int yD, int xH, int yH,char direction,Grid grid) 
	{
//		System.out.println(xD+" "+yD+" "+xH+ " "+yH);
		
		
		/*
		 * Algorithm: Using the coordinate system defined above
		 * 
		 * Start from the dragons position and move towards hero by checking his coordinate
		 * and adding and or subtracting from dragon's location
		 * 
		 * Keep adding the value of each step and add 3 to step if there is X (Wall) in
		 * the  path way then find the route and return the cost.
		 * 
		 * Suppose, 
		 * If the hero is in 1st coordinate then 
		 * Dragon will have to move up or in left to reach hero and it will calculate the 
		 * cost of movement in both direction then based on cost it will decide where to move.
		 * 
		 * If the hero is in 2nd coordinate then 
		 * Dragon will have to move up or in right to reach hero and it will calculate the 
		 * cost of movement in both direction then based on cost it will decide where to move.
		 * 
		 * If the hero is in 3rd coordinate then 
		 * Dragon will have to move down or in left to reach hero and it will calculate the 
		 * cost of movement in both direction then based on cost it will decide where to move.
		 * 
		 * If the hero is in 4th coordinate then 
		 * Dragon will have to move down or in right to reach hero and it will calculate the 
		 * cost of movement in both direction then based on cost it will decide where to move.
		 * 
		 */
		
		if(xD >= grid.getRows() || yD >= grid.getCols())
		{
			return 0;
		}
		
		if( (xD-xH == 0) && (yD-yH==0) )
		{
			return cost;
		}
		
		if(grid.getGridCellActor(xD, yD) == 'X')
		{
			cost = cost +3;
		}
		
		if(direction == 'u')
		{
			xD-=1;
			cost++;
			if(xD == xH && (yD-yH < 0))
			{
				calculatePath(xD,yD,xH,yH,'r',grid);
			}
			else if(xD == xH && (yD-yH > 0))
			{
				calculatePath(xD,yD,xH,yH,'l',grid);
			}
			else if(yD-yH == 0)
			{
				calculatePath(xD,yD,xH,yH,'u',grid);
			}
			else
			{
				calculatePath(xD,yD,xH,yH,'u',grid);
			}
		}
		
		if(direction == 'd')
		{
			xD+=1;
			cost++;
			if(xD == xH && (yD-yH < 0))
			{
				calculatePath(xD,yD,xH,yH,'r',grid);
			}
			else if(xD == xH && (yD-yH > 0))
			{
				calculatePath(xD,yD,xH,yH,'l',grid);
			}
			else if(yD-yH == 0)
			{
				calculatePath(xD,yD,xH,yH,'d',grid);
			}
			else
			{
				calculatePath(xD,yD,xH,yH,'d',grid);
			}
		}
		
		if(direction == 'l')
		{
			yD-=1;
			cost++;
			if(yD == yH && (xD-xH < 0))
			{
				calculatePath(xD,yD,xH,yH,'d',grid);
			}
			else if(yD == yH && (xD-xH > 0))
			{
				calculatePath(xD,yD,xH,yH,'u',grid);
			}
			else if(xD-xH == 0)
			{
				calculatePath(xD,yD,xH,yH,'l',grid);
			}
			else
			{
				calculatePath(xD,yD,xH,yH,'l',grid);
			}
			
		}
		if(direction == 'r')
		{
			yD+=1;
			cost++;
			if(yD == yH && (xD-xH < 0))
			{
				calculatePath(xD,yD,xH,yH,'d',grid);
			}
			else if(yD == yH && (xD-xH > 0))
			{
				calculatePath(xD,yD,xH,yH,'u',grid);
			}
			else if(xD-xH == 0)
			{
				calculatePath(xD,yD,xH,yH,'r',grid);
			}
			else
			{
				calculatePath(xD,yD,xH,yH,'r',grid);
			}
		}
		
		return cost;
	}
	

	
	// Method responsible for moving the dragon
	public void moveDragon(int x, int y,Grid grid)
	{
		
		int xD = grid.getDragonLocX();
		int yD = grid.getDragonLocY();
		
		// If the next movement is wall then brake it
		if(grid.getGridCellActor(x, y) == 'X' )
		{
			breakWall = true;
			World.setStatus("********  Breaking Wall  ********");
			pre_act= '.';
		}
		
		// If its hero Dragon wins
		else if(grid.getGridCellActor(x, y) == 'H' )
		{
			World.setStatus( "********   You Lose  *******");
			System.out.println("****** Dragon Won ( hehehe ) ******");
			System.out.println("********   FAILURE  *******");
			System.exit(0);
		}
		
		else 
		{
			breakWall = false;
			counterWall=1;
		}

		// move to the next location
		// keep what ever actor is in the next location in a actor
		// when moving forward replace it back
		
		Actor open = new Actor(pre_act);
		grid.setGridCellActor(xD, yD,open);
		
		
		if(pre_act == 'F' || pre_act == 'K')
		{
			pre_act = '.';
		}
		
		if(grid.getGridCellActor(x, y) == 'F' )
		{
			pre_act = 'F';
		}
		
		if( grid.getGridCellActor(x, y) == 'K')
		{
			pre_act = 'K';
		}
		
		
		Actor dragon = new Dragon('D');
		grid.setGridCellActor(x, y,dragon);
		// set dragon location
		grid.setDragonLocX(x);
		grid.setDragonLocY(y);
		
	}
	
	
	
	// It will check for the dragon movement 
	// whether it can move or not.
	// according to the condition it will decide the move.
	
	public  void checkDragonMovement(Grid grid)
	{
		
		if(freeze == true && counterFreeze < 3)
		{
			counterFreeze++;
		}
		
		else if (breakWall == true && counterWall < 3)
		{ 
			counterWall++;
		}
		
		else if(counterFreeze == 3)
		{
			freeze = false;
			World.setStatus("Dragon is coming .... Run Run..!!");
			counterFreeze=0;
			findPath(grid);
		}
		
		else if(counterWall == 3)
		{
			breakWall = false;
			World.setStatus("Dragon is coming .... Run Run..!!");
			counterWall=1;
			findPath(grid);
		}
		else
		{
			findPath(grid);
		}
		
	}
}
