class BlaResept extends Resept{
  BlaResept(Legemiddel legemiddel, Lege utskrivendeLege,int pasientId, int reit){
    super(legemiddel,utskrivendeLege,pasientId,reit);
  }

  public String farge(){
    return "Blaa";
  }

  //for blaResept betaler kundene kun 25 prosent
  public int prisAaBetale(int pris){
    double nyPris = pris*0.25;
    return (int)nyPris;
  }

  public String toString(){
    return "Legemiddel: " + legemiddel +" reseptID: " +reseptID+ " Lege: "+ utskrivendeLege +
                      " pasientId : " +pasientId + " reit: " + reit + " farge: " + farge();
  }


}
