class Hovedprogram{
  public static void main(String[] args){
    //oppretter instanser fra alle klassene
    Vanlig vLegemiddel = new Vanlig("VanligLegemiddel",150,0.8);
    Narkotisk nLegemiddel = new Narkotisk("NarkotiskLegemiddel",500,10.8,11);
    Vanedannende vdLegemiddel = new Vanedannende("VanedannendeLegemiddel",100,20.8,1);

    Lege lege = new Lege("Dr. Newton");
    Spesialist spesLege = new Spesialist("Dr. Einstin","12");

    PResept pRespet = new PResept(vLegemiddel,lege,1);  
    BlaResept bResept= new BlaResept(nLegemiddel,lege,2,2);
    MilitearResept mResept = new MilitearResept(vdLegemiddel,lege,3,12);

    //printer ut informasjon om alle objektene
    System.out.println("Legemidler: ");
    System.out.println(vLegemiddel);
    System.out.println(nLegemiddel);
    System.out.println(vdLegemiddel);

    System.out.println("");
    System.out.println("Leger: ");
    System.out.println(lege);
    System.out.println(spesLege);

    System.out.println("");
    System.out.println("Resepter:");
    System.out.println(pRespet);
    System.out.println(bResept);
    System.out.println(mResept);


  }
}
