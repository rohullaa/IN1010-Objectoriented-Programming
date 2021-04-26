import java.util.ArrayList;

abstract class Rute{
  public int kolonne;
  public int rad;

  //naboer:
  public Rute nord;
  public Rute syd;
  public Rute vest;
  public Rute oest;

  public Labyrint labyrint;
  public ArrayList<Tuppel> vei;
  private ArrayList<Tuppel> gammelVei;

  public Rute[] naboListe;

  //definerer to dimensjonal array, kolonne-nummer og radnummer som instans variabler til
  //denne klassen
  public Rute(Labyrint labyrint,int kolonne,int rad){
    this.labyrint = labyrint;
    this.kolonne = kolonne;
    this.rad = rad;
  }

  public void leggTilNaboer(Rute nord,Rute syd,Rute oest,Rute vest){
    this.nord = nord;
    this.syd = syd;
    this.vest = vest;
    this.oest = oest;

    naboListe = new Rute[]{nord,syd,oest,vest};
  }

  public abstract char tilTegn();
  public abstract void gaa(Rute forrigePosisjon, ArrayList<Tuppel> gammelVei);

  public void finnUtvei(){
    gammelVei = new ArrayList<Tuppel>();
    gammelVei.add(new Tuppel(kolonne,rad));

    //For hver nabo, dersom naboen ikke er null saa gaar den der. 
    for(Rute nabo: naboListe){
      if(nabo != null){
        nabo.gaa(this,gammelVei);
      }
    }
  }

}
