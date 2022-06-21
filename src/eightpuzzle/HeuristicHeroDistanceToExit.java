package eightpuzzle;

import agent.Heuristic;

public class HeuristicHeroDistanceToExit extends Heuristic<MummyMazeProblem, MummyMazeState>{

    @Override
    public double compute(MummyMazeState state){
        return state.computeHeroDistanceToExit();
    }
    
    @Override
    public String toString(){
        return "Hero Distance to Exit";
    }
}