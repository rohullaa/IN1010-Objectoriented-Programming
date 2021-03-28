class TestKatt{
  public static void main(String[] args) {
    Katt[] liste = {new Katt("Katt1",10),new Katt("Katt1",100),new Katt("Katt1",1)};
    Katt eldste = liste[0];
    for(Katt k : liste){
      if(k.compareTo(eldste) > 0){
        eldste = k;
      }
    }

    System.out.println(eldste);
  }
}
