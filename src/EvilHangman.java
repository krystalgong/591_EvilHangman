import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class EvilHangman {

    private ArrayList<String> wordList;
    private HashSet<Character> previousGuesses;
    private TreeSet<Character> incorrectGuesses;
    private Scanner inputScanner;
    private EvilSolution solution;

    public EvilHangman() {
        this("engDictionary.txt");
    }

    public EvilHangman(String filename) {
        try {
            wordList = dictionaryToList(filename);
        } catch (IOException e) {
            System.out.printf("Couldn't read from the file %s.\n", filename);
            e.printStackTrace();
            System.exit(0);
        }
        inputScanner = new Scanner(System.in);
        initializeGame();
    }

    private void initializeGame() {
        previousGuesses = new HashSet<>();
        incorrectGuesses = new TreeSet<>();
        Random rand = new Random();
        int randomLength = wordList.get(rand.nextInt(wordList.size())).length();
        wordList.removeIf(word -> word.length() != randomLength);
        solution = new EvilSolution(randomLength);
    }

    private static ArrayList<String> dictionaryToList(String filename) throws IOException {
        FileInputStream fs = new FileInputStream(filename);
        Scanner scanner = new Scanner(fs);
        ArrayList<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
        scanner.close();
        fs.close();
        return words;
    }

    public void start() {
        System.out.println("Welcome to Hangman!");

        while (!solution.isSolved()) {
            System.out.println("\nPlease guess a letter: ");
            solution.printProgress();
            System.out.println("Incorrect guesses: " + incorrectGuesses);

            char guess = promptForGuess();
            if (!previousGuesses.contains(guess)) {
                previousGuesses.add(guess);
                if (!solution.updateWithGuess(wordList, guess)) {
                    incorrectGuesses.add(guess);
                }
            } else {
                System.out.println("You have already guessed that letter. Try again.");
            }
        }
        solution.printVictory();
        inputScanner.close();
    }

    private char promptForGuess() {
        String input = inputScanner.next().toLowerCase();
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.println("Invalid input. Please enter a single letter.");
            return promptForGuess();
        }
        return input.charAt(0);
    }
}
