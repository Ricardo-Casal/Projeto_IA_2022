package eightpuzzle;

import agent.Action;
import agent.State;

import javax.crypto.spec.PSource;
import javax.swing.text.Position;
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
    private int mummyLine;
    private int mummyColumn;
    private int redMummyLine;
    private int redmummycolumn;
    private int scorpionLine;
    private int scorpionColumn;
    private int linhaArmadilha;
    private int colunaArmadilha;
    private LinkedList<Enemy>enemyLinkedList;


    public MummyMazeState(char[][] matrix, LinkedList<Enemy> enemyLinkedList, int lineHero,int columnHero,int lineExit,int columnExit ) {
        this.matrix = new char[matrix.length][matrix.length];
        this.lineHero=lineHero;
        this.columnHero=columnHero;
        this.lineExit=lineExit;
        this.columnExit = columnExit;

        this.enemyLinkedList = new LinkedList<>();
        for (Enemy e:enemyLinkedList){
            System.out.println(e);
            this.enemyLinkedList.add(e.clone());
        }



        for (int j = 0;j < matrix.length; j++) {
            for (int k = 0; k < matrix.length; k++) {
                this.matrix[j][k] = matrix[j][k];
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
        if (lineHero == 1 && matrix[lineHero - 1][columnHero] == 'S')
            return true;
        if (lineHero > 1 && matrix[lineHero - 1][columnHero] == ' ' && matrix[lineHero - 2][columnHero] == '.')
            return true;

        return false;
    }

    public boolean canMoveRight() {
        if (columnHero == 11 && matrix[lineHero][columnHero + 1] == 'S')
            return true;
        if (columnHero < 11 && matrix[lineHero][columnHero + 1] == ' ' && matrix[lineHero][columnHero + 2] == '.')
            return true;
        return false;
    }

    public boolean canMoveDown() {
        if (lineHero == 11 && matrix[lineHero + 1][columnHero] == 'S')
            return true;
        if (lineHero < 11 && matrix[lineHero + 1][columnHero] == ' ' && matrix[lineHero + 2][columnHero] == '.')
            return true;

        return false;
    }

    public boolean canMoveLeft() {
        if (columnHero == 1 && matrix[lineHero][columnHero - 1] == 'S')
            return true;
        if (columnHero > 1 && matrix[lineHero][columnHero - 1] == ' ' && matrix[lineHero][columnHero - 2] == '.')
            return true;
        return false;

    }


    /*
     * In the next four methods we don't verify if the actions are valid.
     * This is done in method executeActions in class EightPuzzleProblem.
     * Doing the verification in these methods would imply that a clone of the
     * state was created whether the operation could be executed or not.
     */

    public void wontMove() {
        moveInimigo();

    }

    public void moveUp() {
        if (lineHero > 1) {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero -= 2][columnHero] = 'H';
        } else {
            matrix[lineHero][columnHero] = '.';
            System.out.println(lineHero + "" + columnHero);
            matrix[lineHero -= 1][columnHero] = 'H';
        }

        moveInimigo();

    }
/*ANTES DE MOVER FAZER UM IF PARA VER NA COOLUNA E NA LINHA DA MUMIA VER ARMADILHA ANTES DE COLOCAR A MUMIA = -2
* IF INIMIGO TEM ARMADILHA
*
* MATRIZ LINE MUMMY, COLUM MUMMY = 'A'
* ELSE NAO TEM ARMADLIHA LINE...='A'
*
* ANTES DE MOVER PARA  POS -2
*
* FAZER OUTRO IF SEPARADO DO DE CIMA
* IF NA MATRIZ LINE MUMY COLUMN MUMMY -2, SE NA POSICAO PARA ONDE ELE VAI TIVER UMA CHAVE OU ARMADILHA
*
*
*
* */
    public void moveRight() {
        if (columnHero < 11) {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero][columnHero += 2] = 'H';
        } else {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero][columnHero += 1] = 'H';
        }

        moveInimigo();


    }

    public void moveDown() {
        if (lineHero < 11) {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero += 2][columnHero] = 'H';
        } else {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero += 1][columnHero] = 'H';
        }
        moveInimigo();


    }

    public void moveLeft() {
        if (columnHero > 1) {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero][columnHero -= 2] = 'H';
        } else {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero][columnHero -= 1] = 'H';
        }
        moveInimigo();
    }


    private void moveInimigo() {
        //System.out.println("tamanho da lista mumias" + enemyLinkedList.size());
        //FOR PARA A MUMIA BRANCA


        for (int i = 0; i < enemyLinkedList.size(); i++) {
            System.out.println("aqui"+enemyLinkedList.get(i));

            if (enemyLinkedList.get(i).getEnemyType()== 'M') {
                mummyLine = enemyLinkedList.get(i).getLinha();
                mummyColumn = enemyLinkedList.get(i).getColuna();
                for (int j = 0; j < 2; j++) {
                    if (mummyColumn > columnHero) {
                        if (matrix[mummyLine][mummyColumn - 1] == ' ') {
                            matrix[mummyLine][mummyColumn] = '.';
                            matrix[mummyLine][mummyColumn -= 2] = 'M';
                        } else if (mummyLine > lineHero) {
                            if (matrix[mummyLine - 1][mummyColumn] == ' ') {
                                matrix[mummyLine][mummyColumn] = '.';
                                matrix[mummyLine -= 2][mummyColumn] = 'M';
                            }
                        } else if (mummyLine < lineHero) {
                            if (matrix[mummyLine + 1][mummyColumn] == ' ') {
                                matrix[mummyLine][mummyColumn] = '.';
                                matrix[mummyLine += 2][mummyColumn] = 'M';
                            }
                        }
                    } else if (mummyColumn < columnHero) {
                        if (matrix[mummyLine][mummyColumn + 1] == ' ') {
                            matrix[mummyLine][mummyColumn] = '.';
                            matrix[mummyLine][mummyColumn += 2] = 'M';
                        } else if (mummyLine > lineHero) {
                            if (matrix[mummyLine - 1][mummyColumn] == ' ') {
                                matrix[mummyLine][mummyColumn] = '.';
                                matrix[mummyLine -= 2][mummyColumn] = 'M';
                            }
                        } else if (mummyLine < lineHero) {
                            if (matrix[mummyLine + 1][mummyColumn] == ' ') {
                                matrix[mummyLine][mummyColumn] = '.';
                                matrix[mummyLine += 2][mummyColumn] = 'M';
                            }
                        }

                    } else {
                        if (mummyLine > lineHero) {
                            if (matrix[mummyLine - 1][mummyColumn] == ' ') {
                                matrix[mummyLine][mummyColumn] = '.';
                                matrix[mummyLine -= 2][mummyColumn] = 'M';
                            }
                        } else if (mummyLine < lineHero) {
                            if (matrix[mummyLine + 1][mummyColumn] == ' ') {
                                matrix[mummyLine][mummyColumn] = '.';
                                matrix[mummyLine += 2][mummyColumn] = 'M';
                            }
                        }
                    }
                    enemyLinkedList.get(i).setLinha(mummyLine);
                    enemyLinkedList.get(i).setColuna(mummyColumn);
                    if (matrix[lineHero][columnHero] == 'M') {
                        lineHero = 0;
                        columnHero = 0;
                        return;
                    }

                }
            }


            if (enemyLinkedList.get(i).getEnemyType()== 'V') {
                redMummyLine = enemyLinkedList.get(i).getLinha();
                redmummycolumn = enemyLinkedList.get(i).getColuna();
                System.out.println("POS DA MUMIA: "+redMummyLine+"linha/ coluna"+redmummycolumn);
//                System.out.println("POS MUMIA VERMELHA");
//                System.out.println(enemyLinkedList.get(i).getLinha());
//                System.out.println(enemyLinkedList.get(i).getColuna());
                for (int j = 0; j < 2; j++) {
                    if (redMummyLine > lineHero) {
                        if (matrix[redMummyLine - 1][redmummycolumn] == ' ') {
                            matrix[redMummyLine][redmummycolumn] = '.';
                            matrix[redMummyLine -=2][redmummycolumn] = 'V';
                        } else if (redmummycolumn > columnHero) {
                            if (matrix[redMummyLine][redmummycolumn - 1] == ' ') {
                                matrix[redMummyLine][redmummycolumn] = '.';
                                matrix[redMummyLine][redmummycolumn -=2] = 'V';
                            }
                        } else if (redmummycolumn < columnHero) {
                            if (matrix[redMummyLine][redmummycolumn + 1] == ' ') {
                                matrix[redMummyLine][redmummycolumn] = '.';
                                matrix[redMummyLine][redmummycolumn += 2] = 'V';
                            }
                        }
                    } else if (redMummyLine < lineHero) {
                        if (matrix[redMummyLine + 1][redmummycolumn] == ' ') {
                            matrix[redMummyLine][redmummycolumn] = '.';
                            matrix[redMummyLine +=2][redmummycolumn] = 'V';
                        } else if (redmummycolumn > columnHero) {
                            if (matrix[redMummyLine][redmummycolumn - 1] == ' ') {
                                matrix[redMummyLine][redmummycolumn] = '.';
                                matrix[redMummyLine][redmummycolumn -=2] = 'V';
                            }
                        } else if (redmummycolumn < columnHero) {
                            if (matrix[redMummyLine][redmummycolumn + 1] == ' ') {
                                matrix[redMummyLine][redmummycolumn] = '.';
                                matrix[redMummyLine][redmummycolumn +=2] = 'V';
                            }
                        }

                    } else {
                        if (redmummycolumn > columnHero) {
                            if (matrix[redMummyLine][redmummycolumn - 1] == ' ') {
                                matrix[redMummyLine][redmummycolumn] = '.';
                                matrix[redMummyLine][redmummycolumn -=2] = 'V';
                            }
                        } else if (redmummycolumn < columnHero) {
                            if (matrix[redMummyLine][redmummycolumn + 1] == ' ') {
                                matrix[redMummyLine][redmummycolumn] = '.';
                                matrix[redMummyLine][redmummycolumn +=2] = 'V';
                            }
                        }
                    }

                enemyLinkedList.get(i).setLinha(redMummyLine);
                enemyLinkedList.get(i).setColuna(redmummycolumn);

                    // System.out.println(this);
                    if (matrix[lineHero][columnHero] == 'V') {
                        lineHero = 0;
                        columnHero = 0;
                        return;

                    }

                }
            }//-----------------------------------------------

            if (enemyLinkedList.get(i).getEnemyType() == 'E') {
                scorpionLine = enemyLinkedList.get(i).getLinha();
                scorpionColumn = enemyLinkedList.get(i).getColuna();
                    if (scorpionColumn > columnHero) {
                        if (matrix[scorpionLine][scorpionColumn - 1] == ' ') {
                            matrix[scorpionLine][scorpionColumn] = '.';
                            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+ enemyLinkedList.get(i));
                      //      if (matrix[scorpionLine][scorpionColumn-2]=='A'){
                                matrix[scorpionLine][scorpionColumn -= 2] = 'E';
                        //    }

                        } else if (scorpionLine > lineHero) {
                            if (matrix[scorpionLine - 1][scorpionColumn] == ' ') {
                                matrix[scorpionLine][scorpionColumn] = '.';
                                matrix[scorpionLine -= 2][scorpionColumn] = 'E';
                            }
                        } else if (scorpionLine < lineHero) {
                            if (matrix[scorpionLine + 1][scorpionColumn] == ' ') {
                                matrix[scorpionLine][scorpionColumn] = '.';
                                matrix[scorpionLine += 2][scorpionColumn] = 'E';
                            }
                        }
                    } else if (scorpionColumn < columnHero) {
                        if (matrix[scorpionLine][scorpionColumn + 1] == ' ') {
                            matrix[scorpionLine][scorpionColumn] = '.';
                            matrix[scorpionLine][scorpionColumn += 2] = 'E';
                        } else if (scorpionLine > lineHero) {
                            if (matrix[scorpionLine - 1][scorpionColumn] == ' ') {
                                matrix[scorpionLine][scorpionColumn] = '.';
                                matrix[scorpionLine -= 2][scorpionColumn] = 'E';
                            }
                        } else {
                            if (matrix[scorpionLine + 1][scorpionColumn] == ' ') {
                                matrix[scorpionLine][scorpionColumn] = '.';
                                matrix[scorpionLine += 2][scorpionColumn] = 'E';
                            }
                        }

                    } else {
                        if (scorpionLine > lineHero) {
                            if (matrix[scorpionLine - 1][scorpionColumn] == ' ') {
                                matrix[scorpionLine][scorpionColumn] = '.';
                                matrix[scorpionLine -= 2][scorpionColumn] = 'E';
                            }
                        } else if (scorpionLine < lineHero) {
                            if (matrix[scorpionLine + 1][scorpionColumn] == ' ') {
                                matrix[scorpionLine][scorpionColumn] = '.';
                                matrix[scorpionLine += 2][scorpionColumn] = 'E';
                            }
                        }
                    }

                enemyLinkedList.get(i).setLinha(scorpionLine);
                enemyLinkedList.get(i).setColuna(scorpionColumn);
                    // System.out.println(this);
                    if (matrix[lineHero][columnHero] == 'E') {
                        lineHero = 0;
                        columnHero = 0;
                        return;
                    }
            }
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

    public int getMummyLine() {
        return mummyLine;
    }

    public int getMummyColumn() {
        return mummyColumn;
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
        return new MummyMazeState(matrix,enemyLinkedList,lineHero,columnHero,lineExit,columnExit);


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
