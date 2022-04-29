package eightpuzzle;

import agent.Action;
import agent.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MummyMazeState extends State implements Cloneable {

    static final char[][] GOAL_MATRIX = {};
    final int[] linesfinalMatrix = {0, 0, 0, 1, 1, 1, 2, 2, 2};
    final int[] colsfinalMatrix = {0, 1, 2, 0, 1, 2, 0, 1, 2};
    public static final int SIZE = 3;
    private final char[][] matrix;
    private LinkedList<String> lista = new LinkedList<String>();
    private int lineHero;
    private int columnHero;

    public MummyMazeState(char[][] matrix) {
        this.matrix = new char[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 'H') {
                    lineHero = i;
                    columnHero = j;
                }
            }
        }
    }

    public char[][] getMatrix() {
        return matrix;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        firePuzzleChanged(null);
    }

    public boolean canMoveUp() {
        int lineHeroAuxUp =lineHero-1;
        int columnHeroAuxUp =columnHero;
        int lineHeroAuxUpTwice = lineHero-2;
        int  columnHeroAuxUpTwice = columnHero;
        System.out.println("Entra no mexer cima");

        if (this.matrix[lineHeroAuxUp][columnHeroAuxUp]==' ' && this.matrix[lineHeroAuxUpTwice][columnHeroAuxUpTwice]=='.')
            return true;
        if (this.matrix[lineHeroAuxUp][columnHeroAuxUp]=='S'){
            return true;
        }else return false;

    }

    public boolean canMoveRight() {
        int lineHeroAuxUp =lineHero;
        int columnHeroAuxUp =columnHero+1;
        int lineHeroAuxUpTwice = lineHero;
        int  columnHeroAuxUpTwice = columnHero+2;
        System.out.println("Entra no mexer direita");
        if (this.matrix[lineHeroAuxUp][columnHeroAuxUp]==' ' && this.matrix[lineHeroAuxUpTwice][columnHeroAuxUpTwice]=='.')
            return true;
        if (this.matrix[lineHeroAuxUp][columnHeroAuxUp]=='S'){
            return true;
        }else return false;
    }

    public boolean canMoveDown() {
        System.out.println("Entra no mexer baixo");

        if (this.matrix[lineHero+1][columnHero]==' ' && this.matrix[lineHero+2][columnHero]=='.')
            return true;
        if (this.matrix[lineHero+1][columnHero]=='S'){
            return true;
        }else return false;
    }

    public boolean canMoveLeft() {
        int lineHeroAuxUp =lineHero;
        int columnHeroAuxUp =columnHero-1;
        int lineHeroAuxUpTwice = lineHero;
        int  columnHeroAuxUpTwice = columnHero-2;
        System.out.println("Entra no mexer esquerda");

        if (this.matrix[lineHeroAuxUp][columnHeroAuxUp]==' ' && this.matrix[lineHeroAuxUpTwice][columnHeroAuxUpTwice]=='.')
            return true;
        if (this.matrix[lineHeroAuxUp][columnHeroAuxUp]=='S')
            return true;
        if (this.matrix[lineHeroAuxUp-1][columnHeroAuxUp-1]==' ' && this.matrix[lineHeroAuxUpTwice][columnHeroAuxUpTwice]=='.'){

        }else return false;
    }

    /*
     * In the next four methods we don't verify if the actions are valid.
     * This is done in method executeActions in class EightPuzzleProblem.
     * Doing the verification in these methods would imply that a clone of the
     * state was created whether the operation could be executed or not.
     */
    public void moveUp() {
        matrix[lineHero][columnHero] = matrix[lineHero-2][columnHero];
        matrix[lineHero][columnHero] = '.';
    }

    public void moveRight() {
        matrix[lineHero][columnHero] = matrix[lineHero][columnHero+2];
        matrix[lineHero][columnHero] = '.';
    }

    public void moveDown() {
        matrix[lineHero][columnHero] = matrix[lineHero+2][columnHero];
        matrix[lineHero][columnHero] = '.';
    }

    public void moveLeft() {
        matrix[lineHero][columnHero] = matrix[lineHero][columnHero-2];
        matrix[lineHero][columnHero] = '.';
    }

    public double computeTilesOutOfPlace(MummyMazeState finalState) {
        double h = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (this.matrix[i][j] != 0) { // Blank is ignored so that the heuristic is admissible
                    if(this.matrix[i][j]!= finalState.matrix[i][j]){
                        h++;
                    }
                }
            }
        }
        return h;
    }

    public double computeTileDistances(MummyMazeState finalState) {
        double h = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (this.matrix[i][j] != 0) { // Blank is ignored so that the heuristic is admissible
                    h += Math.abs(i - linesfinalMatrix[this.matrix[i][j]])
                            + Math.abs(j - colsfinalMatrix[this.matrix[i][j]]);
                }
            }
        }
        return h;
    }

    public int getNumLines() {
        return matrix.length;
    }

    public int getNumColumns() {
        return matrix[0].length;
    }

    public int getTileValue(int line, int column) {
        if (!isValidPosition(line, column)) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        return matrix[line][column];
    }

    public boolean isValidPosition(int line, int column) {
        return line >= 0 && line < matrix.length && column >= 0 && column < matrix[0].length;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MummyMazeState)) {
            return false;
        }

        MummyMazeState o = (MummyMazeState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
            }
            buffer.append('\n');
        }
        return buffer.toString();
    }

    @Override
    public MummyMazeState clone() {
        return new MummyMazeState(matrix);


    }
    //Listeners
    private transient ArrayList<MummyMazeListner> listeners = new ArrayList<MummyMazeListner>(3);

    public synchronized void removeListener(MummyMazeListner l) {
        if (listeners != null && listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    public synchronized void addListener(MummyMazeListner l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void firePuzzleChanged(MummyMazeEvent pe) {
        for (MummyMazeListner listener : listeners) {
            listener.puzzleChanged(null);
        }
    }
}
