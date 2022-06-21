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
    private int lineHero;
    private int linhaArmadilha;
    private int colunaArmadilha;
    private int linhaPorta;
    private int linhaChave;
    private int colunaChave;
    private int colunaPorta;


    public MummyMazeAgent(MummyMazeState environemt) {
        super(environemt);
        initialEnvironment = (MummyMazeState) environemt.clone();
        heuristics.add(new HeuristicHeroDistanceToExit());
        heuristics.add(new HeuristicEnemyDistanceToHero());
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
