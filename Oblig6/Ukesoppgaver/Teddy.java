public class Teddy{
  public static boolean teddy(int n){
    if(n == 42){
      return true;
    }else if((n & 1) == 0){ //sjekker om det er partall
      return teddy(n/2);
    }else if(n % 5 ==0){
      return teddy(42);
    }else if(n % 3 ==0 || n % 4 ==0){
      return teddy(n-10);
    }else{
      return false;
    }
  }

  public static void main(String[] args) {
    System.out.println(teddy(-1));
  }
}
