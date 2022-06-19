package trivia;

import java.util.Arrays;
import java.util.HashMap;

public class CardDeck {
    private HashMap<QuestionCategory, Integer> drawnPerCategory = new HashMap();
    private int cardsPerCategory;

    public CardDeck(QuestionCategory[] categories, int cardsPerCategory) {
        this.cardsPerCategory = cardsPerCategory;

        for (QuestionCategory category: categories) {
            drawnPerCategory.put(category, 0);
        }
    }

    public int drawCard(QuestionCategory category) {
        //TODO handle max number of cards
        int questionNumber = drawnPerCategory.get(category);
        drawnPerCategory.put(category, questionNumber + 1);
        return questionNumber;
    }
}
