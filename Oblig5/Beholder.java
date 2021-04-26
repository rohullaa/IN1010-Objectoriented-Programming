import java.util.HashMap;
import java.util.ArrayList;

public class Beholder{
  private ArrayList<HashMap<String,Subsekvens> > liste;
  private boolean virusTilstand;//true hvis personen har hatt viruset og false for motsatt

  public Beholder(boolean virusTilstand){
    this.virusTilstand = virusTilstand;
    liste = new ArrayList<HashMap<String,Subsekvens>>();
  }

  public void leggTilHash(HashMap<String, Subsekvens> h){
    liste.add(h);
  }

  public HashMap<String, Subsekvens> taUtHash(){
    return liste.remove(0);
  }
  public ArrayList<HashMap<String,Subsekvens> > hentListe(){
    return liste;
  }

  public int hentLengde(){
    return liste.size();
  }


}
