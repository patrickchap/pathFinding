package sample.BFSScene;


import javafx.scene.paint.Color;

import java.util.*;

public class GenerateMaze {

    private Stack<Node> nodeStack = new Stack<>();
    private Graph graph;
    private Node[][] twoDimArr;

    public GenerateMaze(Graph graph){
        this.graph = graph;
        this.twoDimArr = graph.getNodeArr();
    }


    private void setGraphToBlack(){

        for(int i = 1; i < this.graph.getRows()-1; i++){
            for(int j = 1; j < this.graph.getCols()-1; j++){
                Node node = twoDimArr[i][j];
                node.border.setFill(Color.TRANSPARENT);
            }
        }

        for(int i = 0; i < this.graph.getRows(); i+=2){
            for(int j = 0; j < this.graph.getCols(); j++){
                Node node = twoDimArr[i][j];
                node.border.setFill(Color.BLACK);
            }
        }
        for(int i = 0; i < this.graph.getRows(); i++){
            for(int j = 0; j < this.graph.getCols(); j+=2){
                Node node = twoDimArr[i][j];
                node.border.setFill(Color.BLACK);
            }
        }

    }

    private void setMazeNeighbors(){

        for(int i = 1; i < this.graph.getRows()-1; i+=2){
            for(int j = 1; j < this.graph.getCols() -1; j+=2){
                Node node = twoDimArr[i][j];

                    //north
                    if(i - 2 > 0)
                        node.addMazeNeighbor("North",twoDimArr[i - 2][j]);
                    //south
                    if(i + 2 != this.graph.getRows())
                        node.addMazeNeighbor("South", twoDimArr[i + 2][j]);
                    //west
                    if(j + 2 != this.graph.getCols())
                        node.addMazeNeighbor("West", twoDimArr[i][j+2]);
                    //east
                    if(j - 2 > 0)
                        node.addMazeNeighbor("East", twoDimArr[i][j-2]);
            }
        }
    }

    private void removeWall(Node node, String string){

        for(int i = 1; i < this.graph.getRows()-1; i+=2){
            for(int j = 1; j < this.graph.getCols() -1; j+=2) {
                if(node == twoDimArr[i][j]){
                    if(string == "South")
                        twoDimArr[i-1][j].border.setFill(Color.TRANSPARENT);
                    if(string == "North")
                        twoDimArr[i+1][j].border.setFill(Color.TRANSPARENT);
                    if(string == "West")
                        twoDimArr[i][j-1].border.setFill(Color.TRANSPARENT);
                    if(string == "East")
                        twoDimArr[i][j+1].border.setFill(Color.TRANSPARENT);
                }
            }
        }
    }


    public void maze(){
        this.setGraphToBlack();

        this.setMazeNeighbors();
        Random rand = new Random();
        Node startNode = this.twoDimArr[1][1];
        nodeStack.push(startNode);
        startNode.mazeVisited = true;
        Map<String, Node> currentNeighbors;

        while(nodeStack.size() != 0 ){
            try {
                Thread.currentThread().sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentNeighbors = startNode.getMazeNeighbors();
            if(currentNeighbors.size() != 0) {
                int index = Math.abs(rand.nextInt() % currentNeighbors.size());
                Set<String> keys = currentNeighbors.keySet();
                ArrayList<String> directions = new ArrayList<>();

                for(String key : keys){
                    directions.add(key);
                }
                String indexString = directions.get(index);
                startNode = currentNeighbors.get(indexString);

                if (startNode.mazeVisited == false) {

                    startNode.mazeVisited = true;
                    this.removeWall(startNode, indexString);
                    nodeStack.push(startNode);

                } else {
                    currentNeighbors.remove(indexString,startNode);
                }
            }else {
                startNode = nodeStack.pop();
            }
        }
    }


}
