package eightpuzzle;

import agent.Heuristic;

public class HeuristicEnemyDistanceToHero extends Heuristic<MummyMazeProblem, MummyMazeState> {

    @Override
    public double compute(MummyMazeState state) {
        return state.computeEnemyDistanceToHero();
    }
    
    @Override
    public String toString(){
        return "Hero Distance to Enemy";
    }    
}