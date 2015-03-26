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
    
    private class SayNo extends Exception {}
    
    private class Symbol {
        private int x_coord;
        private int y_coord;
        private char symbol;
        
        public Symbol(int x, int y, char c) {
            this.x_coord = x;
            this.y_coord = y;
            this.symbol = c;
        }
    }
    
    private Symbol getSymbol(int x, int y, String[] letterMaze) {
        Symbol s = null;
        if (y < letterMaze.length) {
            String row = letterMaze[y];
            if (x < row.length()) {
                char c = row.charAt(x);
                s = new Symbol(x, y, c);
            }
        }
        return s;
    }
    
    private Symbol findNextNeighbor(String[] letterMaze) {
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
        String t = letterMaze[0];
        int lol = t.indexOf('A');
        return "YES";
    }
}