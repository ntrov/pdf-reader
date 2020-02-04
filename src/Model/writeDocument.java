package Model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class writeDocument {
  /*
    private readDocument doc;
    private FindAndReplace FR;
    public writeDocument(){
        doc = new readDocument();
        FR = new FindAndReplace();
    }


   */
    public void writePdf(String text[]) {

        String textArray[];
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File("resultAfterReplace.pdf")));
            //open
            document.open();
            for(int i = 0; i < text.length; i++){
                textArray = text[i].split("\n");
                for(int j = 0; j < textArray.length; j++){
                    Paragraph p = new Paragraph();
                    p.add(textArray[j]);
                   // p.setAlignment(Element.ALIGN_LEFT);
                    document.add(p);
                }
            }
            /*
            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);
     */
            document.close();

            System.out.println("Done");

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
