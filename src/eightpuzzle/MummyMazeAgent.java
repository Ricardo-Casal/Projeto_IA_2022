package eightpuzzle;

import agent.Agent;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class MummyMazeAgent extends Agent<MummyMazeState> {

    protected MummyMazeState initialEnvironment;
    private int columnHero;
    private int lineExit;
    private int columnExit;
    private Enemy enemy;
    private LinkedList<Enemy> enemyLinkedList;
    private Hero armadilha;
    private LinkedList<Hero> armadilhaLinkedList;
    private int linhaArmadilha;
    private int colunaArmadilha;
    private int linhaChave;
    private int colunaChave;
    private int linhaPorta;
    private int colunaPorta;

    private int lineHero;

    public int getColumnHero() {
        return columnHero;
    }

    public int getLineExit() {
        return lineExit;
    }

    public int getColumnExit() {
        return columnExit;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public LinkedList<Enemy> getEnemyLinkedList() {
        return enemyLinkedList;
    }

    public int getLinhaArmadilha() {
        return linhaArmadilha;
    }

    public int getColunaArmadilha() {
        return colunaArmadilha;
    }

    public int getLineHero() {
        return lineHero;
    }

    public MummyMazeAgent(MummyMazeState environemt) {
        super(environemt);
        initialEnvironment = (MummyMazeState) environemt.clone();
        heuristics.add(new HeuristicTileDistance());
        heuristics.add(new HeuristicTilesOutOfPlace());
        heuristic = heuristics.get(0);
    }

    public MummyMazeState resetEnvironment() {
        environment = (MummyMazeState) initialEnvironment.clone();
        return environment;
    }


    public MummyMazeState readInitialStateFromFile(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        char[][] charArray = new char[13][13];
        enemyLinkedList = new LinkedList<>();
        armadilhaLinkedList = new LinkedList<>();
        int i = 0;
        while (scanner.hasNextLine()) {
            charArray[i] = scanner.nextLine().toCharArray();
            i++;
        }

        for (int j = 0; j < charArray.length; j++) {
            for (int k = 0; k < charArray.length; k++) {
                if (charArray[j][k] == 'H') {
                    lineHero = j;
                    columnHero = k;
                }
                if (charArray[j][k] == 'S') {
                    lineExit = j;
                    columnExit = k;
                }
                if (charArray[j][k] == 'E') {

                    enemy = new Enemy(j, k, 'E');
                    enemyLinkedList.add(enemy);

                }
                if (charArray[j][k] == 'V') {

                    enemy = new Enemy(j, k, 'V');
                    enemyLinkedList.add(enemy);

                }
                if (charArray[j][k] == 'M') {
                    enemy = new Enemy(j, k, 'M');
                    enemyLinkedList.add(enemy);
                }
                if (charArray[j][k] == 'A') {
                    linhaArmadilha = j;
                    colunaArmadilha = k;
                    armadilha = new Hero(j,k);
                    armadilhaLinkedList.add(armadilha);


                }
                if (charArray[j][k] == 'C') {
                    linhaChave = j;
                    colunaChave = k;
                }
                if (charArray[j][k] == '=') {
                    linhaPorta = j;
                    colunaPorta = k;
                }
            }
        }


        initialEnvironment = new MummyMazeState(charArray, enemyLinkedList, lineHero, columnHero,'.', lineExit, columnExit);
        resetEnvironment();
        return environment;
    }
}
