package simplicity;

import javax.swing.*;

import java.util.*;

public class Main {

    private static World world = World.getWorld();
    private static Time time;
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
        actionMenu.append("2. Change Job\n"); // masuk action ato ngga
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

    public boolean isNotRegistered(String simName){
        String nameUpper = simName.toUpperCase();
        for (Sim sim : listSim){
            if (nameUpper.equals(sim.getFullName())){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // cobaJavaSwing.start();
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        time = new Time();
        // time.runTime();
        // time.setIsNotIdle(true);
        main.showMenuBegin();
        boolean isStarted = false;
        System.out.println("Ketik 'START GAME' untuk memulai game atau 'HELP' untuk melihat menu game");
        while (!isStarted) {
            System.out.print("Masukkan perintah: ");
            String menu = scanner.nextLine();
            String menuUpper = menu.toUpperCase();
            if (menuUpper.equals("START GAME")) {
                isStarted = true;
            } else if (menuUpper.equals("EXIT")) {
                System.out.println("Anda keluar dari game Simplicity");
                System.out.println("Bye...");
                System.exit(0);
            } else if (menuUpper.equals("HELP")) {
                main.showMenuBegin();
            } else {
                System.out.println("Perintah tidak valid");
            }
        }

        System.out.println(time.getTime());
        // pilih Sim // tambahin cek uppercase in semua
        if (main.listSim.size() == 0) {
            System.out.println("Tidak ada Sim yang tersedia, silahkan daftarkan Sim anda terlebih dahulu");
            System.out.print("Masukkan nama Sim anda: ");
            String simName = scanner.nextLine();
            String nameUpper = simName.toUpperCase();
            main.currentSim = new Sim(nameUpper);
            // main.currentSim.setCurrentTime(time);
            main.listSim.add(main.currentSim);
            System.out.println("Selamat bermain!");

        } else {
            System.out.print("Apakah anda ingin membuat Sim baru? (ya/tidak) ");
            String ans = scanner.nextLine();
            String ansUpper = ans.toUpperCase();

            if (ans.equals("YA")) {
                boolean done = false;
                String simName = null;
                while (!done){
                    System.out.print("Masukkan nama Sim anda: ");
                    simName = scanner.nextLine();
                    if (!main.isNotRegistered(simName)){
                        System.out.println("Nama Sim sudah terdaftar. Silahkan masukkan nama yang berbeda");
                        main.printListSim();
                    } else {
                        done = true;
                    }
                }

                String nameUpper = simName.toUpperCase();
                main.currentSim = new Sim(nameUpper);
                // main.currentSim.setCurrentTime(time);
                main.listSim.add(main.currentSim);
                System.out.println("Selamat bermain!");

            } else { // jawaban selain ya dan tidak dianggap tidak
                main.printListSim();

                boolean done = false;
                while (!done) {
                    System.out.print("Masukkan nama sim yang ingin dimainkan: ");
                    String simName = scanner.nextLine();
                    if (!main.isNotRegistered(simName)){
                        System.out.println("Sim tidak ditemukan di list Sim.");
                        main.printListSim();
                    } else {
                        String nameUpper = simName.toUpperCase();
                        // ambil Sim di list
                        for (Sim sim : main.listSim){
                            if (sim.getFullName().equals(nameUpper)){
                                main.currentSim = sim;
                                // main.currentSim.setCurrentTime(time);
                                break;
                            }
                        }

                        done = true;
                    }
                }

                System.out.println("Selamat bermain!");
            }
        }

        System.out.println("Waktu yang tersisa di " + time.getTime());
        main.currentSim.setCurrentTime(time);

        System.out.println("Ketik 'HELP' untuk melihat menu game yang tersedia ");
        while (isStarted) {
            time.runTime();
            // time.setIsNotIdle(true);
            System.out.println("Waktu yang tersisa di " + time.getTime());
            System.out.print("Masukkan perintah: ");
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
                main.currentSim.moveRoom();

            } else if (menuUpper.equals("EDIT ROOM")) {
                boolean done = false;
                while (!done){
                    System.out.print("Apakah anda ingin membeli barang baru atau memindahkan barang? (beli/pindah)");
                    String ans = scanner.nextLine();
                    String ansUpper = ans.toUpperCase();
                    if (ansUpper.equals("BELI")){
 
                        done = true;
                    } else if (ansUpper.equals("PINDAH")){

                        done = true;
                    } else {
                        System.out.println("Perintah tidak valid");
                    }
                }

            } else if (menuUpper.equals("ADD SIM")) {
                // hanya dapat dilakukan 1 hari sekali
                if (time.getDay() > main.dayAddSim) {
                    boolean found = true;
                    String simName = null;
                    while (found){
                        System.out.print("Masukkan nama Sim yang ingin anda tambahkan: ");
                        simName = scanner.nextLine();
                        if (main.isNotRegistered(simName)){
                            found = false;
                        } else {
                            System.out.println("Nama telah terdaftar. Silahkan menggunakan nama lain");
                            main.printListSim();
                        }
                    }

                    System.out.println("Masukkan titik untuk mendirikan rumah: ");
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
                    // cek fungsi isWorldAvail
                    if (world.isWorldAvail(houseLoc)) {
                        String nameUpper = simName.toUpperCase();
                        Sim newSim = new Sim(nameUpper);
                        main.listSim.add(newSim);
                        main.dayAddSim = time.getDay();
                        System.out.println("Sim berhasil ditambahkan");
                    } else {
                        System.out.println("Tidak memungkinkan untuk membuat rumah baru");
                    }
                } else {
                    System.out.println("Menu 'ADD SIM' hanya dapat dijalankan 1 hari sekali");
                }

            } else if (menuUpper.equals("CHANGE SIM")) {
                // di listSim hanya ada 1 sim
                if (main.listSim.size() == 1){
                    System.out.println("Tidak bisa dilakukan pergantian Sim. Hanya terdapat 1 Sim yang terdaftar.");
                    System.out.println("Ketik 'ADD SIM' untuk menambah Sim baru");
                } else {
                    Sim oldSim = main.currentSim;
                    main.printListSim();

                    boolean done = false;
                    while (!done) {
                        System.out.print("Masukkan nama sim yang ingin dimainkan: ");
                        String simName = scanner.nextLine();
                        String nameUpper = simName.toUpperCase();
                        if (nameUpper.equals(oldSim.getFullName())){
                            System.out.println("Nama Sim sama dengan yang sedang anda mainkan.");
                        } else {
                            for (Sim sim : main.listSim) {
                                if (sim.getFullName().equals(nameUpper)) {
                                    main.currentSim = sim;
                                    break;
                                }
                            }

                            if (!main.currentSim.equals(oldSim)) {
                                done = true;
                                main.currentSim.setCurrentTime(time);
                            } else {
                                System.out.println("Sim tidak ditemukan di list Sim");
                            }
                        }
                    }

                    System.out.println("Sim berhasil diganti. Selamat bermain!");
                }

            } else if (menuUpper.equals("LIST OBJECT")) {
                // menampilkan daftar objek dalam sebuah ruangan
                // asumsi: sim harus berada di dalam ruangan untuk melihat daftar objek dalam ruangan tsb
                ArrayList<NonFood> listObjek = main.currentSim.getSimLoc().getRoom().getListObjek();
                int i = 1;
                for (NonFood nonFood : listObjek){
                    System.out.println(i + ". " + nonFood);
                    i++;
                }

            } else if (menuUpper.equals("GO TO OBJECT")) {

            } else if (menuUpper.equals("ACTION")) {
                // time.setIsNotIdle(true);
                main.showAction();
                System.out.print("Masukkan aksi yang ingin dijalankan: ");
                String act = scanner.nextLine();
                String actUpper = act.toUpperCase();
                if (actUpper.equals("WORK")) {
                    // time.setIsNotIdle(true);
                    System.out.print("Masukkan durasi Sim bekerja: ");
                    int simWorkTime = scanner.nextInt();
                    main.currentSim.work(simWorkTime);
                } else if (actUpper.equals("CHANGE JOB")) { // action ato taruh di work?
                    // time.setIsNotIdle(true);
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
                    main.currentSim.moveRoom();

                } else if (actUpper.equals("VIEW INVENTORY")) {
                    // time.setIsNotIdle(true);
                    System.out.println(time.getTime());
                    main.currentSim.viewSimInventory();

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
