package sample.Controler;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sample.Model.GenerateMaze;
import sample.Model.Graph;
import sample.Model.WeightedSearch;

public class AStarController {
    @FXML
    AnchorPane ancorPane;
    Graph graph;

    public void initialize(){
        graph = new Graph(41,51);
        ancorPane.getChildren().add(graph);
    }

    public void runAstar(){
        graph.setElements();
        graph.setNeighbors();
        graph.setNeighborsDistance();

        WeightedSearch aStar = new WeightedSearch();

        Thread t = new Thread(() -> {
            aStar.AStar(graph);
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
