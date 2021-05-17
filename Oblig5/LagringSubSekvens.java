import java.util.*;
import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LagringSubSekvens implements Runnable{
  //definerer instansvariablene
  private String filnavn;
  private Beholder totalHash;
  private int subLengde;
  private final Lock lock = new ReentrantLock();

  public LagringSubSekvens(String filnavn, Beholder totalHash,int subLengde){
    this.filnavn = filnavn;
    this.totalHash = totalHash;
    this.subLengde = subLengde;
  }

  //hashmap for aa lagre subseksvensene
  private HashMap<String,SubSekvens>  subSekvenseHash = new HashMap <> ();
  private String linje, subStreng;

  public void run(){
    lock.lock(); //laaser run-metoden
    try {
      //leser gjennom hver fil
      Scanner leser = new Scanner(new File("TestData/"+filnavn));
      HashMap<String,SubSekvens>  subSekvenseHash = new HashMap <> ();

      while(leser.hasNextLine()){
        linje = leser.nextLine();
        linje = linje.trim();
        for (int ind = 0; ind + subLengde <= linje.length(); ind ++) {
            subStreng = linje.substring(ind,ind+subLengde);

            //putter subStrengen kun dersom det er ingen slik subStreng fra før i HashMap (inge duplikater)
            subSekvenseHash.putIfAbsent(subStreng,new SubSekvens(subStreng,1));
        }
      }
      leser.close();
      totalHash.leggTilHashMap(subSekvenseHash);
    }catch (IOException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }finally{
      lock.unlock();
    }
  }

  public Beholder hentBeholder(){
    return totalHash;
  }
}
