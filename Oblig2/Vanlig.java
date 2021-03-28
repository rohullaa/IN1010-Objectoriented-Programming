class Vanlig extends Legemiddel{
  Vanlig(String navn, int pris, double virkestoff){
    super(navn,pris,virkestoff);
  }

  public String toString(){
    return "Navn: " + navn +" ID: " +ID+ " Pris: "
            + pris + " Virkestoff: " + virkestoff;
  }
}
