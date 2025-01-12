import java.util.Random;

public class Game {
    private static final String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static final int[] values = {1, 2, 3 , 4, 5, 6, 7, 8, 9, 10};

    private String playerName;
    private Card[] deck;
    private int playerScore;
    private int dealerScore;
    private int playerHandValue;
    private int dealerHandValue;
    private Card[] playerHand;
    private Card[] dealerHand;
    private int playerHandSize;
    private int dealerHandSize;
    private Random rand;

    public Game(String name){
        playerName = name;
        playerScore = 0;
        dealerScore = 0;
        startDeck();
        rand = new Random();
    }

    private void startDeck(){
        deck = new Card[cards.length];
        deck[0] = new Card(cards[0], values[0], values[9]);
        for (int i = 1; i < deck.length; i++){
            if (i <= 9)
               deck[i] = new Card(cards[i], values[i]);
            else
                deck[i] = new Card(cards[i], values[9]);
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public boolean canPlayerHit(){
        return playerHandValue < 21;
    }

    public boolean didPlayerBust() {
        return playerHandValue > 21;
    }

    public boolean canDealerHit(){
        return dealerHandValue < 17;
    }

    public boolean didDealerBust(){
        return dealerHandValue > 21;
    }

    public boolean isDraw(){
        return playerHandValue == dealerHandValue;
    }

    public boolean hasWon(){
        if (playerHandValue > dealerHandValue){
            playerScore++;
            return true;
        }
        else {
            dealerScore++;
            return false;
        }
    }

    public void newHand(){
        playerHandValue = 0;
        dealerHandValue = 0;
        playerHand = new Card[20];
        dealerHand = new Card[20];
        playerHandSize = 0;
        dealerHandSize = 0;
        shuffleCards();
    }

    private void shuffleCards(){
        for (int i = 0; i < deck.length; i++){
            deck[i].shuffle();
        }
    }

    public void getPlayerCard(){
        Card card = getCard();
        playerHand[playerHandSize++] = card;
        playerHandValue += card.getValue();
    }

    public void getDealerCard(){
        Card card = getCard();
        dealerHand[dealerHandSize++] = card;
        dealerHandValue += card.getValue();
    }

    private Card getCard(){
        int idx;
        do {
            idx = rand.nextInt(13);
        } while (deck[idx].getCardsLeft() == 0);
        deck[idx].cardDealt();
        return deck[idx];
    }

    public HandIterator getPlayerHand(){
        return new HandIterator(playerHand, playerHandSize);
    }

    public HandIterator getDealerHand(){
        return new HandIterator(dealerHand, dealerHandSize);
    }

    public void handLost(){
        dealerScore++;
    }

    public void handWon(){
        playerScore++;
    }
}
