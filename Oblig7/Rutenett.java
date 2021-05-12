import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Rutenett{
  public static void main(String[] args) {
    JFrame vindu = new JFrame("Enkelt rutenett");
    vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    vindu.add(panel);

    JPanel rutenett = new JPanel();
    rutenett.setLayout(new GridLayout(3,3));
    for(int i = 1; i < 10; i++){
      JLabel nr = new JLabel("" + i);
      nr.setPreferredSize(new Dimension(50,50));
      nr.setHorizontalAlignment(JLabel.CENTER);
      nr.setVerticalAlignment(JLabel.CENTER);
      nr.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      rutenett.add(nr);
    }
    panel.add(rutenett);

    vindu.pack();
    vindu.setVisible(true);
  }
}
