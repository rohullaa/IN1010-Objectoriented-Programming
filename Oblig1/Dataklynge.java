import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Dataklynge{

  // velger ArrayList for å lagre rackobjekter siden den er veldig
  //fleksibel ang. adding av objekter på slutten av arraylisten og vi trenger
  //ikke å oppgi hvor "stor" den skal være

  private ArrayList<Rack> rackListe ;
  private int maksAntallNoder; //maksAntallNoder i en rack
  private String filnavn;

  public Dataklynge(String filnavn){
    rackListe = new ArrayList<Rack>();
    Rack rack = new Rack();
    rackListe.add(rack);

    //leser filen
    this.filnavn = filnavn;
    File fil = new File(filnavn);
    Scanner sc = null;

    try{
      sc = new Scanner(fil);
    }catch (Exception e){
      System.out.println("Filen finnes ikke.");
      System.exit(1);
    }

    //leser forste linjen og lagrer det som maksAntallNoder
    String[] noderPerRack = sc.nextLine().split(" ");
    maksAntallNoder = Integer.parseInt(noderPerRack[0]);

    while(sc.hasNextLine()){
      //leser de andre linjene og lagrer det som:
      // antallNoder MinnePerNode AntallProsessorer per rack
      String[] biter = sc.nextLine().split(" ");
      int AntallNoder = Integer.parseInt(biter[0]);
      int MinnePerNode = Integer.parseInt(biter[1]);
      int AntallProsessorer = Integer.parseInt(biter[2]);

      //lager ett nytt objekt med parametere fra filen
      Node node = new Node(MinnePerNode,AntallProsessorer);
      for(int i = 0; i < AntallNoder; i++){
        this.settInnNode(node);
      }

    }
    sc.close();
  }

  public void settInnNode(Node node){
    //sjekker om det er ledig plass i racken:
    if(rackListe.get(rackListe.size()-1).getAntNoder() < maksAntallNoder){
      rackListe.get(rackListe.size()-1).settInn(node);
    }else if(rackListe.get(rackListe.size()-1).getAntNoder() == maksAntallNoder){
      //ikke ledig plass, og derfor lages nytt objekt
      Rack nyRack = new Rack();
      rackListe.add(nyRack);
      nyRack.settInn(node);
    }
  }

  public int antProsessorer(){
    int antPros = 0;
    for(Rack rack1:rackListe){
      antPros += rack1.antProsessorer();
    }
    return antPros;
  }

  public int noderMedNokMinne(int paakrevdMinne){
    //antall noder med minst paakrevdMinne
    int antallNoder = 0;
    for(Rack rack : rackListe){
      antallNoder += rack.noderMedNokMinne(paakrevdMinne);
    }
    return antallNoder;
  }

  public int antRacks(){
    return rackListe.size();
  }

}
