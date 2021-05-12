import java.io.*;
import java.util.*;

class Labyrint{

  private Rute[][] labyrintArray;

  private int antallKolonner;
  private int antallRader;
  private File fil;

  public ArrayList<ArrayList<Tuppel>> utVei;

  public Labyrint(File fil)throws FileNotFoundException{

    //leser filen og finner storrelsen:
    Scanner sc = new Scanner(fil);
    String forsteLinje = sc.nextLine();
    String[] storrelsen = forsteLinje.split(" ");

    this.antallKolonner = Integer.parseInt(storrelsen[0]);
    this.antallRader = Integer.parseInt(storrelsen[1]);
    //this.labyrintArray = new Rute[antallRader][antallKolonner];
    this.labyrintArray = new Rute[antallKolonner][antallRader];

    //leser resten av filen og oppretter Rute-objektene:
    //int rad = 0;
    int kolonne = 0;
    while(sc.hasNextLine()){ //saa lenge filen har neste linje
      //int kolonne = 0;
      int rad = 0;
      Rute[] perKolonne = new Rute[this.antallRader];

      String linje = sc.nextLine();
      String[] tegn = linje.split("");
      for(String t: tegn){
        Rute rute = null;
        if(t.equals(".")){
          //dersom tegnet er "." lages det HvitRute eller Aapning

          //sjekker om det er en Aapning:
          if(rad == 0 || rad == antallRader -1 || kolonne == 0 || kolonne == antallKolonner -1){
            rute = new Aapning(this,kolonne,rad);
          }else{
            //hvis det er ikke Aapning saa lages det vanlig HvitRute
            rute = new HvitRute(this,kolonne,rad);
          }
        }else{
          //ellers lages SortRute objekt
          rute = new SortRute(this,kolonne,rad);
        }
        //setter inn ruter for hver kolonne
        perKolonne[rad] = rute;
        rad++;
      }
      //setter inn rute-objekt per rad i ruter arrayet paa plass rad
      labyrintArray[kolonne] = perKolonne;
      kolonne++;

    }

    //legger til naboer ved aa iterere gjennom hver rand sin hver kolonne.
    for(int k = 0; k < antallKolonner; k++){
      for(int r = 0; r < antallRader; r++){
        Rute nord = null;
        Rute syd = null;
        Rute oest = null;
        Rute vest = null;

        //gitt at vi har en tilfeldig rute med indekser labyrint[k][r] saa kan vi finne naboen
        //implementerer det med noen enkle if-setninger slik at indeksene blir riktige
        if(k != 0){
          nord = labyrintArray[k-1][r];
        }if(k != antallKolonner - 1){
          syd = labyrintArray[k+1][r];
        }if(r != antallRader -1){
          oest = labyrintArray[k][r+1];
        }if(r != 0){
          vest = labyrintArray[k][r-1];
        }

        //registerer disse naboene med leggTilNaboer-metoden
        labyrintArray[k][r].leggTilNaboer(nord,syd,oest,vest);
      }
    }
  }

  public int hentAntallRader(){
    return this.antallRader;
  }
  public int hentAntallKolonner(){
    return this.antallKolonner;
  }

  public Rute[][] hentLabyrintArray(){
    return labyrintArray;
  }

  public ArrayList<ArrayList<Tuppel>> hentUtveier(int kol, int rad){
    utVei = new ArrayList<ArrayList<Tuppel>> ();
    labyrintArray[kol][rad].finnUtvei();
    return utVei;
  }

  public String toString(){
    String tekst = "";
    for(int k = 0; k < antallKolonner; k++){
      for(int r = 0; r < antallRader; r++){
        tekst += labyrintArray[k][r].tilTegn();
      }
      tekst += "\n";
    }
    return tekst;
  }

}
