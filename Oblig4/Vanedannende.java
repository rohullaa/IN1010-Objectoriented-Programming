class Vanedannende extends Legemiddel{
  private int styrke;
  Vanedannende(String navn, double pris, double virkestoff,int styrke){
    super(navn,pris,virkestoff);
    this.styrke = styrke;
  }

  private int hentVanedannendeStyrke(){
    return styrke;
  }

  public String toString(){
    return "Navn: " + navn +"|"+" ID: " +ID+"|"+ " Pris: "
            + pris + "|"+" Virkestoff: " + virkestoff +"|"+ " Styke: " + styrke;
  }
}
