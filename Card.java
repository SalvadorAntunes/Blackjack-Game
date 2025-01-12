public class Card {

    private String card;
    private int value;
    private int aceValue;
    private int cardsLeft;

    public Card(String card, int value){
        this.card = card;
        this.value = value;
        aceValue = 0;
        cardsLeft = 4;
    }

    public Card(String card, int value, int ace){
        this.card = card;
        this.value = value;
        aceValue = ace;
        cardsLeft = 4;
    }

    public String getCard() {
        return card;
    }

    public int getValue() {
        return value;
    }

    public int getAceValue() {
        return aceValue;
    }

    public int getCardsLeft(){
        return cardsLeft;
    }

    public void shuffle(){
        cardsLeft = 4;
    }

    public void cardDealt(){
        cardsLeft--;
    }
}
