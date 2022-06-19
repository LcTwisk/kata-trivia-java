package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public final class GameBetter implements IGame {
   private static final int BOARD_TILES = 12;
   private static final int COIN_TARGET = 6;
   private ArrayList<Player> players = new ArrayList();
   private CardDeck cardDeck = new CardDeck(QuestionCategory.values());
   private int currentPlayerIndex = 0;

   public void add(String playerName) {
      players.add(new Player(playerName));
      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
   }

   public void roll(int roll) {
      System.out.println(currentPlayer().getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer().isInPrison()) {
         if (roll % 2 != 0) {
            currentPlayer().setIsEligibleToGetOutOfPrison(true);
            System.out.println(currentPlayer().getName() + " is getting out of the penalty box");
            performMove(roll);
         } else {
            System.out.println(currentPlayer().getName() + " is not getting out of the penalty box");
            currentPlayer().setIsEligibleToGetOutOfPrison(false);
         }
      } else {
         performMove(roll);
      }
   }

   public boolean processCorrectAnswer() {
      if (currentPlayer().isInPrison()) {
         if (currentPlayer().isEligibleToGetOutOfPrison()) {
            awardRightAnswer();
            boolean winner = didPlayerWin();
            nextTurn();
            return winner;
         } else {
            nextTurn();
            return true;
         }
      } else {
         awardRightAnswer();
         boolean winner = didPlayerWin();
         nextTurn();
         return winner;
      }
   }

   public boolean processWrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer().getName() + " was sent to the penalty box");

      currentPlayer().sendToPrison();
      nextTurn();

      return true;
   }

   private void awardRightAnswer() {
      System.out.println("Answer was correct!!!!");
      currentPlayer().addCoin();
      System.out.println(currentPlayer().getName()
              + " now has "
              + currentPlayer().getCoins()
              + " Gold Coins.");
   }

   private void performMove(int roll) {
      updatePlace(roll);
      requestQuestion();
   }

   private void requestQuestion() {
      QuestionCategory category = currentCategory();
      String categoryName = category.toString();
      System.out.println("The category is " + categoryName);
      System.out.println(categoryName + " Question " + cardDeck.drawCard(category));
   }


   private QuestionCategory currentCategory() {
      switch (currentPlayer().getPlace() % 4) {
         case 0: return QuestionCategory.POP;
         case 1: return QuestionCategory.SCIENCE;
         case 2: return QuestionCategory.SPORTS;
         default: return QuestionCategory.ROCK;
      }
   }

   private boolean didPlayerWin() {
      return !(currentPlayer().getCoins() == COIN_TARGET);
   }

   private Player currentPlayer() {
      return players.get(currentPlayerIndex);
   }

   private void nextTurn() {
      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
   }

   private void updatePlace(int roll) {
      int newPlace = (currentPlayer().getPlace() + roll) % BOARD_TILES;
      currentPlayer().setPlace(newPlace);

      System.out.println(currentPlayer().getName()
              + "'s new location is "
              + currentPlayer().getPlace());
   }
}
