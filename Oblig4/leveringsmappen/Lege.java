class Lege implements Comparable <Lege>{
  protected String legeNavn;
  protected Lenkeliste<Resept> utskrevedeResepter;

  public Lege(String legeNavn){
    this.legeNavn = legeNavn;
    utskrevedeResepter = new Lenkeliste<Resept>();
  }

  public Lenkeliste<Resept> hentUtskrevedeResepter(){
    return utskrevedeResepter;
  }

  public String hentLegeNavn(){
    return legeNavn;
  }

  public String toString(){
    return "Navn: " + legeNavn;
  }

  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{
    //dersom denne legen er ikke Spesialist og legemidlet inneholder Narkotisk saa blir det
    //kastet ut UlovligUtskrift
    if(!(this instanceof Spesialist) && legemiddel instanceof Narkotisk){
      throw new UlovligUtskrift(this,legemiddel);
    }
    //ellers lages det nytt objekt av HvitResept og den blir lagt inn i listen ovenfor
    HvitResept resept = new HvitResept(legemiddel,this,pasient,reit);
    utskrevedeResepter.leggTil(resept);
    pasient.leggTilResept(resept);
    return resept;
  }

  public MilitearResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{
    //dersom denne legen er ikke Spesialist og legemidlet inneholder Narkotisk saa blir det
    //kastet ut UlovligUtskrift
    if(!(this instanceof Spesialist) && legemiddel instanceof Narkotisk){
      throw new UlovligUtskrift(this,legemiddel);
    }
    //ellers lages det nytt objekt av HvitResept og den blir lagt inn i listen ovenfor
    MilitearResept resept = new MilitearResept(legemiddel,this,pasient,reit);
    utskrevedeResepter.leggTil(resept);
    pasient.leggTilResept(resept);
    return resept;
  }

  public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient)throws UlovligUtskrift{
    //dersom denne legen er ikke Spesialist og legemidlet inneholder Narkotisk saa blir det
    //kastet ut UlovligUtskrift
    if(!(this instanceof Spesialist) && legemiddel instanceof Narkotisk){
      throw new UlovligUtskrift(this,legemiddel);
    }
    //ellers lages det nytt objekt av HvitResept og den blir lagt inn i listen ovenfor
    PResept resept = new PResept(legemiddel,this,pasient);
    utskrevedeResepter.leggTil(resept);
    pasient. leggTilResept(resept);
    return resept;
  }

  public BlaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{
    //dersom denne legen er ikke Spesialist og legemidlet inneholder Narkotisk saa blir det
    //kastet ut UlovligUtskrift
    if(!(this instanceof Spesialist) && legemiddel instanceof Narkotisk){
      throw new UlovligUtskrift(this,legemiddel);
    }
    //ellers lages det nytt objekt av HvitResept og den blir lagt inn i listen ovenfor
    BlaResept resept = new BlaResept(legemiddel,this,pasient,reit);
    utskrevedeResepter.leggTil(resept);
    pasient. leggTilResept(resept);
    return resept;
  }

  @Override
  public int compareTo(Lege lege){
    return this.legeNavn.compareTo(lege.hentLegeNavn());
  }

}
