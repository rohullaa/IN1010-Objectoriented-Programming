import java.util.*;

class Aapning extends HvitRute{
  public Aapning(Labyrint labyrint,int kolonne,int rad){
    super(labyrint,kolonne,rad);
  }

  public void gaa(Rute forrigePosisjon, ArrayList<Tuppel> gammelVei){
    vei = new ArrayList<>(gammelVei);
    vei.add(new Tuppel(kolonne,rad));

    labyrint.utVei.add(vei); //leger den inn i utvei ArrayList og terminerer.
  }
}
