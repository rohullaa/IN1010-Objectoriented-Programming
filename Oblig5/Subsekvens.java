class SubSekvens{
  private int antall;
  private String sekvens;

  public SubSekvens(String sekvens,int antall){
    this.sekvens = sekvens;
    this.antall = antall;
  }

  public String hentSekvens(){
    return sekvens;
  }

  public int hentAntall(){
    return antall;
  }

  public void leggTilForekomster(int nyForekomst){
    antall += nyForekomst;
  }

}
