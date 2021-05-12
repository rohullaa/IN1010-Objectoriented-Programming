class Tuppel{
  public int k;
  public int r;

  public Tuppel(int k, int r){
    this.r = r;
    this.k = k;
  }

  @Override
  public String toString() {
    return "(" + k + ", " + r + ")";
  }
}
