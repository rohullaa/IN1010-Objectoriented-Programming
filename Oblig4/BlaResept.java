class BlaResept extends Resept{
  BlaResept(Legemiddel legemiddel, Lege utskrivendeLege,Pasient pasient, int reit){
    super(legemiddel,utskrivendeLege,pasient,reit);
  }

  //for blaResept betaler kundene kun 25 prosent
  public int prisAaBetale(int pris){
    double nyPris = pris*0.25;
    return (int)nyPris;
  }

  public String farge(){
    return "Blaa";
  }


  public String toString(){
    return "ReseptID: " +reseptID + "|Reit: " + reit + "|Resepttype: " + farge() +
                "|Legemiddel: " + legemiddel.hentNavn() + "|Lege: "+ utskrivendeLege
                    + "|PasientID : " +pasient.hentId();
  }


}
