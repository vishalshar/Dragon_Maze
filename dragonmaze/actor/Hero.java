//name : Vishal Sharma
//A01789836
package cs5060.project.dragonmaze.actor;

import cs5060.project.dragonmaze.game.Grid;
import cs5060.project.dragonmaze.game.World;


public class Hero extends Actor
{

	/**
	 * Class to define hero and its movement
	 */
	private  boolean key = false;
	
	public Hero(char ch)
	{
		super(ch);
		setKey(false);
	}
	
	public boolean getKey() {
		return key;
	}


	public void setKey(boolean key) {
		this.key = key;
	}
	
	
	/* 
	 * check for the movement 
	 * if the cell is having Freeze orb activate freeze dragon
	 * if the cell is having key activate exit
	 * if cell contains exit (check for key and display result)
	 * 
	 */	
	public void checkMovementHero(int x1, int y1,Grid grid, Dragon dragon) throws Exception
	{
		
		int row = grid.getHeroLocX();
		int col = grid.getHeroLocY();
		Actor open = new Actor('.');
		
		if(!(grid.getGridCellActor(x1,y1) == 'X'))
		{
			char actor = grid.getGridCellActor(x1,y1);
			if(actor == 'K')
			{
				grid.setGridCellActor(x1, y1, open);
				World.setStatus( " ******* Got Key .. yay.. run to EXIT !! *******");
				this.setKey(true);
			}
			if(actor == 'F')
			{
				grid.setGridCellActor(x1, y1, open);
				dragon.freeze = true;
				World.setStatus(" ******* Dragron Freeze ..Run Run ..!! *******");
				dragon.counterFreeze = 0;
			}
			if(actor == 'E')
			{
				if(this.getKey() == true)
				{
					System.out.println();
					World.setStatus("********   You Won  *******");
					moveHero(x1,y1,grid);
					
					System.out.println("********   You Won  *******");
					System.out.println("********   SUCCESS  *******");
					System.exit(0);
				}
			}
			if(actor == 'D')
			{
				World.setStatus("********   You Lose  *******");
				System.out.println("********   You Lose  *******");
				System.out.println("********   FAILURE  *******");
				System.exit(0);
			}
		}
		
	}
	
	
	// move hero and change hero location
	public void moveHero(int x, int y,Grid grid) throws Exception
	{
		int row = grid.getHeroLocX();
		int col = grid.getHeroLocY();
		
		Actor hero = new Actor('H');
		Actor open = new Actor('.');
		
		grid.setGridCellActor(row, col, open);
		grid.setGridCellActor(x, y, hero);
		grid.setHeroLocX(x);
		grid.setHeroLocY(y);

	}



}
