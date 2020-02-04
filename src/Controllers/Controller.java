package Controllers;

import com.itextpdf.text.DocumentException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import Model.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {
    @FXML
    private Button btnOpenPdf;
    @FXML
    private Button btnPassword;
    @FXML
    private javafx.scene.control.TextField txtMeaning;
    @FXML
    private TextFlow textFlow;
    @FXML
    private Label lablTotlPages;
    @FXML
    private javafx.scene.control.TextField pageNo;
    @FXML
    private javafx.scene.control.ComboBox<String> font;
    @FXML
    private javafx.scene.control.ComboBox<String> selectPdf;
    @FXML
    private javafx.scene.control.TextField find;
    @FXML
    private javafx.scene.control.TextField replace;
    @FXML
    private javafx.scene.control.TextField addBookmark;
    @FXML
    private Button mode;
    @FXML
    private javafx.scene.control.TextField txtEmail;

    private String[] text;
    private readDocument doc;
    private Bookmark book;
    private combinePdf combine;
    private Page page;
    private Dictionary dict;
    private Model.Font fon;
    private FindAndReplace FR;
    private Mode mod;
    private writeDocument write;
    private static Stage stage1;
    private SharePDF mail;
    public static Stage stage2;
    public static String msg;
    public Controller(){
        mail = new SharePDF();
        doc = new readDocument();
        combine = new combinePdf();
        page = new Page();
        dict = new Dictionary();
        fon = new Model.Font();
        FR = new FindAndReplace();
        mod = new Mode();
        write = new writeDocument();
    }

    public void initialize(){
        String array[];
        book = new Bookmark();
        array = book.searchBookmark();
        for(int i = 0; i < array.length; i++){
            selectPdf.getItems().addAll(array[i]);
        }
        font.getItems().addAll("5","10","15","20","25","30","35","40");
    }

    public void changeMode(){
        if(doc.openFlag){
            String checkMode = mode.getText();
            mod.setMode(checkMode);
            displayPdf();
        }


    }

    public void saveBookmark() {
        if (doc.openFlag) {
            doc.setBookmark(addBookmark.getText(),Integer.toString(page.getPageNo()));
            initialize();
        }
    }


    public void selectBookmark(){
        String[] array;
        array = book.searchBookmarkPath(selectPdf.getSelectionModel().getSelectedItem());
        doc.extractData(array[0]);
        page.setPageNo(Integer.parseInt(array[1]));
        displayPdf();
    }


    public void editPDF(){
        if (doc.openFlag) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../View/write.fxml"));
                Scene scene = null;
                scene = new Scene(fxmlLoader.load(), 750, 850);
                stage1 = new Stage();
                stage1.setTitle("Edit Pdf");
                stage1.setScene(scene);
                stage1.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void refresh(){
        if(doc.openFlag) {
            displayPdf();
        }
    }

    public void displayPdf(){
        text = doc.getTextPdf();
        textFlow.getChildren().clear();
        Text text_1 = new Text(text[page.getPageNo()-1]);
        text_1.setFont(Font.font(java.awt.Font.MONOSPACED, fon.getFontSize()));
        //t.setFont(Font.font ("Verdana", 20));
        if(mod.getTextColor().equals("white")){
            textFlow.setStyle(mod.getBackGroundColor());
            text_1.setFill(Color.WHITE);
            mode.setText("DayMode");
        }
        else{
            textFlow.setStyle(mod.getBackGroundColor());
            text_1.setFill(Color.BLACK);
            mode.setText("NightMode");
        }
        textFlow.getChildren().add(text_1);
        //textFlow.setStyle();=
        lablTotlPages.setText('/' + Integer.toString(text.length));
        pageNo.setText(Integer.toString(page.getPageNo()));
        font.getSelectionModel().select(Integer.toString(fon.getFontSize()));
    }

    public void afterEdition(){
        stage1.close();
        textFlow.getChildren().clear();
        displayPdf();
        write.writePdf(text);

    }

    public void findWordDisplay() {
        if(doc.openFlag) {
            String[] wordArray;
            textFlow.getChildren().clear();
            wordArray = text[page.getPageNo() - 1].split(find.getText());
            for (int i = 0; i < wordArray.length; i++) {
                Text text_1 = new Text(wordArray[i]);
                text_1.setFont(Font.font(java.awt.Font.MONOSPACED, fon.getFontSize()));
                Text text_2 = new Text(find.getText());
                text_2.setFont(Font.font(java.awt.Font.MONOSPACED, fon.getFontSize()));
                text_2.setFill(Color.RED);
                if (i == wordArray.length - 1) {
                    textFlow.getChildren().addAll(text_1);
                } else {
                    textFlow.getChildren().addAll(text_1, text_2);
                }
                if (mod.getTextColor().equals("white")) {
                    textFlow.setStyle(mod.getBackGroundColor());
                    text_1.setFill(Color.WHITE);
                    mode.setText("DayMode");
                } else {
                    textFlow.setStyle(mod.getBackGroundColor());
                    text_1.setFill(Color.BLACK);
                    mode.setText("NightMode");
                }

                lablTotlPages.setText('/' + Integer.toString(text.length));
                pageNo.setText(Integer.toString(page.getPageNo()));
                font.getSelectionModel().select(Integer.toString(fon.getFontSize()));
            }
        }
    }


    public void chosePath() {
//        PdfReader pdfReader;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(Main.stage);
        doc.extractData(selectedFile.getAbsolutePath());
        page.setPageNo(1);
        displayPdf();
    }
    public void setMergePdf(){
        if(doc.openFlag) {
            FileChooser fileChooser1 = new FileChooser();
            fileChooser1.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File selectedFile1 = fileChooser1.showOpenDialog(Main.stage);
            System.out.println(selectedFile1.getAbsolutePath());
            combine.mergePdf(selectedFile1.getAbsolutePath());
        }
    }
    public void setFont(){
        if(doc.openFlag) {
            if (5 <= Integer.parseInt(font.getSelectionModel().getSelectedItem()) && Integer.parseInt(font.getSelectionModel().getSelectedItem()) <= 40) {
                fon.setFontSize(Integer.parseInt(font.getSelectionModel().getSelectedItem()));
                displayPdf();
            }
        }
    }

    public  void setWord(){
        if(doc.openFlag) {
            FR.setFindWord(find.getText());
            FR.setReplaceWord(replace.getText());
            FR.setAfterReplaceWord(text);
            text = FR.getAfterReplaceWord();
            write.writePdf(text);
            displayPdf();
        }
    }

    public void nextPage(){
        if(doc.openFlag) {
            if (Integer.parseInt(pageNo.getText()) < text.length) {
                page.setNextPage();
                displayPdf();
            }
        }
    }

   public void previousPage(){
       if(doc.openFlag) {
           if (Integer.parseInt(pageNo.getText()) > 1) {
               page.setPreviousPage();
               displayPdf();
           }
       }
   }

   public void jumpPage() {
       if(doc.openFlag) {
           if (Integer.parseInt(pageNo.getText()) <= text.length) {
               page.setPageNo(Integer.parseInt(pageNo.getText()));
               displayPdf();
           }
       }
   }

   public void findMeaning() {
       if(doc.openFlag) {
           ArrayList<String> meanings = new ArrayList<String>();
           dict.searchMeaning(txtMeaning.getText());
           meanings = dict.getMeaning();
           String outString = "";
           int i = 1;
           for (String meaning : meanings) {

               outString = outString + Integer.toString(i) + ") " + meaning + "\n";
               i++;
           }
           outString = outString.replaceAll(":","");
           msg = outString;
           showError();
       }
   }


    public void sendPdf() {
        String path = doc.getFilePath();
        String toEmail = txtEmail.getText();

        if(mail.verify_email_address(toEmail)) {
            mail.sendEmail(path, toEmail);
        }
        else {
            msg = "Enter Correct Email";
            showError();


            //cont.showMsg("Enter Correct Email");
        }

    }

    public void showError() {
        if (doc.openFlag) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../View/ErrorPrompt.fxml"));
                Scene scene = null;
                scene = new Scene(fxmlLoader.load());
                stage2 = new Stage();
                stage2.setTitle("Show Message");
                stage2.setScene(scene);
                stage2.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
