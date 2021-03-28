class Spesialist extends Lege implements Godkjenningsfritak{
  protected String kontrollID;
  Spesialist(String legeNavn, String kontrollID){
    super(legeNavn);
    this.kontrollID = kontrollID;
  }
  @Override
  public String hentKontrollID(){
    return kontrollID;
  }

  public String toString(){
    return "Navn: " + legeNavn +" kontrollID: " +kontrollID;
  }
}
