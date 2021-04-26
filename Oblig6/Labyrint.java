import java.io.*;
import java.util.*;

class Labyrint{
  //denne arrayen skal holde styr paa alle ruter objekter. Har valgt aa indeksere
  //den slik: labyrint[rad][kolonne1, kolonne2,....]
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

    this.antallRader = Integer.parseInt(storrelsen[0]);
    this.antallKolonner = Integer.parseInt(storrelsen[1]);
    this.labyrintArray = new Rute[antallRader][antallKolonner];

    //leser resten av filen og oppretter Rute-objektene:
    int rad = 0;
    while(sc.hasNextLine()){ //saa lenge filen har neste linje
      int kolonne = 0;
      Rute[] perRad = new Rute[this.antallKolonner];

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
        perRad[kolonne] = rute;
        kolonne++;
      }
      //setter inn rute-objekt per rad i ruter arrayet paa plass rad
      labyrintArray[rad] = perRad;
      rad++;

    }

    //legger til naboer ved aa iterere gjennom hver rand sin hver kolonne.
    for(int r = 0; r < antallRader; r++){
      for(int k = 0; k < antallKolonner; k++){
        Rute nord = null;
        Rute syd = null;
        Rute oest = null;
        Rute vest = null;

        //gitt at vi har en tilfeldig rute med indekser labyrint[k][r] saa kan vi finne naboen
        //implementerer det med noen enkle if-setninger slik at indeksene blir riktige
        if(r != 0){
          nord = labyrintArray[r-1][k];
        }if(r != antallRader - 1){
          syd = labyrintArray[r+1][k];
        }if(k != antallKolonner -1){
          oest = labyrintArray[r][k+1];
        }if(k != 0){
          vest = labyrintArray[r][k-1];
        }

        //registerer disse naboene med leggTilNaboer-metoden
        labyrintArray[r][k].leggTilNaboer(nord,syd,oest,vest);
      }
    }
  }

  public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int kol, int rad){
    utVei = new ArrayList<ArrayList<Tuppel>> ();
    labyrintArray[rad][kol].finnUtvei();
    return utVei;
  }

  public String toString(){
    String tekst = "";
    for(int r = 0; r < antallRader; r++){
      for(int k = 0; k < antallKolonner; k++){
        tekst += labyrintArray[r][k].tilTegn();
      }
      tekst += "\n";
    }
    return tekst;
  }
}
