import java.util.*;
import java.io.*;
import java.util.concurrent.CountDownLatch;

class Hovedprogram{
  static final int subLengde = 3;
  static String filBane = "./Data";
  static HashMap <String, Boolean> metadataHash;

  public static void main(String[] args)throws Exception{
    int antallTraader = Integer.valueOf(args[0]);
    System.out.println();
    System.out.println("Programmet kjorer.Vennligst vent!");
    System.out.println();

    Beholder negativBeholder = new Beholder(false);
    Beholder positivBeholder = new Beholder(true);
    MonitorFletting monitor = new MonitorFletting(positivBeholder,negativBeholder);
    File[] alleFiler = (new File(filBane)).listFiles(); //henter navn til alle filene i testData-filen

    //fyller metadata HashMap med data fra metadata-filen
    metadataHash = hentData("metadata.csv",filBane);

    //finner Subsekvenser:
    CountDownLatch barriere = new CountDownLatch(alleFiler.length); //lager barriere med samme lengde som antall filer
    for(File fil: alleFiler){
      if(metadataHash.containsKey(fil.getName())){ //hvis metadataHash inneholder filnavnet
        Runnable minRun = new MinRun(fil,metadataHash.get(fil.getName()),barriere,monitor);
        Thread traad = new Thread(minRun);//lager traad for hver fil
        traad.run();
      }else{
        barriere.countDown();
      }
    }
    try{
      barriere.await();
    }catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }

    //setter i gang flettingen
    CountDownLatch nyBarriere = new CountDownLatch(antallTraader); //lager barrierer med samme lengde som burkeren taster inn
    //fordeler antall traader
    int antallTraaderPositiv = antallTraader/2;
    int antallTraaderNegativ = antallTraader - antallTraaderPositiv;
    for(int i = 0; i < alleFiler.length; i++){
      if(antallTraaderPositiv > 0){
        Thread traad = new Thread(new RunFletting(true,nyBarriere,monitor));
        traad.start();
        antallTraaderPositiv--;
      }

      if(antallTraaderNegativ > 0){
        Thread traad = new Thread(new RunFletting(false,nyBarriere,monitor));
        traad.start();
        antallTraaderNegativ--;
      }
    }try {
      nyBarriere.await();
    } catch (InterruptedException e){
      System.out.println(e.getMessage());
    }

    //statistikk:
    HashMap<String,Subsekvens> h1 = monitor.hentHash(true).get(0);
    HashMap<String,Subsekvens> h2 = monitor.hentHash(false).get(0);

    System.out.println();
    System.out.println( "Subsekvens - forekomster1 - forekomster2 - differeranse");

    for(String key: h2.keySet()){
      if(h1.containsKey(key)){
        int forekomster1 = h1.get(key).hentAntall();
        int forekomster2 = h2.get(key).hentAntall();
        //differeranse mellom alle har som hatt minus alle som ikke har hatt
        int differeranse = forekomster1 - forekomster2;
        if(differeranse >= 5){
          System.out.format("%3s %2d %2d %2d", key, forekomster1, forekomster2,differeranse);
          System.out.println();
        }
      }
    }
  }

  private static HashMap<String, Boolean> hentData(String metadata, String filnavn) throws Exception,FileNotFoundException {
    HashMap<String, Boolean> hash = new HashMap<>();
    File f = new File(filnavn + "/" + metadata);

      Scanner sc = new Scanner(f);
      while (sc.hasNext()) {
        String linje = sc.nextLine();
        String[] info = linje.trim().split(",");
        try {
          hash.put(info[0], Boolean.valueOf(info[1].toLowerCase()));
        } catch (Exception e) {
          System.out.println("Feil format. linje: " + linje);
        }
      }
      sc.close();

    return hash;
  }

}
