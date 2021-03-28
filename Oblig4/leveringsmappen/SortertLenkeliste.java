class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

  @Override
  public void leggTil(T x){
    //to tilfeller: hvis listen er tom og listen har mange elementer
    //Deretter ser vi etter elementer i listen som er større enn det vi skal sett inn

    if(stoerrelse() == 0){
      super.leggTil(x);
    }else{
      //itererer gjennom alle elementene i listen
      for(int i = 0; i < stoerrelse(); i++){
        //sammenligner alle elementene med input x, og sjekker om det er storre enn 0
        if(hent(i).compareTo(x) > 0){
          //hvis det er storre enn 0, saa blir den satt inn i i-te posisjon og de
          //andre elementene blir flyttet ett hakk fremover
          super.leggTil(i,x);
          return; //avslutter metoden naar det har skjedd
        }
      }
      //if-setningnen ikke gjor noe, saa blir x bare satt inn i listen paa vanlig maate
      super.leggTil(x);
    }

  }



  @Override
  public T fjern(){
    //fjerner elementet paa siste plassen ved aa bruke fjern(int pos) metoden
    return fjern(stoerrelse() - 1);
  }

  @Override
  public void sett(int pos, T x)throws UnsupportedOperationException{
    throw new UnsupportedOperationException();
  }

  @Override
  public void leggTil(int pos, T x)throws UnsupportedOperationException{
    throw new UnsupportedOperationException();
  }

}
