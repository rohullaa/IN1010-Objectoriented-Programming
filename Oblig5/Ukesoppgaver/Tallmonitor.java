import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

class Tallmonitor{
  private int detMinste = Integer.MIN_VALUE;
  private int detStorste = Integer.MAX_VALUE;
  private Lock laas = new ReentrantLock();

  public boolean settMinste(int minVerdi){
    laas.lock();
    try {
       if (minVerdi <= detMinste) return false;
       detMinste = minVerdi;
       return true;
    }
    finally {
       laas.unlock();
    }
  }

  public boolean settStorste(int maxVerdi){
    laas.lock();
    try {
       if (maxVerdi <= detMinste) return false;
       detStorste = maxVerdi;
       return true;

    }
    finally {
       laas.unlock();
    }

  }
}
