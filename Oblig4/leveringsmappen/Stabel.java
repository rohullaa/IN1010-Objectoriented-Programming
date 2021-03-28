class Stabel<T> extends Lenkeliste<T>{
  public void leggPaa(T x){
    //her er det ingen forandringer, vi setter bare x inn på slutten av listen
    leggTil(x);
  }

  public T taAv(){
    //her maa vi fjerne det siste elementet
    return fjern(stoerrelse() -1);

  }

}
