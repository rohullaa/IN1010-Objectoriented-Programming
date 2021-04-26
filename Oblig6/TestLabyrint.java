import java.util.*;
import java.io.*;

class TestLabyrint{
  //Brukte denne klassen til aa programmet
  public static void main(String[] args) throws FileNotFoundException{
    String filnavn = "7.in";
    File fil = new File(filnavn);
    Labyrint l = new Labyrint(fil);
    System.out.println(l.toString());

    ArrayList<ArrayList<Tuppel>> utVeier = l.finnUtveiFra(1,1);
    System.out.println("Utveier:");

    for (ArrayList<Tuppel> lis: utVeier) {
      for(Tuppel t: lis){
        System.out.println(t);
      }
      System.out.println();
    }


  }
}
