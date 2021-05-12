import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;

class TreBegre{
  static Svar[] svar = new Svar[3];
  public static void main(String[] args) {
    JFrame vindu = new JFrame("Tre begre og en ball");
    vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    vindu.add(panel);

    JLabel tavle = new JLabel("Gjett under hvilken knapp ligger ballen");

    Random tilfeldig = new Random();
    int valgt = tilfeldig.nextInt(3);

    JPanel alternativer = new JPanel();
    for (int i = 0;i<3 ;i++ ) {
      String s = "Knapp" + Integer.toString(i+1);
      svar[i] = new Svar(s,valgt);
      svar[i].initGui();
      alternativer.add(svar[i]);
    }

    panel.add(tavle);

    vindu.pack();
    vindu.setVisible(true);
  }
}
class Svar extends JButton{
  String s;
  int valgt;
  Svar(String s, int valgt){
    super(s);
    this.s = s;
    this.valgt = valgt;
  }

  class Velger implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      if(valgt) setText("OK");
      else setText("Nei");
    }
  }

  public void initGui(){
    addActionListener(new Velger());
  }
}
