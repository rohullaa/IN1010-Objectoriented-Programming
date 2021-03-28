class Pasient{
  protected String pasientNavn;
  protected String fodselsnummer;

  protected static int nyID = 1; //static variabler som vil øke med for hver ny objekt som blir lagret
  protected int pasientID;

  protected Stabel<Resept> reseptListe;

  public Pasient(String pasientNavn, String fodselsnummer){
    this.pasientNavn = pasientNavn;
    this.fodselsnummer = fodselsnummer;

    this.pasientID = nyID ++;
    reseptListe = new Stabel<Resept> ();
  }

  public int hentId(){
    return this.pasientID;
  }

  public String hentNavn(){
    return pasientNavn;
  }
  public String hentFodselsnummer(){
    return fodselsnummer;
  }


  //legge til nye resepter
  public void leggTilResept(Resept resept){
    reseptListe.leggPaa(resept);
  }

  //hente ut hele reseptlisten
  public Stabel<Resept> hentReseptListen(){
    return reseptListe;
  }

  public String toString(){
    return ("ID: " + pasientID + " | Navn: " + this.pasientNavn + " | Fodselsnummer: " + this.fodselsnummer);
  }

}
