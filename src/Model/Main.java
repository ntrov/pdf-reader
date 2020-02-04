package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("PDF Reader");
        stage.show();
    }


    public static void main(String[] args){
        launch(args);
    }
}