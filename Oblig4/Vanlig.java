class Vanlig extends Legemiddel{
  Vanlig(String navn, double pris, double virkestoff){
    super(navn,pris,virkestoff);
  }

  public String toString(){
    return "Navn: " + navn +"| ID: " +ID+ "| Pris: "
            + pris + "| Virkestoff: " + virkestoff;
  }
}
