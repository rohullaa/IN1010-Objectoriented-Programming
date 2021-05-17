import java.util.*;
import java.io.*;

public class Hovedprogram{
  static int subLengde = 3; //velger lengden av SubSekvenser til aa vaere 3
  static String metadataRetning = "TestData/metadata.csv";

  public static void main (String [ ] args) throws IOException {
    String linje;
    //definerer to beholdere, en for positiv og en for negativ
    Beholder beholderTrue = new Beholder();
    Beholder beholderFalse = new Beholder();

    try{
        //scanner filnavnene
        Scanner leser = new Scanner(new File(metadataRetning));
        leser.nextLine(); //ignorerer forste linjen

        while(leser.hasNextLine()) {
          linje = leser.nextLine();
          linje = linje.trim();

          //lagrer informasjon av personer og dersom de har hatt virus eller ikke.
          String[] filData = linje.split(",");
          //basert paa om de har hatt viruset før eller ikke, saa lages det nye traader
          if(filData[1].equals("True")){
            new Thread(new LagringSubSekvens(filData[0],beholderTrue,subLengde)).start();
          }else{
            new Thread(new LagringSubSekvens(filData[0],beholderFalse,subLengde)).start();
          }
        }
        leser.close();
    } catch (IOException e) {System.out.println(e.getMessage()); }

    //slaar sammen hashmaper og legger resultatet tilbake i beholderen
    SammenSlaaing slaarPositiv = new SammenSlaaing(beholderTrue);
    SammenSlaaing slaarNegativ = new SammenSlaaing(beholderFalse);

    beholderTrue = slaarPositiv.slaaSammen();
    beholderFalse = slaarNegativ.slaaSammen();

    //kjorer statistikk delen ved aa hente ut den siste hashmappen i begge beholdere,
    //og sjekke dersom forskjellen mellom antall forekomster er storre eller lik 5
    HashMap<String,SubSekvens> personPositiv = beholderTrue.taUtHashMap();
    HashMap<String,SubSekvens> personNegativ = beholderFalse.taUtHashMap();

    for(SubSekvens pp:personPositiv.values()){
      for(SubSekvens pn:personNegativ.values()){
        int forskjell = pp.hentForekomster() - pn.hentForekomster();
        if(forskjell >= 5){
          System.out.println("AntallForekomster: "+ pp.hentForekomster()+ ""+pn.hentForekomster()+"med: "+ pp.hentSekvens());
        }
      }
    }
  }
}
