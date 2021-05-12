import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class LesGeometri{
  static ArrayList<String> infoRec = new ArrayList<>();
  static ArrayList<String> infoCir = new ArrayList<>();
  static ArrayList<String> infoPol = new ArrayList<>();

  public static void main(String[] args) {
    JFileChooser velger = new JFileChooser();
    int resultat = velger.showOpenDialog(null);
    if (resultat != JFileChooser.APPROVE_OPTION){
      System.exit(1);
    }
    File f = velger.getSelectedFile();
    Scanner scanner = null;
    try{
      scanner = new Scanner(f);
    }catch (FileNotFoundException e) {
      System.exit(1);
    }
    lesFil(scanner);

    //oppretter vindu og panel:
    JFrame vindu = new JFrame("Tegning av ulike geometrier!");
    vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    vindu.add(panel);

    panel.add(new Tegning(infoRec,infoCir,infoPol));

    vindu.pack();
    vindu.setVisible(true);
  }
  public static void lesFil(Scanner scanner){
    while(scanner.hasNextLine()){
      String linje = scanner.nextLine();
      String[] info = linje.split(" ");
      int siste = info.length -1;

      if(info.length == 4 && info[siste].equals("RED") || info[siste].equals("BLUE")){
        infoCir.add(linje);
      }else if(info.length == 5 && info[siste].equals("RED") || info[siste].equals("BLUE")){
        infoRec.add(linje);
      }else if(info.length == 4 && Integer.parseInt(info[siste]) > 0){
        infoRec.add(linje + " BLACK");
      }else if(info.length > 5){
        infoPol.add(linje);
      }
    }
  }
}
class Tegning extends JComponent{
  static ArrayList<String> infoRec;
  static ArrayList<String> infoCir;
  static ArrayList<String> infoPol;

  Tegning(ArrayList<String> infoRec,ArrayList<String> infoCir,ArrayList<String> infoPol){
    setPreferredSize(new Dimension(450,250));
    this.infoCir= infoCir;
    this.infoRec= infoRec;
    this.infoPol = infoPol;
  }
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    tegningRec(g2);
    tegningCir(g2);
    tegningPol(g2);
  }
  public void tegningRec(Graphics2D g2){
    for(String linje: infoRec){
      String[] info = linje.split(" ");
      int x = Integer.parseInt(info[0]);
      int y = Integer.parseInt(info[1]);
      int width = Integer.parseInt(info[2]);
      int height = Integer.parseInt(info[3]);
      String farge = info[4];
      if(farge.equals("RED")){
        g2.setColor(Color.RED);
      }else if(farge.equals("BLACK")){
        g2.setColor(Color.BLACK);
      }else{
        g2.setColor(Color.BLUE);
      }
      g2.fillRect(x,y,width,height);
    }
  }
  public void tegningPol(Graphics2D g2){
    for(String linje: infoPol){
      String[] info = linje.split(" ");

      int lengde = (info.length -1);
      int x[] = new int[lengde];
      int y[] = new int[lengde];

      for(int i = 0; i < lengde; i++){
        String[] koordinater = info[i].split(",");
        int x1 = Integer.parseInt(koordinater[0]);
        int y1 = Integer.parseInt(koordinater[1]);

        x[i]  = x1;
        y[i] = y1;
      }

      String farge = info[lengde];
      if(farge.equals("RED")){
        g2.setColor(Color.RED);
      }else if(farge.equals("BLACK")){
        g2.setColor(Color.BLACK);
      }else{
        g2.setColor(Color.BLUE);
      }
      g2.fillPolygon(x,y,info.length -1);
    }
  }

  public void tegningCir(Graphics2D g2){
    for(String linje: infoCir){
      String[] info = linje.split(" ");
      int x = Integer.parseInt(info[0]);
      int y = Integer.parseInt(info[1]);
      int radius = Integer.parseInt(info[2]);
      String farge = info[3];
      if(farge.equals("RED")){
        g2.setColor(Color.RED);
      }else if(farge.equals("BLACK")){
        g2.setColor(Color.BLACK);
      }else{
        g2.setColor(Color.BLUE);
      }
      g2.fillOval(x,y,radius,radius);
    }
  }
}
