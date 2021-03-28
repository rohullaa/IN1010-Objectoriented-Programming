class TestResept{
  public static void main(String[] args){
    Vanlig legemiddel= new Vanlig("VanligLegemiddel",150,0.8);
    Lege lege = new Lege();

    Resept[] arrayResepter = new Resept[3];
    arrayResepter[0] = new PResept(legemiddel,lege,1);
    arrayResepter[1] = new BlaResept(legemiddel,lege,2,2);
    arrayResepter[2] = new MilitearResept(legemiddel,lege,3,12);

    //forventede verdier:
    int[] forventedeReseptID = {1,2,3};
    Legemiddel[] forventedeLegemidler ={legemiddel,legemiddel,legemiddel};
    Lege[] forventedeLege = {lege,lege,lege};
    int[] forventedePasientId ={1,2,3};
    int[] forventedeReit = {3,2,12};
    String[] forventedeFarge = {"Hvit","Blaa","Hvit"};
    int[] forventedePris = {42,(int)37.5,0};

    int i = 0;
    for(Resept r:arrayResepter){
      System.out.println(r);
      //enkel test som sjekker alle dataene ved en true eller false test.
      //faar true dersom det stemmer, false ellers
      boolean testReseptID = r.hentId() == forventedeReseptID[i];
      boolean testLegemiddel = r.hentLegemiddel() == forventedeLegemidler[i];
      boolean testLege = r.hentLege() == forventedeLege[i];
      boolean testPasientID = r.hentPasientId() == forventedePasientId[i];
      boolean testReit = r.hentReit() == forventedeReit[i];
      boolean testFarge = r.farge() == forventedeFarge[i];
      boolean testPris = r.prisAaBetale(150) == forventedePris[i];
      //enkel if-test som printer "Riktig" dersom alle detaljene stemmer
      if(testReseptID&&testLegemiddel&&testLege&&testPasientID&&testReit&&testFarge&&testPris){
        System.out.println("Riktig: alt stemmer");
      }else{
        System.out.println("Feil: " +" testReseptID: "+testReseptID+
                                    " testLegemiddel: " + testLegemiddel+ " testLege: "+testLege+
                                    " testPasientID "+testPasientID+" testReit: "+testReit+
                                    " testFarge: "+testFarge+" testPris: "+testPris);
      }
      i++;
    }



  }
}
