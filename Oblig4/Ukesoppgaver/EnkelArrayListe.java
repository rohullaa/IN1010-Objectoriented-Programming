import java.util.Iterator;

class EnkelArrayListe implements Iterable<String> {
  private String[] arr;
  private int maksStorrelse;
  private int storrelse = 0;

  @SuppressWarnings("unchecked")
  public EnkelArrayListe(int maksStorrelse) {
    this.maksStorrelse = maksStorrelse;
    arr = new String[maksStorrelse];
  }

  public void leggTil(String streng){
    if(storrelse >= maksStorrelse){
      throw new IllegalStateException("Ikke plass til flere");
    }
    arr[storrelse++] = strengen;
  }

  public Iterator<String> iterator(){
        return new ListeIterator();
  }

  private class ListeIterator implements Iterator<String>{
    private int gjeldendeIndeks = 0;
    public String next() {
      return arr[gjeldendeIndeks++];
    }

    public boolean hasNext(){
      return gjeldendeIndeks < storrelse;
    }
  }
}
