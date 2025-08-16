package brazilian_blackjack;
import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> cards;
	//private Card;
		
	public Hand() 
	{
		cards = new ArrayList<>();
	}
	
	//method to add newCard to player hand
	public void addCard(Card newCard) 
	{
		cards.add(newCard);
	}
	
	//Method to remove cards
	public void removeCards() 
	{
		cards.clear();
	}
	
	
	//Method calculates players hand value
	public int getHandValue() 
	{
		int handValue = 0;
		boolean hasAce = false;
		
		for (Card currentCard : cards) 
		{
			handValue += currentCard.getCardValue();
			if (currentCard.isAce()) 
			{
				hasAce = true;
			}
		}
		
		//Method will update value if player has ace and their value is above 21
		if (handValue > 21 && hasAce) 
		{
			for(Card currentCd: cards) 
			{
				if(handValue > 21 && currentCd.isAce()) 
				{
					handValue -=10;
				}
				
			}
		}
		return handValue;
	}
	
	
	//Method calculates players hand value
	public int getHandValueOne() 
	{
		int handValue = 0;
		boolean hasAce = false;
		
		for (Card currentCard : cards) 
		{
			handValue += currentCard.getCardValue();
			if (currentCard.isAce()) 
			{
				hasAce = true;
			}
		}
		
		//Method will update value if player has ace and their value is above 21
		if (handValue > 21 && hasAce) 
		{
			for(Card currentCd: cards) 
			{
				if(handValue > 21 && currentCd.isAce()) 
				{
					handValue -=10;
				}
				
			}
		}
		return handValue;
	}
	
	
	//Method for displaying what is in players hands
	public String printHand() 
	{
	
		StringBuilder hand = new StringBuilder();
        for (Card deck : cards) 
        {
            hand.append(deck.getFace()).append(deck.getSuit()).append(" ");
        }
		
		return hand.toString().trim();
	}
	
	public String printFirstCard() 
	{
	
		StringBuilder hand = new StringBuilder();
        for (Card deck : cards) 
        {
            hand.append(deck.getFace()).append(deck.getSuit()).append(" ");
        }
		
		return hand.toString().trim();
	}
	
	
}
