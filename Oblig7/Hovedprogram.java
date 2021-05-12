import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class Hovedprogram{
  public static void main(String[] args){

    //bruker JFileChooser til aa lese filen
    JFileChooser velger = new JFileChooser();
    int resultat = velger.showOpenDialog(null);
    if (resultat != JFileChooser.APPROVE_OPTION){
      System.exit(1);
    }
    File fil = velger.getSelectedFile();
    Scanner scanner = null;
    try{
      scanner = new Scanner(fil);
    }catch (FileNotFoundException e) {
      System.exit(1);
    }

    //bruker filen til aa opprette Labyrint objekt
    Labyrint l = null;
    try {
        l = new Labyrint(fil);
        System.out.println(l);
    } catch (FileNotFoundException e) {
        System.out.printf("FEIL: det gikk noe feil med aa lese filen.");
        System.exit(1);
    }

    //oppretter vindu og panel:
    JFrame vindu = new JFrame("Velkommen til Labyrint!");
    vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Panel panel = new Panel(l);
    panel.initGUI(); //setter i gang panelet
    vindu.add(panel);


    //prover aa bestemme storrelsen til vinduet slik at alle filene kan enkelt vises
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int vinduStorrelse = panel.hentVinduStorrelse();
    if(vinduStorrelse == 0)
      vindu.setPreferredSize(new Dimension(screenSize.width * 10/25, screenSize.height* 2/3));
    else
      vindu.setPreferredSize(new Dimension(screenSize.width * 1/2, screenSize.height));

    vindu.pack();
    vindu.setVisible(true);

  }
}

class Panel extends JPanel{
  private Labyrint l;
  JButton sluttknapp;
  JLabel velkomstTekst;
  Brett brett;

  Panel(Labyrint l){
    this.l = l;
    brett = new Brett(this,l);
  }

  void initGUI(){
    brett.initGUI(); //setter i gang brettet sitt gui
    add(brett);

    velkomstTekst = new JLabel("Vennligst velg en hvit rute.");
    add(velkomstTekst);

    sluttknapp = new JButton("Exit");
    class Stoppbehandler implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            System.exit(0);
        }
    }
    sluttknapp.addActionListener(new Stoppbehandler());
    add(sluttknapp);
  }

  int hentVinduStorrelse(){
    return brett.hentVinduStorrelse();
  }


}

class Brett extends JPanel{
  private int kol;
  private int rad;
  private Rute[][] labyrintArray;
  private Labyrint l;
  private Panel panel;
  private int storrelse;
  protected Knapp [][] knapper ;
  public int vinduStorrelse;

  Brett(Panel panel, Labyrint l){
    this.l = l;
    this.panel = panel;

    //henter arrayen fra Labyrint-objektet
    labyrintArray = l.hentLabyrintArray();
    kol = labyrintArray.length;
    rad = labyrintArray[0].length;

    //noen if-statements for aa avgjore storrelsen til knappene
    if(kol > 50 || rad > 50){
      storrelse = 10;
      this.vinduStorrelse = 1;
    }
    else if(kol < 25 && kol > 20 || rad > 20 && rad < 25){
      storrelse  = 30;
      this.vinduStorrelse = 1;
    }
    else if(kol < 50 && kol > 30 || rad < 50 && rad > 30){
      storrelse  = 20;
      this.vinduStorrelse = 1;
    }else if(kol < 20 && kol > 10 || rad < 20 && rad > 10){
      storrelse  = 50;
      this.vinduStorrelse = 1;
    }
    else{
      storrelse = 50;
      this.vinduStorrelse = (int) 2/3;
    }

    //fyller opp knapper arrayet med Knapp-objekter
    knapper = new Knapp[kol][rad];
    for (int i = 0; i<kol ;i++){
      for(int j=0; j < rad; j++){
        knapper[i][j] = new Knapp(this,l,i,j,panel,storrelse);
      }
    }
  }

  void initGUI(){
    //bruker setLayout til aa sette opp knappene
    setLayout(new GridLayout(kol,rad));
    for (int x = 0;x < kol ;x++){
      for(int y=0; y < rad; y++){
        Knapp k = knapper[x][y];
        //dersom rute-objektet er instans av SortRute er saa lages det sort knapp
        if(labyrintArray[x][y] instanceof SortRute){
          k.initGUISort();
          add(k);
        }else{ //ellers saa lages det hvit knapp
          k.initGUIHvit();
          add(k);
        }
      }
    }
  }

