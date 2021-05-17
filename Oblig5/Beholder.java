import java.util.HashMap;
import java.util.ArrayList;

public class Beholder{
  ArrayList<HashMap<String,SubSekvens> > liste = new ArrayList<HashMap<String,SubSekvens> >();
  int antall = 0;

  public void leggTilHashMap(HashMap<String, SubSekvens> h){
    antall ++;
    liste.add(h);
  }

  public HashMap<String, SubSekvens> taUtHashMap()throws IndexOutOfBoundsException{
    antall--;
    return liste.remove(0);
  }

  public int hentAntall(){
    return antall;
  }

  public void testHele () {
      System.out.println(" Utskrift av antallet i hver hashmap i beholderen; ");
      for (HashMap<String,SubSekvens> hashN: liste){
        System.out.println(" Storrelsen av HashMap:  " + hashN.size());
      }
  }

}
