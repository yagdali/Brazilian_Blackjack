package brazilian_blackjack;

public class Card {
	private String suit;
	private String face;
	private int value;
	private boolean isAce;
	
	public Card(String suit, String face, int value, boolean isAce) 
	{
		this.suit = suit;
		this.face = face;
		this.value = value;
		this.isAce = isAce;
	}
	
	public int getCardValue() 
	{
		return this.value;
	}
	
	public String getSuit() 
	{
		return suit;
	}
	
	public String getFace() 
	{
		return face;
	}
	
	public boolean isAce() 
	{
		return isAce;
	}
	
}
