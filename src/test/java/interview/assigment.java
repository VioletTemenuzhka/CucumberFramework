package interview;

public class assigment {
    /* Reverse the following string "Hello Violet"

     */


    public static void main(String[] args) {
        System.out.println(reverseString("Hello Violet"));

    }

    public static String reverseString (String strToRev){
        StringBuilder sb = new StringBuilder();
        String[] stringArray = strToRev.split(" ");
        for(int i=stringArray.length-1; i>=0; i--){
                   sb = sb.append(" ").append(stringArray[i]);
        }
        return sb.toString().trim();
    }
}