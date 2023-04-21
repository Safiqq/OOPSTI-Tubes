package simplicity;

import javax.swing.*;

import java.util.*;

public class Main {

    private static List<Sim> listSim = new ArrayList<>();
    private Sim currentSim = null;

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

        // pilih Sim dulu berartii?
        if (listSim.size() == 0){
            System.out.println("Tidak ada Sim yang tersedia, silahkan daftarkan Sim anda terlebih dahulu");
            System.out.print("Masukkan nama Sim anda: ");
            String simName = scanner.nextLine();
            currentSim = new Sim(simName);
            currentSim.setCurrentTime(time);
            listSim.add(currentSim);
        } else {
            System.out.print("Apakah anda ingin membuat Sim baru? (ya/tidak) ");
            String ans = scanner.nextLine();
            if (ans.equals("ya")){
                System.out.print("Masukkan nama Sim anda: ");
                String simName = scanner.nextLine();
                currentSim = new Sim(simName);
                currentSim.setCurrentTime(time);
                listSim.add(currentSim);
            } else { // jawaban selain ya dan tidak dianggap tidak
                System.out.println("Daftar Sim yang dapat dimainkan: ");
                int i = 1;
                for (Sim sim : listSim){
                    System.out.println(i + ". " + sim.getFullName());
                    i++;
                }

                boolean done = false;
                while (!done){
                    System.out.print("Masukkan nama sim yang ingin dimainkan: ");
                    String simName = scanner.nextLine();
                    // ambil Sim di list
                    for (Sim sim : listSim){
                        if(sim.getFullName().equals(simName)){
                            currentSim = sim;
                            break;
                        }
                    }

                    if (currentSim != null){
                        done = true;
                    } else {
                        System.out.println("Sim tidak ditemukan di list Sim");
                    }
                }
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
                System.out.print("Masukkan durasi Sim bekerja: ");
                int simWorkTime = scanner.nextInt();
                currentSim.work(simWorkTime);
            } else if (menuUpper.equals("CHANGE JOB")){
                currentSim.newJob();
            } else {
                System.out.println("Perintah tidak valid");
            }
        }

        scanner.close();
    }
}
