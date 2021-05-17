import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

public class Minst {
  public static void main(String[ ] args) {

    int [ ] tabell;
  	MinstMonitor monitor;
  	Runnable runAbl;
  	final int ANTTRAD = 64;
  	final int ANTPERTRAD = 100000;
  	CountDownLatch bariere = new CountDownLatch(ANTTRAD);

    tabell = new int[ANTTRAD * ANTPERTRAD];
    for (int in = 0; in < ANTTRAD * ANTPERTRAD; in++)  {
	    tabell[in] = (int)Math.round(Math.random()* Integer.MAX_VALUE);
	  }
    monitor = new MinstMonitor();
    for (int i = 0; i< ANTTRAD; i++){
      runAbl = new MinstRun(tabell,i*ANTPERTRAD,((i+1)*ANTPERTRAD)-1,monitor,bariere);
      new Thread(runAbl).start();
    }
    try {
      bariere.await();
      System.out.println("Minste verdi var: " + monitor.hentMinste());
    }
    catch (InterruptedException ex){
       System.out.println(" Uventet avbrudd ");  System.exit(1);
    }
  }
}

class MinstMonitor {
  private Lock laas = new ReentrantLock();
  int minstTilNa = Integer.MAX_VALUE;
  int antallFerdigeSubtrader = 0;
  public void giMinsteVerdi (int minVerdi) {
     laas.lock();
     try {
        if (minstTilNa > minVerdi) minstTilNa = minVerdi;
     }
     finally {
        laas.unlock();
     }
  }

  public int hentMinste () {return minstTilNa;}  //Trenger ikke laases
                                     // fordi alle andre operasjoner er ferdige
}

class MinstRun implements Runnable {
  int [ ] tab; int startInd, endInd;
  MinstMonitor mon;
  CountDownLatch bariere;
  MinstRun(int [ ] tb, int st, int en, MinstMonitor m, CountDownLatch bariere) {
      tab = tb; startInd = st; endInd = en;
      mon = m; this.bariere = bariere;
  }
  public void run(){
    int minVerdi = Integer.MAX_VALUE;
    for (int ind = startInd; ind <= endInd; ind++) {
            if(tab[ind] < minVerdi) minVerdi = tab[ind];
    }
    mon.giMinsteVerdi(minVerdi);
    bariere.countDown();
  }
}


/*
bariere blir skrevet med:
CountDownLatch bariere = new CountDownLatch(ANTTRAD);
der ANTTRAD er antall tråder vi har valgt å dele programmet i eller det er de trådene vi har valgt å vente på.

Når run metoden velger å avslutte så sier vi:
bariere.countDown

Da vil bariere.await() som driver og venter på at alle trådene har kjørt blir terminert og programmet
forsetter.
*/
