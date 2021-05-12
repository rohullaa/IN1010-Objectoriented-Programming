import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//deklarere vindu:
JFrame vindu = new JFrame("Xxx");
vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//opprett tegneflaten
JPanel panel = new JPanel();
vindu.add(panel);

vindu.pack();
vindu.setVisible(true);

//lage trykknapper:
JButton knapp = new JButton;
panel.add(knapp);
class GjorNoe implements ActionListener{
  @Override
  public void actionPerformed(ActionEvent e){
    //do something
  }
}
//koble knapp og kode:
knapp.addActionListener(new GjorNoe());


//lage tekst-knapper:

//å velge en fil: JFileChooser
JFileChooser velger = new JFileChooser();
