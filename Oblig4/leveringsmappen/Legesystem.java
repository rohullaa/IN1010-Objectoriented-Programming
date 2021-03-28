import java.io.*;
import java.util.*;

class Legesystem{

  //definerer lister for aa lagre objekter av Pasienter, Legemidler, Leger og Resepter
  static Lenkeliste<Pasient> pasientListe = new Lenkeliste<Pasient>();
  static Lenkeliste<Legemiddel> legemiddelListe = new Lenkeliste<Legemiddel>();
  static SortertLenkeliste<Lege> legeListe = new SortertLenkeliste<Lege>();
  static Lenkeliste<Resept> reseptListe = new Lenkeliste<Resept>();



  public static void main(String[] args) {
    File minFil = new File("input.txt");
    //leser filen og lagrer objektene
    lesObjekter(minFil);

    //kjorer kommandolokke og viser HOVEDMENY
    kjorKommando();
  }

  private static void lesObjekter(File minFil){
    Scanner sc = null;
    try{
      sc = new Scanner(minFil);
    }catch(FileNotFoundException e){
      System.out.println("Filen ble ikke funnet.");
      return;
    }

    //variabel for data nummer(1:Pasient, 2:Legemidler osv..)
    int dataNr = 0;
    while(sc.hasNextLine()){
      String forsteLinje = sc.nextLine();
      if(forsteLinje.startsWith("#")){
        //dersom en linje starter med '#' saa blir dataNr okt med 1
        dataNr++;
        forsteLinje = sc.nextLine();
      }
      //lagrer pasienter
      if(dataNr == 1){
        String[] biter = forsteLinje.split(",");
        if(biter.length == 2){ //passer at det er kun to elementer
          Pasient pasient = new Pasient(biter[0],biter[1]);
          pasientListe.leggTil(pasient);
        }else{
          System.out.println("Feil: dataen for Pasienter stemmer ikke.");
        }
      }

      //lagrer Legemidler
      else if(dataNr == 2){
        String[] biter = forsteLinje.split(",");
        String navn = biter[0];
        double pris = Double.valueOf(biter[2]);
        double virkestoff = Double.valueOf(biter[3]);

        if(biter.length == 5){ //dersom det er 5 elementer
          int styrke = Integer.parseInt(biter[4]);
          if(biter[1].compareTo("vanedannende") ==0){ //sjekker om det er vanedannende
            Vanedannende vd = new Vanedannende(navn,pris,virkestoff,styrke);
            legemiddelListe.leggTil(vd);
          }else if(biter[1].compareTo("narkotisk") ==0){
            Narkotisk narkotisk = new Narkotisk(navn,pris,virkestoff,styrke);
            legemiddelListe.leggTil(narkotisk);
          }
        }else if(biter.length == 4){//dersom det er ingen styrke:
          if(biter[1].compareTo("vanlig") ==0){
            Vanlig vanlig = new Vanlig(navn,pris,virkestoff);
            legemiddelListe.leggTil(vanlig);
          }
        }else{
          System.out.println("Feil: dataen for Legemidler stemmer ikke.");
        }
      }

        //lagrer Leger
      else if(dataNr == 3){
        String[] biter = forsteLinje.split(",");
        if(biter.length == 2){
          if(biter[1].compareTo("0")==0){
            Lege lege = new Lege(biter[0]);
            legeListe.leggTil(lege);
          }else{
            Spesialist spesialist = new Spesialist(biter[0],biter[1]);
            legeListe.leggTil(spesialist);
          }
        }else{
          System.out.println("Feil: dataen for Leger stemmer ikke.");
        }
      }
      //lagrer Resepter
      else if(dataNr == 4){
        String[] biter = forsteLinje.split(",");
        //lagrer hver input med navnene deres
        int legemiddelNummer = Integer.parseInt(biter[0]);
        String legeNavn = biter[1];
        int pasientID = Integer.parseInt(biter[2]);

        //bruker disse variablene til aa hentene objektene:
        Legemiddel legemiddel = null;
        for(Legemiddel lg:legemiddelListe){
          if(lg.hentId() == legemiddelNummer){
            legemiddel = lg;
          }
        }

        Pasient pasient = null;
        for(Pasient p: pasientListe){
          if(p.hentId()== pasientID){
            pasient = p;
          }
        }

        Lege lege = null;
        for(Lege lg: legeListe){
          if(lg.hentLegeNavn().compareTo(legeNavn)==0){
            lege = lg;
          }
        }

        if(biter.length == 4){ //dersom ingen reit
          if(biter[3].compareTo("p")==0){ //sjekker om det er p-type
            try{
              Resept resept = lege.skrivPResept(legemiddel,pasient);
              reseptListe.leggTil(resept);
            }catch(UlovligUtskrift e){
              System.out.println(e);
            }
          }
        }else if(biter.length == 5){//med reit
          int reit = Integer.parseInt(biter[4]);

          //legger til de ulike reseptene
          if(biter[3].compareTo("hvit")==0){
            try{
              Resept resept = lege.skrivHvitResept(legemiddel,pasient,reit);
              reseptListe.leggTil(resept);
            }catch(UlovligUtskrift e){
              System.out.println(e);
            }
          }
          else if(biter[3].compareTo("blaa")==0){

              try{
                Resept resept = lege.skrivBlaaResept(legemiddel,pasient,reit);
                reseptListe.leggTil(resept);
              }catch(UlovligUtskrift e){
                System.out.println(e);
              }

          }else if(biter[3].compareTo("millitaer")==0){
            try{
              Resept resept = lege.skrivMilitaerResept(legemiddel,pasient,reit);
              reseptListe.leggTil(resept);
            }catch(UlovligUtskrift e){
                System.out.println(e);
            }
          }

        }else{
          System.out.println("Feil: dataen for Resepter stemmer ikke.");
        }
      }
    }
    sc.close();
  }

