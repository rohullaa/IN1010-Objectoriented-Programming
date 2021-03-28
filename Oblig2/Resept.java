abstract class Resept{
  protected Legemiddel legemiddel;
  protected Lege utskrivendeLege;
  protected int pasientId;
  protected int reit;
  protected static int nyID = 1;
  protected int reseptID;

  public Resept(Legemiddel legemiddel, Lege utskrivendeLege,int pasientId, int reit){
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.pasientId = pasientId;
    this.reit = reit;
    this.reseptID = nyID++;
  }

  protected int hentId(){
    return reseptID;
  }

  protected Legemiddel hentLegemiddel(){
    return legemiddel;
  }

  protected Lege hentLege(){
    return utskrivendeLege;
  }

  protected int hentPasientId(){
    return pasientId;
  }

  protected int hentReit(){
    return reit;
  }

  public boolean bruk(){
    //retunerer true dersom det er 1 eller fler reit igjen.
    //retunerer false dersom den er oppbrukt
    if(reit >= 1){
      reit --;
      return true;
    }else{
      return false;
    }
  }

  abstract public String farge();
  abstract public int prisAaBetale(int pris);

}
