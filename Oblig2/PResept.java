class PResept extends HvitResept{
  PResept(Legemiddel legemiddel, Lege utskrivendeLege,int pasientId){
    super(legemiddel,utskrivendeLege,pasientId,3);
    //denne resepten har alltid 3 reit og derfor setter vi den lik 3.
    
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

  public String toString(){
    return "Legemiddel: " + legemiddel +" reseptID: " +reseptID+ " Lege: "+ utskrivendeLege +
                      " pasientId : " +pasientId + " reit: " + reit + " farge: " + farge();
  }
}