  //Denne metoden kjorer kommandolokken og setter opp HOVEDMENY
  private static void kjorKommando(){
    String input = "";
    //bruker en while til aa sette opp HOVEDMENY-en som
    //kjorer kommandolokke saa lenge brukeren ikke taster inn "5"
    while(input.compareTo("5") != 0){
      System.out.println("\n --------HOVEDMENY----------");
      System.out.println("1: Skriv ut oversikt");
      System.out.println("2: Legg til ny data");
      System.out.println("3: Bruke resept");
      System.out.println("4: Vis statistikk");
      System.out.println("5: Avslutt programmet");
      System.out.println();

      //sjekker hva brukeren har tastet inn og kjorer kommandolokke
      Scanner sc = new Scanner(System.in);
      input = sc.nextLine();

      if(input.compareTo("1")==0){
        skrivOversikt();
      }
      else if(input.compareTo("2") ==0){
        leggTilNyData();
      }
      else if(input.compareTo("3")==0){
        bruktResept();
      }else if(input.compareTo("4")==0){
        visStatistikk();
      }
    }
  }

  private static void visStatistikk(){
    //itererer gjennom reseptListe og sjekker om legemidlet i hver resept er narkotisk eller vanedannende.
    //Dersom det er sant saa oker antallNarkotiske eller antallVanedannende
    int antallNarkotiske = 0;
    int antallVanedannende = 0;
    for(Resept r: reseptListe){
      if(r.hentLegemiddel() instanceof Narkotisk){
        antallNarkotiske++;
      }else if(r.hentLegemiddel() instanceof Vanedannende){
        antallVanedannende++;
      }
    }
    System.out.println("\nTotalt antall resepter på: ");
    System.out.println("* Narkotiske Legemidler: "+antallNarkotiske);
    System.out.println("* Vanedannende Legemidler: "+antallVanedannende);

    //finner antall leger som har skrevet resepter med narkotiske innhold
    System.out.println("\nLeger som har skrevet resepter med narkotiske legemidler:");
    int antallLeger = 0;
    for(Lege l: legeListe){
      antallLeger = 0;
      //finner alle resepter som hver Lege l har skrevet
      for(Resept r: l.hentUtskrevedeResepter()){
        //sjekker om hver resept er instans av Narkotisk, hvis det er sant saa okes antallLeger
        if(r.hentLegemiddel() instanceof Narkotisk){
          antallLeger++;
        }

      }
      //dersom en lege har utskrevet minst et resept med narkotisk innhold:
      if(antallLeger>0){
        System.out.println(l.hentLegeNavn()+": "+antallLeger);
      }
    }

    //finner antall pasient som har fått resepter med narkotiske innhold
    System.out.println("\nPasienter som har fått resepter med narkotiske legemidler");
    int antallPasienter = 0;
    for(Pasient p: pasientListe){
      antallPasienter = 0;
      //finner alle resepter som hver pasient har faatt
      for(Resept r: p.hentReseptListen()){
        //sjekker om hver resept er instans av Narkotisk, hvis det er sant saa okes antallLeger
        if(r.hentLegemiddel() instanceof Narkotisk){
          antallPasienter ++;
        }
      }
      if(antallPasienter > 0){
        System.out.println(p.hentNavn()+": "+antallPasienter);
      }
    }

  }

