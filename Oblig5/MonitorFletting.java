import java.util.*;
import java.util.concurrent.locks.*;

class MonitorFletting{
  public Beholder positivBeholder; //beholder for de som har hatt viruset
  public Beholder negativBeholder; //beholder for de som har ikke hatt viruset

  private Lock positivLaas;
  private Condition positivNy;
  private int positiveTraader;

  private Lock negativLaas;
  private Condition negativNy;
  private int negativeTraader;

  public MonitorFletting(Beholder positivBeholder, Beholder negativBeholder){
    this.positivBeholder = positivBeholder;
    this.negativBeholder = negativBeholder;

    positivLaas = new ReentrantLock();
    positivNy = positivLaas.newCondition();
    positiveTraader = 0; //antall positive traader i arbeid

    negativLaas = new ReentrantLock();
    negativNy = negativLaas.newCondition();
    negativeTraader = 0;//antall negative traader i arbeid
  }


  public ArrayList<HashMap<String,Subsekvens>> hentFletting(boolean virusTilstand){
    //hetner to Subsekvenser og oker antall traader i arbeid.
    if(virusTilstand == true){
      positivLaas.lock();
      try{
        while(positivBeholder.hentLengde() <2){ //det maa vaere minst to Subsekvenser for aa flette
          if(positiveTraader == 0 && positivBeholder.hentLengde() == 1){
            //dersom det er en Subsekvens igjen saa stopper metoden
            Thread.currentThread().interrupt();
            return null;
          }
          positivNy.await();
        }
        ArrayList<HashMap<String,Subsekvens>> hash = new ArrayList<>();
        hash.add(positivBeholder.taUtHash());
        hash.add(positivBeholder.taUtHash());
        positiveTraader ++;
        return hash;
      }catch (InterruptedException e) { //catcher opp InterruptedException
        System.out.println(e.getMessage());
      }finally{
        positivLaas.unlock();
      }
    }else{
      negativLaas.lock();
      try{
        while(negativBeholder.hentLengde() <2){ //det maa vaere minst to Subsekvenser for aa flette
          if(negativeTraader == 0 && negativBeholder.hentLengde() == 1){
            //dersom det er en Subsekvens igjen saa stopper metoden
            Thread.currentThread().interrupt();
            return null;
          }
          negativNy.await();
        }
        ArrayList<HashMap<String,Subsekvens>> hash = new ArrayList<>();
        hash.add(negativBeholder.taUtHash());
        hash.add(negativBeholder.taUtHash());
        negativeTraader ++;
        return hash;
      }catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
      finally{
        negativLaas.unlock();
      }
    }
    return null;
  }


  public ArrayList<HashMap<String,Subsekvens>> hentHash(boolean virusTilstand){
    if(virusTilstand == true){
      positivLaas.lock();
      try{
        while(positivBeholder.hentLengde() == 0){
          positivNy.await();
        }
        return positivBeholder.hentListe();

      }catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }finally{
        positivLaas.unlock();
      }
    }
    else{
      negativLaas.lock();
      try{
        while(negativBeholder.hentLengde() == 0){
          negativNy.await();
        }
        return negativBeholder.hentListe();

      }catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }finally{
        negativLaas.unlock();
      }
    }
    return null;
  }

  //legger til hash i de to beholdere avhengig av om virusTilstand er positiv eller negativ
  public void leggTilHash(boolean virusTilstand, HashMap<String, Subsekvens> hash){
    if(virusTilstand == true){
      positivLaas.lock();
      try{
        positivBeholder.leggTilHash(hash);
        positivNy.signalAll();
      }finally{
        positivLaas.unlock();
      }
    }
    else{
      negativLaas.lock();
      try{
        negativBeholder.leggTilHash(hash);
        negativNy.signalAll();
      }finally{
        negativLaas.unlock();
      }

    }
  }

  public void leggTilFletter(boolean virusTilstand,HashMap<String,Subsekvens> hash){
    //registerer alle flettene/sammenslaaingene. Og etter det reduserer antall traader:
    if(virusTilstand == true){
      positivLaas.lock();
      try{
        positivBeholder.leggTilHash(hash);
        positiveTraader --;
        positivNy.signalAll();
      }finally{
        positivLaas.unlock();
      }
    }else{
      negativLaas.lock();
      try{
        negativBeholder.leggTilHash(hash);
        negativeTraader --;
        negativNy.signalAll();
      }finally{
        negativLaas.unlock();
      }
    }
  }
}
