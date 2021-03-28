class HvitResept extends Resept{
  HvitResept(Legemiddel legemiddel, Lege utskrivendeLege,Pasient pasient, int reit){
    super(legemiddel,utskrivendeLege,pasient,reit);
  }

  //ingen rabatt for bare "hvit" resept
  //man maa spesifisere hva slags hvit resept en man skal ha
  public int prisAaBetale(int pris){
    return pris;
  }

  public String farge(){
    return "Hvit";
  }


  public String toString(){
    return "ReseptID: " +reseptID + "|Reit: " + reit + "|Resepttype: " + farge() +
                "|Legemiddel: " + legemiddel.hentNavn() + "|Lege: "+ utskrivendeLege
                    + "|PasientID : " +pasient.hentId();
  }
}
