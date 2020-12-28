/*  Student information for assignment:

 *
 *  On MY honor, Ayush Patel,
 *  this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: ap55837
 *  email address: patayush01@utexas.edu
 *  Grader name: Tony
 *  Section number: 50865
 *
 */

//imports

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Various recursive methods to be implemented. Given shell file for CS314
 * assignment.
 */
public class Recursive {
	// constant because used by multiple methods, and adjacencies same for both
	private final static int[][] ADJACENCY = { { 0, 0, 1, -1 }, { 1, -1, 0, 0 } };
	private final static int ALL_COINS=2;
	private final static int EXIT_FOUND= 1;
	private final static int NO_SOLUTION= 0;
	/**
	 * Problem 1: convert a base 10 int to binary recursively. <br>
	 * pre: n != Integer.MIN_VALUE<br>
	 * <br>
	 * post: Returns a String that represents N in binary. All chars in returned
	 * String are '1's or '0's. Most significant digit is at position 0
	 * 
	 * @param n the base 10 int to convert to base 2
	 * @return a String that is a binary representation of the parameter n
	 */
	public static String getBinary(int n) {
		if (n == Integer.MIN_VALUE) {
			throw new IllegalArgumentException(
					"Failed precondition: " + "getBinary. n cannot equal "
							+ "Integer.MIN_VALUE. n: " + n);
		}
		// base case: if abs(n) < 1, then at the end, so return n (if its neg,
		// neg sign will be added appropriately)
		if (Math.abs(n) <= 1)
			return n + "";
		// recursive step: call method and add the remainder of n/2
		// concatenated as string after to return string backwards at end
		return getBinary(n / 2) + Math.abs(n % 2) + "";

	}

