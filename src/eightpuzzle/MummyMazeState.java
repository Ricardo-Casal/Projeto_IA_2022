package eightpuzzle;

import agent.Action;
import agent.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MummyMazeState extends State implements Cloneable {

    public static final int SIZE = 3;
    private final char[][] matrix;
    private LinkedList<String> lista = new LinkedList<String>();
    private int lineHero;
    private int columnHero;
    private int lineExit;
    private int columnExit;


    public MummyMazeState(char[][] matrix) {
        this.matrix = new char[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 'H') {
                    lineHero = i;
                    columnHero = j;
                }
                if (this.matrix[i][j] == 'S') {
                    lineExit = i;
                    columnExit = j;
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
        System.out.println("Entra no mexer cima");
        if (lineHero == 1 && matrix[lineHero - 1][columnHero] == 'S')
            return true;
        if (lineHero > 1 && matrix[lineHero - 1][columnHero] == ' ' && matrix[lineHero - 2][columnHero] == '.')
            return true;

        return false;
    }

    public boolean canMoveRight() {
        System.out.println("Entra no mexer Direita");
        if (columnHero == 11 && matrix[lineHero][columnHero+1] == 'S')
            return true;
        if (columnHero < 11 && matrix[lineHero][columnHero+1] == ' ' && matrix[lineHero][columnHero+2] == '.')
            return true;

        return false;
    }

    public boolean canMoveDown() {
        System.out.println("Entra no mexer Baixo");
        if (lineHero == 11 && matrix[lineHero + 1][columnHero] == 'S')
            return true;
        if (lineHero < 11 && matrix[lineHero + 1][columnHero] == ' ' && matrix[lineHero + 2][columnHero] == '.')
            return true;

        return false;
    }

    public boolean canMoveLeft() {
        System.out.println("Entra no mexer Esquerda");
        if (columnHero == 1 && matrix[lineHero][columnHero-1] == 'S')
            return true;
        if (columnHero > 1 && matrix[lineHero][columnHero-1] == ' ' && matrix[lineHero][columnHero-2] == '.')
            return true;

        return false;
    }

    /*
     * In the next four methods we don't verify if the actions are valid.
     * This is done in method executeActions in class EightPuzzleProblem.
     * Doing the verification in these methods would imply that a clone of the
     * state was created whether the operation could be executed or not.
     */
    public void moveUp() {
        if (lineHero > 1) {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero -= 2][columnHero] = 'H';
        } else {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero -= 1][columnHero] = 'H';
        }

    }

    public void moveRight() {
        if (lineHero < 11) {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero][columnHero+=2] = 'H';
        } else {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero][columnHero+=1] = 'H';
        }
    }

    public void moveDown() {
        if (lineHero < 11) {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero += 2][columnHero] = 'H';
        } else {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero += 1][columnHero] = 'H';
        }
    }

    public void moveLeft() {
        if (lineHero > 1) {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero][columnHero-=2] = 'H';
        } else {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero][columnHero-=1] = 'H';
        }
    }

    public double computeTilesOutOfPlace() {
        double h = 0;
        return h;
    }

    public double computeTileDistances() {
        double h = 0;
        return h;
    }

    public int getLineExit() {
        return lineExit;
    }

    public int getColumnExit() {
        return columnExit;
    }

    public int getLineHero() {
        return lineHero;
    }

    public int getColumnHero() {
        return columnHero;
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
