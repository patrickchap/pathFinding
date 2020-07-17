package sample.Model;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.lang.*;

public class WeightedSearch {

    private Node start;
    private Node goal;
    public PriorityQueue<Node> pq = new PriorityQueue<>(2000);

    public void dijkstra(Graph graph) {

        this.start = graph.start;
        this.goal = graph.goal;

        if(this.start == null || this.goal == null) {
            return;
        }
        //all nodes distance are infinity
        //set Starting node distance to 0
        start.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        pq.add(start);

        while (pq.size() != 0){
            //slow down the search
            try {
                Thread.currentThread().sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Node curr = pq.remove();

            //check to see if the goal has been found
            if (curr.equals(goal)) {
                Node node = goal;
                //if goal is found set camefrom nodes to pink to show the shortes path starting node
                while (!node.getCameFrom().equals(start)) {
                    node = node.getCameFrom();
                    node.border.setFill(Color.PINK);
                }
                this.goal.border.setFill(Color.GREEN);
                return;
            }

            //search through adjacent nodes
            for (Map.Entry<Node, Integer> m : curr.getAdjacentNodes().entrySet()) {
                Node adjacentNode = m.getKey();
                if(adjacentNode.border.getFill() != Color.BROWN)
                    adjacentNode.border.setFill(Color.LIGHTBLUE);
                Integer edgeWeight = m.getValue();
                //If the adjacent node has not been settled calc min distance and add to unsettled
                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeight, curr, adjacentNode);
                }
            }
            settledNodes.add(curr);
        }
    }

    //sets the sourceNode distance and path if sD + sE < eD
    private void CalculateMinimumDistance(Node evaluationNode,
                                                 Integer edgeWeigh, Node sourceNode, Node adjacentNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            adjacentNode.setCameFrom(sourceNode);
            pq.add(adjacentNode);
        }
    }


    public void AStar(Graph graph) {

        this.start = graph.start;
        this.goal = graph.goal;


        if(this.start == null || this.goal == null) {
            return;
        }
        //all nodes distance are infinity
        //set Starting node distance to 0
        start.setDistance(0);
        start.setH(0);
        start.setF(0);
        start.isAStar = true;
        Set<Node> settledNodes = new HashSet<>();
        pq.add(start);

        while (pq.size() != 0){
            //slow down the search
            try {
                Thread.currentThread().sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Node curr = pq.remove();

            //check to see if the goal has been found
            if (curr.equals(goal)) {
                Node node = goal;
                //if goal is found set camefrom nodes to pink to show the shortes path starting node
                while (!node.getCameFrom().equals(start)) {
                    node = node.getCameFrom();
                    node.border.setFill(Color.PINK);
                }
                this.goal.border.setFill(Color.GREEN);
                return;
            }

            //search through adjacent nodes

            for (Map.Entry<Node, Integer> m : curr.getAdjacentNodes().entrySet()) {
                m.getKey().isAStar = true;
                Node adjacentNode = m.getKey();
                m.getKey().setH(euclideanDistance(m.getKey(),this.goal));
                m.getKey().setF(m.getKey().getH() + m.getValue());


                if(adjacentNode.border.getFill() != Color.BROWN)
                    adjacentNode.border.setFill(Color.LIGHTBLUE);

//                m.setValue(m.getValue() + euclideanDistance(m.getKey(), goal));

                Integer edgeWeight = m.getValue(); // + euclideanDistance(m.getKey(), this.goal);
                //If the adjacent node has not been settled calc min distance and add to unsettled
                System.out.println(curr.getF() + " " + m.getKey().getF());
                if (!settledNodes.contains(adjacentNode) ) {
                    CalculateMinimumDistanceAStar(adjacentNode, edgeWeight, curr);
                }
            }
            settledNodes.add(curr);
        }

    }

    public static Integer euclideanDistance(Node node, Node goal){
        return (int) Math.sqrt ( Math.pow((node.getX() - goal.getX()),2) +
                Math.pow(node.getY() - goal.getY(),2) );

    }

    private void CalculateMinimumDistanceAStar(Node evaluationNode,
                                          Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();


        Integer h = euclideanDistance(evaluationNode, this.goal);

        if (sourceDistance + edgeWeigh < (evaluationNode.getDistance())) {

            evaluationNode.setDistance(sourceDistance + edgeWeigh + h);
            evaluationNode.setCameFrom(sourceNode);
            pq.add(evaluationNode);
        }
    }




}
