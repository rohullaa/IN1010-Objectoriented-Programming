import java.util.*;

public class SammenSlaaing{
  //denne klassen sin oppgave var aa hente to hashmapper og slaa dem sammen.
  //Denne skulle vaere en trådklasse, men jeg slet med oppgaven og klarte ikke å lose den helt.
  private Beholder beholder;
  public SammenSlaaing(Beholder beholder){
    this.beholder = beholder;
  }

  public Beholder slaaSammen(){
    while (beholder.hentAntall() > 1) {
        HashMap<String,SubSekvens> res = fletting(beholder.taUtHashMap(), beholder.taUtHashMap());
        beholder.leggTilHashMap(res);
    }
    return beholder;
  }
  public HashMap<String, SubSekvens> fletting(HashMap<String, SubSekvens> h1,HashMap<String, SubSekvens> h2){
    SubSekvens hentaSub;
    HashMap<String,SubSekvens> subSekHashNy = new HashMap<String,SubSekvens> ();
      for(SubSekvens  sub1:  h1.values()) {
        hentaSub = h2.remove(sub1.hentSekvens());
        if (hentaSub == null) {
            subSekHashNy.put(sub1.hentSekvens(), sub1);
        }
        else {
          int ant = hentaSub.hentForekomster();
          sub1.leggTilForekomster(ant);
          subSekHashNy.put(sub1.hentSekvens(), sub1);
        }
    }
    // Legger inn resten av h2:
    for(SubSekvens  sub2:  h2.values()) {
        subSekHashNy.put(sub2.hentSekvens(),sub2);
    }
    return subSekHashNy;
  }
}
