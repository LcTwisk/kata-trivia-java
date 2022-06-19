package trivia;

import java.util.Arrays;
import java.util.HashMap;

public final class CardDeck {
    private HashMap<QuestionCategory, Integer> drawnPerCategory = new HashMap();

    public CardDeck(QuestionCategory[] categories) {
        for (QuestionCategory category: categories) {
            drawnPerCategory.put(category, 0);
        }
    }

    public int drawCard(QuestionCategory category) {
        int questionNumber = drawnPerCategory.get(category);
        drawnPerCategory.put(category, questionNumber + 1);
        return questionNumber;
    }
}
