package eightpuzzle;

import agent.Action;
import agent.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MummyMazeState extends State implements Cloneable {

    private final char[][] matrix;
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
    private LinkedList<Enemy> enemyLinkedList;
    private char onTopOfH;
    int cont =0;


    public MummyMazeState(char[][] matrix, LinkedList<Enemy> enemyLinkedList, int lineHero, int columnHero, char onTopOfH, int lineExit, int columnExit) {
        this.matrix = new char[matrix.length][matrix.length];
        this.lineHero = lineHero;
        this.columnHero = columnHero;
        this.lineExit = lineExit;
        this.columnExit = columnExit;
        this.onTopOfH = onTopOfH;


        this.enemyLinkedList = new LinkedList<>();
        for (Enemy e : enemyLinkedList) {
            System.out.println(e);
            this.enemyLinkedList.add(e.clone());
        }


        for (int j = 0; j < matrix.length; j++) {
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
        if (lineHero > 1 && matrix[lineHero - 1][columnHero] == ' ' && matrix[lineHero - 2][columnHero] == 'C')
            return true;
        if (columnHero > 1 && matrix[lineHero - 1][columnHero] == '_' && matrix[lineHero - 2][columnHero] == '.')
            return true;
        if (columnHero > 1 && matrix[lineHero - 1][columnHero] == ')' && matrix[lineHero - 2][columnHero] == '.')
            return true;

        return false;
    }

    public boolean canMoveRight() {
        if (columnHero == 11 && matrix[lineHero][columnHero + 1] == 'S')
            return true;
        if (columnHero < 11 && matrix[lineHero][columnHero + 1] == ' ' && matrix[lineHero][columnHero + 2] == '.')
            return true;
        if (columnHero < 11 && matrix[lineHero][columnHero + 1] == ' ' && matrix[lineHero][columnHero + 2] == 'C')
            return true;
        if (columnHero < 11 && matrix[lineHero][columnHero + 1] == '_' && matrix[lineHero][columnHero + 2] == '.')
            return true;
        if (columnHero < 11 && matrix[lineHero][columnHero + 1] == ')' && matrix[lineHero][columnHero + 2] == '.')
            return true;
        return false;

        //do fibonacci function





    }


    public boolean canMoveDown() {
        if (lineHero == 11 && matrix[lineHero + 1][columnHero] == 'S')
            return true;
        if (lineHero < 11 && matrix[lineHero + 1][columnHero] == ' ' && matrix[lineHero + 2][columnHero] == '.')
            return true;
        if (lineHero < 11 && matrix[lineHero + 1][columnHero] == ' ' && matrix[lineHero + 2][columnHero] == 'C')
            return true;
        if (columnHero < 11 && matrix[lineHero + 1][columnHero] == '_' && matrix[lineHero + 2][columnHero] == '.')
            return true;
        if (columnHero < 11 && matrix[lineHero + 1][columnHero] == ')' && matrix[lineHero + 2][columnHero] == '.')
            return true;

        return false;
    }

    public boolean canMoveLeft() {
        if (columnHero == 1 && matrix[lineHero][columnHero - 1] == 'S')
            return true;
        if (columnHero > 1 && matrix[lineHero][columnHero - 1] == ' ' && matrix[lineHero][columnHero - 2] == '.')
            return true;
        if (columnHero > 1 && matrix[lineHero][columnHero - 1] == ' ' && matrix[lineHero][columnHero - 2] == 'C')
            return true;
        if (columnHero > 1 && matrix[lineHero][columnHero - 1] == '_' && matrix[lineHero][columnHero - 2] == '.')
            return true;
        if (columnHero > 1 && matrix[lineHero][columnHero - 1] == ')' && matrix[lineHero][columnHero - 2] == '.')
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



    private void verChave(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == '=') {
                    matrix[i][j] = '_';
                } else if (matrix[i][j] == '_') {
                    matrix[i][j] = '=';
                }
                if (matrix[i][j] == '"') {
                    matrix[i][j] = ')';
                }else
                if (matrix[i][j] == ')') {
                    matrix[i][j] = '"';
                }

            }

        }
    }

    public void moveUp() {
        if (lineHero > 1) {
            matrix[lineHero][columnHero] = onTopOfH;
            onTopOfH = matrix[lineHero -= 2][columnHero];
            matrix[lineHero][columnHero] = 'H';
            if (onTopOfH == 'C') {
                verChave();
            }
        } else {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero -= 1][columnHero] = 'H';
            return;
        }


        moveInimigo();

    }

    public void moveRight() {
        if (columnHero < 11) {

            matrix[lineHero][columnHero] = onTopOfH;
            onTopOfH = matrix[lineHero][columnHero += 2];
            matrix[lineHero][columnHero] = 'H';
            if (onTopOfH == 'C') {
                verChave();
            }
        } else {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero][columnHero += 1] = 'H';
            return;
        }

        moveInimigo();


    }

    public void moveDown() {
        if (lineHero < 11) {
            matrix[lineHero][columnHero] = onTopOfH;
            onTopOfH = matrix[lineHero += 2][columnHero];
            matrix[lineHero][columnHero] = 'H';
            if (onTopOfH == 'C') {
                verChave();
            }
        } else {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero += 1][columnHero] = 'H';
            return;
        }
        moveInimigo();


    }

    public void moveLeft() {

        if (columnHero > 1) {


            matrix[lineHero][columnHero] = onTopOfH;
            onTopOfH = matrix[lineHero][columnHero -= 2];
            matrix[lineHero][columnHero] = 'H';
            if (onTopOfH == 'C') {
                verChave();
            }
        } else {
            matrix[lineHero][columnHero] = '.';
            matrix[lineHero][columnHero -= 1] = 'H';
            return;
        }
        moveInimigo();
    }

    private int findEnemmy(int linha, int coluna) {
        for (int i = 0; i < enemyLinkedList.size(); i++) {
            if (linha == enemyLinkedList.get(i).getLinha() && coluna == enemyLinkedList.get(i).getColuna()) {

                return i;
            }
        }return 0;
    }

    private void moveInimigo() {
        //System.out.println("tamanho da lista mumias" + enemyLinkedList.size());
        //FOR PARA A MUMIA BRANCA
        System.out.println(enemyLinkedList.size());
        LinkedList<Enemy> enemyLinkedListRemover = new LinkedList<>(enemyLinkedList);

        for (int i = 0; i < enemyLinkedList.size(); i++) {
            if(!enemyLinkedListRemover.contains(enemyLinkedList.get(i))){
                continue;
            }
            char onTopOf = enemyLinkedList.get(i).getOnTopOf();

            System.out.println("aqui" + enemyLinkedList.get(i));
            if (enemyLinkedList.get(i).getEnemyType() == 'M') {
                mummyLine = enemyLinkedList.get(i).getLinha();
                mummyColumn = enemyLinkedList.get(i).getColuna();
                for (int j = 0; j < 2; j++) {
                    if (mummyColumn > columnHero) {
                        if (matrix[mummyLine][mummyColumn - 1] == ' ' || matrix[mummyLine][mummyColumn - 1] == ')') {
                            matrix[mummyLine][mummyColumn] = onTopOf;/*  nesta linha ele vai colocar na matriz o que passou por cima anteriormente*/
                            if (matrix[mummyLine][mummyColumn - 2] == 'M') {
                                enemyLinkedList.remove(i);
                                System.out.println("passou1");
                                i--;
                                break;
                            } else if (matrix[mummyLine][mummyColumn - 2] == 'E') {
                                enemyLinkedList.remove(findEnemmy(mummyLine, mummyColumn - 2));
                                matrix[mummyLine][mummyColumn - 2]='.';
                            }

                            onTopOf = matrix[mummyLine][mummyColumn -= 2];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            matrix[mummyLine][mummyColumn] = 'M';
                            if (onTopOf == 'C') {
                                verChave();
                            }
                            //VER SE mumia tem chaves
                        } else if (mummyLine > lineHero) {
                            if (matrix[mummyLine - 1][mummyColumn] == ' ' || matrix[mummyLine - 1][mummyColumn] == '_') {
                                matrix[mummyLine][mummyColumn] = onTopOf;
                                if (matrix[mummyLine - 2][mummyColumn] == 'M') {
                                    enemyLinkedList.remove(i);
                                    System.out.println("passou2");
                                    i--;
                                    break;
                                } else if (matrix[mummyLine - 2][mummyColumn] == 'E') {
                                    enemyLinkedListRemover.remove(findEnemmy(mummyLine - 2, mummyColumn));
                                    matrix[mummyLine - 2][mummyColumn]='.';
                                }
                                onTopOf = matrix[mummyLine -= 2][mummyColumn];
                                enemyLinkedList.get(i).setOnTopOf(onTopOf);
                                matrix[mummyLine][mummyColumn] = 'M';
                                if (onTopOf == 'C') {
                                    verChave();
                                }
                            }
                        } else if (mummyLine < lineHero) {
                            if (matrix[mummyLine + 1][mummyColumn] == ' ' || matrix[mummyLine + 1][mummyColumn] == '_') {
                                matrix[mummyLine][mummyColumn] = onTopOf;
                                if (matrix[mummyLine + 2][mummyColumn] == 'M') {
                                    enemyLinkedList.remove(i);
                                    System.out.println("passou3");
                                    i--;
                                    break;
                                } else if (matrix[mummyLine + 2][mummyColumn] == 'E') {
                                    enemyLinkedListRemover.remove(findEnemmy(mummyLine + 2, mummyColumn));
                                    matrix[mummyLine + 2][mummyColumn ]='.';
                                }
                                onTopOf = matrix[mummyLine += 2][mummyColumn];
                                enemyLinkedList.get(i).setOnTopOf(onTopOf);
                                matrix[mummyLine][mummyColumn] = 'M';
                                if (onTopOf == 'C') {
                                    verChave();
                                }
                            }
                        }
                    } else if (mummyColumn < columnHero) {
                        if (matrix[mummyLine][mummyColumn + 1] == ' ' || matrix[mummyLine][mummyColumn + 1] == ')') {
                            matrix[mummyLine][mummyColumn] = onTopOf;
                            if (matrix[mummyLine][mummyColumn + 2] == 'M') {
                                enemyLinkedList.remove(i);
                                System.out.println("passou4");
                                i--;
                                break;
                            } else if (matrix[mummyLine][mummyColumn + 2] == 'E') {
                                enemyLinkedListRemover.remove(findEnemmy(mummyLine, mummyColumn + 2));
                                matrix[mummyLine][mummyColumn + 2]='.';
                            }

                            onTopOf = matrix[mummyLine][mummyColumn += 2];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            matrix[mummyLine][mummyColumn] = 'M';
                            if (onTopOf == 'C') {
                                verChave();
                            }
                        } else if (mummyLine > lineHero) {
                            if (matrix[mummyLine - 1][mummyColumn] == ' ' || matrix[mummyLine - 1][mummyColumn] == '_') {
                                matrix[mummyLine][mummyColumn] = onTopOf;
                                if (matrix[mummyLine - 2][mummyColumn] == 'M') {
                                    enemyLinkedList.remove(i);
                                    i--;
                                    break;
                                } else if (matrix[mummyLine - 2][mummyColumn] == 'E') {
                                    enemyLinkedListRemover.remove(findEnemmy(mummyLine-2, mummyColumn));
                                    matrix[mummyLine - 2][mummyColumn]='.';
                                }
                                onTopOf = matrix[mummyLine -= 2][mummyColumn];
                                enemyLinkedList.get(i).setOnTopOf(onTopOf);
                                matrix[mummyLine][mummyColumn] = 'M';
                                if (onTopOf == 'C') {
                                    verChave();
                                }
                            }
                        } else if (mummyLine < lineHero) {
                            if (matrix[mummyLine + 1][mummyColumn] == ' ' || matrix[mummyLine + 1][mummyColumn] == '_') {
                                matrix[mummyLine][mummyColumn] = onTopOf;

                                if (matrix[mummyLine + 2][mummyColumn] == 'M') {
                                    enemyLinkedList.remove(i);
                                    i--;
                                    break;
                                } else if (matrix[mummyLine+ 2][mummyColumn] == 'E') {
                                    enemyLinkedListRemover.remove(findEnemmy(mummyLine + 2, mummyColumn));
                                    matrix[mummyLine + 2][mummyColumn]='.';
                                }
                                onTopOf = matrix[mummyLine += 2][mummyColumn];
                                enemyLinkedList.get(i).setOnTopOf(onTopOf);
                                matrix[mummyLine][mummyColumn] = 'M';
                                if (onTopOf == 'C') {
                                    verChave();
                                }
                            }
                        }

                    } else if (mummyLine > lineHero) {
                        if (matrix[mummyLine - 1][mummyColumn] == ' ' || matrix[mummyLine - 1][mummyColumn] == '_') {
                            matrix[mummyLine][mummyColumn] = onTopOf;
                            System.out.println(mummyLine +  " " +mummyColumn);
                            if (matrix[mummyLine - 2][mummyColumn] == 'M') {
                                enemyLinkedList.remove(i);
                                i--;
                                break;
                            } else if (matrix[mummyLine - 2][mummyColumn] == 'E') {
                                enemyLinkedListRemover.remove(findEnemmy(mummyLine - 2, mummyColumn));
                                matrix[mummyLine - 2][mummyColumn]='.';
                            }
                            onTopOf = matrix[mummyLine -= 2][mummyColumn];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            matrix[mummyLine][mummyColumn] = 'M';
                            if (onTopOf == 'C') {
                                verChave();
                            }
                        }
                    } else if (mummyLine < lineHero) {
                        if (matrix[mummyLine + 1][mummyColumn] == ' ' || matrix[mummyLine + 1][mummyColumn] == '_') {
                            matrix[mummyLine][mummyColumn] = onTopOf;
                            if (matrix[mummyLine + 2][mummyColumn] == 'M') {
                                enemyLinkedList.remove(i);
                                i--;
                                break;
                            } else if (matrix[mummyLine + 2][mummyColumn] == 'E') {
                                enemyLinkedListRemover.remove(findEnemmy(mummyLine + 2, mummyColumn));
                                matrix[mummyLine + 2][mummyColumn]='.';
                            }
                            onTopOf = matrix[mummyLine += 2][mummyColumn];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            matrix[mummyLine][mummyColumn] = 'M';
                            if (onTopOf == 'C') {
                                verChave();
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
            } else

            if (enemyLinkedList.get(i).getEnemyType() == 'V') {
                redMummyLine = enemyLinkedList.get(i).getLinha();
                redmummycolumn = enemyLinkedList.get(i).getColuna();
                for (int j = 0; j < 2; j++) {
                    if (redMummyLine > lineHero) {
                        if (matrix[redMummyLine - 1][redmummycolumn] == ' ' || matrix[redMummyLine - 1][redmummycolumn] == '_') {
                            matrix[redMummyLine][redmummycolumn] = onTopOf;
                            if (matrix[redMummyLine-2][redmummycolumn] == 'V') {
                                enemyLinkedList.remove(i);
                                i--;
                                break;
                            } else if (matrix[redMummyLine-2][redmummycolumn] == 'E') {
                                enemyLinkedListRemover.remove(findEnemmy(redMummyLine-2, redmummycolumn));
                                matrix[redMummyLine - 2][redmummycolumn]='.';

                            }
                            onTopOf = matrix[redMummyLine -= 2][redmummycolumn];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            System.out.println("DEPOIS DE REMOVER:  "+ onTopOf );
                            System.out.println("TAMANHO LISTA: "+enemyLinkedList.size());
                            matrix[redMummyLine][redmummycolumn] = 'V';
                            if (onTopOf == 'C') {
                                verChave();
                            }
                        } else if (redmummycolumn > columnHero) {
                            if (matrix[redMummyLine][redmummycolumn - 1] == ' ' || matrix[redMummyLine][redmummycolumn - 1] == ')') {
                                matrix[redMummyLine][redmummycolumn] = onTopOf;
                                if (matrix[redMummyLine][redmummycolumn - 2] == 'V') {
                                    enemyLinkedList.remove(i);
                                    i--;
                                    break;
                                } else if (matrix[redMummyLine][redmummycolumn - 2] == 'E') {
                                    enemyLinkedListRemover.remove(findEnemmy(redMummyLine, redmummycolumn - 2));
                                    matrix[redMummyLine][redmummycolumn - 2]='.';


                                }
                                onTopOf = matrix[redMummyLine][redmummycolumn -= 2];
                                enemyLinkedList.get(i).setOnTopOf(onTopOf);
                                matrix[redMummyLine][redmummycolumn] = 'V';
                                if (onTopOf == 'C') {
                                    verChave();
                                }
                            }
                        } else if (redmummycolumn < columnHero) {
                            if (matrix[redMummyLine][redmummycolumn + 1] == ' ' || matrix[redMummyLine][redmummycolumn + 1] == ')') {
                                matrix[redMummyLine][redmummycolumn] = onTopOf;
                                if (matrix[redMummyLine][redmummycolumn + 2] == 'V') {
                                    enemyLinkedList.remove(i);
                                    i--;
                                    break;
                                } else if (matrix[redMummyLine][redmummycolumn + 2] == 'E') {
                                    enemyLinkedListRemover.remove(findEnemmy(redMummyLine, redmummycolumn + 2));
                                    matrix[redMummyLine][redmummycolumn + 2] = '.';

                                }
                                onTopOf = matrix[redMummyLine][redmummycolumn += 2];
                                enemyLinkedList.get(i).setOnTopOf(onTopOf);
                                matrix[redMummyLine][redmummycolumn] = 'V';
                                if (onTopOf == 'C') {
                                    verChave();
                                }
                            }
                        }
                    } else if (redMummyLine < lineHero) {
                        if (matrix[redMummyLine + 1][redmummycolumn] == ' ' || matrix[redMummyLine + 1][redmummycolumn] == '_') {
                            matrix[redMummyLine][redmummycolumn] = onTopOf;
                            if (matrix[redMummyLine+2][redmummycolumn] == 'V') {
                                enemyLinkedList.remove(i);
                                i--;
                                break;
                            } else if (matrix[redMummyLine+2][redmummycolumn] == 'E') {
                                enemyLinkedListRemover.remove(findEnemmy(redMummyLine+2, redmummycolumn));
                                matrix[redMummyLine + 2][redmummycolumn] ='.';

                            }
                            onTopOf = matrix[redMummyLine += 2][redmummycolumn];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            matrix[redMummyLine][redmummycolumn] = 'V';
                            if (onTopOf == 'C') {
                                verChave();
                            }
                        } else if (redmummycolumn > columnHero) {
                            if (matrix[redMummyLine][redmummycolumn - 1] == ' ' || matrix[redMummyLine][redmummycolumn - 1] == ')') {
                                matrix[redMummyLine][redmummycolumn] = onTopOf;
                                if (matrix[redMummyLine][redmummycolumn - 2] == 'V') {
                                    enemyLinkedList.remove(i);
                                    i--;
                                    break;
                                } else if (matrix[redMummyLine][redmummycolumn - 2] == 'E') {
                                    enemyLinkedListRemover.remove(findEnemmy(redMummyLine, redmummycolumn - 2));
                                    matrix[redMummyLine][redmummycolumn - 2]='.';

                                }
                                onTopOf = matrix[redMummyLine][redmummycolumn -= 2];
                                enemyLinkedList.get(i).setOnTopOf(onTopOf);
                                matrix[redMummyLine][redmummycolumn] = 'V';
                                if (onTopOf == 'C') {
                                    verChave();
                                }
                            }
                        } else if (redmummycolumn < columnHero) {
                            if (matrix[redMummyLine][redmummycolumn + 1] == ' ' || matrix[redMummyLine][redmummycolumn + 1] == ')') {
                                matrix[redMummyLine][redmummycolumn] = onTopOf;
                                if (matrix[redMummyLine][redmummycolumn + 2] == 'V') {
                                    enemyLinkedList.remove(i);
                                    i--;
                                    break;
                                } else if (matrix[redMummyLine][redmummycolumn + 2] == 'E') {
                                    enemyLinkedListRemover.remove(findEnemmy(redMummyLine, redmummycolumn + 2));
                                    matrix[redMummyLine][redmummycolumn + 2]='.';

                                }
                                onTopOf = matrix[redMummyLine][redmummycolumn += 2];
                                enemyLinkedList.get(i).setOnTopOf(onTopOf);
                                matrix[redMummyLine][redmummycolumn] = 'V';
                                if (onTopOf == 'C') {
                                    verChave();
                                }
                            }
                        }

                    } else {
                        if (redmummycolumn > columnHero) {
                            if (matrix[redMummyLine][redmummycolumn - 1] == ' ' || matrix[redMummyLine][redmummycolumn - 1] == ')') {
                                matrix[redMummyLine][redmummycolumn] = onTopOf;
                                if (matrix[redMummyLine][redmummycolumn - 2] == 'V') {
                                    enemyLinkedList.remove(i);
                                    i--;
                                    break;
                                } else if (matrix[redMummyLine][redmummycolumn - 2] == 'E') {
                                    enemyLinkedListRemover.remove(findEnemmy(redMummyLine, redmummycolumn - 2));
                                    matrix[redMummyLine][redmummycolumn - 2]='.';

                                }
                                onTopOf = matrix[redMummyLine][redmummycolumn -= 2];
                                enemyLinkedList.get(i).setOnTopOf(onTopOf);
                                matrix[redMummyLine][redmummycolumn] = 'V';
                                if (onTopOf == 'C') {
                                    verChave();
                                }
                            }
                        } else if (redmummycolumn < columnHero) {
                            if (matrix[redMummyLine][redmummycolumn + 1] == ' ' || matrix[redMummyLine][redmummycolumn + 1] == ')') {
                                matrix[redMummyLine][redmummycolumn] = onTopOf;
                                if (matrix[redMummyLine][redmummycolumn + 2] == 'V') {
                                    enemyLinkedList.remove(i);
                                    i--;
                                    break;
                                } else if (matrix[redMummyLine][redmummycolumn + 2] == 'E') {
                                    enemyLinkedListRemover.remove(findEnemmy(redMummyLine, redmummycolumn + 2));
                                    matrix[redMummyLine][redmummycolumn + 2]='.';
                                }
                                onTopOf = matrix[redMummyLine][redmummycolumn += 2];
                                enemyLinkedList.get(i).setOnTopOf(onTopOf);
                                matrix[redMummyLine][redmummycolumn] = 'V';
                                if (onTopOf == 'C') {
                                    verChave();
                                }
                            }
                        }
                    }

                    enemyLinkedList.get(i).setLinha(redMummyLine);
                    enemyLinkedList.get(i).setColuna(redmummycolumn);
                    if (matrix[lineHero][columnHero] == 'V') {
                        lineHero = 0;
                        columnHero = 0;
                        return;

                    }

                }
            }else///-----------------------------------------------*/

           if (enemyLinkedList.get(i).getEnemyType() == 'E') {
                scorpionLine = enemyLinkedList.get(i).getLinha();
                scorpionColumn = enemyLinkedList.get(i).getColuna();
                if (scorpionColumn > columnHero) {
                    if (matrix[scorpionLine][scorpionColumn - 1] == ' ' || matrix[scorpionLine][scorpionColumn - 1] == ')') {
                        matrix[scorpionLine][scorpionColumn] = onTopOf;
                        if (matrix[scorpionLine][scorpionColumn - 2] == 'E' || matrix[scorpionLine][scorpionColumn - 2] == 'M' || matrix[scorpionLine][scorpionColumn - 2] == 'V') {
                            enemyLinkedList.remove(i);
                            System.out.println("passou1");
                            i--;
                            continue;
                        }
                        onTopOf = matrix[scorpionLine][scorpionColumn -= 2];
                        enemyLinkedList.get(i).setOnTopOf(onTopOf);
                        matrix[scorpionLine][scorpionColumn] = 'E';
                        if (onTopOf == 'C') {
                            verChave();
                        }

                    } else if (scorpionLine > lineHero) {
                        if (matrix[scorpionLine - 1][scorpionColumn] == ' ' || matrix[scorpionLine - 1][scorpionColumn] == '_') {
                            matrix[scorpionLine][scorpionColumn] = onTopOf;
                            if (matrix[scorpionLine-2][scorpionColumn] == 'E' || matrix[scorpionLine-2][scorpionColumn] == 'M'|| matrix[scorpionLine-2][scorpionColumn] == 'V') {
                                enemyLinkedList.remove(i);

                                System.out.println("passou1");
                                i--;
                                continue;
                            }
                            onTopOf = matrix[scorpionLine -= 2][scorpionColumn];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            matrix[scorpionLine][scorpionColumn] = 'E';
                            if (onTopOf == 'C') {
                                verChave();
                            }
                        }
                    } else if (scorpionLine < lineHero) {
                        if (matrix[scorpionLine + 1][scorpionColumn] == ' ' || matrix[scorpionLine + 1][scorpionColumn] == '_') {
                            matrix[scorpionLine][scorpionColumn] = onTopOf;
                            if (matrix[scorpionLine+2][scorpionColumn] == 'E' || matrix[scorpionLine+2][scorpionColumn] == 'M' || matrix[scorpionLine+2][scorpionColumn] == 'V') {
                                enemyLinkedList.remove(i);
                                System.out.println("passou1");
                                i--;
                                continue;
                            }
                            onTopOf = matrix[scorpionLine += 2][scorpionColumn];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            matrix[scorpionLine][scorpionColumn] = 'E';
                            if (onTopOf == 'C') {
                                verChave();
                            }
                        }
                    }
                } else if (scorpionColumn < columnHero) {
                    if (matrix[scorpionLine][scorpionColumn + 1] == ' ' || matrix[scorpionLine][scorpionColumn + 1] == ')') {
                        matrix[scorpionLine][scorpionColumn] = onTopOf;
                        if (matrix[scorpionLine][scorpionColumn + 2] == 'E' || matrix[scorpionLine][scorpionColumn + 2] == 'M' || matrix[scorpionLine][scorpionColumn + 2] == 'V') {
                            enemyLinkedList.remove(i);
                            System.out.println("passou1");
                            i--;
                            continue;
                        }
                        onTopOf = matrix[scorpionLine][scorpionColumn += 2];
                        enemyLinkedList.get(i).setOnTopOf(onTopOf);
                        matrix[scorpionLine][scorpionColumn] = 'E';
                        if (onTopOf == 'C') {
                            verChave();
                        }
                    } else if (scorpionLine > lineHero) {
                        if (matrix[scorpionLine - 1][scorpionColumn] == ' ' || matrix[scorpionLine - 1][scorpionColumn] == '_') {
                            matrix[scorpionLine][scorpionColumn] = onTopOf;
                            if (matrix[scorpionLine-2][scorpionColumn] == 'E' || matrix[scorpionLine-2][scorpionColumn] == 'M' || matrix[scorpionLine-2][scorpionColumn] == 'V') {
                                enemyLinkedList.remove(i);
                                System.out.println("passou1");
                                i--;
                                continue;
                            }
                            onTopOf = matrix[scorpionLine -= 2][scorpionColumn];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            matrix[scorpionLine][scorpionColumn] = 'E';
                            if (onTopOf == 'C') {
                                verChave();
                            }
                        }
                    } else {
                        if (matrix[scorpionLine + 1][scorpionColumn] == ' ' || matrix[scorpionLine + 1][scorpionColumn] == '_') {
                            matrix[scorpionLine][scorpionColumn] = onTopOf;
                            if (matrix[scorpionLine+2][scorpionColumn] == 'E' || matrix[scorpionLine+2][scorpionColumn] == 'M' || matrix[scorpionLine+2][scorpionColumn] == 'V') {
                                enemyLinkedList.remove(i);
                                System.out.println("passou1");
                                i--;
                                continue;
                            }
                            onTopOf = matrix[scorpionLine += 2][scorpionColumn];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            matrix[scorpionLine][scorpionColumn] = 'E';
                            if (onTopOf == 'C') {
                                verChave();
                            }
                        }
                    }

                } else {
                    if (scorpionLine > lineHero) {
                        if (matrix[scorpionLine - 1][scorpionColumn] == ' ' || matrix[scorpionLine - 1][scorpionColumn] == '_') {
                            matrix[scorpionLine][scorpionColumn] = onTopOf;
                            if (matrix[scorpionLine-2][scorpionColumn] == 'E' || matrix[scorpionLine-2][scorpionColumn] == 'M' || matrix[scorpionLine-2][scorpionColumn] == 'V') {
                                enemyLinkedList.remove(i);
                                System.out.println("passou1");
                                i--;
                                continue;
                            }
                            onTopOf = matrix[scorpionLine -= 2][scorpionColumn];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            matrix[scorpionLine][scorpionColumn] = 'E';
                            if (onTopOf == 'C') {
                                verChave();
                            }
                        }
                    } else if (scorpionLine < lineHero) {
                        if (matrix[scorpionLine + 1][scorpionColumn] == ' ' || matrix[scorpionLine + 1][scorpionColumn] == '_') {
                            matrix[scorpionLine][scorpionColumn] = onTopOf;
                            if (matrix[scorpionLine+2][scorpionColumn] == 'E' || matrix[scorpionLine+2][scorpionColumn] == 'M' || matrix[scorpionLine+2][scorpionColumn] == 'V') {
                                enemyLinkedList.remove(i);
                                System.out.println("passou1");
                                i--;
                                continue;
                            }
                            onTopOf = matrix[scorpionLine += 2][scorpionColumn];
                            enemyLinkedList.get(i).setOnTopOf(onTopOf);
                            matrix[scorpionLine][scorpionColumn] = 'E';
                            if (onTopOf == 'C') {
                                verChave();
                            }
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
        enemyLinkedList.retainAll(enemyLinkedListRemover);
    }












    public double computeEnemyDistanceToHero() {
        double h ;
        double r ;
        double m;
        double s;
        m=Math.abs(lineHero+mummyLine)+Math.abs(columnHero+mummyColumn);
        r=Math.abs(lineHero+redMummyLine)+Math.abs(columnHero+redmummycolumn);
        s=Math.abs(lineHero+scorpionLine)+Math.abs(columnHero+scorpionColumn);
        h=m+r+s;

        return h;
    }

    public double computeHeroDistanceToExit() {
        double h = 0;
        h= Math.abs(lineHero-lineExit)+Math.abs(columnHero-columnExit);

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
        return new MummyMazeState(matrix, enemyLinkedList, lineHero, columnHero, onTopOfH, lineExit, columnExit);


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
