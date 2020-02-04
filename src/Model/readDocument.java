package Model;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class readDocument {
    private static String filePath;
    private static String[] text;
    private Bookmark book;

    public static boolean openFlag;
    public readDocument(){
       // filePath = "";
        book = new Bookmark();
    }
    public void extractData(String filePath) {
        PdfReader pdfReader;
        this.filePath = filePath;
        try {
            //Create PdfReader instance.
            pdfReader = new PdfReader(filePath);
            //Get the number of pages in pdf.
            int pages = pdfReader.getNumberOfPages();
            text = new String[pages];
            //Iterate the pdf through pages.
            for(int i=0; i< pages; i++) {
                //Extract the page content using PdfTextExtractor.
                text[i] = PdfTextExtractor.getTextFromPage(pdfReader, i+1);
            }
            openFlag = true;
            removeEndline();
            //Close the PdfReader.
            pdfReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBookmark(String name, String  page){
        book.addBookmark(name, filePath,page);
    }


    public String getFilePath(){
        return filePath;
    }

    public void setTextPdf(String text[]){
        this.text = text;
    }


    public void removeEndline(){
        String rtext[];

        for(int i = 0; i < text.length; i++){
            int maxLength = 0;
            StringBuilder string = new StringBuilder("");
            rtext = text[i].split("\n");
            for (int j = 0; j < rtext.length; j++) {
                if(rtext[j].length() > maxLength){
                    maxLength = rtext[j].length();
                }
            }
            for(int k = 0; k < rtext.length; k++){
                if (rtext[k].length() <= maxLength && rtext[k].length() >= maxLength - 15) {
                    string.append(rtext[k]);
                }
                else {
                    string.append(rtext[k]+"\n");
                }
            }
            text[i] = string.toString();
        }
    }
    public String[] getTextPdf(){
        return this.text;
    }
}