  private static void bruktResept(){
    System.out.println("\n Hvilken pasient vil du se resepter for?");
    int ii = 0;
    for(Pasient p: pasientListe){
      System.out.println(" "+ii +": "+p.hentNavn() +"| Fodselsnummer: " +p.hentFodselsnummer());
      ii++;
    }

    //scanner inn pasientID fra brukeren
    Scanner sc = new Scanner(System.in);
    int pasientID = Integer.valueOf(sc.nextLine());

    //bruker pasientID til aa hente Pasient
    Pasient pasient = pasientListe.hent(pasientID);
    System.out.println("Valgt pasient: " +pasient.hentNavn());

    //dersom den valgte pasient har ingen resepter saa starter denne metoden paa nytt
    if(pasient.hentReseptListen().stoerrelse()==0){
      System.out.println("Denne pasienten har ingen resepter.");
      bruktResept();
    }

    System.out.println("Hvilken resept vil du bruke?");
    int i = 0;
    for(Resept r: pasient.hentReseptListen()){
      System.out.println(i+": "+ r.hentLegemiddel().hentNavn() +"("+ r.hentReit() +" igjen)");
      i++;
    }

    //bruker reseptID-en til aa hente resept
    int reseptID = Integer.parseInt(sc.nextLine());
    Resept resept = pasient.hentReseptListen().hent(reseptID);

    //sjekker om denne resepten har reit
    if(! resept.bruk()){
      System.out.println("Kunne ikke bruke resept paa "+resept.hentLegemiddel().hentNavn()+" (ingen reit igjen).");
    }else{
      System.out.println("Resept paa " +resept.hentLegemiddel().hentNavn()+ " ble brukt." );
    }

  }

  //metode for aa legg til data fra terminalen
  private static void leggTilNyData(){
    System.out.println("Velg hva du vil legge til");
    System.out.println("1: Pasient");
    System.out.println("2: Legemiddel");
    System.out.println("3: Leger");
    System.out.println("4: Resepter");

    //definerer disse for aa lese input fra terminalen
    Scanner sc = new Scanner(System.in);
    String nyInput = sc.nextLine();

    //dersom brukeren velger aa legge til Pasient
    if(nyInput.compareTo("1")==0){
      leggTilPasient(sc);
    }

    //dersom brukeren velger aa legge til legemiddel
    else if(nyInput.compareTo("2") == 0){
      leggLegemiddel(sc);
    }
    //dersom brukeren velger aa legge til leger:
    else if(nyInput.compareTo("3")==0){
      leggTilLege(sc);
    }

    //dersom burkeren velger aa legge til Resepter:
    else if(nyInput.compareTo("4")==0){
      leggTilResept(sc);
    }
  }

