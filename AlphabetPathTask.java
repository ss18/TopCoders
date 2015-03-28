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


/**
 *
 * @author ss18
 */
public class AlphabetPathTask {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] array =  { "ABCDEFG", "HHH"};
        AlphabetPath path = new AlphabetPath();
        System.out.println(path.doesItExist(array));
    }
    
   
}
class AlphabetPath {
    
    private class Symbol {
        public final int x_coord;
        public final int y_coord;
        public final char symbol;
        
        public Symbol(int x, int y, char c) {
            this.x_coord = x;
            this.y_coord = y;
            this.symbol = c;
        }
    }
    
    private Symbol getSymbol(int x, int y, String[] letterMaze) {
        Symbol s = null;
        if (y >= 0 && y < letterMaze.length) {
            String row = letterMaze[y];
            if (x >= 0 && x < row.length()) {
                char c = row.charAt(x);
                s = new Symbol(x, y, c);
            }
        }
        return s;
    }
    
    private Symbol findNextNeighbor(String[] letterMaze, Symbol symbol) {
        Symbol up = this.getSymbol(symbol.x_coord, symbol.y_coord + 1, letterMaze);
        Symbol down = this.getSymbol(symbol.x_coord, symbol.y_coord - 1, letterMaze);
        Symbol right = this.getSymbol(symbol.x_coord + 1, symbol.y_coord, letterMaze);        
        Symbol left = this.getSymbol(symbol.x_coord - 1, symbol.y_coord, letterMaze);
        
        if (up != null && up.symbol == symbol.symbol + 1) {
            return up;
        } else if (down != null && down.symbol == symbol.symbol + 1) {
            return down;
        } else if (right != null && right.symbol == symbol.symbol + 1) {
            return right;        
        } else if (left != null && left.symbol == symbol.symbol + 1) {
            return left;        
        }
        
        return null;
    }
    
    private Symbol findA(String[] letterMaze) {
        Symbol aSymbol = null;
        for (int y = 0; y < letterMaze.length; y++) {
            String row = letterMaze[y];
            int x = row.indexOf('A');
            if (x != -1) {
                aSymbol = new Symbol(x, y, 'A');
                break;
            }
        }
        return aSymbol;
    }

    public String doesItExist(String[] letterMaze) {
        Symbol aSymbol = this.findA(letterMaze);
        if (aSymbol != null) {
            Symbol symbol = aSymbol;
            while (true) {
                symbol = this.findNextNeighbor(letterMaze, symbol);
                if (symbol == null) {
                    break;
                } else if (symbol.symbol == 'Z') {
                    return "YES";
                }
            }
        }
        return "NO";
    }
}