package sample.Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainSceneController {


    public void initialize(){
//        graph = new Graph(41,51);
//        ancorPane.getChildren().add(graph);
    }

    @FXML
    public void openBFSScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/View/pathFinderVisual.fxml"));
        Scene scene = new Scene(root, 1100, 800);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("BFS");
        stage.setScene(scene);
        stage.show();
    }

    public void openDijkstra(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/View/DijkstraVisual.fxml"));
        Scene scene = new Scene(root, 1100, 800);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Dijkstra");
        stage.setScene(scene);
        stage.show();
    }


    public void openAStar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/View/AstarVisual.fxml"));
        Scene scene = new Scene(root, 1100, 800);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Dijkstra");
        stage.setScene(scene);
        stage.show();
    }



}