  //Metode for aa skrive oversiken
  public static void skrivOversikt(){
    System.out.println("\n Pasienter:");
    System.out.println(" ----------");
    for(Pasient p: pasientListe){
      System.out.println(" "+p.toString());
    }
    System.out.println("\n");

    System.out.println("\n Legemidler:");
    System.out.println(" ----------");
    for(Legemiddel lg: legemiddelListe){
      System.out.println(" "+lg.toString());
    }
    System.out.println("\n");

    System.out.println("\n Leger:");
    System.out.println(" ----------");
    for(Lege l: legeListe){
      System.out.println(" "+l.toString());
    }
    System.out.println("\n");

    System.out.println("\n Resepter:");
    System.out.println(" ----------");
    for(Resept r: reseptListe){
      System.out.println(" "+r.toString());
    }
    System.out.println("\n");
  }

  //---------------------------------------------------------------------------
  //Metoder for legge til pasient, legemiddel osv...

  private static void leggTilPasient(Scanner sc){
    System.out.println("Fyll ut informasjon om paseinten");
    System.out.println("Navn: ");
    String navn = sc.nextLine(); //leser input fra terminalen

    System.out.println("Fodselsnummer: ");
    String fodselsnummer = sc.nextLine();

    //lager pasient med de nye variablene
    Pasient pasient = new Pasient(navn,fodselsnummer);
    pasientListe.leggTil(pasient);
    System.out.println("Pasient ble registert.");
  }

  private static void leggLegemiddel(Scanner sc){
    System.out.println("Fyll ut informasjon om legemidlet");

    System.out.println("Navn: ");
    String navn = sc.nextLine();

    //brukeren maa velge type av legemiddel
    System.out.println("Velg type: ");
    System.out.println("a) Vanlig");
    System.out.println("b) Vanedannende");
    System.out.println("c) Narkotisk");
    String valgtLegemiddel = sc.nextLine();

    //variabler for aa konverterte streng til double og int
    double pris = 0;
    double virkestoff = 0;
    int styrke = 0;

    //hjelpevariabel for aa sjekke at alle streng-tallene blir konvertert til int/double
    boolean sjekk = true;
    while(sjekk){
      //try-catch: for aa sikre at brukeren skriver tall og ikke tekst
      try{
        System.out.println("Pris: ");
        pris = Double.valueOf(sc.nextLine());

        System.out.println("Virkestoff: ");
        virkestoff = Double.valueOf(sc.nextLine());

        System.out.println("Styrke: (skriv 0 dersom du ikke vil skrive virkestoff)");
        styrke = Integer.parseInt(sc.nextLine());

        //dersom alle disse tallene blir konvertert saa blir sjekk false og denne lokken stopper
        sjekk = false;
      }catch(NumberFormatException i){
        System.out.println("Vennligst skriv igjen med riktige tall.");
      }
    }

    //legger til de ulike legemidlene avhengig av hva brukeren har svart
    if(valgtLegemiddel.compareTo("a")==0){ //legger til vanlig legemiddel
      Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
      legemiddelListe.leggTil(vanlig);
      System.out.println("Legemidlet ble registert.");

    }else if(valgtLegemiddel.compareTo("b") ==0){//legger til Vanedannende legemiddel
      Vanedannende vd = new Vanedannende(navn,pris,virkestoff,styrke);
      legemiddelListe.leggTil(vd);
      System.out.println("Legemidlet ble registert.");

    }else if(valgtLegemiddel.compareTo("c")==0){//legger til Narkotisk legemiddel
      Narkotisk nk = new Narkotisk(navn,pris,virkestoff,styrke);
      legemiddelListe.leggTil(nk);
      System.out.println("Legemidlet ble registert.");

    }
  }

  private static void leggTilLege(Scanner sc){
    System.out.println("Fyll ut informasjon om legen");
    System.out.println("Navn: ");

    String navn = sc.nextLine();
    boolean sjekk = true;
    int kontrollid = 0;
    while(sjekk){
      try{
        System.out.println("Kontrollid: (skriv 0 for vanlig lege) ");
        kontrollid = Integer.parseInt(sc.nextLine());

        //dersom alle disse tallene blir konvertert saa blir sjekk false og denne lokken stopper
        sjekk = false;
      }catch(NumberFormatException i){
        System.out.println("Vennligst skriv igjen med riktige tall.");
      }
    }
    if(kontrollid == 0){ //vanlig lege
      Lege lege = new Lege(navn);
      legeListe.leggTil(lege);
      System.out.println("Legen ble registert.");
    }else if(kontrollid > 0){ //spesialist
      Spesialist spLege = new Spesialist(navn,String.valueOf(kontrollid));
      legeListe.leggTil(spLege);
      System.out.println("Legen ble registert.");
    }else{
      System.out.println("Feil: kontrollid kan ikke vaere mindre 0");
    }
  }

