class GiOgTa implements Runnable{
  private int verdi;
  private Bank bank;
  GiOgTa(Bank bank, int verdi){
    this.bank = bank;
    this.verdi = verdi;
  }
  public void run(){
    for(int i = 0; i< 10; i++){
      bank.ta(verdi);
      bank.gi(verdi);
      System.out.println(bank.saldo());
    }

  }
}
