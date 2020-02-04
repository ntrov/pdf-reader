package Model;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.util.ArrayList;

public class combinePdf {
    private readDocument doc;

    public  combinePdf(){
        doc = new readDocument();
    }

    public void mergePdf(String newFilePath){
        ArrayList<InputStream> list = new ArrayList<InputStream>();
        try {
            // Source pdfs
            list.add(new FileInputStream(new File(doc.getFilePath())));
            list.add(new FileInputStream(new File(newFilePath)));

            // Resulting pdf
            OutputStream out = new FileOutputStream(new File("MergeResult.pdf"));
            doMerge(list, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doMerge(ArrayList<InputStream> list, OutputStream outputStream) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();

        for (InputStream in : list) {
            PdfReader reader = new PdfReader(in);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                document.newPage();
                //import the page from source pdf
                PdfImportedPage page = writer.getImportedPage(reader, i);
                //add the page to the destination pdf
                cb.addTemplate(page, 0, 0);
            }
        }
        outputStream.flush();
        document.close();
        outputStream.close();
    }
}
