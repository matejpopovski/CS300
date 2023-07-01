
public class isPalindrome {

  
  public static <T> boolean isPalindrome(T item) {
    
    String p = item.toString();
    p = p.replaceAll("\\p{Punct}", "");
    p = p.replaceAll(" ", "");
    p = p.toLowerCase();
    String reverse = "";
    for(int i = p.length() - 1; i >= 0; i--) {
      reverse = reverse + p.substring(i, i+1);
    }
    if(reverse.equals(p)) {
      System.out.print(reverse + " " + p);
      return true;
    }
    
    System.out.print(reverse + " " + p);
    
    return false;
    
  } 
  
  public static void main(String args[]) {
    String a = "1010101";
    String b = "  ./ 21  /. = ' 7 2 ";
    String c = "A nut for a jar of tuna...";
    
    System.out.println(isPalindrome(a));
    System.out.println(isPalindrome(b));
    System.out.println(isPalindrome(c));
    
  }



}
