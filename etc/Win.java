import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Win {
  public static void main (String[] args) {
    JFrame fr = new JFrame ("Sample");

    fr.setSize (400, 400);
    fr.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

    Pan panel = new Pan ();
    fr.add(panel);

    fr.setVisible (true);
  }
}

class Pan extends JPanel {
  @Override
  public void paintComponent (Graphics gr) {
      int x=5;
      int y=15;
      for (int a=0; a<4; a++) {
        gr.drawString ("Hello, World!", x, y);
        x+=20;
        y+=15;
      }

      gr.drawString ("Hello, World!", x, y);

      for (int a=0; a<4; a++) {
        x-=20;
        y+=15;
        gr.drawString ("Hello, World!", x, y);
      }
  }
}