  //markerer alle utveiene ved aa sette X
  void merkUtvei(int x, int y){
    knapper[x][y].setText("X");
    knapper[x][y].setForeground(Color.RED);
  }

  //farger start-knappen rod
  void fargStart(int x, int y){
    knapper[x][y].setBackground(Color.RED);
  }

  void restart(){
    //restarter ved fjerne alle markeringene fra tidligere utveiene
    for (int x = 0;x < kol ;x++){
      for(int y=0; y < rad; y++){
        if(labyrintArray[x][y] instanceof HvitRute || labyrintArray[x][y] instanceof Aapning){
          knapper[x][y].setBackground(Color.WHITE); //fargen til knappen
          knapper[x][y].setText("");
        }
      }
    }
  }

  int hentVinduStorrelse(){
    return this.vinduStorrelse;
  }

}

class Knapp extends JButton{
  //denne klassen setter i gangene knappene
  Labyrint l;
  Brett brett;
  Panel panel;

  int x;
  int y;
  int storrelse;

  Knapp(Brett brett, Labyrint l,int x, int y, Panel panel, int storrelse){
    this.brett = brett;
    this.l = l;
    this.x = x;
    this.y = y;
    this.panel = panel;
    this.storrelse = storrelse;
  }

  //setter sorte knapper
  void initGUISort(){
    setBorder(BorderFactory.createLineBorder(Color.black)); //setter på grensene
    setPreferredSize(new Dimension(storrelse, storrelse)); //setter storrelsen til knappen
    setBackground(Color.BLACK); //fargen til knappen
    setOpaque(true);
  }

  //setter hvite knapper
  void initGUIHvit(){
    setBorder(BorderFactory.createLineBorder(Color.black));
    setPreferredSize(new Dimension(storrelse, storrelse));
    setBackground(Color.WHITE);
    setOpaque(true);

    //setter paa en actionLister
    addActionListener(new Hvit(x,y,l,brett,panel));
  }
}

class Hvit implements ActionListener{
  //actionLister klassen. Her bestemmes hva som skal skje dersom en klikker paa de hvite knappene

  protected int x;
  protected int y;
  protected Labyrint l;
  protected Brett brett;
  protected Panel panel;

  Hvit(int x, int y,Labyrint l,Brett brett,Panel panel){
    this.x = x;
    this.y = y;
    this.l = l;
    this.brett = brett;
    this.panel = panel;
  }

  @Override
  public void actionPerformed(ActionEvent e){
    //knappen med koordinater x,y blir trukket paa. Saa skjer resten av metoden:
    brett.restart();

    System.out.println("Start: " + "(" + x + ", " + y + ")");
    brett.fargStart(x,y);

    //setter paa start-koordinater
    JLabel visStart = new JLabel("");
    visStart.setText("Start: " + "(" + x + ", " + y + ")");

    //finner utveiene
    ArrayList<ArrayList<Tuppel>> utVeier = l.hentUtveier(x,y);
    int x1;
    int y1;
    JLabel visUtvei = new JLabel("");

    if(utVeier.size() == 0 ){ //dersom ingen utvei
      brett.merkUtvei(x,y);
      System.out.println("Ingen utveier \n");
      visUtvei.setText(" - Ingen utVeier");

    }else{ //dersom det finnes utvei
      System.out.println("Antall losninger ble funnet: " + utVeier.size());
      visUtvei.setText(" - Antall losninger ble funnet: " + utVeier.size());

      ArrayList<Tuppel> lis = utVeier.get(0); //viser den forste losningen
      for(Tuppel t: lis){
        System.out.println(t);
        String tekst = t.toString().replaceAll("[()]", ""); //fjerner bort parantesene
        String[] tall_str = tekst.split(", ");
        x1 = Integer.parseInt(tall_str[0]);
        y1 = Integer.parseInt(tall_str[1]);
        brett.merkUtvei(x1,y1); //markerer alle utveiene
      }
      System.out.println();
    }

    //setter informasjon om starten og utveiene i panel
    JPanel utskriftsPanel = new JPanel();
    utskriftsPanel.add(visStart);
    utskriftsPanel.add(visUtvei);
    panel.add(utskriftsPanel);
  }

}
