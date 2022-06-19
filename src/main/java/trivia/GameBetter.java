package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {
   private ArrayList<Player> players = new ArrayList();

   private LinkedList popQuestions = new LinkedList();
   private LinkedList scienceQuestions = new LinkedList();
   private LinkedList sportsQuestions = new LinkedList();
   private LinkedList rockQuestions = new LinkedList();

   private int currentPlayerIndex = 0;
   private boolean isGettingOutOfPenaltyBox;

   public GameBetter() {
      for (int i = 0; i < 50; i++) {
         popQuestions.addLast("Pop Question " + i);
         scienceQuestions.addLast(("Science Question " + i));
         sportsQuestions.addLast(("Sports Question " + i));
         rockQuestions.addLast(("Rock Question " + i));
      }
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));
      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public void roll(int roll) {
      System.out.println(currentPlayer().getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer().isInPrison()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(currentPlayer().getName() + " is getting out of the penalty box");

            performMove(roll);
         } else {
            System.out.println(currentPlayer().getName() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }
      } else {
         performMove(roll);
      }
   }

   public boolean wasCorrectlyAnswered() {
      if (currentPlayer().isInPrison()) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            currentPlayer().addCoin();
            System.out.println(currentPlayer().getName()
                    + " now has "
                    + currentPlayer().getCoins()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            nextTurn();

            return winner;
         } else {
            nextTurn();
            return true;
         }


      } else {

         System.out.println("Answer was corrent!!!!");
         currentPlayer().addCoin();
         System.out.println(currentPlayer().getName()
                 + " now has "
                 + currentPlayer().getCoins()
                 + " Gold Coins.");

         boolean winner = didPlayerWin();

         nextTurn();

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer().getName() + " was sent to the penalty box");
      currentPlayer().sendToPrison();

      nextTurn();

      return true;
   }

   private void performMove(int roll) {
      updatePlace(roll);
      requestQuestion();
   }

   private void requestQuestion() {
      System.out.println("The category is " + currentCategory());

      if (currentCategory() == "Pop")
         System.out.println(popQuestions.removeFirst());
      if (currentCategory() == "Science")
         System.out.println(scienceQuestions.removeFirst());
      if (currentCategory() == "Sports")
         System.out.println(sportsQuestions.removeFirst());
      if (currentCategory() == "Rock")
         System.out.println(rockQuestions.removeFirst());
   }


   private String currentCategory() {
      switch (currentPlayer().getPlace() % 4) {
         case 0: return "Pop";
         case 1: return "Science";
         case 2: return "Sports";
         default: return "Rock";
      }
   }

   private boolean didPlayerWin() {
      return !(currentPlayer().getCoins() == 6);
   }

   private Player currentPlayer() {
      return players.get(currentPlayerIndex);
   }

   private void nextTurn() {
      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
   }

   private void updatePlace(int roll) {
      int newPlace = (currentPlayer().getPlace() + roll) % 12;
      currentPlayer().setPlace(newPlace);

      System.out.println(currentPlayer().getName()
              + "'s new location is "
              + currentPlayer().getPlace());
   }
}
