package trivia;

public enum QuestionCategory {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String text;

    QuestionCategory(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
