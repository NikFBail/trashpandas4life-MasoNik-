import java.util.HashMap;
import java.util.Arrays;

public class StringSplicer {
    // Initializing variables
    static int LTR;
    static int RTL;
    static int originaL;
    static HashMap<Integer, String> results = new HashMap<Integer, String>();
    public static void main(String[] args){
        int[] partitions = {2, 5, 9, 12, 18};
        String test = "C'est amusant, n'est-ce pas ?";
        originaL = test.length();
        System.out.println("cheapest traversal: " + splice(test, partitions, originaL));
    }

    // Main method for cutting the string at specified indices
    public static int splice(String s, int[] partitions, int staticLength){
       leftToRight(s, partitions, staticLength);
       rightToLeft(s, partitions, staticLength);
       return Math.min(LTR, RTL);

    }
    
    /* Method for splitting the string
     * starting from the leftmost partition
     * and moving to the right.
     * Method is recursive and calls itself on
     * the shorter a 'cut' string each time,
     * moving on to the next partition.
     * Method returns the cut string
     */
    public static String leftToRight(String s, int[] partitions, int staticLength){
        int shift = s.length() - staticLength;
        LTR += s.length();
        if (partitions.length < 1){
            return s;
        } else {
            int cutIndex = partitions[0] + shift;
            if (results.containsKey(partitions[0])){
                // Recursive call
                return results.get(partitions[0]) + leftToRight(s.substring(cutIndex), Arrays.copyOfRange(partitions, 1, partitions.length), staticLength);
            } else {
                String submarine = s.substring(0, cutIndex);
                results.put(cutIndex, submarine);
                // Recursive call
                // Use the string from cutIndex to the end of the string, as the first part is what was 'cut off'
                return submarine + "\n" + leftToRight(s.substring(cutIndex), Arrays.copyOfRange(partitions, 1, partitions.length), staticLength);
            } 
        }
    }

    /* Method for splitting the string
     * starting from the rightmost partition
     * and moving to the left.
     * Method is recursive and calls itself on
     * the shorter a 'cut' string each time,
     * moving on to the next partition.
     * Method returns the cut string
     */
    public static String rightToLeft(String s, int[] partitions, int staticLength){
        RTL += s.length();
        if (partitions.length < 1){
            return s;
        } else {
            int lastIndex = partitions.length - 1;
            int cutIndex = partitions[lastIndex];
            if (results.containsKey(partitions[lastIndex])){
                // Recursive call
                return results.get(partitions[lastIndex]) + rightToLeft(s.substring(cutIndex), Arrays.copyOfRange(partitions, 0, lastIndex), staticLength);
            } else {
                String submarine = s.substring(0, cutIndex);
                results.put(cutIndex, submarine);
                // Recursive call
                // Use the string from the beginning of the string to cutIndex, as the end is what was 'cut off'
                return submarine + "\n" + rightToLeft(s.substring(0, cutIndex), Arrays.copyOfRange(partitions, 0, lastIndex), staticLength);
            }
        }
    }
}