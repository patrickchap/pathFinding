package sample.Model;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node extends StackPane implements Comparable<Node>  {

    public Rectangle border = new Rectangle(15-2, 15-2);
    private List<Node> neighbors = new ArrayList<Node>();
    private Node cameFrom;
    private  Map<String, Node> mazeNeighbors = new HashMap<>();

    public boolean visited = false;
    public boolean mazeVisited = false;
    public boolean isAStar = false;


    //used for dijkstras
    private Integer distance = Integer.MAX_VALUE;
    //used for dijkstras
    Map<Node, Integer> adjacentNodes = new HashMap<>();
    //used for dijkstras
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }


    //used for A*
    private Integer h;
    private Integer  x, y;
    private  Integer f;


    public Node(int x, int y) {

        border.setStroke(Color.GREEN);
        border.setFill(Color.TRANSPARENT);
        getChildren().addAll(border);
        setLayoutX(x);
        setLayoutY(y);
    }

    //a*
    public Integer getH(){
        return this.h;
    }

    public void setH(Integer i){
        this.h = i;
    }

    public Integer getF(){
        return this.f;
    }

    public void setF(Integer i){
        this.f = i;
    }


    public Integer getX(){
        return this.x;
    }

    public Integer getY(){
        return this.y;
    }

    public void setX(Integer i){
        this.x = i;
    }

    public void setY(Integer i){
        this.y = i;
    }




    public Map<Node, Integer> getAdjacentNodes(){
        return this.adjacentNodes;
    }

    //used for dijkstras
    public Integer getDistance(){
        return this.distance;
    }
    //used for dijkstras
    public Integer setDistance(Integer i){
        return this.distance = i;
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


    @Override
    public int compareTo(Node node) {

       if(this.isAStar){
           if (this.getF() < node.getF())
               return -1;
           if (this.getF() > node.getF())
               return 1;
           return 0;
       }else {
           if (this.getDistance() < node.getDistance())
               return -1;
           if (this.getDistance() > node.getDistance())
               return 1;
       }
        return 0;




//        return this.getDistance().compareTo(node.getDistance());
    }
}