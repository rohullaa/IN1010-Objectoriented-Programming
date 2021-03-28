class PResept extends HvitResept{
  PResept(Legemiddel legemiddel, Lege utskrivendeLege,Pasient pasient){
    super(legemiddel,utskrivendeLege,pasient,0);

  }

  public int prisAaBetale(int pris){
  //kundene faar 108 kr rabatt
    if(pris >= 108){
      int nyPris = pris - 108;
      return nyPris;
    }else{
      return 0;
    }
  }

  public String type(){
    return "p";
  }

  public String toString(){
    return "ReseptID: " +reseptID + "|Reit: " + reit + "|Resepttype: " + type() +
                "|Legemiddel: " + legemiddel.hentNavn() + "|Lege: "+ utskrivendeLege
                    + "|PasientID : " +pasient.hentId();
  }
}
