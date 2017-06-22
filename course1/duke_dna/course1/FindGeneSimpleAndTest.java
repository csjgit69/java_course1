package course1;
import edu.duke.*;

public class FindGeneSimpleAndTest {
	public String findGeneSimple(String dna) {
		String result = "";
		int startIdx = dna.indexOf("ATG");
		int endIdx 	 = dna.indexOf("TAA", startIdx+3);
		boolean validG	 = (startIdx - endIdx)%3 == 0;
		System.out.println("startI: " + startIdx + " endI: " + endIdx + " is valid: " + validG);
		if (startIdx >= 0 & endIdx >= 0 & validG) {
			result		 = dna.substring(startIdx, endIdx+3);
		} else {
			result	= "No Gene";
		}
		return result;
	}
	public String findGeneSimple2(String dna, String startCodon, String endCodon) {
		String result = "";
		int startIdx = dna.indexOf(startCodon);
		int endIdx 	 = dna.indexOf(endCodon, startIdx+3);
		boolean validG	 = (startIdx - endIdx)%3 == 0;
		System.out.println("startI: " + startIdx + " endI: " + endIdx + " is valid: " + validG);
		if (startIdx >= 0 & endIdx >= 0 & validG) {
	
			result		 = dna.substring(startIdx, endIdx+3);
		} else {
			result	= "No Gene";
		}
		return result;
	}
	public boolean twoOccurrences(String stringA, String stringB) {
		boolean result;
		int len		  = stringA.length();
		int startIdx1 = stringB.indexOf(stringA);
		int endIdx1	  = startIdx1 + len;
		int startIdx2 = stringB.indexOf(stringA, endIdx1);
//		boolean validG	 = (startIdx - endIdx)%3 == 0;
		System.out.println("start1: " + startIdx1 + " endIdx1: " + endIdx1 + " startIdx2: " + startIdx2);
		if (startIdx1 >= 0 & startIdx2 >= 0) {
			result	= true;
		} else {
			result	= false;
		}
		return result;
	}

    public String lastPart(String stringA, String stringB) {
        String result;
        // get lenght of stringB
        int len       = stringA.length();
        //get the start of stringA in stringB
        int startIdx1 = stringB.indexOf(stringA);
        //get the end of stringA in stringB
        int endIdx1   = startIdx1 + len;
//        int startIdx2 = stringB.indexOf(stringA, endIdx1);
//      boolean validG   = (startIdx - endIdx)%3 == 0;
        System.out.println("start1: " + startIdx1 + " endIdx1: " + endIdx1 );
        if (startIdx1 >= 0 ) {
            result  = stringB.substring(startIdx1+len);
        } else {
            result  = stringB;
        }
        return result;
    }

    public void testFindGeneSimple() {
		String gene = "";
        //             v  v  v  v  v
//        String dna = "AATGCGTAATTAATCG";
		System.out.println("");
		String dna = "ATGGCGGGGTAATTAGTCG";
        System.out.println("DNA strand is: 	" + dna);
//        String gene = findGeneSimple(dna);
        gene = findGeneSimple2(dna, "ATG", "TAA");
        System.out.println("Gene is:        " + gene);
        //       v  v  v  v  v  v  v  v
		System.out.println("");
		dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("DNA strand is: 	" + dna);
        gene = findGeneSimple2(dna, "ATG", "TAA");
        System.out.println("Gene is:        " + gene);
        //       v  v  v  v  v  v  v  v
		System.out.println("");
		dna = "CGATGTGTTGATAAGCCTAAGCTAAA";
        System.out.println("DNA strand is:  " + dna);
        gene = lastPart("GCTA", dna);
        System.out.println("last part:        " + gene);
        //       v  v  v  v  v  v  v  v
		System.out.println("");
		dna = "A story by Abby Long";
        System.out.println("String is:      " + dna);
        String dHit = lastPart("by", dna);
        System.out.println("last part:    " + dHit);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindGeneSimpleAndTest testG = new FindGeneSimpleAndTest();
		testG.testFindGeneSimple();
	}

}
