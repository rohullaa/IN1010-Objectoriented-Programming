public class TestProgram{
  public static void main(String[] args) {
    Beholder<Sirkel> element = new Beholder <>();
    Sirkel sirkel = new Sirkel(1);
    Kvadrat kvadrat = new Kvadrat(1);

    element.settInn(sirkel);
    element.settInn(kvadrat);
  }
}
