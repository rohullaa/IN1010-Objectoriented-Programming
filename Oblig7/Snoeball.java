import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Snoeball{
  public static void main(String[] args) {
    //oppretter vindu
    JFrame vindu = new JFrame("Snoeball");
    vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //lager tegneflaten
    JPanel panel = new JPanel();
    vindu.add(panel);

    panel.add(new Tegning());


    //pakker ut vinduet
    vindu.pack();
    vindu.setVisible(true);
  }

}
class Tegning extends JComponent{
  Tegning(){
    setPreferredSize(new Dimension(300,300));
  }
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;

    g2.setColor(Color.WHITE);
    g2.fillOval(100,40,100,100);
    g2.setColor(Color.BLACK);
    g2.setStroke(new BasicStroke(5));
    g2.drawOval(100,40,100,100);

    g2.setColor(Color.WHITE);
    g2.fillOval(80,100,150,150);
    g2.setColor(Color.BLACK);
    g2.setStroke(new BasicStroke(5));
    g2.drawOval(80,100,150,150);

    g2.setColor(Color.RED);
    g2.setStroke(new BasicStroke(5));
    g2.drawRect(120,20,50,20);

  }
}
