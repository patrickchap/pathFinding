package sample.algorithm;
import javafx.scene.paint.Color;
import sample.Build.Graph;
import sample.Build.Node;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

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
        //all nodes distance is infinity but the start node
        start.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
//        Set<Node> unsettledNodes = new HashSet<>();

//        unsettledNodes.add(start);
        pq.add(start);

//        while(unsettledNodes.size() != 0) {
        while (pq.size() != 0){
            //slow down the search
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            Node curr = getLowestDistanceNode(unsettledNodes);
//            unsettledNodes.remove(curr);
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
//                    unsettledNodes.add(adjacentNode);
//                    pq.add(adjacentNode);
//                    adjacentNode.border.setFill(Color.GOLD);
//                    System.out.println(pq.size());

                }
            }
            settledNodes.add(curr);
        }
    }

    //returns the node in the unsettled set with the smallest distance
    private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
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


}
