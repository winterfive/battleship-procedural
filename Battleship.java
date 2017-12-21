// A battleship style game 
// @author Lee Gainer
// @since October 2017

import java.util.Random;
import java.util.Scanner;

public class Game {
	
	public static void main(String args[]) {
		
		System.out.println("*** Welcome to the Battleship Game ***\n\nRight now, the sea is empty.");
		
		// Create game array to display
		String [][] gameboard = new String [12][16];
		
		// create gameboard
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 16; j++) {
				
				// place numbers in first row
				if (i == 0 && j != 0 && j != 1 && j != 2 && j != 13 && j != 14 && j != 15) {
					gameboard[i][j] = String.valueOf(j - 3) + " ";
				}
				
				// draw spaces in 12th column w/ no space
				else if(i != 0 && i != 11 && j != 12) {
					gameboard[i][j] = "  ";
				}
				
				// draw spaces
				else {
					gameboard[i][j] = " ";					
				}
				
				// place numbers in bottom row
				if (i == 11 && j != 0 && j != 1 && j != 2 && j != 13 && j != 14 && j != 15) {
					gameboard[i][j] = String.valueOf(j - 3) + " ";
				}
				
				// place numbers in columns on Lside of gameboard
				if (j == 0 && i != 0 && i != 11) {
					gameboard[i][j] = String.valueOf(i - 1);
				}
				
				// place numbers in columns on Rside of gameboard
				if (j == 15 && i != 0 && i != 11) {
					gameboard[i][j] = String.valueOf(i - 1) + " ";
				}
				
				//place spaces along side columns
				if ((j == 1 || j == 14) && i != 0 && i != 11) {
					gameboard[i][j] = " ";
				}
				
				// place pipes along sides
				if ((j == 2 || j == 13) && i != 0 && i != 11) {
					gameboard[i][j] = "|";
				}
			}			
		}
		
		// display gameboard
		for(int i = 0; i < 12; i++) {
			System.out.println("\r");
			for(int j = 0; j < 16; j++) {
				System.out.print(gameboard[i][j]);
			}
		}
		
		// Let's play!
		System.out.println("\n\nPrepare your fleet for battle!\n");
		
		// track number of ships in the fleet
		int playerShipCount = 1;
		
		// track all ships for each player in separate array
		int[][] shipTracker = new int [10][10];
		
		// Use loop to get coordinates for all five player ships
		while (playerShipCount <= 5) {
			
			// Get col coordinate for ship
			Scanner getCol = new Scanner(System.in);
	        System.out.print("Enter X coordinate for ship #" + playerShipCount + ": ");
	        int col = getCol.nextInt();
	        
	        // If col coordinate is not on gameboard...
			while (col > 9 || col < 0) {
				System.out.println("That coordinate isn't available.");
				
				Scanner getColAgain = new Scanner(System.in);
				System.out.println("Please enter a number between 0 and 9.");
				col = getColAgain.nextInt();
				if (col <= 9 && col >= 0) {
					continue;
				}
			}
			
			// Get row coordinate for a ship			
	        Scanner getRow = new Scanner(System.in);
	        System.out.print("Enter Y coordinate for ship #" + playerShipCount + ": ");
	        int row = getRow.nextInt();
	        
	        // If row coordinate is not on gameboard...
			while (row > 9 || row < 0) {
				System.out.println("That coordinate isn't available.");			
				Scanner getRowAgain = new Scanner(System.in);
				System.out.println("Please enter a number between 0 and 9: ");
				row = getRowAgain.nextInt();
				if (row <= 9 && row >= 0) {
					continue;
				}
			} 
			
			// Check if ship location is already filled
			if(shipTracker[row][col] == 1) {
				System.out.println("That spot is taken. Try again.");
				continue;
			}
				
			// Store row and col in gameboard
			if(col == 9) {
				gameboard[row + 1][col + 3] = "S";
			} else {
				gameboard[row + 1][col + 3] = "S ";
			}
			
			// Store player ship in shipTracker array
			// 1 = player's ship
			// 2 = computer ship
			// 3 = shot already fired by computer
			shipTracker[row][col] = 1;
			
			// Display gameboard with ship location
			for(int i = 0; i < 12; i++) {
				System.out.println("\r");
				for(int j = 0; j < 16; j++) {
					System.out.print(gameboard[i][j]);
				}
			}
			
			// Blank line
			System.out.println("\n");
				
			playerShipCount++;   
		}
		
		System.out.println("The computer is deploying it's fleet.");		
		
		// Generate random assignments for computer ships
		Random a = new Random();
		
		int compShipCount = 1;
		
		while(compShipCount <= 5) {
			int b = a.nextInt(9);
			int c = a.nextInt(9);
			
			// Check that computer coordinates are unique from other ships		
			while(shipTracker[b][c] == 1 || shipTracker[b][c] == 2) {
				b = a.nextInt(9);
				c = a.nextInt(9);
			}
			
			// Pause and then announce ship deployment 
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}	
			
			System.out.println("Enemy ship #" + compShipCount + " is ready for battle.");
			
			// Store comp ship in shipTracker
			shipTracker[b][c] = 2;
			
			compShipCount++;
		}
		
		System.out.println("\nLet the battle begin!\n");
		
		// Begin play.
				
		// Track number of shots fired by player
		int playerShotCount = 1;
		
		// Track ships remaining for each player
		int playerShipsRemaining = 5;
		int compShipsRemaining = 5;
		
		// Play continues until a fleet has 0 ships remaining
		do {
			
			// Get coordinates for player shot.
			// Get x coordinate.
			Scanner colShot = new Scanner(System.in);
			System.out.println("YOUR TURN\nPlease enter X coordinate for shot #" + playerShotCount + ": ");
			int col = colShot.nextInt();
			
			// If X coordinate is not on gameboard...
			while (col > 9 || col < 0) {
				System.out.println("That coordinate isn't available.");			
				Scanner getColShotAgain = new Scanner(System.in);
				System.out.println("Please enter a number between 0 and 9: ");
				col = getColShotAgain.nextInt();
				if (col <= 9 && col >= 0) {
					continue;
				}
			}
			
			// Get Y coordinate for player shot.
			Scanner rowShot = new Scanner(System.in);
			System.out.println("Please enter Y coordinate for shot #" + playerShotCount + ": ");
			int row = rowShot.nextInt();
			
			// If Y coordinate is not on gameboard...
			while (row > 9 || row < 0) {
				System.out.println("That coordinate isn't available.");			
				Scanner getRowShotAgain = new Scanner(System.in);
				System.out.println("Please enter a number between 0 and 9: ");
				row = getRowShotAgain.nextInt();
				if (row <= 9 && row >= 0) {
					continue;
				}
			}
			
			playerShotCount++;
			
			// Evaluate the shot.			
			// Player sank their own ship.
			if(shipTracker[row][col] == 1) {
				System.out.println("\nYou sank your own ship!");
				
				// Update gameboard
				if(col == 9) {
					gameboard[row + 1][col + 3] = "X";
				} else {
					gameboard[row + 1][col + 3] = "X ";
				}
				
				shipTracker[row][col] = 3;
				
				playerShipsRemaining--;
			}
			
			// Player sank an enemy ship.
			else if(shipTracker[row][col] == 2) {
				System.out.println("\nYou sank an enemy ship!");
				
				// Update gameboard
				if(col == 9) {
					gameboard[row + 1][col + 3] = "!";
				} else {
					gameboard[row + 1][col + 3] = "! ";
				}
				
				shipTracker[row][col] = 3;
				
				compShipsRemaining--;
			}
			
			// Player missed or repeated a shot.
			else {
				// Player hits sunken ship
				if(gameboard[row + 1][col + 3] == "X" ||
				   gameboard[row + 1][col + 3] == "X " ||
				   gameboard[row + 1][col + 3] == "-" ||
				   gameboard[row + 1][col + 3] == "- " ||
				   gameboard[row + 1][col + 3] == "!" ||
				   gameboard[row + 1][col + 3] == "! ") {
					System.out.println("You've made that shot before.\nDon't waste missles... they're expensive!");					
				}
				
				// Player missed.
				else {
					System.out.println("\nYou missed.");
					
					// Update gameboard
					if(col == 9) {
						gameboard[row + 1][col + 3] = "-";
					} else {
						gameboard[row + 1][col + 3] = "- ";
					}					
				}				
			}
			
			// Display gameboard with updated data
			for(int i = 0; i < 12; i++) {
				System.out.println("\r");
				for(int j = 0; j < 16; j++) {
					System.out.print(gameboard[i][j]);
				}
			}
			
			if(playerShipsRemaining == 0 || compShipsRemaining == 0) {
				break;
			}
			
			// Display remaining ship count
			if(playerShipsRemaining == 1) {
				System.out.println("\n\nYou have " + playerShipsRemaining + " ship left.");				
			} else {
				System.out.println("\n\nYou have " + playerShipsRemaining + " ships left.");
			}
			
			if(compShipsRemaining == 1) {
				System.out.println("The computer has " + compShipsRemaining + " ship left.");				
			} else {
				System.out.println("The computer has " + compShipsRemaining + " ships left.");
			}		
			
			// Display "COMPUTER'S TURN"
			System.out.println("\nCOMPUTER'S TURN");
			
			// Pause and then announce computer's shot response 
				try {
					Thread.sleep(3000);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			
			int r, c;
			
			// Check if shot is a repeat
			do {
				// Create random coordinates for shot
				Random s = new Random();	
				r = s.nextInt(9);
				c = s.nextInt(9);
			} while(shipTracker[r][c] == 3 || shipTracker[r][c] == 2);
			
			// Pause and then announce computer shot response 
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			// 1) Computer sank player's ship
			if(shipTracker[r][c] == 1) {
				System.out.println("The computer sank your ship!");
				
				// Update gameboard
				if(c == 9) {
					gameboard[r + 1][c + 3] = "X";
				} else {
					gameboard[r + 1][c + 3] = "X ";
				}
				
				// Update shipTracker
				shipTracker[r][c] = 3;
				
				playerShipsRemaining--;
			}
				
			// 2) Computer missed
			else {
				System.out.println("The computer missed.");
				
				// Update shipTracker
				shipTracker[r][c] = 3;
			}			
			
			// Display gameboard with updated data
			for(int i = 0; i < 12; i++) {
				System.out.println("\r");
				for(int j = 0; j < 16; j++) {
					System.out.print(gameboard[i][j]);
				}
			}
			
			// Blank line
			System.out.println("\n");
			
		} while(playerShipsRemaining != 0 && compShipsRemaining != 0);
		
		if(playerShipsRemaining == 0) {
			System.out.println("\nYour fleet was destroyed.  You lose!");
		}
		
		if(compShipsRemaining == 0) {
			System.out.println("\nYou destroyed all of the computer's ships.  You won the battle!");
		}		
	}
}
