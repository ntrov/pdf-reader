package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import Model.Page;
import Model.readDocument;

import java.io.IOException;

public class controller2 {

    @FXML
    private javafx.scene.control.TextArea editText;

    private readDocument doc;
    private Page page;
    //private Controller cont;
    private String text[];
    public controller2(){
        doc = new readDocument();
        page = new Page();
        //cont = new Controller();
    }

    public void initialize(){
        text = doc.getTextPdf();
        editText.setWrapText(true);
        editText.setText(text[page.getPageNo()-1]);
    }

    public void edit(){
        int p = page.getPageNo();
        text[p-1] = editText.getText();
        doc.setTextPdf(text);
        page.setPageNo(p);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/wajahat/IdeaProjects/pdfReaderProject/src/sample/sample.fxml"));
//        //cont.displayPdf();
//        Controller controller = loader.getController();
//        System.out.println(controller);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/sample.fxml"));
        try {
            Parent root = (Parent)loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller cont = loader.getController();
        cont.afterEdition();
    }
}

