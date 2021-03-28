public class Hovedprogram{
  public static void main(String[] args) {
    //har utvidet main-metoden etter oppgave D.
    //Den skal printe alle filene
    
    printing("dataklynge.txt");
    printing("dataklynge2.txt");
    printing("dataklynge3.txt");
    printing("dataklynge4.txt");
  }

  public static void printing(String fil){
    Dataklynge abel = new Dataklynge(fil);

    System.out.println("Filnavn: "+fil);
    System.out.println("Noder med minst 32 GB: " + abel.noderMedNokMinne(32));
    System.out.println("Noder med minst 64 GB: " + abel.noderMedNokMinne(64));
    System.out.println("Noder med minst 128 GB: " + abel.noderMedNokMinne(128));

    System.out.println("Antall prosessorer: " + abel.antProsessorer());
    System.out.println("Antall rack: " + abel.antRacks());
    System.out.println("-----------------------------");
  }
}
