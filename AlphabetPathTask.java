/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


/**
 *
 * @author semen
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