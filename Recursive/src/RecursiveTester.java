
/*  Student information for assignment:

 *
 *  On MY honor, Ayush Patel, this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: ap55837
 *  email address: patayush01@utexas.edu
 *  Grader name: Tony
 *  Section number: 50865
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Tester class for the methods in Recursive.java
 * 
 * @author scottm
 *
 */
public class RecursiveTester {

	// run the tests
	public static void main(String[] args) {
		studentTests();
	}

	private static void runMazeTest(String rawMaze, int rows, int expected,
			int num) {
		char[][] maze = makeMaze(rawMaze, rows);
		System.out.println("Can escape maze test number " + num);
		printMaze(maze);
		int actual = Recursive.canEscapeMaze(maze);
		System.out.println("Expected result: " + expected);
		System.out.println("Actual result:   " + actual);
		if (expected == actual) {
			System.out.println("passed test " + num);
		} else {
			System.out.println("FAILED TEST " + num);
		}
		System.out.println();
	}

	// print the given maze
	// pre: none
	private static void printMaze(char[][] maze) {
		if (maze == null) {
			System.out.println("NO MAZE GIVEN");
		} else {
			for (char[] row : maze) {
				for (char c : row) {
					System.out.print(c);
					System.out.print(" ");
				}
				System.out.println();
			}
		}
	}

	// generate a char[][] given the raw string
	// pre: rawMaze != null, rawMaze.length() % rows == 0
	private static char[][] makeMaze(String rawMaze, int rows) {
		if (rawMaze == null || rawMaze.length() % rows != 0) {
			throw new IllegalArgumentException(
					"Violation of precondition in makeMaze."
							+ "Either raw data is null or left over values: ");
		}
		int cols = rawMaze.length() / rows;
		char[][] result = new char[rows][cols];
		int rawIndex = 0;
		for (int r = 0; r < result.length; r++) {
			for (int c = 0; c < result[0].length; c++) {
				result[r][c] = rawMaze.charAt(rawIndex);
				rawIndex++;
			}
		}
		return result;
	}

	

	private static void doOneFlowTest(int[][] world, int r, int c,
			boolean expected, int testNum) {
		System.out.println("Can Flow Off Map Test Number " + testNum);
		boolean actual = Recursive.canFlowOffMap(world, r, c);
		System.out.println("Start location = " + r + ", " + c);
		System.out.println(
				"Expected result = " + expected + " actual result = " + actual);
		if (expected == actual) {
			System.out.println("passed test " + testNum + " can flow off map.");
		} else {
			System.out.println("FAILED TEST " + testNum + " can flow off map.");
		}
		System.out.println();
	}

	// pre: r != null
	// post: run student test
	private static void studentTests() {
		int testNum = 1;

		// getBinary tests
		String actualBinary = Recursive.getBinary(8);
		String expectedBinary = "1000";
		if (actualBinary.equals(expectedBinary))
			System.out
					.println("Test " + testNum++ + " " + "passed. get binary.");
		else
			System.out.println(
					"Test " + testNum++ + " " + "failed. get binary. expected: "
							+ expectedBinary + ", actual: " + actualBinary);

		actualBinary = Recursive.getBinary(17);
		expectedBinary = "10001";
		if (actualBinary.equals(expectedBinary))
			System.out
					.println("Test " + testNum++ + " " + "passed. get binary.");
		else
			System.out.println(
					"Test " + testNum++ + " " + "failed. get binary. expected: "
							+ expectedBinary + ", actual: " + actualBinary);

		// revString tests
		String actualRev = Recursive.revString("");
		String expectedRev = "";
		if (actualRev.equals(expectedRev))
			System.out.println(
					"Test " + testNum++ + " " + "passed. reverse string.");
		else
			System.out.println("Test " + testNum++ + " "
					+ "failed. reverse string. expected: " + expectedRev
					+ ", actual: " + actualRev);

		actualRev = Recursive.revString("HeLlO WoRlD");
		expectedRev = "DlRoW OlLeH";
		if (actualRev.equals(expectedRev))
			System.out.println(
					"Test " + testNum++ + " " + "passed. reverse string.");
		else
			System.out.println("Test " + testNum++ + " "
					+ "failed. reverse string. expected: " + expectedRev
					+ ", actual: " + actualRev);

		// nextIsDouble tests
		int[] numsForDouble = {0};
		int actualDouble = Recursive.nextIsDouble(numsForDouble);
		int expectedDouble = 0;
		if (actualDouble == expectedDouble)
			System.out.println(
					"Test " + testNum++ + " " + "passed. next is double.");
		else
			System.out.println("Test " + testNum++ + " "
					+ "failed. next is double. expected: " + expectedDouble
					+ ", actual: " + actualDouble);

		numsForDouble = new int[] { 9, 18, 27, 2, 4, 8, 16 };
		actualDouble = Recursive.nextIsDouble(numsForDouble);
		expectedDouble = 4;
		if (actualDouble == expectedDouble)
			System.out.println(
					"Test " + testNum++ + " " + "passed. next is double.");
		else
			System.out.println("Test " + testNum++ + " "
					+ "failed. next is double. expected: " + expectedDouble
					+ ", actual: " + actualDouble);

		// mnemonics tests
		ArrayList<String> mnemonics = Recursive.listMnemonics("1");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("1");
		if (mnemonics.equals(expected))
			System.out.println(
					"Test " + testNum++ + " " + "passed. Phone mnemonics.");
		else
			System.out.println(
					"Test " + testNum++ + " " + "failed. Phone mnemonics.");

		mnemonics = Recursive.listMnemonics("33");
		Collections.sort(mnemonics);
		expected.clear();
		expected.add("DD");
		expected.add("DE");
		expected.add("DF");
		expected.add("ED");
		expected.add("EE");
		expected.add("EF");
		expected.add("FD");
		expected.add("FE");
		expected.add("FF");
		Collections.sort(expected);
		if (mnemonics.equals(expected))
			System.out.println(
					"Test " + testNum++ + " " + "passed. Phone mnemonics.");
		else
			System.out.println(
					"Test " + testNum++ + " " + "failed. Phone mnemonics.");

		mnemonics = Recursive.listMnemonics("4");
		expected.clear();
		expected.add("G");
		expected.add("H");
		expected.add("I");
		if (mnemonics.equals(expected))
			System.out.println(
					"Test " + testNum++ + " " + "passed. Phone mnemonics.");
		else
			System.out.println(
					"Test " + testNum++ + " " + "failed. Phone mnemonics.");
		System.out.println();

		// flow tests
		int[][] world = { { 5, 100, 90 }, { 4, 90, 90 }, { 2, 90, 90 } };
		doOneFlowTest(world, 0, 0, true, testNum++);
		int[][] worldTwo = { { 5, 100, 90 }, { 4, 4, 90 }, { 10, 90, 90 } };
		doOneFlowTest(worldTwo, 1, 1, false, testNum++);

		// minDifference tests
		int[] abilities = { 15, 40, 10, 30, 20, -10, 15 };
		if (Recursive.minDifference(3, abilities) == 0)
			System.out.println(
					"Test " + testNum++ + " " + "passed. min difference.");
		else
			System.out.println(
					"Test " + testNum++ + " " + "failed. min difference.");

		if (Recursive.minDifference(5, abilities) == 15)
			System.out.println(
					"Test " + testNum++ + " " + "passed. min difference.");
		else
			System.out.println(
					"Test " + testNum++ + " " + "failed. min difference.");
		// maze tests
		runMazeTest("*$SGE**$**", 2, 2, testNum++);
		runMazeTest("$YYSG$$$$*", 2, 0, testNum++);

	}

}