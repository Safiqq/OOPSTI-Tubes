package simplicity;

import javax.swing.*;

import java.util.*;

public class Main {

    private static World world;
    private List<Sim> listSim = new ArrayList<>();
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

    public void showMenuBegin() {
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

    public void showAction() {
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

    public void printListSim(){
        System.out.println("Daftar Sim yang dapat dimainkan: ");
        int i = 1;
        for (Sim sim : listSim){
            System.out.println(i + ". " + sim.getFullName());
            i++;
        }
        System.out.println("");
    }

    public Sim addSim(){
        boolean done = false;
        String simName = null;
        // validasi nama Sim
        while (!done){
            System.out.print("Masukkan nama Sim baru: ");
            simName = scanner.nextLine();
            // nama Sim tidak boleh sama
            boolean same = false;
            for (Sim sim : listSim){
                if (simName.equals(sim.getFullName)){
                    System.out.println("Sim tidak boleh memiliki nama yang sama");
                    same = true;
                    break;
                }
            }

            if (!same){
                done = true;
            } else {
                printListSim();
            }
        }

        Sim newSim = new Sim(simName);
        listSim.add(newSim);
        return newSim;
    }

    public void playSim(String oldSimName, Time time){
        printListSim();
        boolean done = false;
        while (!done){
            System.out.print("Masukkan nama Sim yang ingin dimainkan: ");
            String simName = scanner.nextLine();
            if (simName.equals(oldSimName)){
                System.out.println("Nama Sim sama dengan yang sedang anda mainkan");
            } else {
                // ambil Sim di list
                for (Sim sim : listSim){
                    if (sim.getFullName().equals(simName)){
                        currentSim = sim;
                        break;
                    }
                }

                if (!currentSim.getFullName().equals(oldSimName)){
                    done = true;
                } else {
                    System.out.println("Sim tidak ditemukan di list Sim");
                }
            }
        }
        currentSim.setCurrentTime(time);
    }

    public static void main(String[] args) {
        // cobaJavaSwing.start();
        // ini time sm world jadiin atribut kelas Main ato taruh sini aja?
        // World world = new World();
        world.getWorld();
        Time time = new Time();
        time.runTime();
        System.out.println("Waktu yang tersisa di " + time.getTime());
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        main.showMenuBegin();
        boolean isStarted = false;
        System.out.println("Ketik 'START GAME' untuk memulai game atau 'HELP' untuk melihat menu game");
        while (!isStarted) {
            System.out.println("Waktu yang tersisa di " + time.getTime());
            System.out.print("Masukkan perintah: ");
            String menu = scanner.nextLine();
            String menuUpper = menu.toUpperCase();
            if (menuUpper.equals("START GAME")) {
                isStarted = true;
            } else if (menuUpper.equals("EXIT")) {
                System.out.println("Anda keluar dari game Simplicity");
                System.out.println("Waktu yang tersisa di " + time.getTime());
                System.out.println("Bye...");
                System.exit(0);
            } else if (menuUpper.equals("HELP")) {
                main.showMenuBegin();
            } else {
                System.out.println("Perintah tidak valid");
            }
        }

        // pilih Sim
        if (main.listSim.size() == 0) {
            System.out.println("Tidak ada Sim yang tersedia, silahkan daftarkan Sim anda terlebih dahulu");
            main.currentSim = main.addSim();
            main.currentSim.setCurrentTime(time);
            System.out.println("Selamat bermain!");

        } else {
            System.out.print("Apakah anda ingin membuat Sim baru? (ya/tidak) ");
            String ans = scanner.nextLine();

            if (ans.equals("ya")) {
                main.currentSim = main.addSim();
                main.currentSim.setCurrentTime(time);
                System.out.println("Selamat bermain!");

            } else { // jawaban selain ya dan tidak dianggap tidak
                main.playSim(null, time);
                System.out.println("Selamat bermain!");
            }
        }

        System.out.println("Ketik 'HELP' untuk melihat menu game yang tersedia ");
        while (isStarted) {
            System.out.println("Waktu yang tersisa di " + time.getTime());
            System.out.println("Masukkan perintah: ");
            String menu = scanner.nextLine();
            String menuUpper = menu.toUpperCase();
            if (menuUpper.equals("HELP")) {
                main.showMenu();
            } else if (menuUpper.equals("EXIT")) {
                System.out.println("Anda keluar dari game Simplicity");
                System.out.println("Waktu yang tersisa di " + time.getTime());
                System.out.println("Bye...");
                System.exit(0);
            } else if (menuUpper.equals("VIEW SIM INFO")) {
                main.currentSim.viewSimInfo();

            } else if (menuUpper.equals("VIEW CURRENT LOCATION")) {
                main.currentSim.viewSimLoc();

            } else if (menuUpper.equals("VIEW INVENTORY")) {
                main.currentSim.viewSimInventory();

            } else if (menuUpper.equals("UPGRADE HOUSE")) {

            } else if (menuUpper.equals("MOVE ROOM")) {

            } else if (menuUpper.equals("EDIT ROOM")) {

            } else if (menuUpper.equals("ADD SIM")) {
                // hanya dapat dilakukan 1 hari sekali
                if (time.getDay() > main.dayAddSim) {
                    System.out.print("Masukkan nama Sim yang ingin anda tambahkan: ");
                    String simName = scanner.nextLine();
                    System.out.println("Masukkan titik untuk mendirikan rumah: ");
                    // kenapa gabisa nambahin rumah??
                    boolean done = false;
                    int x = 0, y = 0;
                    while (!done){
                        System.out.print("X: ");
                        x = scanner.nextInt();
                        System.out.print("Y: ");
                        y = scanner.nextInt();
                        if ((x < 0 || x > 64) || (y < 0 || y > 64)){
                            System.out.println("Titik tidak valid. World berukuran 64x64");
                        } else {
                            done = true;
                        }
                    }

                    Point houseLoc = new Point(x, y);
                    if (world.isWorldAvail(houseLoc)) {
                        Sim newSim = new Sim(simName);
                        main.listSim.add(main.currentSim);
                        main.dayAddSim = time.getDay();
                    } else {
                        System.out.println("Tidak memungkinkan untuk membuat rumah baru");
                    }
                } else {
                    System.out.println("Menambah sim baru hanya dapat dilakukan 1 hari sekali");
                }
            } else if (menuUpper.equals("CHANGE SIM")) {
                // di listSim hanya ada 1 sim
                if (main.listSim.size() == 1){
                    System.out.println("Tidak bisa dilakukan pergantian Sim. Hanya terdapat 1 Sim yang terdaftar.");
                    System.out.println("Ketik 'ADD SIM' untuk menambah Sim baru");
                } else {
                    Sim oldSim = main.currentSim;
                    main.playSim();
                    System.out.println("Sim berhasil diganti. Selamat bermain!");
                }

            } else if (menuUpper.equals("LIST OBJECT")) {

            } else if (menuUpper.equals("GO TO OBJECT")) {

            } else if (menuUpper.equals("ACTION")) {
                main.showAction();
                System.out.print("Masukkan aksi yang ingin dijalankan: ");
                String act = scanner.nextLine();
                String actUpper = act.toUpperCase();
                if (actUpper.equals("WORK")) {
                    System.out.print("Masukkan durasi Sim bekerja: ");
                    int simWorkTime = scanner.nextInt();
                    main.currentSim.work(simWorkTime);
                } else if (actUpper.equals("CHANGE JOB")) { // action ato taruh di work?
                    main.currentSim.newJob();
                } else if (actUpper.equals("EXCERCISE")) {

                } else if (actUpper.equals("SLEEP")) {

                } else if (actUpper.equals("EAT")) {

                } else if (actUpper.equals("COOK")) {

                } else if (actUpper.equals("VISIT")) {

                } else if (actUpper.equals("PEE")) {

                } else if (actUpper.equals("UPGRADE HOUSE")) {

                } else if (actUpper.equals("BUY ITEM")) {

                } else if (actUpper.equals("MOVE ROOM")) {

                } else if (actUpper.equals("VIEW INVENTORY")) {

                } else if (actUpper.equals("INSTALL ITEM")) {

                } else if (actUpper.equals("CHECK TIME")) {
                    // pake harus ngehampirin objek jam dulu
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
