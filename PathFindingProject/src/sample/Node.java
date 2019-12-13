package sample;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node extends StackPane {


    public Rectangle border = new Rectangle(15-2, 15-2);
    private List<Node> neighbors = new ArrayList<Node>();
    private Node cameFrom;

    private  Map<String, Node> mazeNeighbors = new HashMap<>();

    public boolean visited = false;
    public boolean mazeVisited = false;


    public Node(int x, int y) {

        border.setStroke(Color.GREEN);
        border.setFill(Color.TRANSPARENT);
        getChildren().addAll(border);
        setLayoutX(x);
        setLayoutY(y);
    }

    /**
     *
     * @param node
     * 		Node to add the neighbor List
     *
     */
    public void addNeighbor(Node node) {
        this.neighbors.add(node);
    }

    public void addMazeNeighbor(String direction, Node node){
        this.mazeNeighbors.put(direction,node);
    }

    /**
     *
     * @return
     * 		List of neighbor Nodes
     */
    public List<Node> getNeighbors() {
        return this.neighbors;
    }

    public Map<String, Node> getMazeNeighbors(){
        return this.mazeNeighbors;
    }

    /**
     *
     * @param node
     * 		set previous Node in path
     */
    public void setCameFrom (Node node) {
        this.cameFrom = node;
    }


    /**
     *
     * @return
     * 		get previous Node in path
     */
    public Node getCameFrom () {
        return this.cameFrom;
    }

}