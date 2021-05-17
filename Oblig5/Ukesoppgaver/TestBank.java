import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

class TestBank{
  public static void main(String[] args) {
    Bank bank = new Bank();

    for(int in = 1; in < 100; in++){
        Thread traad = new Thread(new GiOgTa(bank,in));
        traad.start();
    }
  }
}
