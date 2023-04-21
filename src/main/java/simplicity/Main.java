package simplicity;

import javax.swing.*;

import java.util.*;

public class Main {

    private static List<Sim> listSim = new ArrayList<>();
    private Sim currentSim = null;
    private int dayAddSim = 0;

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
        System.out.println(menu);
    }

    public void showAction(){
        StringBuilder actionMenu = new StringBuilder();
        actionMenu.append("Aksi yang dapat dipilih: \n");
        actionMenu.append("1. Work\n");
        // actionMenu.append("2. Change Job\n"); -> masuk action ato ngga
        actionMenu.append("3. Exercise\n");
        actionMenu.append("4. Sleep\n");
        actionMenu.append("5. Eat\n");
        actionMenu.append("6. Cook\n");
        actionMenu.append("7. Visit\n");
        actionMenu.append("8. Pee\n");
        actionMenu.append("9. Upgrade House\n");
        actionMenu.append("10. Buy Item\n");
        actionMenu.append("11. Move Room\n");
        actionMenu.append("12. View Inventory\n");
        actionMenu.append("13. Install Item\n");
        actionMenu.append("14. Check Time\n");
        System.out.println(actionMenu);
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

        // pilih Sim
        if (listSim.size() == 0){
            System.out.println("Tidak ada Sim yang tersedia, silahkan daftarkan Sim anda terlebih dahulu");
            System.out.print("Masukkan nama Sim anda: ");
            String simName = scanner.nextLine();
            currentSim = new Sim(simName);
            currentSim.setCurrentTime(time);
            listSim.add(currentSim);
            System.out.println("Selamat bermain!");
        } else {
            System.out.print("Apakah anda ingin membuat Sim baru? (ya/tidak) ");
            String ans = scanner.nextLine();
            if (ans.equals("ya")){
                System.out.print("Masukkan nama Sim anda: ");
                String simName = scanner.nextLine();
                currentSim = new Sim(simName);
                currentSim.setCurrentTime(time);
                listSim.add(currentSim);
                System.out.println("Selamat bermain!");
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
                        currentSim.setCurrentTime(time);
                    } else {
                        System.out.println("Sim tidak ditemukan di list Sim");
                    }
                }

                System.out.println("Selamat bermain!");
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
                System.out.println("Nama Sim: " + currentSim.getFullName());
                System.out.println("Pekerjaan Sim: " + currentSim.getOccupation().getJobName());
                System.out.println("Kesehatan Sim: " + currentSim.getMotive().getHealth());
                System.out.println("Kekenyangan Sim: " + currentSim.getMotive().getHunger());
                System.out.println("Mood Sim: " + currentSim.getMotive().getMood());
                System.out.println("Uang Sim: " + currentSim.getMoney());
            } else if (menuUpper.equals("VIEW CURRENT LOCATION")){
                System.out.println("Lokasi Sim: ");
                // menunjukkan lokasi dari nama?
                System.out.println("Rumah milik: " + currentSim.getSimLoc().getHouse().getOwner());
                System.out.println("Nama ruangan: " + currentSim.getSimLoc().getRoom().getRoomName());
            } else if (menuUpper.equals("VIEW INVENTORY")){

            } else if (menuUpper.equals("UPGRADE HOUSE")){

            } else if (menuUpper.equals("MOVE ROOM")){

            } else if (menuUpper.equals("EDIT ROOM")){

            } else if (menuUpper.equals("ADD SIM")){
                // hanya dapat dilakukan 1 hari sekali
                if (time.getDay() > dayAddSim){
                    System.out.print("Masukkan nama Sim yang ingin anda tambahkan: ");
                    String simName = scanner.nextLine();
                    System.out.println("Masukkan titik untuk mendirikna rumah: ");
                    System.out.print("X: ");
                    int x = scanner.nextInt();
                    System.out.print("Y: ");
                    int y = scanner.nextInt();
                    Point pointSim = new Point(x, y);
                    // cek di World ada ga yg nempatin -> addAble
                    boolean addAble = false;
                    if (addAble){
                        Sim newSim = new Sim(simName);
                        listSim.add(currentSim);
                        dayAddSim = time.getDay();
                    } else {
                        System.out.println("Tidak memungkinkan untuk membuat rumah baru");
                    }
                } else {
                    System.out.println("Menambah sim baru hanya dapat dilakukan 1 hari sekali");
                }
            } else if (menuUpper.equals("CHANGE SIM")){
                Sim oldSim = currentSim;
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
                    for (Sim sim : listSim){
                        if (sim.getFullName().equals(simName)){
                            currentSim = sim;
                            break;
                        }
                    }

                    if (!currentSim.equals(oldSim)){
                        done = true;
                        currentSim.setCurrentTime(time);
                    } else {
                        System.out.println("Sim tidak ditemukan di list Sim");
                    }
                }

                System.out.println("Sim berhasil diganti. Selamat bermain!");

            } else if (menuUpper.equals("LIST OBJECT")){

            } else if (menuUpper.equals("GO TO OBJECT")){

            } else if (menuUpper.equals("ACTION")){
                main.showAction();
                System.out.print("Masukkan aksi yang ingin dijalankan: ");
                String act = scanner.nextLine();
                String actUpper = act.toUpperCase();
                if (actUpper.equals("WORK")){
                    System.out.print("Masukkan durasi Sim bekerja: ");
                    int simWorkTime = scanner.nextInt();
                    currentSim.work(simWorkTime);
                } else if (actUpper.equals("CHANGE JOB")){ // action ato taruh di work?
                    currentSim.newJob();
                } else if (actUpper.equals("EXCERCISE")){

                } else if (actUpper.equals("SLEEP")){

                } else if (actUpper.equals("EAT")){

                } else if (actUpper.equals("COOK")){

                } else if (actUpper.equals("VISIT")){

                } else if (actUpper.equals("PEE")){

                } else if (actUpper.equals("UPGRADE HOUSE")){

                } else if (actUpper.equals("BUY ITEM")){

                } else if (actUpper.equals("MOVE ROOM")){

                } else if (actUpper.equals("VIEW INVENTORY")){

                } else if (actUpper.equals("INSTALL ITEM")){

                } else if (actUpper.equals("CHECK TIME")){
                    System.out.println("Waktu yang tersisa di- " + time.getTime());
                    // sisa waktu yang masih ada untuk seluruh tindakan yang bisa ditinggal
                } else {
                    System.out.println("Aksi tidak valid");
                }
            } else {
                System.out.println("Perintah tidak valid");
            }
        }

        scanner.close();
    }
}
