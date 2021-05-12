import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Sjakkbrett{
  public static void main(String[] args) {
    JFrame vindu = new JFrame("Sjakkbrett");
    vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    vindu.add(panel);

    JPanel rutenett = new JPanel();
    rutenett.setLayout(new GridLayout(8,8));
    for(int i = 1; i <= 8; i++){
      for(int j = 1; j <= 8; j++){
        if((i+j) % 2 == 0){
          JLabel nr = new JLabel("");
          nr.setPreferredSize(new Dimension(50,50));
          nr.setBackground(Color.BLACK);
          nr.setOpaque(true);
          rutenett.add(nr);
        }else{
          JLabel nr = new JLabel("");

          nr.setPreferredSize(new Dimension(50,50));
          nr.setBackground(Color.WHITE);
          nr.setOpaque(true);
          rutenett.add(nr);
        }
      }
    }
    panel.add(rutenett);
    vindu.pack();
    vindu.setVisible(true);
  }
}
