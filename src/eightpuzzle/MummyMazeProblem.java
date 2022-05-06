package eightpuzzle;

import agent.Action;
import agent.Problem;

import java.util.LinkedList;
import java.util.List;

public class MummyMazeProblem extends Problem<MummyMazeState> {

    protected List<Action> actions;

    public MummyMazeProblem(MummyMazeState initialState) {
        super(initialState);
        actions = new LinkedList<Action>() {{
            add(new ActionDown());
            add(new ActionUp());
            add(new ActionRight());
            add(new ActionLeft());
            add(new ActionNoMove());
        }};
    }

    @Override
    public List<Action<MummyMazeState>> getActions(MummyMazeState state) {
        List<Action<MummyMazeState>> possibleActions = new LinkedList<>();
// if para ver se tem heroi no estado se nao tiver nao da retunr a action vazio
   //     System.out.println(state.getColumnHero() +" " + state.getLineHero());
        if (state.getColumnHero()==0 && state.getLineHero()==0) {
            System.out.println("paaaaaaaaa");
            return possibleActions;
        }

        for (Action action : actions) {
            if (action.isValid(state)) {
                possibleActions.add(action);
            }
        }
        return possibleActions;
    }

    @Override
    public MummyMazeState getSuccessor(MummyMazeState state, Action action){
        MummyMazeState successor = state.clone();

        action.execute(successor);
        return successor;
    }

    @Override
    public boolean isGoal(MummyMazeState state) {
        if (state.getLineExit()==state.getLineHero() && state.getColumnExit()==state.getColumnHero())
            return true;
        return false;

    }

    @Override
    public double computePathCost(List<Action> path) {
        return path.size();
    }

}
