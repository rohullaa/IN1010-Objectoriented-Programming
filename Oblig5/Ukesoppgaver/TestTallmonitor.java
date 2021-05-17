import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

class TestTallmonitor{
  public static void main(String[] args) {
    Tallmonitor tm = new Tallmonitor();
    Thread traad1 = new Thread(new Nedover(tm));
    Thread traad2 = new Thread(new Oppover(tm));
    traad1.start();
    traad2.start();
  }
}
