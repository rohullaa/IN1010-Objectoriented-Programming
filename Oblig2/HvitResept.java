class HvitResept extends Resept{
  HvitResept(Legemiddel legemiddel, Lege utskrivendeLege,int pasientId, int reit){
    super(legemiddel,utskrivendeLege,pasientId,reit);
  }
  public String farge(){
    return "Hvit";
  }
  //ingen rabatt for bare "hvit" resept
  //man maa spesifisere hva slags hvit resept en man skal ha
  public int prisAaBetale(int pris){
    return pris;
  }
}
