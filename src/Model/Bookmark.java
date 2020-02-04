package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bookmark {
    private java.sql.Statement s;
    private int size;
    private static String[] bookmarkArray;
    private connection con;
    private Connection cn;
    private Page page;

    public Bookmark(){
        con = new connection();
        page = new Page();
    }


    public void addBookmark(String pdfName, String pdfPath, String page){
        cn = con.connect();

        String query = "INSERT INTO bookmark VALUES('"+pdfName+"','"+pdfPath+"', '"+page+"')";
        try {
            s = cn.createStatement();
            s.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public String[] searchBookmark() {
        cn = con.connect();
        String queryCount = "Select count(*) as rowcount from bookmark";
        String query = "Select * from bookmark";
        ResultSet res;
        size = 0;
        try {
            s = cn.createStatement();
            res = s.executeQuery(queryCount);
            if(res.next()) {
                size = res.getInt("rowcount");
            }
            bookmarkArray = new String[size];
            res = s.executeQuery(query);
            int count = 0;
            while(res.next()) {
                bookmarkArray[count] = res.getString("pdfName");
                count++;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bookmarkArray;
    }

    public String[] searchBookmarkPath(String name) {
        cn = con.connect();
        String[] path = new String[2];
        int pg;
        String query = "Select pdfPath, pdfPage from bookmark where(pdfName = '"+name+"')";
        ResultSet res;
        try {
            s = cn.createStatement();
            res = s.executeQuery(query);
            if(res.next()) {
                path[0] = res.getString("pdfPath");
                path[1] = res.getString("pdfPage");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return path;
    }

}
