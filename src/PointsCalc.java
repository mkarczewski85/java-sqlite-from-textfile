import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maciej on 2017-08-25.
 */
public class PointsCalc {

    /**
     * Metoda zwracająca końcową wartość punktów dla całego słowa
     *
     * @param word
     * @return
     */
    public static int calcFinalScore(String word) {
        int finalScore = 0;
        for (char letter : word.toCharArray()) {
            finalScore += getLetterScore(letter);
        }
        return finalScore;
    }

    /**
     * Metoda zwracająca wartość punktów dla pojedynczej litery
     *
     * @param letter
     * @return
     */
    public static int getLetterScore(char letter) {
        int score = 0;
        for (LetterSet letterSet : letterSets) {
            if (letterSet.containsLetter(letter)) {
                score = letterSet.getScore();
            }
        }
        return score;
    }

    private static List<LetterSet> letterSets = Arrays.asList(
            new LetterSet(1, "aeinorswz"),
            new LetterSet(2, "cdklmpty"),
            new LetterSet(3, "bghjłu"),
            new LetterSet(5, "ąęfóśż"),
            new LetterSet(6, "ć"),
            new LetterSet(7, "ń"),
            new LetterSet(9, "ź")
    );

    public static final class LetterSet {

        private int score;
        private List<Character> letters;

        public LetterSet(int score, String letters) {
            this.score = score;
            this.letters = new ArrayList<>(letters.length());
            for (char letter : letters.toCharArray()) {
                this.letters.add(letter);
            }
        }

        /**
         * Zwraca wynik
         *
         * @return
         */
        public int getScore() {
            return score;
        }

        /**
         * Sprawdza czy litera znajduje się na liście
         *
         * @param letter
         * @return
         */
        public boolean containsLetter(Character letter) {
            return letters.contains(letter);
        }
    }

}
