import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorBokstav{
  private ArrayList<String> ordListe;
  private char bokstav;
  private Lock laas;
  public MonitorBokstav(ArrayList<String> ordListe, char bokstav){
    this.laas = new ReentrantLock();
    this.ordListe = ordListe;
    this.bokstav = bokstav;
  }

  public String hentOrd(){
    laas.lock();
    try{
      if(ordListe.isEmpty()){
        return null;
      }
      int siste = ordListe.size() -1;
      return ordListe[siste];
    }finally{
      laas.unlock();
    }
  }

  public char hentBokstav(){
    return bokstav;
  }

}
