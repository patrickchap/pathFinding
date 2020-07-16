package sample.DijstrasScene;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sample.Build.GenerateMaze;
import sample.Build.Graph;
import sample.algorithm.WeightedSearch;

public class DijkstraController {

    @FXML
    AnchorPane dijAnchorPane;
    Graph graph;

    @FXML
    public void initialize(){
        graph = new Graph(41,51);
        dijAnchorPane.getChildren().add(graph);
    }


    public void runDijkstras(){
        graph.setElements();
        graph.setNeighbors();
        graph.setNeighborsDistance();

        WeightedSearch dij = new WeightedSearch();

        Thread t = new Thread(() -> {
            dij.dijkstra(graph);
        });
        t.start();


    }




    public void setStartNode() {
        graph.setOnMousePressed(e ->   graph.setStartingNode());
    }

    public void setGoalNode() {
        graph.setOnMousePressed(e ->   graph.setGoalNode());
    }

    public void setWalls(){
        graph.setOnMousePressed(e ->   graph.setWalls());
    }

    public void removeWalls(){
        graph.setOnMousePressed((e -> graph.removeWalls()));
    }


    public void setWeight(){
        graph.setOnMousePressed((e -> graph.setWeightedWall()));
    }

    public void generateMaze(){
        GenerateMaze generateMaze = new GenerateMaze(graph);
        Thread t = new Thread(() -> {
            generateMaze.maze();
        });
        t.start();
    }



}
