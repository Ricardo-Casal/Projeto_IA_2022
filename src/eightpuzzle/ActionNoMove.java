package eightpuzzle;

import agent.Action;

public class ActionNoMove extends Action<MummyMazeState> {

    public ActionNoMove(){
        super(1);
    }

    @Override
    public void execute(MummyMazeState state){
        state.wontMove();
        state.setAction(this);
    }

    @Override
    public boolean isValid(MummyMazeState state){
        return true;
    }
}
