package searchmethods;

import agent.State;
import utils.NodeLinkedList;

public class BreadthFirstSearch extends GraphSearch<NodeLinkedList> {

    public BreadthFirstSearch() {
        frontier = new NodeLinkedList();
    }

    @Override
    public void addSuccessorToFrontier(State successor, Node parent) {
        System.out.println("Fiz o breadth fist search add successor to frontier");
        if (!(frontier.containsState(successor) || explored.contains(successor))) {
            frontier.addLast(new Node(successor, parent));
        }
    }

    @Override
    public String toString() {
        return "Breadth first search";
    }
}