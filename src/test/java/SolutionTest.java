import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

public class SolutionTest {

    @Test
    public void solutionTest() throws Exception {
        File input = new File("src/test/resources/input.txt");
        File expectedOutput = new File("src/test/resources/output.txt");
        Scanner scanExpected = new Scanner(expectedOutput);
        Main.solution(input);
        File actualOutput = new File("src/main/resources/output.txt");
        Scanner scanOutput = new Scanner(actualOutput);
        while (scanExpected.hasNextLine()) {
            Assert.assertTrue(scanExpected.nextLine().equals(scanOutput.nextLine()));
        }
    }
}
