package Model;

public class FindAndReplace {
    private String findWord;
    private String replaceWord;
    private static String[] text;
    public FindAndReplace(){
        findWord = "";
        replaceWord = "";
    }

    public void setFindWord(String word){
        findWord = word;
    }

    public void setReplaceWord(String word){
        replaceWord = word;
    }

    public void setAfterReplaceWord(String text[]){
        String[] wordArray;
        String[] charBefore = {" ", "\n"};
        String[] charAfter = {" ", "\n",",",":",";"};
        for(int m = 0; m < charBefore.length; m++) {
            for (int n = 0; n < charAfter.length; n++) {
                for (int i = 0; i < text.length; i++) {
                    StringBuilder string = new StringBuilder("");
                    wordArray = text[i].split(charBefore[m] + findWord + charAfter[n]);

                    for (int j = 0; j < wordArray.length; j++) {
                        string.append(wordArray[j]);
                        if (j < wordArray.length - 1) {
                            string.append(charBefore[m] + replaceWord + charAfter[n]);
                        }
                    }
                    text[i] = string.toString();


                    //text[i] = text[i].replaceAll(charBefore[m]+findWord+charAfter[n],charBefore[m]+replaceWord+charAfter[n]);
                    //break;
                }

            }
        }
        this.text = text;
    }

    public String[] getAfterReplaceWord(){
        return text;
    }
}
