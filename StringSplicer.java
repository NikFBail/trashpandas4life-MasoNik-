import java.util.HashMap;
import java.util.Arrays;

public class StringSplicer {
    static int LTR;
    static int RTL;
    static int originaL;
    static HashMap<Integer, String> results = new HashMap<Integer, String>();
    public static void main(String[] args){
        String toSplice;
        int[] partitions = {2, 5, 9, 12, 18};
        
        String test = "C'est amusant, n'est-ce pas ?";
        originaL = test.length();
        System.out.println("cheapest traversal: " + splice(test, partitions, originaL));
    }

    public static int splice(String s, int[] partitions, int staticLength){
       
       leftToRight(s, partitions, staticLength);
       rightToLeft(s, partitions, staticLength);
       return Math.min(LTR, RTL);

    }
    
    public static String leftToRight(String s, int[] partitions, int staticLength){
        int shift = s.length() - staticLength;
        LTR += s.length();
        if (partitions.length < 1){
            return s;
        }
        else {
            int cutIndex = partitions[0] + shift;
            if (results.containsKey(partitions[0])){
                return results.get(partitions[0]) + leftToRight(s.substring(cutIndex), Arrays.copyOfRange(partitions, 1, partitions.length), staticLength);
            }
            else {
                
                String submarine = s.substring(0, cutIndex);
                results.put(cutIndex, submarine);
                return submarine + "\n" + leftToRight(s.substring(cutIndex), Arrays.copyOfRange(partitions, 1, partitions.length), staticLength);
            }
            
        }
    }
    public static String rightToLeft(String s, int[] partitions, int staticLength){
    
        RTL += s.length();
        if (partitions.length < 1){
            return s;
        }
        else {
            int lastIndex = partitions.length - 1;
            int cutIndex = partitions[lastIndex];
            if (results.containsKey(partitions[lastIndex])){
                return results.get(partitions[lastIndex]) + rightToLeft(s.substring(cutIndex), Arrays.copyOfRange(partitions, 0, lastIndex), staticLength);
            }
            else {
                String submarine = s.substring(0, cutIndex);
                results.put(cutIndex, submarine);
                return submarine + "\n" + rightToLeft(s.substring(cutIndex), Arrays.copyOfRange(partitions, 0, lastIndex), staticLength);
            }
            
        }
    }

}