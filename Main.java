import java.util.Scanner;

public class Main {

    private static void printCommands(){
        System.out.println("Commands:");
        System.out.println("D - deal cards");
        System.out.println("H - hit");
        System.out.println("S - stand");
        System.out.println("quit - quit game");
        System.out.print("Please enter your name: ");
    }

    private static void printScore(Game game){
        System.out.printf("%s %d - %d Dealer\n", game.getPlayerName(), game.getPlayerScore(), game.getDealerScore());
    }

    private static void printHands(Game game){
        String dealerHand = "Dealer's hand: ";
        String playerHand = "Your hand: ";
        HandIterator dealer = game.getDealerHand();
        while (dealer.hasNext())
            dealerHand += dealer.next().getCard() + " ";
        HandIterator player = game.getPlayerHand();
        while (player.hasNext())
            playerHand += player.next().getCard() + " ";
        System.out.println(dealerHand);
        System.out.println(playerHand);
    }

    private static void dealCards(Game game){
        game.newHand();
        game.getDealerCard();
        game.getPlayerCard();
        game.getPlayerCard();
        printHands(game);
    }

    private static void handleHit(Game game){
        game.getPlayerCard();
        printHands(game);
    }

    private static void doNothing(){

    }

    private static void startHand(Game game, Scanner in){
        dealCards(game);
        String command;
        do {
            command = in.nextLine().trim();
            switch (command){
                case "D" -> System.out.println("You are already in a hand");
                case "quit" -> System.out.println("You can't quit in the middle of a hand");
                case "H" -> handleHit(game);
                case "S" -> doNothing();
                default -> System.out.println("Invalid Command");
            }
        } while (!command.equals("S") && game.canPlayerHit());
        if (game.didPlayerBust()){
            System.out.println("Your hand value exceeds 21");
            System.out.println("Dealer wins");
            game.handLost();
            printScore(game);
        }
        else {
            while(game.canDealerHit())
                game.getDealerCard();
            printHands(game);
            if (game.didDealerBust()){
                System.out.println("Dealer busted");
                System.out.printf("%s wins\n", game.getPlayerName());
                game.handWon();
            } else if (game.isDraw())
                System.out.println("Draw");
            else if (game.hasWon())
                System.out.printf("%s wins\n", game.getPlayerName());
            else
                System.out.println("Dealer wins");
            printScore(game);
        }
    }

    private static void handleQuit(Game game){
        System.out.println("Final Score:");
        printScore(game);
        System.out.println("Thank you for playing");
    }

    private static void readCommands(Game game, Scanner in){
        String command;
        System.out.println("Press D to start the game");
        do {
            command = in.nextLine().trim();
            switch (command){
                case "D" -> startHand(game, in);
                case "quit" -> handleQuit(game);
                case "H", "S" -> System.out.println("Please start the hand first");
                default -> System.out.println("Invalid Command");
            }
        } while (!command.equals("quit"));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printCommands();
        Game game = new Game(in.nextLine().trim());
        readCommands(game, in);
    }
}