package sample.BFSScene;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sample.algorithm.unweightedSearch;

public class Controller {

    @FXML
    AnchorPane ancorPane;
//    @FXML
//    MenuButton menueBtn;

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
//        menueBtn.setText("Set Starting Node");
    }

    public void setGoalNode() {
        graph.setOnMousePressed(e ->   graph.setGoalNode());
//        menueBtn.setText("Set Goal Node");
    }

    public void setWalls(){
        graph.setOnMousePressed(e ->   graph.setWalls());
//        menueBtn.setText("Set Walls");
    }

    public void removeWalls(){
        graph.setOnMousePressed((e -> graph.removeWalls()));
//        menueBtn.setText("Remove Walls");
    }

    public void generateMaze(){
        GenerateMaze generateMaze = new GenerateMaze(graph);
        Thread t = new Thread(() -> {
            generateMaze.maze();
        });
        t.start();
    }


}
