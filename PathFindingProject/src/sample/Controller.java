package sample;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    AnchorPane ancorPane;
    @FXML
    MenuButton menueBtn;

    Graph graph;

    public void show(){
        graph = new Graph(41,51);
        ancorPane.getChildren().add(graph);
    }

    public void runBFS() {
        graph.setElements();
        graph.setNeighbors();
        Thread t = new Thread(() -> {
            graph.bfs();
        });
        t.start();
    }

    public void setStartNode() {
        graph.setOnMousePressed(e ->   graph.setStartingNode());
        menueBtn.setText("Set Starting Node");
    }

    public void setGoalNode() {
        graph.setOnMousePressed(e ->   graph.setGoalNode());
        menueBtn.setText("Set Goal Node");
    }

    public void setWalls(){
        graph.setOnMousePressed(e ->   graph.setWalls());
        menueBtn.setText("Set Walls");
    }

    public void generateMaze(){
        GenerateMaze generateMaze = new GenerateMaze(graph);
        generateMaze.setGraphToBlack();
        Thread t = new Thread(() -> {
            generateMaze.maze();
        });
        t.start();
    }


}
