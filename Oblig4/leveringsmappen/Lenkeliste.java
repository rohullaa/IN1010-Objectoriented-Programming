import java.util.*;

class Lenkeliste<T> implements Liste<T>,Iterable<T> {
  //implementerer en enveis lenketliste med start og slutt
  Node start = null;
  Node slutt = null;

  private class Node{
    Node neste = null;
    T data;
    Node(T x){
      data = x;
    }
  }


  //definerer denne klassen for aa kunne iterere gjennom listen.
  private class LenkelisteIterator<T> implements Iterator<T>{
    private Lenkeliste<T> lenkeliste;
    private int pos = 0;


    public LenkelisteIterator(Lenkeliste<T> lenkeliste){
      this.lenkeliste = lenkeliste;
    }

    @Override
    public T next(){
      if(hasNext()){
        return lenkeliste.hent(pos++);
      }
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext(){
      return (pos < lenkeliste.stoerrelse());
    }

  }

  //returnerer nytt LenkelisteIterator objekt
  public Iterator<T> iterator(){
    return new LenkelisteIterator(this);
  }


  public int stoerrelse(){
    int teller = 0;
    Node peker = start;
    while(peker != null){
      teller ++;
      peker = peker.neste;
    }
    return teller;
  }


  public void leggTil(int pos, T x) throws UgyldigListeIndeks{
    // her er det tre scenarier for pos: hvis pos = stoerrelse, pos > 0 and mindre enn stoerrelse
    // og pos = 0. Utfra dette maa vi kunne sette elementet paa riktig plass

    if( pos == stoerrelse()){
      //setter vi bare x i siste elementet
      leggTil(x);
    }else if(pos ==0){
      //her maa vi sette x i forste plassen, dvs vi maa flytte start elementetene mot andre plassen

      Node nyNode = new Node(x);
      Node tempNode = start;
      //nyNode sin neste skal vaere startnoden
      nyNode.neste = tempNode;
      //nynode skal ta plassen til startnoden
      start = nyNode;
      //slutten av listen skal vaere nynode sin neste
      slutt = nyNode.neste;

    }else if(pos > 0 && pos < stoerrelse()){
      Node nyNode = new Node(x);
      Node peker = start;
      //definerer en ny node som har forrige posisjon iforhold til pos
      Node forrigeNode = null;
      for(int i = 0; i < pos; i++){
        forrigeNode = peker;
        peker = peker.neste;
      }

      forrigeNode.neste = nyNode;  //setter forrigeNode sin neste til aa vaere nyNode
      nyNode.neste = peker; //nyNode sin neste er startNode
    } else{
      throw new UgyldigListeIndeks(pos);
    }
  }


  public void leggTil(T x){
    //her setter vi noder paa slutten. Her er det tre scenarier: listen inneholder bare 1 elementer,
    //listen er tom og listen inneholder flere elemeneter

    Node nyNode = new Node(x);
    if(start != null && slutt == null){ //hvis det er bare 1 element
      slutt = nyNode;
      start.neste = slutt;
    }else if(start == null){ // hvis listen er tom
      start = nyNode;
    }else{ //flere elementer
      Node peker = start;

      while(peker.neste != null){
        peker = peker.neste;
      }
      peker.neste = nyNode;
      slutt = nyNode;
    }
  }


  public void sett(int pos, T x) throws UgyldigListeIndeks{
    //for aa kunne sett inn elementer ved gitt posisjon saa maa listen ha minst 1 element
    if(stoerrelse() > 0){
      //elementet maa ogsaa vaere innen listen sin lengde
      if( pos >= 0 && pos < stoerrelse()){
        Node peker = start;
        for(int i = 0; i < pos ; i ++){
          peker = peker.neste;
        }
        peker.data = x;
      } else{
        //dersom posisjon er ikke innenfor listen sin lengde saa er UgyldigListeIndeks
        throw new UgyldigListeIndeks(pos);
      }
    }
    else{
      //dersom listen er tom saa er UgyldigListeIndeks
      throw new UgyldigListeIndeks(pos);
    }

  }


  public T hent(int pos)throws UgyldigListeIndeks{
    if (pos >= 0 && pos < stoerrelse()){
      Node peker = start;
      for(int i = 0; i < pos ; i++){
        peker = peker.neste;
      }
      return peker.data;
    }
    //dersom ingen av if-setningene fungerer saa er UgyldigListeIndeks
    throw new UgyldigListeIndeks(pos);
  }


  public T fjern(int pos)throws UgyldigListeIndeks{
    if (pos >= 0 && pos < stoerrelse()){
      if(stoerrelse() > 1){
        Node peker = start;
        //iterer gjennom hele listen og finner start sin neste
        for(int i = 0; i < pos -1; i++){
          peker = peker.neste;
        }
        //noden som skal fjernes er peker sin neste
        Node fjernNode = peker.neste;
        peker.neste = fjernNode.neste;
        return fjernNode.data;
      }else{
        Node fjernNode = start;
        start = null;
        return fjernNode.data;
      }

    }
    throw new UgyldigListeIndeks(pos);
  }


  public T fjern() throws UgyldigListeIndeks{
    if (stoerrelse() > 0){
      Node fjernNode = start;
      start = start.neste;
      return fjernNode.data;
    }
    throw new UgyldigListeIndeks(0);

  }


  public String toString(){
    Node tempNode = start;
    String innhold = "";
    while(tempNode != null){
      innhold = innhold + tempNode.data + " ";
      tempNode = tempNode.neste;
    }
    return innhold;
  }


}
