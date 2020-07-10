package sample.BFSScene;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.*;


public class Graph extends Pane {

    private Node[][] twoDimNodeArray;
    private int rows;
    private int cols;
    private Queue<Node> queue = new LinkedList<>();
    public Node start;
    public Node goal;

    public Graph(int rows, int cols) {

        setLayoutX(14);
        setLayoutY(14);
        setPrefHeight(525);
        setPrefWidth(410);
        twoDimNodeArray = new Node[rows][cols];
        this.rows = rows;
        this.cols = cols;

        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j< this.cols; j++){
                Node node = new Node((j*15), (i*15));
                if(i == 0 || i == this.rows -1 || j == 0 || j == this.cols-1)
                    node.border.setFill(Color.BLACK);
                twoDimNodeArray[i][j] = node;
                this.add(node);
            }
        }
    }

    public void add(Node node){
        getChildren().add(node);
    }


    public void setElements(){
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j< this.cols; j++){
                if(twoDimNodeArray[i][j].border.getFill() == Color.GREEN)
                    this.goal = twoDimNodeArray[i][j];
                if(twoDimNodeArray[i][j].border.getFill() == Color.RED)
                    this.start = twoDimNodeArray[i][j];
                if(twoDimNodeArray[i][j].border.getFill() == Color.BLACK)
                    twoDimNodeArray[i][j] = null;
            }
        }
    }

    /**
     * Method to set the neighbors for each node in the input file
     *
     */
    public void setNeighbors() {

        //loop through the graph except the border
        for(int i = 1; i < this.rows -1; i++) {
            for(int j = 1; j < this.cols -1; j++) {
                Node currentNode = twoDimNodeArray[i][j];
                //create a variable for every possible neighbor to currentNode
                Node nodeAbove = twoDimNodeArray[i - 1][j];
                Node nodeBelow = twoDimNodeArray[i + 1][j];
                Node nodeRight = twoDimNodeArray[i][j+1];
                Node nodeLeft = twoDimNodeArray[i][j-1];

                if(currentNode != null) {
                    if(nodeAbove != null)
                        twoDimNodeArray[i][j].addNeighbor(twoDimNodeArray[i - 1][j]);
                    if(nodeBelow != null)
                        twoDimNodeArray[i][j].addNeighbor(twoDimNodeArray[i + 1][j]);
                    if(nodeRight != null)
                        twoDimNodeArray[i][j].addNeighbor(twoDimNodeArray[i][j+1]);
                    if(nodeLeft != null)
                        twoDimNodeArray[i][j].addNeighbor(twoDimNodeArray[i][j-1]);
                }
            }
        }
    }


    //used for dijkstras
    public void setNeighborsDistance(){
        for(int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Node currentNode = twoDimNodeArray[i][j];
                if (currentNode != null){
                    for (Node n : currentNode.getNeighbors()) {
                        if (n.border.getFill() == Color.BROWN) {
                            currentNode.addDestination(n, 10);
//                            System.out.println("Wall hit");
                        }
                        if (n.border.getFill() == Color.TRANSPARENT) {
                            currentNode.addDestination(n, 1);
                        }
                        if (n.border.getFill() == Color.GREEN) {
                            currentNode.addDestination(n, 1);

                        }

                    }
                }
            }
        }
    }



    /**
     *
     */
    public void setStartingNode(){
        int counter = 0;
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j< this.cols; j++){
                Node node = twoDimNodeArray[i][j];
                if(node.border.getFill() == Color.RED && counter == 1) {
                    node.border.setFill(Color.TRANSPARENT);
                }else{
                    node.setOnMouseReleased(e -> node.border.setFill(Color.RED));
                    counter = 1;
                }
            }
        }
    }

    /**
     *
     */
    public void setGoalNode(){
        int counter = 0;
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j< this.cols; j++){
                Node node = twoDimNodeArray[i][j];
                if(node.border.getFill() == Color.GREEN && counter == 1) {
                    node.border.setFill(Color.TRANSPARENT);
                }else{
                    node.setOnMouseReleased(e -> node.border.setFill(Color.GREEN));
                    counter = 1;
                }
            }
        }
    }

    /**
     *
     */
    public void setWalls() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Node node = twoDimNodeArray[i][j];
                    node.addEventFilter(MouseEvent.DRAG_DETECTED, e -> {
                        startFullDrag();
                        node.border.setFill(Color.BLACK);
                    });
                    node.setOnMouseDragOver(e -> {
                        node.border.setFill(Color.BLACK);
                    });
                    node.setOnMouseReleased(e ->  node.border.setFill(Color.BLACK));
            }
        }
    }



    public void setWeightedWall() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Node node = twoDimNodeArray[i][j];
                node.addEventFilter(MouseEvent.DRAG_DETECTED, e -> {
                    startFullDrag();
                    node.border.setFill(Color.BROWN);
                });
                node.setOnMouseDragOver(e -> {
                    node.border.setFill(Color.BROWN);
                });
                node.setOnMouseReleased(e ->  node.border.setFill(Color.BROWN));
            }
        }
    }


    //weight = brown




    /**
     *
     */

    public void removeWalls(){
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Node node = twoDimNodeArray[i][j];
                node.addEventFilter(MouseEvent.DRAG_DETECTED, e -> {
                    startFullDrag();
                    node.border.setFill(Color.TRANSPARENT);
                });
                node.setOnMouseDragOver(e -> {
                    node.border.setFill(Color.TRANSPARENT);
                });
                node.setOnMouseReleased(e ->  node.border.setFill(Color.TRANSPARENT));
            }
        }

    }

    public int getRows(){
        return this.rows;
    }

    public int getCols(){
        return this.cols;
    }

    public Node[][] getNodeArr(){
        return this.twoDimNodeArray;
    }

}





