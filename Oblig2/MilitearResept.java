class MilitearResept extends HvitResept{
  MilitearResept(Legemiddel legemiddel, Lege utskrivendeLege,int pasientId, int reit){
    super(legemiddel,utskrivendeLege,pasientId,reit);
  }
  public int prisAaBetale(int pris){
  //kundene faar 100 prosent rabatt
    return 0;
  }
  public String toString(){
    return "Legemiddel: " + legemiddel +" reseptID: " +reseptID+ " Lege: "+ utskrivendeLege +
                      " pasientId : " +pasientId + " reit: " + reit + " farge: " + farge();
  }
}
