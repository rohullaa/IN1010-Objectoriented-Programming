class TestLegemiddel{
  public static void main(String[] args) {

    Legemiddel[] arrayLegemidler = new Legemiddel[3];
    arrayLegemidler[0] = new Vanlig("VanligLegemiddel",150,0.8);
    arrayLegemidler[1] = new Narkotisk("NarkotiskLegemiddel",500,10.8,11);
    arrayLegemidler[2] = new Vanedannende("VanedannendeLegemiddel",100,20.8,1);

    //testing:
    //lager arrays av alle dataene
    String[] arrayNavn = {"VanligLegemiddel","NarkotiskLegemiddel","VanedannendeLegemiddel"};
    int[] arrayPris = {150,500,100};
    double[] arrayVirkestoff = {0.8,10.8,20.8};

    //definerer noen nye variabler for å teste
    int forventetLegemiddelId = 1;
    int indeks = 0;

    for(Legemiddel l: arrayLegemidler){
      System.out.println(l);

      //sjekker om forventningene stemmer med klassene
      boolean testNavn = l.hentNavn() == arrayNavn[indeks];
      boolean testID = l.hentId() == forventetLegemiddelId;
      boolean testPris = l.hentPris() == arrayPris[indeks];
      boolean testVirkestoff = l.hentVirkestoff() == arrayVirkestoff[indeks];

      //enkel if-test som printer "Riktig" dersom alle detaljene stemmer
      if(testNavn && testID && testPris && testVirkestoff){
        System.out.println("Riktig: alle variablene stemmer.");
      }else{
        System.out.println("Feil: ");
        System.out.println("testNavn: "+ testNavn +" testID: "+ testID+ " testPris: "
                            + testPris + " testVirkestoff: "+ testVirkestoff );
      }
      forventetLegemiddelId++;
      indeks++;
      System.out.println("_______________________________________");
    }
  }
}
