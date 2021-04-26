import java.util.*;
import java.io.*;
import java.util.concurrent.CountDownLatch;

class RunFletting implements Runnable{

  private boolean virusTilstand; //true hvis personen har hatt viruset og false for motsatt
  private CountDownLatch barriere;
  private MonitorFletting m;
  private ArrayList<HashMap<String,Subsekvens> > liste;

  public RunFletting(boolean virusTilstand,CountDownLatch barriere, MonitorFletting m){
    this.virusTilstand = virusTilstand;
    this.barriere = barriere;
    this.m = m;
  }

  public void run(){
    while(!Thread.interrupted()){
      liste = m.hentFletting(this.virusTilstand); //henter to hashmap
      if(Thread.interrupted()){ //dersom noen av traadene blir forstyrret saa avsluttes metoden
        barriere.countDown();
        return;
      }
      HashMap<String,Subsekvens> hash1 = liste.get(0);
      HashMap<String,Subsekvens> hash2 = liste.get(1);
      //fletting eller sammenslaaing
      for(String key: hash2.keySet()){
        if(hash1.containsKey(key)){
          //dersom de to hashmappene har samme Subsekvens saa okes antallet
          int temp = hash2.get(key).hentAntall();
          hash1.get(key).leggTilForekomster(temp);
        }else{
          hash1.put(key,hash2.get(key));
        }
      }

      m.leggTilFletter(virusTilstand,hash1);

    }
  }

}
