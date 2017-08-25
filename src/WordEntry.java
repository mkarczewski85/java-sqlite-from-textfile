/**
 * Created by Maciej on 2017-08-25.
 */
public class WordEntry {

    private String word;
    private int score;

    public WordEntry(String word, int score) {
        this.word = word;
        this.score = score;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
