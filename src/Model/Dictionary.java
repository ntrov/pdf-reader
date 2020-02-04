package Model;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.ArrayList;


public class Dictionary {
    private ArrayList<String> meanings;

    public void searchMeaning(String word) {
        String head = new String("http://www.dictionaryapi.com/api/v1/references/collegiate/xml/");
//        String word = new String("banal");
        String apiKey = new String("?key=cf41b631-fa04-4ccc-872f-ed1372bec0b1"); //My API Key for Merriam webster
        String finalURL = head.trim() + word.trim()+ apiKey.trim();
        meanings = new ArrayList<String>();
        try
        {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(finalURL);

            doc.getDocumentElement().normalize();

            NodeList items = doc.getElementsByTagName("entry");
            for (int i = 0; i < items.getLength(); i++)
            {
                Node n = items.item(i);

                if (n.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element e = (Element) n;
                NodeList titleList = e.getElementsByTagName("dt");
                for (int j = 0; j < titleList.getLength(); j++){
                    Node dt = titleList.item(j);
                    if (dt.getNodeType() != Node.ELEMENT_NODE)
                        continue;
                    Element titleElem = (Element) titleList.item(j);
                    Node titleNode = titleElem.getChildNodes().item(0);
                    meanings.add(titleNode.getNodeValue());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getMeaning(){
        return meanings;
    }
}
