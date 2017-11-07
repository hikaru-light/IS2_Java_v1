import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.io.*;
import java.lang.*;
import java.util.Random;

public class Graph3{
  public static void main(String[] args) {
    JFrame fr = new JFrame("Graph3");

    fr.setSize(500,500);
    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fr.getContentPane().setBackground(new Color(255, 255, 255));

    Radersetting panel = new Radersetting();
    panel.setOpaque(false);
    fr.add(panel);

    fr.setVisible(true);

  }
}



class Radersetting extends JPanel{
  String[] data;
  Radersetting(){
    try{
      File inputFile = new File("./data.txt");
      FileReader fi = new FileReader(inputFile);
      BufferedReader br = new BufferedReader(fi);


      String str = br.readLine();

      while (str != null) {
        data = str.split(" ");
        str = br.readLine();
      }

      br.close();
    } catch (FileNotFoundException e) {
      System.err.println("NOT_FOUND");
    } catch (IOException e) {
      System.err.println("CAN_NOT_OPEN");
    }
  }

  public void paintComponent(Graphics g){

    int x = data.length;

    int[] pa = new int[x];
    for (int i = 0; i < x; i++) {
      pa[i] = Integer.parseInt(data[i]);
    }

    int mx=0;
    int mn=0;
    for (int i = 0; i < x; i++) {
      int v = pa[i];
      if (v > mx) {
        mx = v;
      }
      if (v < mn) {
        mn = v;
      }
    }

    double max = 0;
    double r = -90;

    int[] mtx = new int[x];
    int[] mty = new int[x];

    for (int i = 0; i < x ;i++ ) {
      mtx[i] = (int)(190 - Math.round(3.0/5.0 * 250*pa[i]/mx * Math.cos(Math.toRadians(r))));
      mty[i] = (int)(190 + Math.round(3.0/5.0 * 250*pa[i]/mx * Math.sin(Math.toRadians(r))));
      r -= 360/x;
    }

    g.setColor(new Color(80,80,80));
    g.fillPolygon(mtx,mty,x);


    g.setColor(new Color(0,0,0));

    for (int a = 0;a < x ;a++ ) {
      g.drawLine(190, 190, (int)(190 + Math.round(150 * Math.cos(Math.toRadians(r)))), (int)(190 + Math.round(150 * Math.sin(Math.toRadians(r)))));
      g.drawString(String.valueOf(a + 1), (int)(187 - Math.round(170 * Math.cos(Math.toRadians(r)))), (int)(195 + Math.round(170 * Math.sin(Math.toRadians(r)))));
      r -= 360/x;
    }

    for (int j = 30;j <= 150  ;j+=30 ) {
      int[] xx = new int[x];
      int[] yy = new int[x];

      for (int i = 0;i < x ;i++ ) {
        r -= 360/x;
        xx[i] = (int) (190 + Math.round(j * Math.cos(Math.toRadians(r))));
        yy[i] = (int) (190 + Math.round(j * Math.sin(Math.toRadians(r))));
      }

      g.drawPolygon(xx,yy,x);
    }

    int cc = 0;

    for (int c = 0;c <= 5 ;c++ ) {
      g.drawString(String.valueOf((mx/5) * c),195 ,190 - cc);
      cc += 30;
    }
  }
}
