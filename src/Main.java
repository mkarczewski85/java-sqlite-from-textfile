
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<WordEntry> getListWordsFromFile(String filepath) {

        List<WordEntry> wordEntryList = new ArrayList<>();

        try (BufferedReader bReader = Files.newBufferedReader(Paths.get(filepath))) {

            String wordLine = null;
            while ((wordLine = bReader.readLine()) != null) {
                wordEntryList.add(new WordEntry(wordLine, PointsCalc.calcFinalScore(wordLine)));
            }

        } catch (Exception e) {
            System.out.println("Bład odczytu pliku");
            e.printStackTrace();
        }
        return wordEntryList;
    }

    public static void main(String[] args) {

        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("+-------------------------------------------------+");
            System.out.println("|    Sterownik JDBC został załadowany             |");
            System.out.println("+-------------------------------------------------+");

            Connection connection = DriverManager.getConnection("jdbc:sqlite:.\\words.sqlite");
            String query = "INSERT INTO Words(Word, Score) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            List<WordEntry> wordEntryList = getListWordsFromFile("slowa.txt");
            System.out.println("|    Treść pliku przygotowana do zapisu           |");
            System.out.println("+-------------------------------------------------+");

            for (int i = 0; i < wordEntryList.size(); i++) {
                preparedStatement.setString(1, wordEntryList.get(i).getWord());
                preparedStatement.setInt(2, wordEntryList.get(i).getScore());
                preparedStatement.executeUpdate();
                System.out.println("|    Linijka nr " + (i+1) + " zapisana w bazie    |");
                System.out.println("+-------------------------------------------------+");
            }

            System.out.println("|    Baza SQLite została wypełniona               |");
            System.out.println("+-------------------------------------------------+");

        } catch (Exception e) {
            System.out.println("|    Wystąpił błąd zapisu                         |");
            System.out.println("+-------------------------------------------------+");
            e.printStackTrace();
        } finally {
            System.out.println("|    Program został zakończony                    |");
            System.out.println("+-------------------------------------------------+");
        }

    }
}
