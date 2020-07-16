package sample.algorithm;

import javafx.scene.paint.Color;
import sample.Build.Graph;
import sample.Build.Node;

import java.util.LinkedList;
import java.util.Queue;

public class unweightedSearch {

    private Node start;
    private Node goal;
    private Queue<Node> queue = new LinkedList<>();

    public  void bfs(Graph graph){

        this.start = graph.start;
        this.goal = graph.goal;

        if(this.start == null || this.goal == null) {
            return;
        }
        Node start = this.start;
        Node goal = this.goal;
        start.visited = true;
        this.queue.add(start);

        while(this.queue.size() != 0) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Node curr = this.queue.remove();

            if (curr.equals(goal)) {
                Node node = goal;
                //if goal is found set camefrom nodes to pink to show the shortes path starting node
                while (!node.getCameFrom().equals(start)) {
                    node = node.getCameFrom();
                    node.border.setFill(Color.PINK);
                }
                return;
            }
            //search through nodes neighbors that have not been visited
            for (Node n : curr.getNeighbors()) {
                if (!n.visited) {
                    n.visited = true;
                    if(n != this.goal)
                        //set visited nodes blue if it is not goal node
                        n.border.setFill(Color.LIGHTBLUE);
                    n.setCameFrom(curr);
                    this.queue.add(n);
                }
            }
        }
    }
}
