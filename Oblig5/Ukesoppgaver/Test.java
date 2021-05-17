class Test{
  public static void main(String[] args) {
    for (int j = 0; j < 10; j++ ){
      for(int i = 0; i < 1000000; i = 10 + i){
        System.out.println(i);
      }
    }
  }
}
