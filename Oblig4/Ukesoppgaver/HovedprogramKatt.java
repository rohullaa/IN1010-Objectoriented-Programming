class HovedprogramKatt{
  public static void main(String[] args) {
    Katt [] katter = {new Katt("Tom",12),new Katt("Pus",2),new Katt("Leila",3),new Katt("Robabi",112)};
    Katt eldst = katter[0];

    for (Katt k : katter) {
      if (k.compareTo(eldst) > 0){
        eldst = k;
      }
    }

    System.out.println(eldst);
  }
}
