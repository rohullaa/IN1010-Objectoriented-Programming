class Katt implements Comparable <Katt>{
  private String navn;
  private int alder;

  public Katt(String navn, int alder){
    this.navn = navn;
    this.alder = alder;
  }

  public String toString(){
    return navn + " " + alder;
  }

  public int compareTo(Katt annen){
    if(alder < annen.alder){
      return -1;
    }else if(alder > annen.alder){
      return 1;
    }else{
      return 0;
    }
  }
}
