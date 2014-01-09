//name : Vishal Sharma
//A01789836
package cs5060.project.dragonmaze.game;


import java.io.*;
import java.util.*;

public class DragonMaze 
{
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		String input = "";
		while(!input.equals("5"))
		{
			// main menu
			try 
			{
				System.out.println("	********************************************************************");
				System.out.println("		    Enter any of the following options");
				System.out.println();
				System.out.println("			1 - Load a  from file");
				System.out.println("			2 - Generate a random maze");
				System.out.println("			3 - Help with instructions on how to play the game.");
				System.out.println("			4 - About Developer");
				System.out.println("			5 - Exit");
				System.out.println();
				System.out.println("	********************************************************************");
				input = in.nextLine();
				
				switch(input)
				{
					case "1" : 
					{
						try
						{
							// create world and load the grid
								World world = new World();
								world.loadGrid();
						}
						catch (FileNotFoundException e) 
						{
						      System.out.println("File not found");
						} 
						catch (IOException e) 
						{
						      e.printStackTrace();
						}
						
						break;
						
					}
					case "2" :
					{
//						System.out.println("Generating Maze");
						System.out.println();
						World world = new World();
						world.gameLevel(world);
						break;
					}
					case "3" :
					{
						System.out.println();
						System.out.println("	********************************************************************");
						System.out.println("					Help");
						System.out.println();
						System.out.println( "				l to move left");
						System.out.println( "				r to move right");
						System.out.println("				u to move up");
						System.out.println("				d to move down");
						System.out.println("				w to skip a turn");
						System.out.println("				q! to quit the maze.");
						System.out.println();
						System.out.println("	********************************************************************");
						System.out.println();
						break;
					}
					case "4" :
					{
						System.out.println("		About Game and Developer ");
						System.out.println();
						System.out.println("		GAME : Dragon Maze");
						System.out.println();
						System.out.print("		Developer   ");
						System.out.println("NAME : VISHAL SHARMA");
						System.out.println("            		    A Number : A01789836");
						System.out.println();
						System.out.println();
						break;
					}
					case "5" :
					{
						System.exit(0);
					}
					default:
					{
						System.out.println("Invalid input.. PLEASE TRY AGAIN..!!");
					}
				}
			} 
			catch (Exception e) 
			{
				System.out.println("Invalid Input..!!");
				e.printStackTrace();
				
			}
		}
	}
}
