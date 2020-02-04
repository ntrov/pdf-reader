package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class ErrorPrompt {
    @FXML
    private TextFlow errorMsg;

    private Controller cont;


    public  void initialize(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/sample.fxml"));
        try {
            Parent root = (Parent)loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cont = loader.getController();
        errorMsg.getChildren().clear();
        System.out.println(cont.msg);
        Text text_1 = new Text(cont.msg);
        errorMsg.getChildren().add(text_1);

    }
    public void closeWin() {
        cont.stage2.close();
    }
}
