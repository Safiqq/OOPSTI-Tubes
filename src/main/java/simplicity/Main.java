package simplicity;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final World world = World.getWorld();
    private static final String[] menu = {"Start Game - Memulai permainan", "Help - Melihat menu game yang tersedia", "Exit - Keluar dari permainan", "View Sim Info - Menampilkan informasi setiap atribut dari Sim", "View Current Location - Menampilkan lokasi dari Sim", "View Inventory - Menampilkan isi inventory yang dimiliki Sim", "Upgrade House - Melakukan penambahan ruangan dalam rumah", "Move Room - Perpindahan lokasi ke ruang lain yang ada pada rumah yang sedang ditempati Sim", "Edit Room - Melakukan perubahan pada ruangan", "Add Sim - Menambahkan sebuah Sim dalam world", "Change Sim - Mengganti ke Sim lain untuk dimainkan", "List Object - Menampilkan daftar objek dalam sebuah ruangan", "Go to Object - Sim berjalan menuju suatu objek", "Action - Melakukan sebuah aksi pada suatu objek"};
    private static Scanner scanner;
    private static Time time;
    private static boolean isStarted;
    private Sim currentSim;
    private int dayAddSim;

    public Main() {
        Action.fillListAction();
        scanner = new Scanner(System.in);
        time = new Time();
        dayAddSim = 0;
        isStarted = false;
    }

    public static String[] getMenu() {
        return menu;
    }

    public static boolean equals(String str1, String str2) {
        return str1.equalsIgnoreCase(str2);
    }

    public static void main(String[] args) {
        // cobaJavaSwing.start();

        // Inisialisasi main
        Main main = new Main();
        Print.showMenuBegin();
        System.out.println("Ketik 'START GAME' untuk memulai game atau 'HELP' untuk melihat menu game");

        while (!isStarted) {
            System.out.print("Masukkan perintah: ");
            String menu = scanner.nextLine();
            if (equals(menu, "START GAME")) {
                isStarted = true;
            } else if (equals(menu, "EXIT")) {
                System.out.println("Anda keluar dari game Simplicity");
                System.out.println("Bye...");
                System.exit(0);
            } else if (equals(menu, "HELP")) {
                Print.showMenuBegin();
            } else {
                System.out.println("Perintah tidak valid");
            }
        }

        System.out.println(time.getTime());
        // pilih Sim
        if (Sim.getListSim().size() == 0) {
            System.out.println("Tidak ada Sim yang tersedia, Silakan daftarkan Sim anda terlebih dahulu");
            System.out.print("Masukkan nama Sim anda: ");
            String simName = scanner.nextLine();
            Sim newSim = new Sim(simName);
            House newHouse = new House(simName);
            world.addHouse(newHouse);
            main.currentSim = newSim;
            System.out.println("Selamat bermain!");

        } else {
            System.out.print("Apakah anda ingin membuat Sim baru? (ya/tidak) ");
            String ans = scanner.nextLine();

            if (equals(ans, "YA")) {
                // cek masi ada spot kosong di world ato ngga
                if (world.isHouseBuildAble()) {
                    boolean done = false;
                    Sim newSim;
                    while (!done) {
                        newSim = main.menuAddSim();
                        if (newSim != null) {
                            main.currentSim = newSim;
                            System.out.println("Selamat bermain!");
                            done = true;
                        } else {
                            System.out.println("Silakan mencoba mendaftarkan Sim baru di lokasi yang berbeda");
                        }
                    }
                } else {
                    System.out.println("World penuh, tidak memungkinkan membuat Sim baru");
                    System.out.println("Silakan memilih Sim yang sudah ada");
                    main.currentSim = main.chooseSim();
                    System.out.println("Selamat bermain!");
                }

            } else { // jawaban selain ya dan tidak dianggap tidak
                main.currentSim = main.chooseSim();
                System.out.println("Selamat bermain!");
            }
        }

        System.out.println("1Waktu yang tersisa di " + time.getTime());

        System.out.println("Ketik 'HELP' untuk melihat menu game yang tersedia ");
        while (isStarted) {
            System.out.println("2Waktu yang tersisa di " + time.getTime());
            System.out.print("Masukkan perintah: ");
            String menu = scanner.nextLine();
            if (equals(menu, "HELP")) {
                Print.showMenu();

            } else if (equals(menu, "EXIT")) {
                System.out.println("Anda keluar dari game Simplicity");
                System.out.println("3Waktu yang tersisa di " + time.getTime());
                System.out.println("Bye...");
                System.exit(0);

            } else if (equals(menu, "VIEW SIM INFO")) {
                main.currentSim.viewSimInfo();

            } else if (equals(menu, "VIEW CURRENT LOCATION")) {
                main.currentSim.viewSimLoc();

            } else if (equals(menu, "VIEW INVENTORY")) {
                main.currentSim.viewSimInventory();

            } else if (equals(menu, "UPGRADE HOUSE")) {
                // main.currentSim.upgradeHouse(house);
                // selama 18 menit tapi bisa ditinggal
                time.sleep(1080);

            } else if (equals(menu, "MOVE ROOM")) {
                main.currentSim.moveRoom();

            } else if (equals(menu, "EDIT ROOM")) {
                boolean done = false;
                while (!done) {
                    System.out.print("Apakah anda ingin membeli barang baru atau memindahkan barang? (beli/pindah)");
                    String ans = scanner.nextLine();
                    if (equals(ans, "BELI")) {

                        done = true;
                    } else if (equals(ans, "PINDAH")) {

                        done = true;
                    } else {
                        System.out.println("Perintah tidak valid");
                    }
                }

            } else if (equals(menu, "ADD SIM")) {
                // hanya dapat dilakukan 1 hari sekali
                if (time.getDay() > main.dayAddSim) {
                    // cek masi ada spot kosong di world ato ngga
                    if (world.isHouseBuildAble()) {
                        boolean done = false;
                        Sim newSim;
                        while (!done) {
                            newSim = main.menuAddSim();
                            if (newSim != null) {
                                main.dayAddSim = time.getDay(); // hanya dapat dilakukan 1 hari sekali
                                done = true;
                            } else {
                                System.out.println("Silakan mencoba mendaftarkan Sim baru di lokasi yang berbeda");
                            }
                        }
                    } else {
                        System.out.println("World penuh, tidak memungkinkan membuat Sim baru");
                    }
                } else {
                    System.out.println("Menu 'ADD SIM' hanya dapat dijalankan 1 hari sekali");
                }

            } else if (equals(menu, "CHANGE SIM")) {
                // di listSim hanya ada 1 sim
                if (Sim.getListSim().size() == 1) {
                    System.out.println("Tidak bisa dilakukan pergantian Sim. Hanya terdapat 1 Sim yang terdaftar.");
                    System.out.println("Ketik 'ADD SIM' untuk menambah Sim baru");
                } else {
                    Sim oldSim = main.currentSim;
                    boolean done = false;
                    Sim newSim = null;
                    while (!done) {
                        newSim = main.chooseSim();
                        if (equals(newSim.getFullName(), oldSim.getFullName())) {
                            System.out.println("Nama Sim sama dengan yang sedang anda mainkan");
                            System.out.println("Masukkan nama Sim yang berbeda");
                        } else {
                            done = true;
                        }
                    }
                    main.currentSim = newSim;
                    System.out.println("Sim berhasil diganti. Selamat bermain!");
                }

            } else if (equals(menu, "LIST OBJECT")) {
                // menampilkan daftar objek dalam sebuah ruangan
                // asumsi: sim harus berada di dalam ruangan untuk melihat daftar objek dalam
                // ruangan tsb
                ArrayList<NonFood> listObjek = main.currentSim.getSimLoc().getRoom().getListObjek();
                if (listObjek.size() > 0) {
                    int i = 1;
                    for (NonFood nonFood : listObjek) {
                        System.out.println(i + ". " + nonFood.getObjekName());
                        i++;
                    }
                } else {
                    System.out.println("Tidak ada daftar objek dalam ruangan "
                            + main.currentSim.getSimLoc().getRoom().getRoomName() + ".");
                }

            } else if (equals(menu, "GO TO OBJECT")) {

            } else if (equals(menu, "ACTION")) {
                Print.showAction();
                System.out.print("Masukkan aksi yang ingin dijalankan: ");
                String act = scanner.nextLine();

                if (equals(act, "WORK")) {
                    // pekerjaan baru hanya dapat dikerjakan 1 hari setelah hari penggantian pekerjaan
                    if (time.getDay() > main.currentSim.getDayChangeJob()){
                        System.out.print("Masukkan durasi kerja (dalam detik): ");
                        int simWorkTime = scanner.nextInt();
                        main.currentSim.work(simWorkTime);
                    } else {
                        System.out.println("Pekerjaan baru hanya dapat dikerjakan 1 hari setelah hari penggantian pekerjaan");
                    }

                } else if (equals(act, "CHANGE JOB")) { // action ato taruh di work?
                    // harus setidaknya 12 menit bekerja
                    if (main.currentSim.getWorkTime() >= 720){
                        main.currentSim.newJob();
                        main.currentSim.setDayChangeJob(time.getDay());
                    } else {
                        System.out.println("Sim hanya dapat mengganti pekerjaan jika sudah bekerja setidaknya 12 menit");
                    }

                } else if (equals(act, "EXERCISE")) {
                    System.out.print("Masukkan durasi olahraga (dalam detik): ");
                    int simExerciseTime = scanner.nextInt();
                    main.currentSim.exercise(simExerciseTime);

                } else if (equals(act, "SLEEP")) {
                    // sim sebagai manusia harus memiliki waktu tidur min 3 mnt setiap harinya
                    // efek tidak tidur -> -5 kesehatan dan -5 mood setelah 10 mnt tanpa tidur
                    // apakah harus 3 menit langsung atau boleh dicicil?
                    // penambahan efek tidur apakah akumulasi dalam hari tersebut atau langsung
                    // dibagi tiap tidur

                    System.out.print("Masukkan durasi tidur (dalam detik): ");
                    int simSleepTime = scanner.nextInt();
                    main.currentSim.sleep(simSleepTime);
                    time.sleepMain(simSleepTime);

                } else if (equals(act, "EAT")) {
                    main.currentSim.viewSimFood();
                    System.out.print("Mau makan apa? ");
                    String maumakan = scanner.nextLine();
                    main.currentSim.eat(maumakan);

                } else if (equals(act, "COOK")) {
                    Print.showCookingMenu();
                    System.out.print("Masukkan nomor masakan yang ingin dibuat: ");
                    int cooknumber = scanner.nextInt();
                    main.currentSim.cook(cooknumber);

                } else if (equals(act, "VISIT")) {
                    // mau masukin visit rumah orang pake nama owner?
                    boolean done = false;
                    Point houseLoc = null;
                    while (!done) {
                        System.out.print("Masukkan nama pemilik rumah yang ingin dikunjungi: ");
                        String ownerHouse = scanner.nextLine();
                        houseLoc = world.searchHouse(ownerHouse);
                        if (houseLoc == null) {
                            System.out.println("Tidak ada rumah yang dimiliki oleh " + ownerHouse);
                            Print.printListSim();
                        } else {
                            done = true;
                        }
                    }

                    System.out.print("Masukkan durasi berkunjung (dalam detik): ");
                    int simVisitTime = scanner.nextInt();
                    main.currentSim.visit(houseLoc, simVisitTime);

                } else if (equals(act, "PEE")) {
                    // sim minimal buang air 1 kali tiap habis makan
                    // efek tidak buang air: -5 kesehatan dan -5 mood 4 menit setelah makan tanpa
                    // buang air -> gimana

                    main.currentSim.pee();
                    time.sleepMain(10);

                } else if (equals(act, "UPGRADE HOUSE")) {
                    // main.currentSim.upgradeHouse(house);
                    // selama 18 menit tapi bisa ditinggal
                    time.sleep(1080);

                } else if (equals(act, "BUY ITEM")) {
                    Print.showBuyObjectMenu();
                    main.currentSim.buyItem();

                } else if (equals(act, "MOVE ROOM")) {
                    main.currentSim.moveRoom();

                } else if (equals(act, "VIEW INVENTORY")) {
                    System.out.println(time.getTime());
                    main.currentSim.viewSimInventory();

                } else if (equals(act, "INSTALL ITEM")) {

                } else if (equals(act, "CHECK TIME")) {
                    // pake harus ngehampirin objek jam dulu
                    System.out.println("4Waktu yang tersisa di- " + time.getTime());
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

    public void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
                Runtime.getRuntime().exec("cls");
            else
                Runtime.getRuntime().exec("clear");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public Sim chooseSim() {
        Print.printListSim();
        boolean done = false;
        String simName;
        while (!done) {
            System.out.print("Masukkan nama Sim yang ingin dimainkan: ");
            simName = scanner.nextLine();
            if (isNotRegistered(simName)) {
                System.out.println("Sim tidak ditemukan di list Sim");
                Print.printListSim();
            } else {
                // ambil Sim di list
                for (Sim sim : Sim.getListSim()) {
                    if (equals(simName, sim.getFullName())) {
                        return sim;
                    }
                }
                done = true;
            }
        }
        return null;
    }

    public boolean isNotRegistered(String simName) {
        for (Sim sim : Sim.getListSim()) {
            if (equals(simName, sim.getFullName())) {
                return false;
            }
        }
        return true;
    }

    public Sim menuAddSim() {
        // Validasi nama Sim
        boolean found = true;
        String simName = null;
        while (found) {
            System.out.print("Masukkan nama Sim yang ingin anda tambahkan: ");
            simName = scanner.nextLine();
            if (isNotRegistered(simName)) {
                found = false;
            } else {
                System.out.println("Nama Sim telah terdaftar. Silakan menggunakan nama lain");
                Print.printListSim();
            }
        }

        // validasi lokasi rumah Sim
        world.printMatrixHouse();
        System.out.println("Masukkan titik untuk mendirikan rumah: ");
        boolean done = false;
        int x = 0, y = 0;
        while (!done) {
            System.out.print("X: ");
            x = scanner.nextInt();
            System.out.print("Y: ");
            y = scanner.nextInt();
            if ((x < 0 || x > 64) || (y < 0 || y > 64)) {
                System.out.println("Titik tidak valid. World berukuran 64x64");
            } else {
                done = true;
            }
        }

        Point houseLoc = new Point(x, y);
        // cek fungsi isWorldAvail
        if (world.isWorldAvail(houseLoc)) {
            Sim newSim = new Sim(simName);
            world.addHouse(newSim.getFullName(), houseLoc);
            System.out.println("Sim berhasil didaftarkan");
            // world.printMatrixHouse();
            return newSim;
        } else {
            System.out.println("Tidak memungkinkan untuk membuat rumah baru di lokasi tersebut");
            System.out.println("Pendaftaran Sim baru gagal");
            return null;
        }
    }

}
