public class HandIterator {
    private Card[] hand;
    private int nextIdx;
    private int size;

    public HandIterator(Card[] hand, int size){
        this.hand = hand;
        nextIdx = 0;
        this.size = size;
    }

    public boolean hasNext(){
        return nextIdx < size;
    }

    public Card next(){
        return hand[nextIdx++];
    }
}
