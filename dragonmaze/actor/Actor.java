//name : Vishal Sharma
//A01789836
package cs5060.project.dragonmaze.actor;


public class Actor 
{

	/**
	 * Class for define an actor
	 * 
	 * Different type of actors
	 * 
	 * Open space : .
	 * Wall : X
	 * Dragon : D
	 * Hero : H
	 * Exit : E
	 * Key : K
	 * 
	 */
	
	
	protected char actor;
	
	public Actor()
	{
		actor='.';
	}
	
	public Actor(char ch)
	{
		this.actor = ch;
	}
	
	public char getActor()
	{
		return actor;
	}
	
	
}
