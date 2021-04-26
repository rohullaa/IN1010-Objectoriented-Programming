class Subsekvens{
  private int antall;
  private String sekvens;

  public Subsekvens(String sekvens){
    this.sekvens = sekvens;
    antall = 1;
  }

  public String hentSekvens(){
    return sekvens;
  }

  public int hentAntall(){
    return antall;
  }

  public void leggTilForekomster(int nyForekomst){
    antall = antall + nyForekomst;
  }

}
