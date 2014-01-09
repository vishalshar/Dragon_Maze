//name : Vishal Sharma
//A01789836
package cs5060.project.dragonmaze.game;

import cs5060.project.dragonmaze.actor.Actor;


public class GridCell 
{

	/**
	 * Class to define a cell in a grid.
	 */
	
	// location x, y
	private int x;
	private int y;
	
	// actor at a location
	private Actor actor;
	
	GridCell()
	{
		x=0;
		y=0;
		actor = new Actor();
	}
	
	GridCell(int x, int y,Actor actor)
	{
		this.x = x;
		this.y = y;
		this.actor = actor;
	}
	
	GridCell(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void setX(int x)
	{
		this.x=x;
	}
	
	public void setY(int y)
	{
		this.y=y;
	}
	
	public char getActor()
	{
		return actor.getActor();
	}
	
	public void setActor(Actor actor)
	{
		this.actor = actor;
	}
	
	
	
}
