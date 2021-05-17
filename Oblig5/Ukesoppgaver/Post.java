public class Post{
  private String beskrivelse;
  private String mottaker;
  public Post(String beskrivelse,String mottaker){
    this.mottaker = mottaker;
    this.beskrivelse = beskrivelse;
  }

  public String hentBeskrivelse(){return beskrivelse;}
  public String hentMottaker(){return mottaker;}
}
