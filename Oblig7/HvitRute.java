import java.util.*;

class HvitRute extends Rute{
  public HvitRute(Labyrint labyrint,int kolonne,int rad){
    super(labyrint,kolonne,rad);
  }

  @Override
  public char tilTegn(){
    return '.';
  }

  public void gaa(Rute forrigePosisjon, ArrayList<Tuppel> gammelVei){
    vei = new ArrayList<>(gammelVei); //lager kopi av den gamle veien
    vei.add(new Tuppel(kolonne,rad));

    for(Rute nabo: naboListe){
      //rekusivt, dersom den forrige posisjonen er ikke naboen saa gaar den der. Gjentar for alle naboer
      if(forrigePosisjon != nabo){
        nabo.gaa(this,vei);
      }
    }

  }
}
