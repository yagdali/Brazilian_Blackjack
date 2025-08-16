package brazilian_blackjack;

import java.util.*;

public class Tournament {

	public static void main(String[] args) {
		Scanner kbd = new Scanner (System.in);
		
		System.out.println("1: Four Players");
		System.out.println("2: Six Players");
		System.out.println("3: Eight Players");
		System.out.print("Select the number of players in the tournament:");
		
		int choice = kbd.nextInt();
		int player_count = 0;
		int match_count = 0;
		
		switch(choice) {
		case 1:
			//
			player_count = 4;
			match_count = 3;
			Bracket(player_count, match_count);
			break;
		case 2:
			player_count = 6;
			match_count = 5;
			Bracket(player_count, match_count);
			break;
		case 3: 
			player_count = 8;
			match_count = 7;
			Bracket(player_count, match_count);
			break;
		default:
			System.out.println("Please Select 1,2 or 3");
			break;
		}
		
	}
	public static void Bracket(int player_count, int match_count) {
		//Sets up Players and their names
		int counter = 0;
		List<String> Names = new ArrayList<>();
		Scanner kbd = new Scanner (System.in);
		while(counter < player_count) {
			System.out.print("Enter a player's name:");
			Names.add(kbd.next());
			counter++;
			
		}
		//Randomize the order
		Collections.shuffle(Names);
		counter = 0;
		
		while(counter < match_count) {
			int winnernum;
			String P1 = Names.get(0);
			String P2 = Names.get(1);
			System.out.println("Game " + (counter + 1));
			System.out.println(P1 + " V.S " + P2);
			//Start regular match by using its classes here
			//winnernum = 1 or 0 from main depending on who wins
			counter++;
			//Get this integers value as 1 or 0 from main 
			
			winnernum = BlackjackGame.tournamentMatch();
			//Add winning player behind all listed players and remove the losing player
			if(winnernum == 0) {
				Names.remove(0);
				Names.remove(0);
				Names.add(P1);
			}
			
			if(winnernum == 1) {
				Names.remove(0);
				Names.remove(0);
				Names.add(P2);
			}
			
			if (winnernum == 2) {
				winnernum = BlackjackGame.tournamentMatch();
			}
		}
		kbd.close();

		System.out.println("The winner is:" + (Names));
		return;
		
	}
	

}
