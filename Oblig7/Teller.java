import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Teller{
  static int antall = 0;
  public static void main(String[] args) {
    JFrame vindu = new JFrame("Teller");
    vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    vindu.add(panel);

    JLabel tavle = new JLabel(" 0 ");

    JButton slutt = new JButton("Exit");
    class Stopper implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e){
        System.exit(0);
      }
    }
    slutt.addActionListener(new Stopper());

    JButton nullstiller = new JButton("0");
    class NullStiller implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e){
        antall = 0;
        tavle.setText(" " + antall+" ");
      }
    }
    nullstiller.addActionListener(new NullStiller());

    JButton add = new JButton("+");
    class OekAntall implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e){
        antall ++;
        tavle.setText("" + antall+"");
      }
    }
    add.addActionListener(new OekAntall());



    panel.add(tavle);panel.add(add);panel.add(nullstiller);panel.add(slutt);

    vindu.pack();
    vindu.setVisible(true);
  }
}