	/**
	 * Problem 2: reverse a String recursively.<br>
	 * pre: stringToRev != null<br>
	 * post: returns a String that is the reverse of stringToRev
	 * 
	 * @param stringToRev the String to reverse.
	 * @return a String with the characters in stringToRev in reverse order.
	 */
	public static String revString(String stringToRev) {
		if (stringToRev == null) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "revString. parameter may not be null.");
		}
		// base case: if at end of string, return the character at end
		if (stringToRev.length() == 0)
			return stringToRev;
		// recursive step: call method without first letter in string, and add
		// that first letter AFTER recursive call to return string in reverse
		return revString(stringToRev.substring(1))
				+ stringToRev.substring(0, 1);
	}

	/**
	 * Problem 3: Returns the number of elements in data that are followed
	 * directly by value that is double that element. pre: data != null post:
	 * return the number of elements in data that are followed immediately by
	 * double the value
	 * 
	 * @param data The array to search.
	 * @return The number of elements in data that are followed immediately by a
	 *         value that is double the element.
	 */
	public static int nextIsDouble(int[] data) {
		if (data == null) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "nextIsDouble. parameter may not be null.");
		}
		// calling helper method with start pos (0)
		return helperDouble(data, 0);

	}

	private static int helperDouble(int[] data, int pos) {
		// base case: if at the last index, we know index after cannot be
		// double, so dont
		// add to counter (return 0)
		if (pos >= data.length - 1)
			return 0;
		// recursive step: if its double, then add to counter and continue
		// through array
		else if (data[pos] * 2 == data[pos + 1])
			return 1 + helperDouble(data, pos + 1);
		// other recursive step: then not double, so just continue through array
		return helperDouble(data, pos + 1);

	}

	/**
	 * Problem 4: Find all combinations of mnemonics for the given number.<br>
	 * pre: number != null, number.length() > 0, all characters in number are
	 * digits<br>
	 * post: see tips section of assignment handout
	 * 
	 * @param number The number to find mnemonics for
	 * @return An ArrayList<String> with all possible mnemonics for the given
	 *         number
	 */
	public static ArrayList<String> listMnemonics(String number) {
		if (number == null || number.length() == 0 || !allDigits(number)) {
			throw new IllegalArgumentException(
					"Failed precondition: " + "listMnemonics");
		}
		// making arrayList so can you pass by reference to return after
		// recursiveMnemonic call
		ArrayList<String> result = new ArrayList<>();
		// passing in empty string for mnemonicSoFar, as no mnemonics created at
		// beginning
		recursiveMnemonics(result, "", number);
		return result;
	}

	/*
	 * Helper method for listMnemonics mnemonics stores completed mnemonics
	 * mneominicSoFar is a partial (possibly complete) mnemonic digitsLeft are
	 * the digits that have not been used from the original number.
	 */
	private static void recursiveMnemonics(ArrayList<String> mnemonics,
			String mnemonicSoFar, String digitsLeft) {
		// base caseL if no digitsLeft, mnemonic has been fully made, so add it
		// to
		// arrayList mnemonics
		if (digitsLeft.isEmpty()) {
			mnemonics.add(mnemonicSoFar);
		} else {
			// get the letters associated with that first digit
			String letters = digitLetters(digitsLeft.charAt(0));
			for (int i = 0; i < letters.length(); i++) {
				// add the letter at i to mnemonicSoFar
				mnemonicSoFar += letters.substring(i, i + 1);
				// if mnemonic arrayList does not have this combo already
				if (!mnemonics.contains(mnemonicSoFar))
					// recursive step: make recursive call while moving to next
					// digit
					recursiveMnemonics(mnemonics, mnemonicSoFar,
							digitsLeft.substring(1));
				// if got here, then remove the letter added to try other combos
				mnemonicSoFar = mnemonicSoFar.substring(0,
						mnemonicSoFar.length() - 1);

			}
		}

	}

	// used by method digitLetters
	private static final String[] letters = { "0", "1", "ABC", "DEF", "GHI",
			"JKL", "MNO", "PQRS", "TUV", "WXYZ" };

	/*
	 * helper method for recursiveMnemonics pre: ch is a digit '0' through '9'
	 * post: return the characters associated with this digit on a phone keypad
	 */
	private static String digitLetters(char ch) {
		if (ch < '0' || ch > '9') {
			throw new IllegalArgumentException("parameter "
					+ "ch must be a digit, 0 to 9. Given value = " + ch);
		}
		int index = ch - '0';
		return letters[index];
	}

	/*
	 * helper method for listMnemonics pre: s != null post: return true if every
	 * character in s is a digit ('0' through '9')
	 */
	private static boolean allDigits(String s) {
		if (s == null) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "allDigits. String s cannot be null.");
		}
		boolean allDigits = true;
		int i = 0;
		while (i < s.length() && allDigits) {
			allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
			i++;
		}
		return allDigits;
	}

	/**
	 * Problem 5: Draw a Sierpinski Carpet.
	 * 
	 * @param size  the size in pixels of the window
	 * @param limit the smallest size of a square in the carpet.
	 */
	public static void drawCarpet(int size, int limit) {
		DrawingPanel p = new DrawingPanel(size, size);
		Graphics g = p.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, size, size);
		g.setColor(Color.WHITE);
		drawSquares(g, size, limit, 0, 0);
	}

	/*
	 * Helper method for drawCarpet Draw the individual squares of the carpet.
	 * 
	 * @param g The Graphics object to use to fill rectangles
	 * 
	 * @param size the size of the current square
	 * 
	 * @param limit the smallest allowable size of squares
	 * 
	 * @param x the x coordinate of the upper left corner of the current square
	 * 
	 * @param y the y coordinate of the upper left corner of the current square
	 */
	private static void drawSquares(Graphics g, int size, int limit, double x,
			double y) {
		// constant for the future grid size (3 by 3)
		final int NEW_SIDE = 3;
		// if the size of current square is greater than the limit
		if (size > limit) {
			// (traverse amount of squares in grid (3*3)
			for (int i = 0; i < NEW_SIDE * NEW_SIDE; i++) {
				// if not at middle square...
				if (i != NEW_SIDE * NEW_SIDE / 2)
					// recursive step: continuing making pattern with new size
					// and appropriate coordinates
					drawSquares(g, size / NEW_SIDE, limit,
							x + (i % NEW_SIDE * size / NEW_SIDE),
							y + (i / NEW_SIDE * size / NEW_SIDE));
				// base case: at middle square of grid so draw rectangle with
				// new sizes and appropriate coordinates
				else
					g.fillRect((int) x + size / NEW_SIDE,
							(int) y + size / NEW_SIDE, size / NEW_SIDE,
							size / NEW_SIDE);
			}
		}

	}

	/**
	 * Problem 6: Determine if water at a given point on a map can flow off the
	 * map. <br>
	 * pre: map != null, map.length > 0, map is a rectangular ADJACENCY, 0 <=
	 * row < map.length, 0 <= col < map[0].length <br>
	 * post: return true if a drop of water starting at the location specified
	 * by row, column can reach the edge of the map, false otherwise.
	 * 
	 * @param map The elevations of a section of a map.
	 * @param row The starting row of a drop of water.
	 * @param col The starting column of a drop of water.
	 * @return return true if a drop of water starting at the location specified
	 *         by row, column can reach the edge of the map, false otherwise.
	 */
	public static boolean canFlowOffMap(int[][] map, int row, int col) {
		if (map == null || map.length == 0 || !isRectangular(map)
				|| !inbounds(row, col, map)) {
			throw new IllegalArgumentException(
					"Failed precondition: " + "canFlowOffMap");
		}
		// using helper method to not check precon every time (efficiency)
		return canFlowOffMapHelper(map, row, col);

	}

	private static boolean canFlowOffMapHelper(int[][] map, int row, int col) {
		// base case: at an edge index, so we found a path, so return true
		if (row == 0 || row == map.length - 1 || col == 0
				|| col == map[0].length - 1)
			return true;
		// implicit else, for each adjacency available (up/down a row,
		// left/right a col)
		for (int c = 0; c < ADJACENCY[0].length; c++) {
			// create new indices to check using adjacency matrix
			int rowTest = row + ADJACENCY[0][c];
			int colTest = col + ADJACENCY[1][c];
			// don't have to check if in bounds because base case we are not at
			// edge
			// if value @ new indices is less than value @ old indices, can
			// use && to make recursive call to test next adjacencies as && will
			// allow us to return true if find a path
			if (map[rowTest][colTest] < map[row][col]
					&& canFlowOffMapHelper(map, rowTest, colTest))
				return true;
		}
		// if got here, then no path found, so return false
		return false;
	}

	/*
	 * helper method for canFlowOfMap - CS314 students you should not have to
	 * call this method, pre: mat != null,
	 */
	private static boolean inbounds(int r, int c, int[][] mat) {
		if (mat == null) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "inbounds. The 2d array mat may not be null.");
		}
		return r >= 0 && r < mat.length && mat[r] != null && c >= 0
				&& c < mat[r].length;
	}

	/*
	 * helper method for canFlowOfMap - CS314 students you should not have to
	 * call this method, pre: mat != null, mat.length > 0 post: return true if
	 * mat is rectangular
	 */
	private static boolean isRectangular(int[][] mat) {
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "inbounds. The 2d array mat may not be null "
					+ "and must have at least 1 row.");
		}
		boolean correct = true;
		final int numCols = mat[0].length;
		int row = 0;
		while (correct && row < mat.length) {
			correct = (mat[row] != null) && (mat[row].length == numCols);
			row++;
		}
		return correct;
	}

	/*
	 * Based of Mike's isRectangular(int[][] mat) method helper method for
	 * canEscapeMaze pre: mat != null, mat.length > 0 post: return true if mat
	 * is rectangular
	 */
	private static boolean isRectangular(char[][] mat) {
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException("Failed precondition: "
					+ "isRectangular. The 2d array mat may not be null "
					+ "and must have at least 1 row.");
		}
		boolean correct = true;
		final int numCols = mat[0].length;
		int row = 0;
		// if correct turns to false or finished traversing mat
		while (correct && row < mat.length) {
			// storing boolean for row isn't null && that row having same
			// numofCols as first row
			correct = (mat[row] != null) && (mat[row].length == numCols);
			row++;
		}
		return correct;
	}

	/**
	 * Problem 7: Find the minimum difference possible between teams based on
	 * ability scores. The number of teams may be greater than 2. The goal is to
	 * minimize the difference between the team with the maximum total ability
	 * and the team with the minimum total ability. <br>
	 * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams <br>
	 * post: return the minimum possible difference between the team with the
	 * maximum total ability and the team with the minimum total ability.
	 * 
	 * @param numTeams  the number of teams to form. Every team must have at
	 *                  least one member
	 * @param abilities the ability scores of the people to distribute
	 * @return return the minimum possible difference between the team with the
	 *         maximum total ability and the team with the minimum total
	 *         ability. The return value will be greater than or equal to 0.
	 */
	public static int minDifference(int numTeams, int[] abilities) {
		if (numTeams < 2 || abilities == null || abilities.length < numTeams)
			throw new IllegalArgumentException(
					"need to have more players than teams and atleast 2 teams");
		// using helper method for recursion, passing array with numTeams has
		// length, starting index for abilities, the abilities array, and array
		// to store number people per team
		return minDiffCalc(new int[numTeams], 0, abilities, new int[numTeams]);

	}

	// pre: none
	// post: returns minDifference of teams
	public static int minDiffCalc(int[] teams, int indexAbilities,
			int[] abilities, int[] numPerTeam) {
		// used to store compare differences for each combo, initialized to
		// Integer.MAX_VALUE because any difference between teams should replace
		// it with Math.min call
		int bestFromHere = Integer.MAX_VALUE;
		// base case: if everyone added to team
		if (indexAbilities == abilities.length) {
			// check if every team has at least one person
			for (int i = 0; i < numPerTeam.length; i++)
				// if true, then a team has 0 people, so returning
				// Integer.MAX_VALUE
				if (numPerTeam[i] == 0)
					return bestFromHere;
			// if got here, then every team has a person, so return the
			// difference
			// findMax() and findMin() return the index of max/min value in
			// teams respectively
			return teams[findMax(teams)] - teams[findMin(teams)];
		}
		// using Math.min for efficiency, when looking where to add at
		// beginning, should only be able to add to any team with a person and
		// first team without a person (not every team without a person)
		for (int t = 0; t < Math.min(teams.length, indexAbilities + 1); t++) {
			// add that person to respective team
			teams[t] += abilities[indexAbilities];
			// increment count of people on that team
			numPerTeam[t]++;
			// recursive step: store min difference found from all combos of
			// teams
			bestFromHere = Math.min(bestFromHere, minDiffCalc(teams,
					indexAbilities + 1, abilities, numPerTeam));
			// remove that person from team to try another combo
			teams[t] -= abilities[indexAbilities];
			numPerTeam[t]--;
		}
		// returning lowest difference found
		return bestFromHere;
	}

	// pre: none
	// post: returns index of min value
	public static int findMin(int[] teams) {
		int indexOfMin = 0;
		// starts at 1 because indexOfMin set to first index
		for (int i = 1; i < teams.length; i++) {
			// if found a value in teams that is less than the current min,
			// store that index in indexOfMin
			if (teams[i] < teams[indexOfMin])
				indexOfMin = i;
		}
		return indexOfMin;
	}

	// pre: none
	// post: returns index of max value
	public static int findMax(int[] teams) {
		int indexOfMax = 0;
		// starts at 1 because indexOfMax set to first index
		for (int i = 1; i < teams.length; i++) {
			// if found a value in teams that is greater than the current max,
			// store that index in indexOfMax
			if (teams[i] > teams[indexOfMax])
				indexOfMax = i;
		}
		return indexOfMax;
	}

	/**
	 * Problem 8: Maze solver. <br>
	 * pre: board != null <br>
	 * pre: board is a rectangular ADJACENCY <br>
	 * pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*' <br>
	 * pre: there is a single 'S' character present <br>
	 * post: rawMaze is not altered as a result of this method. Return 2 if it
	 * is possible to escape the maze after collecting all the coins. Return 1
	 * if it is possible to escape the maze but without collecting all the
	 * coins. Return 0 if it is not possible to escape the maze. More details in
	 * the assignment handout.
	 * 
	 * @param rawMaze represents the maze we want to escape. rawMaze is not
	 *                altered as a result of this method.
	 * @return per the post condition
	 */
	public static int canEscapeMaze(char[][] rawMaze) {
		// constants for all characters in maze
		final char START = 'S';
		final char GREEN = 'G';
		final char YELLOW= 'Y';
		final char IMPASS = '*';
		final char COIN = '$';
		final char EXIT = 'E';
		// using arrayList to store characters of maze in descending
					// precedence, allows for similar logic (using indices) to know
					// which character to change to.
					// using arrayList over array to use indexOf to find index of
					// certain characters (rather than hard coding)
		ArrayList<Character> allCharacters= new ArrayList<>();
		Collections.addAll(allCharacters, START, GREEN, YELLOW, IMPASS, COIN, EXIT);
		// precons
		if (rawMaze == null || !isRectangular(rawMaze)
				|| !onlyMazeCharsWithOneS(rawMaze, allCharacters, START))
			throw new IllegalArgumentException("preconditions of maze not met");
		// if there is an end, then run recursion
		if (hasEnd(rawMaze, EXIT)) {
			// using helper method to store array with indices of start
			// character
			int[] startIndecies = findStartIndecies(rawMaze, START);
			// using helper method, passing in copy of rawMaze, start row and
			// col, number of coins to find in maze, and then indices, of
			// yellow, impass, coin, and exit in arrayList
			return canEscapeHelper(makeCopy(rawMaze), allCharacters, startIndecies[0],
					startIndecies[1], countCoins(rawMaze, COIN),
					YELLOW, IMPASS, COIN, EXIT);
		}
		// if got here, then maze does not have an end, so return 0
		else
			return 0;
	}

	// pre: none
	// post: returns a copy of array passed in
	private static char[][] makeCopy(char[][] rawMaze) {
		char[][] newMaze = new char[rawMaze.length][rawMaze[0].length];
		for (int r = 0; r < newMaze.length; r++) {
			newMaze[r] = rawMaze[r].clone();
		}
		return newMaze;
	}

	// pre: none
	// post: returns answer for maze
	// does base case checking
	private static int canEscapeHelper(char[][] rawMaze,
			ArrayList<Character> keys, int row, int col, int coinsLeft,
			final char YELLOW, final char IMPASS, final char COIN,
			final char EXIT) {
		// answer is answer to maze, set to 0 because thats worst option
		int answer = NO_SOLUTION;
		// base case: if at an exit.
		if (rawMaze[row][col] == EXIT) {
			// if all coins found, return 2
			if (coinsLeft == 0)
				return ALL_COINS;
			// else, return 1 (at exit but not all coins found)
			return EXIT_FOUND;
		}
		// implicit else, store answer of checkAdjacency
		answer = checkAdjacency(rawMaze, keys, row, col, coinsLeft, YELLOW,
				IMPASS, COIN, EXIT);
		return answer;

	}

	// pre: none
	// post: returns answer for maze
	// checks adjacences and returns best outcome (2>1>0)
	private static int checkAdjacency(char[][] rawMaze,
			ArrayList<Character> keys, int row, int col, int coinsLeft,
			final char YELLOW, final char IMPASS, final char COIN,
			final char EXIT) {
		//set defauly answer to no solution, so if nothing returned, return no solution found
		int answer = NO_SOLUTION;
		// traversing adjacency array to check all possible combos of next
		// location to go in maze
		for (int c = 0; c < ADJACENCY[0].length; c++) {
			// change indices to next spot in maze to check
			int rowTest = row + ADJACENCY[0][c];
			int colTest = col + ADJACENCY[1][c];
			// store character in maze @ row and col in arrayList storing
			// all characters in maze (keys)
			char character = rawMaze[row][col];
			// storing coinsLeft before (possibly) changed
			int oldCoinsLeft = coinsLeft;
			// if not at impassible character and new rowTest and colTest are
			// valid indices
			if (character != IMPASS && rowTest >= 0 && colTest >= 0
					&& rowTest < rawMaze.length
					&& colTest < rawMaze[0].length) {
				// if we are at a coin, decrement coinsLeft and set rawMaze @
				// row and col to yellow cell (have to do this manually rather
				// than using indices arithmetic because keys is stored in
				// descending precedence for all characters WIHTOUT accounting
				// for coin, as coin and green have same after image
				if (character == COIN) {
					coinsLeft--;
					rawMaze[row][col] = YELLOW;
				}
				// if not at a coin, set to rawMaze @ row and col to next index
				// in keys (which is after image of each character that has an
				// after image except for coin)
				else
					rawMaze[row][col] = keys.get(keys.indexOf(character) + 1);
				// recursive step: call to check base cases (canEscapeHelper)
				// which will bring us back to this method to check adjacencies
				// if not at an exit
				answer = Math.max(answer,
						canEscapeHelper(rawMaze, keys, rowTest, colTest,
								coinsLeft, YELLOW, IMPASS, COIN,
								EXIT));
				// efficiency, if found optimal path (all coins found and found
				// exit), return that answer (2) rather than continuing search
				if (answer == ALL_COINS)
					return answer;

			}
			// resetting coinsLeft and character @ row and col in rawMaze to
			// check another adjacency
			coinsLeft = oldCoinsLeft;
			rawMaze[row][col] = character;
		}
		return answer;
	}

	// pre: none
	// post: returns true if maze only has characters specified and there is
	// only one start, returns false otherwise
	private static boolean onlyMazeCharsWithOneS(char[][] rawMaze,
			ArrayList<Character> characters, char start) {
		int countOfS = 0;
		for (int r = 0; r < rawMaze.length; r++) {
			for (int c = 0; c < rawMaze[0].length; c++) {
				// if rawMaze[r][c] is not in characters string (not a specified
				// character), return false
				if (!characters.contains(rawMaze[r][c]))
					return false;
				// if rawMaze[r][c] is a start, add to start counter
				if (rawMaze[r][c] == start)
					countOfS++;
			}
		}
		// if only one S, return true, else return false
		return countOfS == 1;
	}

	// pre: none
	// post: return an ArrayList with all letters in string added to arrayList
	// in separate indices (compared to calling split AND then using
	// Arrays.asList (memory vs time trade off))
	private static ArrayList<Character> mazeCharacters(String allChars) {
		ArrayList<Character> keys = new ArrayList<>();
		for (int i = 0; i < allChars.length(); i++)
			keys.add(allChars.charAt(i));
		return keys;
	}

	// pre: none
	// post: returning amount of coins in rawMaze
	private static int countCoins(char[][] rawMaze, char coin) {
		int count = 0;
		for (int r = 0; r < rawMaze.length; r++) {
			for (int c = 0; c < rawMaze[0].length; c++) {
				// if at a coin, add to coin counter
				if (rawMaze[r][c] == coin)
					count++;
			}
		}
		return count;
	}

	// pre: none
	// post: returns true if there is an exit, false otherwise
	private static boolean hasEnd(char[][] rawMaze, char exit) {
		for (int r = 0; r < rawMaze.length; r++)
			for (int c = 0; c < rawMaze[0].length; c++)
				if (rawMaze[r][c] == exit)
					return true;
		return false;
	}

	// pre: none
	// post: returns indices of start in rawMaze (indices[0] is row index,
	// indices[1] is col index)
	private static int[] findStartIndecies(char[][] rawMaze, char start) {
		int[] indices = new int[2];
		for (int r = 0; r < rawMaze.length; r++) {
			for (int c = 0; c < rawMaze[0].length; c++) {
				// if found start, store row and col in respective locations and
				// return indices (early return because we know only one start
				// in maze (efficiency))
				if (rawMaze[r][c] == start) {
					indices[0] = r;
					indices[1] = c;
					return indices;
				}
			}
		}
		// should never get here (precon of canEscapeMaze) but have to have
		return indices;
	}

}
