package simplicity;

import javax.swing.*;

import java.util.*;

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

    public void showMenuBegin(){
        StringBuilder menu1 = new StringBuilder();
        menu1.append("Menu game yang tersedia:\n");
        menu1.append("1. Start Game\n");
        menu1.append("2. Help\n");
        menu1.append("3. Exit\n");
        System.out.println(menu1);
    }

    public void showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("Menu Game:\n");
        // menu.append("1. Start Game\n");
        menu.append("1. Help\n");
        menu.append("2. Exit\n");
        menu.append("3. View Sim Info\n");
        menu.append("4. View Current Location\n");
        menu.append("5. View Inventory\n");
        menu.append("6. Upgrade House\n");
        menu.append("7. Move Room\n");
        menu.append("8. Edit Room\n");
        menu.append("9. Add Sim\n");
        menu.append("10. Change Sim\n");
        menu.append("11. List Object\n");
        menu.append("12. Go to Object\n");
        menu.append("13. Action\n");
        menu.append("14. Work")
        menu.append("15. Change Job");
        System.out.println(menu);
    }

    public static void main(String[] args) {
        // cobaJavaSwing.start();
        Time time = new Time();
        time.runTime();
        System.out.println("Waktu yang tersisa di " + time.getTime());
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        main.showMenu();
        boolean isStarted = false;
        System.out.println("Ketik 'START GAME' untuk memulai game atau 'HELP' untuk melihat menu game");
        while (!isStarted){
            System.out.println("Waktu yang tersisa di " + time.getTime());
            System.out.print("Masukkan perintah: ");
            String menu = scanner.nextLine();
            String menuUpper = menu.toUpperCase();
            if(menuUpper.equals("START GAME")){
                isStarted = true;
            } else if (menuUpper.equals("EXIT")){
                System.out.println("Anda keluar dari game Simplicity");
                System.out.println("Waktu yang tersisa di " + time.getTime());
                System.out.println("Bye...");
                System.exit(0);
            } else if (menuUpper.equals("HELP")){
                main.showMenuBegin();
            } else {
                System.out.println("Perintah tidak valid");
            }
        }

        System.out.println("Ketik 'HELP' untuk melihat menu game yang tersedia ");
        while (isStarted){
            System.out.println("Waktu yang tersisa di " + time.getTime());
            System.out.println("Masukkan perintah: ");
            String menu = scanner.nextLine();
            String menuUpper = menu.toUpperCase();
            if (menuUpper.equals("HELP")){
                main.showMenu();
            } else if (menuUpper.equals("EXIT")){
                System.out.println("Anda keluar dari game Simplicity");
                System.out.println("Waktu yang tersisa di " + time.getTime());
                System.out.println("Bye...");
                System.exit(0);
            } else if (menuUpper.equals("VIEW SIM INFO")){

            } else if (menuUpper.equals("VIEW CURRENT LOCATION")){

            } else if (menuUpper.equals("VIEW INVENTORY")){

            } else if (menuUpper.equals("UPGRADE HOUSE")){

            } else if (menuUpper.equals("MOVE ROOM")){

            } else if (menuUpper.equals("EDIT ROOM")){

            } else if (menuUpper.equals("ADD SIM")){

            } else if (menuUpper.equals("CHANGE SIM")){

            } else if (menuUpper.equals("LIST OBJECT")){

            } else if (menuUpper.equals("GO TO OBJECT")){

            } else if (menuUpper.equals("ACTION")){

            } else if (menuUpper.equals("WORK")){
                // sim.work();
            } else if (menuUpper.equals("CHANGE JOB")){
                // sim.newJob();
            } else {
                System.out.println("Perintah tidak valid");
            }
        }

        scanner.close();
    }
}
