import java.util.Random;

public class Game {
    private static final String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static final int[] values = {1, 2, 3 , 4, 5, 6, 7, 8, 9, 10, 11};

    private Player player;
    private Card[] deck;
    private int dealerHandValue;
    private Card[] dealerHand;
    private int dealerHandSize;
    private Random rand;

    public Game(String name){
        player = new Player(name);
        startDeck();
        rand = new Random();
    }

    private void startDeck(){
        deck = new Card[cards.length];
        deck[0] = new Card(cards[0], values[10], values[0]);
        for (int i = 1; i < deck.length; i++){
            if (i <= 9)
               deck[i] = new Card(cards[i], values[i]);
            else
                deck[i] = new Card(cards[i], values[9]);
        }
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getPlayerScore() {
        return player.getScore();
    }

    public boolean isPlayerBlackjack(){
        return player.getHandValue() == 21 && player.getHandSize() == 2;
    }

    public boolean isDealerBlackjack(){
        return dealerHandValue == 21 && dealerHandSize == 2;
    }

    public boolean canPlayerHit(){
        return player.getHandValue() != 21 && player.getHandValue() != 0;
    }

    public boolean didPlayerBust() {
        return player.getHandValue() == 0;
    }

    public boolean canDealerHit(){
        return dealerHandValue < 17 && dealerHandValue != 0;
    }

    public boolean didDealerBust(){
        return dealerHandValue == 0;
    }

    public boolean isDraw(){
        return player.getHandValue() == dealerHandValue;
    }

    public boolean hasWon(){
        if (player.getHandValue() > dealerHandValue){
            handWon();
            return true;
        }
        else
            return false;
    }

    public void newHand(){
        dealerHandValue = 0;
        dealerHand = new Card[20];
        dealerHandSize = 0;
        player.newHand();
        shuffleCards();
    }

    private void shuffleCards(){
        for (int i = 0; i < deck.length; i++){
            deck[i].shuffle();
        }
    }

    public void getPlayerCard(){
        player.addCard(getCard());
        setPlayerHandVal();
    }

    public void getDealerCard(){
        dealerHand[dealerHandSize++] = getCard();
        setDealerValue();
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
        return new HandIterator(player.getHand(), player.getHandSize());
    }

    public HandIterator getDealerHand(){
        return new HandIterator(dealerHand, dealerHandSize);
    }

    public void handWon(){
        player.handWon();
    }

    public void setDealerValue(){
        dealerHandValue = getHandValue(dealerHand, dealerHandSize);
    }

    public void setPlayerHandVal(){
        player.setHandVal(getHandValue(player.getHand(), player.getHandSize()));
    }

    public int getHandValue(Card[] hand, int handSize){
        int val = 0;
        for (int i = 0; i < handSize; i++){
            val += hand[i].getValue();
        }
        int i = 0;
        while (val > 21 && i < handSize){
            if (hand[i++].getValue() == 11)
                val -= 10;
        }
        if (val > 21)
            return 0;
        else return val;
    }
}
