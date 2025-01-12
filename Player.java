public class Player {

    private String playerName;
    private int playerScore;
    private Card[] playerHand;
    private int playerHandSize;
    private int playerHandValue;

    public Player(String name){
        playerName = name;
        playerScore = 0;
    }

    public String getName() {
        return playerName;
    }

    public int getScore() {
        return playerScore;
    }

    public Card[] getHand() {
        return playerHand;
    }

    public int getHandSize() {
        return playerHandSize;
    }

    public int getHandValue() {
        return playerHandValue;
    }

    public void setHandVal(int val){
        playerHandValue = val;
    }

    public void handWon(){
        playerScore++;
    }

    public void addCard(Card card){
        playerHand[playerHandSize++] = card;
    }

    public void newHand(){
        playerHand = new Card[20];
        playerHandValue = 0;
        playerHandSize = 0;
    }
}