  private static void leggTilResept(Scanner sc){
    System.out.println("Fyll ut informasjon om resepten");

    System.out.println("Legenavn: ");
    String legeNavn = sc.nextLine();

    //brukeren maa velge mellom de ulike resepttypene
    System.out.println("Velg resepttype: ");
    System.out.println("a) Hvit");
    System.out.println("b) Blaa");
    System.out.println("c) Millitaer");
    System.out.println("d) PResept");
    String valgtType = sc.nextLine();

    boolean sjekk = true;
    int legemiddelNummer = 0;
    int pasientID = 0;
    int reit = 0;

    while(sjekk){
      try{
        System.out.println("Legemiddelnummer: ");
        legemiddelNummer = Integer.parseInt(sc.nextLine());

        System.out.println("PasientID: ");
        pasientID = Integer.parseInt(sc.nextLine());

        System.out.println("Reit: (skriv 0 dersom ingen reit)");
        reit = Integer.parseInt(sc.nextLine());

        sjekk = false;

      }catch(NumberFormatException i){
        System.out.println("Vennligst skriv igjen med riktige tall.");
      }
    }

    //definerer disse for aa fylle dem med de objektene brukeren velger
    Legemiddel legemiddel = null;
    Pasient pasient = null;
    Lege lege = null;

    //try/catch: for aa finne legemiddel, pasient og legen som bruker velger
    try{
      for(Legemiddel l: legemiddelListe){
        if(l.hentId() == legemiddelNummer){
          legemiddel = l;
        }
      }

    }catch(Exception e){
      System.out.println("Legemidlet finnes ikke.");
    }

    try{
      for(Pasient p: pasientListe){
        if(p.hentId()==pasientID){
          pasient = p;
        }
      }
    }catch(Exception e){
      System.out.println("Pasient finnes ikke.");
    }

    try{
      for(Lege lg: legeListe){
        if(lg.hentLegeNavn().compareTo(legeNavn)==0){
          lege = lg;
        }
      }

    }catch(Exception e){
      System.out.println("Legen finnes ikke.");
    }

    //legger til de ulike resepttypene
    if(valgtType.compareTo("a")==0){ //legger til hvitResept
      try{
        Resept hvitResept = lege.skrivHvitResept(legemiddel, pasient, reit);
        reseptListe.leggTil(hvitResept);
        System.out.println("Resepten ble registert.");

      }catch(UlovligUtskrift e){
        System.out.println(e);
      }
    }

    else if(valgtType.compareTo("b")==0){//legger til blaResept
      try{
        Resept blaResept = lege.skrivBlaaResept(legemiddel,pasient,reit);
        reseptListe.leggTil(blaResept);
        System.out.println("Resepten ble registert.");

      }catch(UlovligUtskrift e){
        System.out.println(e);
      }
    }

    else if(valgtType.compareTo("c")==0){//legger til millitaerResept
      try{
        Resept millitaerResept = lege.skrivMilitaerResept(legemiddel,pasient,reit);
        reseptListe.leggTil(millitaerResept);
        System.out.println("Resepten ble registert.");

      }catch(UlovligUtskrift e){
        System.out.println(e);
      }
    }

    else if(valgtType.compareTo("d")==0){//legger til pResept
      try{
        Resept pResept = lege.skrivPResept(legemiddel,pasient);
        reseptListe.leggTil(pResept);
        System.out.println("Resepten ble registert.");

      }catch(UlovligUtskrift e){
        System.out.println(e);
      }
    }

  }

}
