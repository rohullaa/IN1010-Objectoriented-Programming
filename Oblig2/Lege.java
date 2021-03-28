class Lege{
  protected String legeNavn;

  public Lege(String legeNavn){
    this.legeNavn = legeNavn;
  }

  protected String hentLegeNavn(){
    return legeNavn;
  }

  public String toString(){
    return "Navn: " + legeNavn;
  }

}
