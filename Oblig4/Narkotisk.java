class Narkotisk extends Legemiddel{

  protected int styrke;
  Narkotisk(String navn, double pris, double virkestoff,int styrke){
    super(navn,pris,virkestoff);
    this.styrke = styrke;
  }

  protected int hentNarkotiskStyrke(){
    return styrke;
  }

  public String toString(){
    return "Navn: " + navn +"|"+" ID: " +ID+"|"+ " Pris: "
            + pris + "|"+" Virkestoff: " + virkestoff + "|"+" Styke: " + styrke;
  }
}
