class DuplikatExeption extends Exception{
  public DuplikatExeption(String boktittel){
    super("Duplikat: " + boktittel);
  }
}
