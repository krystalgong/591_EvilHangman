import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class EvilSolutionTest {

    private EvilSolution testObject;
    private ArrayList<String> wordList;

    @BeforeEach
    void init() {
        testObject = new EvilSolution(4);
        wordList = new ArrayList<>(Arrays.asList("echo", "heal", "belt", "peel", "hazy"));
    }

    @Test
    void isSolvedInitiallyFalse() {
        assertFalse(testObject.isSolved(), "New game should not be solved");
    }

    @Test
    void updateWithGuessCorrectGuess() {
        assertTrue(testObject.updateWithGuess(wordList, 'e'), "Guess 'e' should be correct");
        assertEquals(Arrays.asList('_', 'e', '_', '_'), testObject.getCurrentPattern(), "Pattern should update with correct guess");
    }

    @Test
    void testUpdateWithGuess() {
        char guess = 'e';
        boolean patternChanged = testObject.updateWithGuess(wordList, guess);
        assertTrue(patternChanged, "Pattern should change with a correct guess");

        guess = 'z';
        patternChanged = testObject.updateWithGuess(wordList, guess);
        assertFalse(patternChanged, "Pattern should not change with an incorrect guess");
    }

    @Test
    void testBuildWordFamilies() {
        char guess = 'e';
        HashMap<String, ArrayList<String>> wordFamilies = testObject.buildWordFamilies(wordList, guess);

        assertNotNull(wordFamilies, "Word families should not be null");
        assertFalse(wordFamilies.isEmpty(), "Word families should not be empty");
        // Additional assertions can be made based on the expected word families
    }

    @Test
    void testChooseLargestFamily() {
        char guess = 'e';
        HashMap<String, ArrayList<String>> wordFamilies = testObject.buildWordFamilies(wordList, guess);
        String chosenPattern = testObject.chooseLargestFamily(wordFamilies);

        assertNotNull(chosenPattern, "Chosen pattern should not be null");
        // Additional assertions can be made based on the expected chosen pattern
    }

    @Test
    void testPrintProgress() {
        // This test is more about seeing the output than asserting conditions
        testObject.printProgress(); // Manually verify that the output is correct
    }

    @Test
    void testPrintVictory() {
        // This test is also more about seeing the output than asserting conditions
        // You might want to simulate a victory scenario and then call printVictory()
        testObject.printVictory(); // Manually verify that the output is correct
    }
}
