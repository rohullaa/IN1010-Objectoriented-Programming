public class Person implements Comparable<Person>{
  private String navn;
  private int fodselsaar;
  private Person mor;
  private Person far;
  Person(String navn, int fodselsaar, Person mor, Person far){
    this.navn = navn;
    this.fodselsaar = fodselsaar;
    this.mor = mor;
    this.far = far;
  }

  private String hentNavn(){
    return navn;
  }

  private Person hentMor(){
    return mor;
  }
  private Person hentFar(){
    return far;
  }
  private int hentFodselsaar(){
    return fodselsaar;
  }

  public String toString(){
    return "Navn: " + navn + "fodselsaar: " + fodselsaar;
  }

  public Person finnEldsteKjenteForfader(){
    if(mor == null){
      if(far == null)
        return this;
      else
        return far.finnEldsteKjenteForfader();
    }

    if(far == null){
      return mor.finnEldsteKjenteForfader();
    }

    Person morside = mor.finnEldsteKjenteForfader();
    Person farside = far.finnEldsteKjenteForfader();

    if(morside.compareTo(farside) < 0)
      return morside;
    else
      return farside;
  }
  
  @Override
  public int compareTo(Person annenPerson){
    return fodselsaar-annenPerson.hentFodselsaar();
  }

}
