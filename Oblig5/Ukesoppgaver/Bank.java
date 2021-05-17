import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

class Bank{
  private int belop = 0;
  private Lock laas = new ReentrantLock();

  public void ta(int verdi){
    laas.lock();
    try{
      belop = belop-verdi;
    }finally{
      laas.unlock();
    }
  }

  public void gi(int verdi){
    laas.lock();
    try{
      belop = belop+verdi;
    }finally{
      laas.unlock();
    }
  }

  public int saldo(){
    return belop;
  }
}
