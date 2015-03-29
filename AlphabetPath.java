/**
 * SRM 523 DIV 2, 250 points Problem
 * Problem Statement
 * You are given a 2-dimensional matrix of characters represented by the String[] letterMaze. 
 * The i-th character of the j-th element of letterMaze represents the character at row i and column j. 
 * Each of the 26 letters from 'A' to 'Z' appears exactly once in letterMaze, the remaining characters are 
 * periods ('.').

 * An alphabet path is a sequence of 26 elements of the matrix such that:
 * The first element contains the letter 'A'.
 * The first element and the second element are (horizontally or vertically) adjacent.
 * The second element contains the letter 'B'.
 * The second element and the third element are (horizontally or vertically) adjacent.
 * The 25-th element and the 26-th element are (horizontally or vertically) adjacent.
 * The last, 26-th element contains the letter 'Z'.

* Given letterMaze return String "YES" if the alphabet path exists in the matrix and "NO" otherwise.

* Definition
* Class: AlphabetPath
* Method: doesItExist
* Parameters: String[]
* Returns: String
* Method signature: String doesItExist(String[] letterMaze)
* (be sure your method is public)


* Limits
* Time limit (s): 2.000
* Memory limit (MB): 64

* Notes
* Formally, elements (x1,y1) and (x2,y2) are horizontally or vertically adjacent 
* if and only if abs(x1 - x2) + abs(y1 - y2) = 1.
* 
* Constraints
*  - letterMaze will contain between 1 and 50 elements, inclusive.
*  - Each element of letterMaze will contain between 1 and 50 characters, inclusive.
*  - All the elements of letterMaze will contain the same number of characters.
*  - Each element of letterMaze will only contain uppercase letters ('A'-'Z') and periods ('.').
*  - Each uppercase letter from 'A' to 'Z' will appear exactly once in letterMaze.

* Examples
* 
* Input 1: {"ABCDEFGHIJKLMNOPQRSTUVWXYZ"}
* Correct answer: "YES"
* Simply go from left to right.

* Input 2: {"ADEHI..Z",
*         "BCFGJK.Y",
*         ".PONML.X",
*         ".QRSTUVW"}
* Correct answer: "YES"

* Input 3: {"ACBDEFGHIJKLMNOPQRSTUVWXYZ"}
* Correct answer: "NO"

* Input 4: {"ABC.......",
*           "...DEFGHIJ",
*           "TSRQPONMLK",
*           "UVWXYZ...."}
* Correct answer: "NO"
* The cells marked with C and D are not adjacent, it is impossible to make an alphabet path in this case.

* Input 5: {"..............",
*           "..............",
*           "..............", 
*           "...DEFGHIJK...",
*           "...C......L...",
*           "...B......M...",
*           "...A......N...",
*           "..........O...",
*           "..ZY..TSRQP...",
*           "...XWVU.......",
*           ".............."}
* Correct answer:: "YES"

* This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved. 
* 
* */

package TopCoder;

import java.awt.Point;

/**
 * 
 * @author ss18
 * 
 * Time complexity O(n).
 * 
 * The idea behind. Find 'A' symbol first. Then check neighbors of 'A' (max 4 neighbors) for 'B'.
 * Then check neighbors of 'B' for 'C' on so on. Any step could fail. So algorithm return "NO".
 * If 'Z' reached return "YES". As simple as that. Of course could be optimized a lot. E.g. find any 
 * symbol first. Not necessary 'A'. Then go from it to 'A' and 'Z' simultaneously. But it 
 * will be still O(n).
 * 
 */
public class AlphabetPath {
    
    /**
     * 
     * @param letterMaze array of strings with maze itself
     * @return "YES" if maze satisfied conditions from above. "NO" otherwise.
     */
    public String doesItExist(String[] letterMaze) {
        Symbol firstElement = this.findA(letterMaze);
        if (firstElement != null) {
            Symbol element = firstElement;
            for(;;) {
                element = this.findNextNeighbor(letterMaze, element);
                if (element == null) {
                    break;
                } else if (element.isLastElement()) {
                    return "YES";
                }
            }
        }
        return "NO";
    }    
    
    /**
     * Get Symbol from maze
     * @param x - x coordinate of the symbol in maze 
     * @param y - y coordinate of the symbol in maze 
     * @param letterMaze - maze itself
     * @return Symbol or null if coordinates are not valid
     */
    protected Symbol getSymbol(int x, int y, String[] letterMaze) {
        Symbol s = null;
        if (y >= 0 && y < letterMaze.length) {
            String row = letterMaze[y];
            if (x >= 0 && x < row.length()) {
                s = new Symbol(x, y, row.charAt(x));
            }
        }
        return s;
    }
    
    /**
     * Looking for next symbol, based on current symbol. Next symbol must satisfy conditions
     * from above.
     * @param letterMaze - maze itself
     * @param symbol - current symbol
     * @return Symbol or null if current symbol has no appropriate neighbor
     */
    protected Symbol findNextNeighbor(String[] letterMaze, Symbol symbol) {
        Point coordinates = symbol.coordinates;
        Symbol up = this.getSymbol(coordinates.x, coordinates.y + 1, letterMaze);
        Symbol down = this.getSymbol(coordinates.x, coordinates.y - 1, letterMaze);
        Symbol right = this.getSymbol(coordinates.x + 1, coordinates.y, letterMaze);        
        Symbol left = this.getSymbol(coordinates.x - 1, coordinates.y, letterMaze);
        
        if (up != null && up.element == symbol.nextElement()) {
            return up;
        } else if (down != null && down.element == symbol.nextElement()) {
            return down;
        } else if (right != null && right.element == symbol.nextElement()) {
            return right;        
        } else if (left != null && left.element == symbol.nextElement()) {
            return left;        
        }
        
        return null;
    }
    /**
     *  Looking for 'A' in maze
     * @param letterMaze - maze itself
     * @return Symbol or null if no 'A' in maze
     */
    protected Symbol findA(String[] letterMaze) {
        Symbol aSymbol = null;
        for (int y = 0; y < letterMaze.length; y++) {
            int x = letterMaze[y].indexOf(Symbol.firstElement());
            if (x != -1) {
                aSymbol = new Symbol(x, y);
                break;
            }
        }
        return aSymbol;
    }
}
    
/**
 * Auxiliary class for representation symbols in maze
 * @author ss18
 */
class Symbol {
    
    public final char element;
    public Point coordinates;

    /**
     * Constructor
     * @param x - x coordinate
     * @param y - y coordinate
     * @param c - maze element
     */
    public Symbol(int x, int y, char c) {
        this.element = c;
        this.coordinates = new Point(x, y);
        
    }
    
    /**
     * Constructor for 'A'
     * @param x - x coordinate
     * @param y - y coordinate
     */    
    public Symbol(int x, int y) {
        this(x, y, Symbol.firstElement());
    }

    /**
     * @return true if this == first element in sequence (see conditions for maze sequence)
     */
    public boolean isFirstElement() {
        return this.element == Symbol.firstElement();
    }
    
    /**
     * @return true if this == last element in sequence (see conditions for maze sequence)
     */
    public boolean isLastElement() {
        return this.element == Symbol.lastElement();
    }
    
    /**
     * @return next sequence element
     */
    public char nextElement() {
        return (char)(this.element + 1);
    }

    /**
     * @return first sequence element
     */
    public static char firstElement() {
        return 'A';
    }

    /**
     * @return last sequence element
     */    
    public static char lastElement() {
        return 'Z';
    }
}

