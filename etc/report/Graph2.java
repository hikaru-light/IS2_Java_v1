import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.io.*;
import java.util.Random;

public class Graph2 {
  public static void main(String[] args) {
    JFrame frm = new JFrame("Graph2");

    frm.setSize(600,450);
    frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frm.getContentPane().setBackground(new Color(255, 255, 255));

    Graph2Panel panel = new Graph2Panel();
    panel.setOpaque(false);
    frm.add(panel);

    frm.setVisible(true);
  }
}

class Graph2Panel extends JPanel {
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

      Random rnd = new Random();
      double sum = 0;
      int start = 90;

      for (int i = 0; i < x; i++) {
        sum += pa[i];
      }

      for (int i = 0; i < x; i++) {
        double rate = pa[i] / sum;
        int rad = (int)(360 * (double)rate);
        g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        g.fillArc(150, 100, 200, 200, start, -rad);
        start -= rad;

        g.drawString(String.valueOf(data[i]), (int)(240+115*(double)Math.cos(start * Math.PI / 180)), (int)(205-115*(double)Math.sin(start * Math.PI / 180)));
        
        g.fillRect(430, 100+i*30, 10, 10);
        g.setColor(new Color(0,0,0));
        g.drawString(String.valueOf(i+1),445, 110+i*30);
      }

    } catch (FileNotFoundException e) {
      System.err.println("NOT_FOUND");
    } catch (IOException e) {
      System.err.println("CAN_NOT_OPEN");
    }
  }
}
