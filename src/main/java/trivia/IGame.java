package trivia;

public interface IGame {

	void add(String playerName);

	void roll(int roll);

	/**
	 * Processes a correct answer.
	 *
	 * @return boolean true if the current player has not(!) won as a result of the answer. */
	boolean processCorrectAnswer();

	/**
	 * Processes an incorrect answer.
	 *
	 * @return boolean true if the current player has not(!) won as a result of the answer. */
	boolean processWrongAnswer();

}