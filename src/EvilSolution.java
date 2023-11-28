import java.util.*;

public class EvilSolution {

    private ArrayList<Character> currentPattern;
    private int wordLength;

    public EvilSolution(int wordLength) {
        this.wordLength = wordLength;
        this.currentPattern = new ArrayList<>(Collections.nCopies(wordLength, '_'));
    }

    public boolean isSolved() {
        return !currentPattern.contains('_');
    }

    public void printProgress() {
        currentPattern.forEach(c -> System.out.print(c + " "));
        System.out.println();
    }

    public boolean updateWithGuess(ArrayList<String> wordList, char guess) {
        HashMap<String, ArrayList<String>> wordFamilies = buildWordFamilies(wordList, guess);

        String newPattern = chooseLargestFamily(wordFamilies);
        boolean result = newPattern.equals(getStringRepresentation(currentPattern));
        updatePattern(newPattern);
        wordList.clear();
        wordList.addAll(wordFamilies.get(newPattern));
        return !result;
    }

    public HashMap<String, ArrayList<String>> buildWordFamilies(ArrayList<String> wordList, char guess) {
        HashMap<String, ArrayList<String>> families = new HashMap<>();

        for (String word : wordList) {
            String pattern = createPattern(word, guess);
            families.putIfAbsent(pattern, new ArrayList<>());
            families.get(pattern).add(word);
        }
        return families;
    }

    public String chooseLargestFamily(HashMap<String, ArrayList<String>> wordFamilies) {
        int maxFamilySize = 0;
        String solution = null;
        for (String pattern: wordFamilies.keySet()) {
            if (wordFamilies.get(pattern).size() > maxFamilySize) {
                maxFamilySize = wordFamilies.get(pattern).size();
                solution = pattern;
            }
        }
        return solution;
    }

    public void updatePattern(String newPattern) {
        for (int i = 0; i < newPattern.length(); i++) {
            char c = newPattern.charAt(i);
            if (c != '_') {
                currentPattern.set(i, c);
            }
        }
    }

    public String createPattern(String word, char guess) {
        StringBuilder patternBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (currentPattern.get(i) != '_') {
                patternBuilder.append(currentPattern.get(i));
            } else {
                patternBuilder.append((c == guess) ? c : '_');
            }
        }
        return patternBuilder.toString();
    }

    public ArrayList<Character> getCurrentPattern() {
        return currentPattern;
    }

    public String getStringRepresentation(ArrayList<Character> pattern) {
        StringBuilder sb = new StringBuilder();
        for (Character ch : pattern) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public void printVictory() {
        System.out.println("Congratulations! You've guessed the word!");
        printProgress();
    }

    public static void main(String[] args) {
        EvilSolution testObject = new EvilSolution(4); // Assume word length is 7 for the test
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList("echo", "heal", "belt", "peel", "hazy"));
        testObject.updateWithGuess(wordList, 'e');
        testObject.updateWithGuess(wordList, 'c');
        testObject.updateWithGuess(wordList, 'h');
        testObject.updateWithGuess(wordList, 'o');
        System.out.println(testObject.isSolved());

    }
}
