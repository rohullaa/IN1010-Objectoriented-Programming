public class BokstavTeller implements Runnable{
  private MonitorBokstav monitor;
  private char bokstav;

  public BokstavTeller(MonitorBokstav monitor){
    this.monitor = monitor;
    this.bokstav = m.hentBokstav();
  }

  public void run(){
    String ord = hentOrd();
    while(ord != null){
      int antall = 0;
      String ordLower = ord.toLowerCase();
      for(int i = 0; i < ord.length(); i++){
        if(ordLower.charAt(i) == bokstav){
          antall++;
        }
      }
      System.out.println(antall);
      String ord = hentOrd();

    }
  }
}
