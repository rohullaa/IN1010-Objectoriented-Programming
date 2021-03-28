class IkkeMerPlassException extends Exception{
  public IkkeMerPlassException(String boktittel){
    super("Det er ikke mer plass til aa sette inn " +boktittel);
  }
}
