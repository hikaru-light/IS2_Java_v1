import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.io.*;

public class Graph1 {
  public static void main(String[] args) {
    JFrame frm = new JFrame("Graph1");

    frm.setSize(500,400);
    frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frm.getContentPane().setBackground(new Color(255, 255, 255));

    Graph1Panel panel = new Graph1Panel();
    panel.setOpaque(false);
    frm.add(panel);

    frm.setVisible(true);
  }
}

class Graph1Panel extends JPanel {
  @Override
  public void paintComponent(Graphics g) {
    try {
      File file = new File("./data.txt");
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);

      String str = br.readLine();
      String[] data = str.split(" ");
      int x = data.length;

      int[] pa = new int[x];
      for (int i = 0; i < x; i++) {
        pa[i] = Integer.parseInt(data[i]);
      }

      br.close();

      g.setColor(new Color(70, 70, 70));
      g.fillRect(50, 50, 380, 200);

      g.setColor(new Color(0, 0, 0));
      g.drawLine(50, 250, 430, 250);
      g.drawLine(50, 50, 50, 250);

      int max=0;
      int min=0;
      for (int i = 0; i < x; i++) {
        int v = pa[i];
        if (v > max) {
          max = v;
        }
        if (v < min) {
          min = v;
        }
      }

      for (int i = 0; i < x; i++) {
        g.drawLine(100 + i * 300/x, 250, 100 + i * 300/x, 252);
      }

      for (int i=0; i<x; i++) {
        g.drawString(String.valueOf(i+1), 85 + i * 300/x, 263);
      }

      for (int i = 0; i < 5; i++) {
        g.drawLine(48, 50 + i * 50, 430, 50 + i * 50);
        g.drawString(String.valueOf(i * max/4), 20, 255 - i * 50);
      }

      for (int i = 0; i+1 < x; i++) {
        g.setColor(new Color(0, 0, 255));
        g.drawLine(100+i*300/x, 250-200*pa[i]/max, 100+(i+1)*300/x, 250-200*pa[i+1]/max);
      }

    } catch (FileNotFoundException e) {
      System.err.println("NOT_FOUND");
    } catch (IOException e) {
      System.err.println("CAN_NOT_OPEN");
    }
  }
}
