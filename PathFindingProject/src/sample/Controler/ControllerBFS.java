package sample.Controler;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sample.Model.GenerateMaze;
import sample.Model.Graph;
import sample.Model.unweightedSearch;

public class ControllerBFS {

    @FXML
    AnchorPane ancorPane;
    Graph graph;

    public void initialize(){
        graph = new Graph(41,51);
        ancorPane.getChildren().add(graph);
    }

    public void runBFS() {
        graph.setElements();
        graph.setNeighbors();
        unweightedSearch bfs = new unweightedSearch();

        Thread t = new Thread(() -> {
            bfs.bfs(graph);
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

    public void generateMaze(){
        GenerateMaze generateMaze = new GenerateMaze(graph);
        Thread t = new Thread(() -> generateMaze.maze());
        t.start();
    }


}
