package brazilian_blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BlackjackGame {
    private static final int SHUFFLE_COUNT = 100000;
    private static final int DECK_SIZE = 8;
    private static final int PLAYER_COUNT = 2; // player 1 is the dealer and player 0 is user
    private static List<Hand> hands = new ArrayList<>();
    private static int wins = 0;
    private static int winsP1 = 0;
    private static int winsP2 = 0;
    private static int games = 0;
    private static Leaderboard leaderboard;

   
    private static List<Card> deck = new ArrayList<>();
    private static final String[] SUITS = {"♠", "♣", "♥", "♦"};
    private static final String[] FACES = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Joker", "Queen", "King"};
    private static final int[] VALUES = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    public static void main(String[] args) {
        createDeck();
        shuffle(deck);
        
        int selection = 0;
        
        Scanner input = new Scanner(System.in);
        /**
         * Main menu for Black jack menu
         * can cycle thru game modes
         */
		do {
			
			System.out.println("Brazillian Blackjack main menu: ");
			System.out.println("1. Solo game");
			System.out.println("2. Tournament mode");
			System.out.println("3. 99 Mode");
			System.out.println("4. 2 player mode");
			System.out.println("9. Exit");
			System.out.print("\n");
			
			selection = input.nextInt();
			switch (selection)
			{
			case 1:	
			
		        String gameOn = "y";

		        while (gameOn.equalsIgnoreCase("y")) 
		        {
		            //clearConsole();
		            playHand(input);

		            System.out.println("You have played " + games + " games and won " + wins + " of them");
		            System.out.print("New hand? [y|n]: ");
		            gameOn = input.next();
		        }
				break;	
				
			case 2:

				Scanner kbd = new Scanner (System.in);
				String gameOn2 = "y";
				  while (gameOn2.equalsIgnoreCase("y")) 
			      {
					    //Scanner kbd = new Scanner (System.in);
						System.out.println("1: Four Players");
						System.out.println("2: Six Players");
						System.out.println("3: Eight Players");
						System.out.print("Select the number of players in the tournament:");
						
						int choice = kbd.nextInt();
						int player_count = 0;
						int match_count = 0;
						
						switch(choice) 
						{
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
					  
			            System.out.print("New tournament? [y|n]: ");
			            gameOn2 = input.next();
			            
			    }
				//kbd.close();
				break;
						
			case 3:
				
				play99Hand(input);
				leaderboard.printLeaderboard();
				break;
				
			case 4:
		        String gameOn3 = "y";

		        while (gameOn3.equalsIgnoreCase("y")) {
		            //clearConsole();
		            twoPlayerHand(input);

		            System.out.println("You have played " + games + " games and p1 has won " + winsP1 + " and p2 has won " + winsP2);
		            System.out.print("New hand? [y|n]: ");
		            gameOn3 = input.next();
		        }
				break;
			
			case 9:
				System.out.print("Have a nice day thank you for playing Brazillian Blackjack" + "\n");
				System.exit(0);
				break;
		}
			
		} while(selection != 9);
		input.close();

    }


	private static void createDeck() {
        for (int d = 1; d < DECK_SIZE; d++) 
        {
            for (String suit : SUITS) 
            {
                for (int i = 0; i < 12; i++) 
                {
                    boolean isAce = VALUES[i] == 11;
                    Card card = new Card(suit, FACES[i], VALUES[i], isAce);
                    deck.add(card);
                }
            }
        }
    }

    /**
     * A function to shuffle cards in the game's deck
     * @param deck
     */
    private static void shuffle(List<Card> deck) 
    {
        Random random = new Random();
        for (int i = 0; i < SHUFFLE_COUNT; i++) 
        {
            int x = random.nextInt(deck.size());
            int y = random.nextInt(deck.size());

            Card temp = deck.get(x);
            deck.set(x, deck.get(y));
            deck.set(y, temp);
        }
    }

    /**
     * Function handles game loop 
     * option to play hands
     * and outputs win loss message to player if they win or fail
     * @param scanner
     */
    private static void playHand(Scanner scanner) {
        hands.clear();

        // Create player hands
        for (int p = 0; p <= PLAYER_COUNT; p++) 
        {
        	
            Hand hand = new Hand();
            hands.add(hand);
        }

        boolean dealt = false;
        Hand dealInital = new Hand();
        // Deal initial 2 cards to EA player
        for (int i = 0; i < 2; i++) 
        {
            for (Hand hand : hands) 
            {
                hand.addCard(deck.get(0));
                if (dealt = false)
                {
                	dealInital.addCard(deck.get(0));
                }
                
                deck.remove(0);
                dealt = true;
            }
        }

        System.out.println("Your hand is " + hands.get(1).printHand() + " for a value of " + hands.get(1).getHandValue());
        System.out.println("The revealed dealer card is " + hands.get(0).getHandValue());
        //System.out.println("The revealed dealer card is " + dealInital.getHandValue());
        
        System.out.println("Would you like to Hit or Stay?");
        System.out.println("Press h for Hit or s to Stay");
        String play = scanner.next();
        

        while (play.equalsIgnoreCase("h") && hands.get(1).getHandValue() <= 21) 
        {
            hands.get(1).addCard(deck.get(0));
            deck.remove(0);

            if (hands.get(1).getHandValue() < 21) 
            {
                System.out.println("Your hand is " + hands.get(1).printHand() + " for a value of " + hands.get(1).getHandValue());
                System.out.println("The revealed dealer card is " + hands.get(0).getHandValue());
                //System.out.println("Your dealer is " + hands.get(0).printHand() + " for a value of " + hands.get(0).getHandValue());

                System.out.println("Would you like to Hit or Stay?");
                System.out.println("Press h for Hit or s to Stay");
                play = scanner.next();
            } 
            
            else if (hands.get(1).getHandValue() == 21) 
            {
                System.out.println("You have 21!");
            } 
            
            else 
            {
            	System.out.println("Your hand is " + hands.get(1).printHand() + " for a value of " + hands.get(1).getHandValue());
                System.out.println("You have a bust :(");
                break;
            }
        }

        System.out.println("The dealer has " + hands.get(0).printHand() + " for a value of " + hands.get(0).getHandValue());

        while (hands.get(0).getHandValue() < 17) 
        {
            hands.get(0).addCard(deck.get(0));
            deck.remove(0);
        }

        System.out.println("The final dealer hand is " + hands.get(0).printHand() + " for a value of " + hands.get(0).getHandValue());

        if (hands.get(0).getHandValue() <= 21 && hands.get(1).getHandValue() <= 21) 
        {
            if (hands.get(0).getHandValue() > hands.get(1).getHandValue()) 
            {
                System.out.println("You lost");
            } 
            
            else if (hands.get(0).getHandValue() == hands.get(1).getHandValue()) 
            {
                System.out.println("Game was a draw");
            } 
            
            else 
            {
                System.out.println("You win!");
                wins++;
            }
            
        } 
        else if (hands.get(0).getHandValue() > 21 && hands.get(1).getHandValue() <= 21) 
        {
            System.out.println("The dealer is the loser");
            System.out.println("You win!");
            wins++;
        }

        games++;
    }
    
    /**
     * Function handles game loop with 99 levels of increasing difficulty.
     * Option to play hands and outputs win/loss message to the player.
     * @param scanner
     */
    private static void play99Hand(Scanner scanner) {
        for (int level = 1; level <= 99; level++) 
        {
            System.out.println("\n*** Level " + level + " ***");
            hands.clear();

            // Create player hands
            for (int p = 0; p <= PLAYER_COUNT; p++) 
            {
                Hand hand = new Hand();
                hands.add(hand);
            }

            boolean dealt = false;
            Hand dealInitial = new Hand();

            // Deal initial cards to each player
            for (int i = 0; i < 2; i++) 
            {
                for (Hand hand : hands) 
                {
                    hand.addCard(deck.get(0));
                    if (!dealt) 
                    {
                        dealInitial.addCard(deck.get(0));
                    }
                    deck.remove(0);
                    dealt = true;
                }
            }

            System.out.println("Your hand is " + hands.get(1).printHand() + " for a value of " + hands.get(1).getHandValue());
            System.out.println("The revealed dealer card is " + dealInitial.getHandValue());

            System.out.println("Would you like to Hit or Stay?");
            System.out.println("Press h for Hit or s to Stay");
            String play = scanner.next();

            while (play.equalsIgnoreCase("h") && hands.get(1).getHandValue() <= 21) {
                hands.get(1).addCard(deck.get(0));
                deck.remove(0);

                if (hands.get(1).getHandValue() < 21) 
                {
                    System.out.println("Your hand is " + hands.get(1).printHand() + " for a value of " + hands.get(1).getHandValue());
                    System.out.println("The revealed dealer card is " + hands.get(0).getHandValue());
                    System.out.println("Would you like to Hit or Stay?");
                    System.out.println("Press h for Hit or s to Stay");
                    play = scanner.next();
                } 
                else if (hands.get(1).getHandValue() == 21) 
                {
                	System.out.println("Your hand is " + hands.get(1).printHand() + " for a value of " + hands.get(1).getHandValue());
                    System.out.println("You have 21!");
                    break;
                } 
                else 
                {
                    System.out.println("Your hand is " + hands.get(1).printHand() + " for a value of " + hands.get(1).getHandValue());
                    System.out.println("You have a bust :(");
                    break;
                }
            }

            // Dealer's turn
            System.out.println("The dealer has " + hands.get(0).printHand() + " for a value of " + hands.get(0).getHandValue());
            while (hands.get(0).getHandValue() < 17 + level / 10) 
            { // Dealer gets harder
                hands.get(0).addCard(deck.get(0));
                deck.remove(0);
            }

            System.out.println("The final dealer hand is " + hands.get(0).printHand() + " for a value of " + hands.get(0).getHandValue());

            // winner message
            if (hands.get(0).getHandValue() <= 21 && hands.get(1).getHandValue() <= 21) 
            {
                if (hands.get(0).getHandValue() > hands.get(1).getHandValue()) 
                {
                    System.out.println("You lost");
                } 
                else if (hands.get(0).getHandValue() == hands.get(1).getHandValue()) 
                {
                    System.out.println("Game was a draw");
                    int p1 =  0;
                    int comp = 0;
                    System.out.println("War sudden death!");
                    do
                    {
                    	   p1 =  (int)(Math.random() * 12);
                           comp = (int)(Math.random() * 12);
                           System.out.println("Your card is worth " + p1);
                           System.out.println("The dealers card is worth " + comp);
                    }while (p1 == comp) ;
                    if(p1 >comp) 
                    {
                    	System.out.println("You won congragulations");
                    	wins++;
                    }
                    else
                    {
                    	System.out.println("You lost ");
                    	break;
                    }
                    
                    

                } 
                else 
                {
                    System.out.println("You win!");
                    wins++;
                }
            } 
            else if (hands.get(0).getHandValue() > 21 && hands.get(1).getHandValue() <= 21) 
            {
                System.out.println("The dealer is the loser");
                System.out.println("You win!");
                wins++;
            }

            games++;
            System.out.println("Completed Level " + level + ". Wins: " + wins + ", Games: " + (games-1) );

            // Shuffle and reset the deck after each level
            if (level <= 99 && wins+1 == level) 
            {
            	scanner.nextLine();
        		System.out.println("What is your name?");
            	String name = scanner.nextLine();
        		Leaderboard.recordHiScore(name, level -1);
        		level = 99;
            	
            }
            //Leaderboard.recordHiScore("Alice", level);
        }

        
        System.out.println("\nCongratulations on making it to level " + wins);
        wins = 0;
        games = 0;
    }
    
    private static void twoPlayerHand(Scanner scanner) {
        hands.clear();

        // Create player hands
        for (int p = 0; p <= PLAYER_COUNT; p++) {
            Hand hand = new Hand();
            hands.add(hand);
        }

        // Deal initial 2 cards to each player
        for (int i = 0; i < 2; i++) {
            for (Hand hand : hands) {
                hand.addCard(deck.get(0));
                deck.remove(0);
            }
        }

        // Player 1 loop
        while (hands.get(1).getHandValue() <= 21) 
        {
            System.out.println("P1: Your hand is " + hands.get(1).printHand() + " for a value of " + hands.get(1).getHandValue());
            System.out.println("P1: Would you like to Hit or Stay? (h for Hit, s for Stay)");
            String play = scanner.next();

            if (play.equalsIgnoreCase("h")) 
            {
                hands.get(1).addCard(deck.get(0));
                deck.remove(0);
            }
            else if (play.equalsIgnoreCase("s")) 
            {
                System.out.println("P1 stays with a hand value of " + hands.get(1).getHandValue());
                break;
            } 
            else 
            {
                System.out.println("Invalid input. Please enter 'h' or 's'.");
            }

            if (hands.get(1).getHandValue() > 21) 
            {
                System.out.println("P1: You have busted with " + hands.get(1).getHandValue() + "!");
                return;
            } 
            else if (hands.get(1).getHandValue() == 21) 
            {
                System.out.println("P1: You have 21!");
                winsP1++;
                break;
            }
        }

        // Player 2 interaction loop
        while (hands.get(0).getHandValue() <= 21) 
        {
            System.out.println("P2: Your hand is " + hands.get(0).printHand() + " for a value of " + hands.get(0).getHandValue());
            System.out.println("P2: Would you like to Hit or Stay? (h for Hit, s for Stay)");
            String play2 = scanner.next();

            if (play2.equalsIgnoreCase("h")) 
            {
                hands.get(0).addCard(deck.get(0));
                deck.remove(0);
            } 
            else if (play2.equalsIgnoreCase("s")) 
            {
                System.out.println("P2 stays with a hand value of " + hands.get(0).getHandValue());
                break;
            } 
            else 
            {
                System.out.println("Invalid input. Please enter 'h' or 's'.");
            }

            if (hands.get(0).getHandValue() > 21) 
            {
                System.out.println("P2: You have busted with " + hands.get(0).getHandValue() + "!");
                return;
            } 
            else if (hands.get(0).getHandValue() == 21) 
            {
                System.out.println("P2: You have 21!");
                winsP1++;
                break;
            }
        }

        // Final result
        System.out.println("Final Results:");
        System.out.println("P1: " + hands.get(1).printHand() + " with value " + hands.get(1).getHandValue());
        System.out.println("P2: " + hands.get(0).printHand() + " with value " + hands.get(0).getHandValue());

        if (hands.get(1).getHandValue() > hands.get(0).getHandValue() && hands.get(1).getHandValue() <= 21) 
        {
            System.out.println("P1 wins!");
        } 
        else if (hands.get(0).getHandValue() > hands.get(1).getHandValue() && hands.get(0).getHandValue() <= 21) 
        {
            System.out.println("P2 wins!");
        } 
        else if (hands.get(1).getHandValue() == hands.get(0).getHandValue()) 
        {
            System.out.println("It's a tie!");
        } 
        else 
        {
            System.out.println("Both players busted!");
        
    	}
        games++;

    }
    
    public static int tournamentMatch() {
    	Scanner scanner = new Scanner(System.in);
        hands.clear();
 
        // Create player hands
        for (int p = 0; p <= PLAYER_COUNT; p++) {
            Hand hand = new Hand();
            hands.add(hand);
        }
 
        // Deal initial 2 cards to each player
        for (int i = 0; i < 2; i++) {
            for (Hand hand : hands) {
                hand.addCard(deck.get(0));
                deck.remove(0);
            }
        }
 
        // Player 1 loop
        while (hands.get(1).getHandValue() <= 21)
        {
            System.out.println("P1: Your hand is " + hands.get(1).printHand() + " for a value of " + hands.get(1).getHandValue());
            System.out.println("P1: Would you like to Hit or Stay? (h for Hit, s for Stay)");
            String play = scanner.next();
 
            if (play.equalsIgnoreCase("h"))
            {
                hands.get(1).addCard(deck.get(0));
                deck.remove(0);
            }
            else if (play.equalsIgnoreCase("s"))
            {
                System.out.println("P1 stays with a hand value of " + hands.get(1).getHandValue());
                break;
            }
            else
            {
                System.out.println("Invalid input. Please enter 'h' or 's'.");
            }
 
            if (hands.get(1).getHandValue() > 21)
            {
                System.out.println("P1: You have busted with " + hands.get(1).getHandValue() + "!");
                return 1;
            }
            else if (hands.get(1).getHandValue() == 21)
            {
                System.out.println("P1: You have 21!");
                winsP1++;
                break;
            }
        }
 
        // Player 2 interaction loop
        while (hands.get(0).getHandValue() <= 21)
        {
            System.out.println("P2: Your hand is " + hands.get(0).printHand() + " for a value of " + hands.get(0).getHandValue());
            System.out.println("P2: Would you like to Hit or Stay? (h for Hit, s for Stay)");
            String play2 = scanner.next();
 
            if (play2.equalsIgnoreCase("h"))
            {
                hands.get(0).addCard(deck.get(0));
                deck.remove(0);
            }
            else if (play2.equalsIgnoreCase("s"))
            {
                System.out.println("P2 stays with a hand value of " + hands.get(0).getHandValue());
                break;
            }
            else
            {
                System.out.println("Invalid input. Please enter 'h' or 's'.");
            }
 
            if (hands.get(0).getHandValue() > 21)
            {
                System.out.println("P2: You have busted with " + hands.get(0).getHandValue() + "!");
                return 0;
            }
            else if (hands.get(0).getHandValue() == 21)
            {
                System.out.println("P2: You have 21!");
                winsP1++;
                break;
            }
        }
 
        // Final result
        System.out.println("Final Results:");
        System.out.println("P1: " + hands.get(1).printHand() + " with value " + hands.get(1).getHandValue());
        System.out.println("P2: " + hands.get(0).printHand() + " with value " + hands.get(0).getHandValue());
 
        if (hands.get(1).getHandValue() > hands.get(0).getHandValue() && hands.get(1).getHandValue() <= 21)
        {
            System.out.println("P1 wins!");
            return 0;
        }
        else if (hands.get(0).getHandValue() > hands.get(1).getHandValue() && hands.get(0).getHandValue() <= 21)
        {
            System.out.println("P2 wins!");
            return 1;
        }
        else if (hands.get(1).getHandValue() == hands.get(0).getHandValue())
        {
            System.out.println("It's a tie!");
            
        }
        else
        {
            System.out.println("Both players busted!");      
        
    	}
        
        return 2;
 
    }
    
	public static void Bracket(int player_count, int match_count) {
		//Sets up Players and their names
		int counter = 0;
		List<String> Names = new ArrayList<>();
		Scanner kbd = new Scanner (System.in);
		while(counter < player_count) 
		{
			System.out.print("Enter a player's name:");
			Names.add(kbd.next());
			counter++;
			
		}
		//Randomize the order
		Collections.shuffle(Names);
		counter = 0;
		
		while(counter < match_count) 
		{
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
		

		System.out.println("The winner is:" + (Names));
		//kbd.close();
		return;
		
	}

 
}
