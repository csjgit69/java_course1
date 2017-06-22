package course1;
import edu.duke.*;


//http://www.dukelearntoprogram.com/course2/data/manylinks.html 

public class UrlParse {

    public boolean urlHit(String stringA) {
        boolean result;
        int len          = stringA.length();
        int startIdx;
        int endIdx;
        int endIdx2;
//        int startIdx1    = stringB.indexOf(stringA);
//        int endIdx1      = startIdx1 + len;
//        int startIdx2    = stringB.indexOf(stringA, endIdx1);
//      boolean validG   = (startIdx - endIdx)%3 == 0;
        int tCnt         = 0;
        String lWord;
//        URLResource inURL = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        URLResource inURL = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html"); 
        for (String word : inURL.words()) {
            // process each word in turn
            tCnt++;
            lWord = word.toLowerCase();
            if (lWord.contains("youtube.com")) {
                startIdx = lWord.lastIndexOf("\"",lWord.indexOf("youtube.com"));
                endIdx   = lWord.indexOf("\"", startIdx+1);
                endIdx2  = lWord.lastIndexOf("\"");
                System.out.println(word.substring(startIdx+1, endIdx));
                System.out.println("startIdx: " + startIdx + " endIdx:  " + endIdx + " endIdx2:  " + endIdx2);
//                System.out.println("       "        + " lword: " + lWord);
            }
//            if (tCnt == 120) {
//                return true;
 //           }
        }
        
        
//        if (startIdx1 >= 0 & startIdx2 >= 0) {
//            result  = true;
//        } else {
//            result  = false;
//        }
        return false;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        UrlParse test = new UrlParse();
        test.urlHit("youtube.com");
    }

}
