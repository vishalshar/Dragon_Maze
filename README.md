		Project : Dragon Maze
		Developer : Vishal Sharma
			    A01789836
		
		
This project is a game in which there is a maze with Walls (X), hero (H), dragon (D) 
and an exit (E).

In the beginning of the game, player and dragon will be in a maze and hero has 
to collect a key (K) before he can exit. Hero also has a power of freezing(F) the
dragon, which is he needs to grab in the maze and it will freeze the dragon for 3 turns.


When the game starts player has following menu:

1 Load a maze.
2 Generate a random maze.
3 Help with instructions on how to play the game.
4 About option that displays name of the game, your name and your A number.
5 Exit. 

Option 1:

It will load a maze from a file which is in maps folder.
Loaded map can also be a custom map.

Option 2: 

It will generate a random maze according to the level choosen by the player.
Levels : 
Easy
Medium
Hard

Option 3: 
It will give information on how to play the game.

Option 4:
It will give information about the game developer.

Option 5:
To exit.

Actors of MAZE :

1) WALL (X)
2) HERO (H)
3) DRAGON (D)
4) FREEZE ORB (F)
5) EXIT (E)
6) Open Space ( . )

Logic for generating Random MAZE: 
(I have explained the same in the code aswell)

 1) Using a structure for defining a maze
	  That is, corner of the end of maze is always a Wall
	  
 2) Generate random location for placing wall. Number of walls depends on the
	size of map, which depends on the level choosen.

 3) Then call AssignActors to the generated grid 
	It will generate random location for all the actors in the Maze.

	
After selecting the level player will get a maze and hero will be trapped and 
he has to reach exit with key before time ends or dragon eats him.

First move is by the player. After which dragon moves.
Dragon need to find a path to move closer to the hero and kill him.


Algorithm For dragon finding the path:
(I have explained the same in the code aswell)

Suppose location of Dragon is (xD,yD) and location of Hero is (xH,yH)
then if we consider location of dragon as origin 
then, changing the location of Hero (xD-xH, yD-yH) with respect to dragon location

Dragon can locate hero using the below co-ordinate system

			    |
 		H(x,y)    |		H(x, -y)
 			    |
 		1st 	  |		2nd
   			    |
 			    |
  	______|___________
 			    |
 			    |			
		  4th	    |		3rd
			    | 		
	H(-x,y)             |		H(-x,-y)
  			    |
			    |

then using the respective coordinate we can define the move of the dragon.


Suppose,
If the hero is in 1st coordinate then dragon can move up or left to be closure to hero.
If the hero is in 2nd coordinate then dragon can move up or right to be closure to hero.
If the hero is in 3rd coordinate then dragon can move down or left to be closure to hero.
If the hero is in 4th coordinate then dragon can move up or right to be closure to hero. 

Comments:

1) During the play when Hero takes the freeze orb (F) it activates the freeze on the dragon
and dragon cannot move for next 3 turns.

2) Player need to get the key before exit.

3) Exit is always on the border of the map but its random in the corners.

4) Location of X,K ,F ,H and D is all random.

5) Recursion is used in finding the cost of the path when dragon moves
Method name calculatePath() in Dragon class

6) Map are always with border.

7) Personal feature added 
	* Key (K) - Player Needs to get the key before exit
	* Freeze orb (F) - It will freeze the dragon for 3 turns

8) Two data structures used are :-
1) GridCell gridcell[][] in Grid class.
2) ArrayList<Character> data in World class.
