import java.util.*;
import java.io.*;
import java.util.concurrent.CountDownLatch;

class MinRun implements Runnable{
  //her implementerer jeg MinRun klassen for aa finne Subsekvenser for hver fil

  private File fil;
  private boolean virusTilstand; //true hvis personen har hatt viruset og false for motsatt
  private CountDownLatch barriere;
  private MonitorFletting m;
  private Scanner leser;

  public MinRun(File fil, boolean virusTilstand, CountDownLatch barriere,MonitorFletting m){
    this.fil = fil;
    this.virusTilstand = virusTilstand;
    this.barriere = barriere;
    this.m = m;
    try{
      leser = new Scanner(this.fil);
    }catch(FileNotFoundException e){
      System.out.println(e.getMessage());
    }
  }

  public void run(){
    int subLengde = 3;
    String linje, subStreng;
    HashMap<String,Subsekvens>  subSeqHash = new HashMap <> ();

    System.out.println(fil.getName() + " blir lest.");
    while(leser.hasNext()) {
      linje = leser.nextLine();
      linje = linje.trim();
      for (int ind = 0; ind + subLengde <= linje.length(); ind ++) {
        subStreng = linje.substring(ind,ind+subLengde);
        subSeqHash.put(subStreng,new Subsekvens(subStreng));
      }
    }

    m.leggTilHash(virusTilstand,subSeqHash);
    barriere.countDown();
  }

}
