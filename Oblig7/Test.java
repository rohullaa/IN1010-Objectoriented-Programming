class Test{
  public static void main(String[] args) {
    String a = "200";
    a = a.replaceAll("\\s+","");


    int b = Integer.parseInt(a);
    System.out.println(b*2);
  }
}
