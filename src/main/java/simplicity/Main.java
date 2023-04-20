package simplicity;

import javax.swing.*;

public class Main {

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

    public void showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("Menu Game:\n");
        menu.append("1. Start Game\n");
        menu.append("2. Help\n");
        menu.append("3. Exit\n");
        menu.append("4. View Sim Info\n");
        menu.append("5. View Current Location\n");
        menu.append("6. View Inventory\n");
        menu.append("7. Upgrade House\n");
        menu.append("8. Move Room\n");
        menu.append("9. Edit Room\n");
        menu.append("10. Add Sim\n");
        menu.append("11. Change Sim\n");
        menu.append("12. List Object\n");
        menu.append("13. Go to Object\n");
        menu.append("14. Action\n");
        System.out.println(menu);
    }

    public static void main(String[] args) {
        // cobaJavaSwing.start();
        Main main = new Main();
        main.showMenu();
    }
}
