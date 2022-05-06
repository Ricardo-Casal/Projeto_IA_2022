package eightpuzzle;

import agent.Agent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MummyMazeAgent extends Agent<MummyMazeState>{
    
    protected MummyMazeState initialEnvironment;
    
    public MummyMazeAgent(MummyMazeState environemt) {
        super(environemt);
        initialEnvironment = (MummyMazeState) environemt.clone();
        heuristics.add(new HeuristicTileDistance());
        heuristics.add(new HeuristicTilesOutOfPlace());
        heuristic = heuristics.get(0);
    }
            
    public MummyMazeState resetEnvironment(){
        environment = (MummyMazeState) initialEnvironment.clone();
        return environment;
    }
                 
    public MummyMazeState readInitialStateFromFile(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        char[][] charArray = new char[13][13];
        int i=0;
        while (scanner.hasNextLine()) {
            charArray[i] = scanner.nextLine().toCharArray();
            i++;
        }
        initialEnvironment = new MummyMazeState(charArray);
        resetEnvironment();
        return environment;
    }
}
