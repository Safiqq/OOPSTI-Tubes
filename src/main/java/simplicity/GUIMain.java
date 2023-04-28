package simplicity;

import javax.swing.*;

public class GUIMain {
    public static class cobaJavaSwing {
        public static void start() {
            JFrame f = new JFrame("Sim-Plicity");
            JButton b = new JButton("Start");
            b.setBounds(130, 100, 100, 40);
            f.add(b);
            f.setSize(400, 500);
            f.setLayout(null);// using no layout managers
            f.setVisible(true);// making the frame visible
        }
    }
}
