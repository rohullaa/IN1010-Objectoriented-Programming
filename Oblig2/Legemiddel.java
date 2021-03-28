abstract class Legemiddel{
  protected String navn;
  protected int pris;
  protected double virkestoff;
  protected static int nyID = 1; //static variabler som vil øke med for hver ny objekt som blir lagret
  protected int ID;

  public Legemiddel(String navn, int pris, double virkestoff){
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    //øker og lagrer ID for hver ny objekt
    this.ID = nyID++;

  }

  protected int hentId(){
    return ID;
  }

  protected String hentNavn(){
    return navn;
  }

  protected int hentPris(){
    return pris;
  }

  protected double hentVirkestoff(){
    return virkestoff;
  }

  protected void settNyPris(int nyPris){
    pris = nyPris;
  }


}
