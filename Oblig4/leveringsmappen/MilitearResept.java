class MilitearResept extends HvitResept{
  MilitearResept(Legemiddel legemiddel, Lege utskrivendeLege,Pasient pasient, int reit){
    super(legemiddel,utskrivendeLege,pasient,reit);
  }
  public int prisAaBetale(int pris){
  //kundene faar 100 prosent rabatt
    return 0;
  }

  public String type(){
    return "Millitaer";
  }

  public String toString(){
    return "ReseptID: " +reseptID + "|Reit: " + reit + "|Resepttype: " + type() +
                "|Legemiddel: " + legemiddel.hentNavn() + "|Lege: "+ utskrivendeLege
                    + "|PasientID : " +pasient.hentId();
  }
}
