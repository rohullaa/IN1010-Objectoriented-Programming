class Nedover implements Runnable{
  private Tallmonitor monitor;
  private int tall;

  Nedover(Tallmonitor monitor){
    this.monitor = monitor;
    this.tall = Integer.MAX_VALUE;
  }

  public void run(){
    while(monitor.settStorste(tall)){
      tall --;
    }
    System.out.println("STOPP! Tallet er ikke lenger minst: " + tall);
  }

}
