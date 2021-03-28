class Node{
  private int minneStr;
  private int antPros;

  public Node(int minneStorrelse, int antallProse){
    minneStr = minneStorrelse;
    antPros = antallProse;
  }

  public int antProsessorer(){
    return antPros;
  }

  public boolean nokMinne(int paakrevdMinne){
    if(minneStr >= paakrevdMinne){
      return true;
    }else{
      return false;
    }
  }
}
