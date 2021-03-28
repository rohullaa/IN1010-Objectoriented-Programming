import java.util.ArrayList;

class Rack{
  // velger ArrayList for å lagre nodeobjekter siden den er veldig
  //fleksibel ang. adding av objekter på slutten av arraylisten
  private ArrayList<Node> noderListe ;

  public Rack(){
    noderListe = new ArrayList<Node>();
  }

  public void settInn(Node node){
    noderListe.add(node);
  }

  public int antProsessorer(){
    int antPros = 0;
    for (Node node: noderListe){
      antPros += node.antProsessorer();
    }
    return antPros;
  }

  public int getAntNoder(){
    //returnerer antall noder i en rack
    return noderListe.size();
  }

  public int noderMedNokMinne(int paakrevdMinne){
    //antall noder med minst paakrevdMinne
    int antallNoder = 0;
    for(Node node: noderListe){
      if(node.nokMinne(paakrevdMinne)){
        antallNoder += 1;
      }
    }
    return antallNoder;
  }

}
