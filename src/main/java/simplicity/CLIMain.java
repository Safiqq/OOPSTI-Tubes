package simplicity;

import java.util.ArrayList;
import java.util.Scanner;

public class CLIMain extends Main {
    private final Scanner scanner;
    private boolean isStarted;

    public CLIMain() {
        scanner = new Scanner(System.in);
        time = new Time();
        dayAddSim = 0;
        isStarted = false;
    }

    public void start() {
        // Inisialisasi main
        CLIMain main = new CLIMain();
        Print.showMenuBegin();
        System.out.println("Ketik 'START GAME' untuk memulai game atau 'HELP' untuk melihat menu game.");

        // Looping sampai START GAME
        while (!isStarted) {
            System.out.print("Masukkan perintah: ");
            String menu = scanner.nextLine();
            if (equals(menu, "START GAME")) {
                isStarted = true;
            } else if (equals(menu, "EXIT")) {
                System.out.println("Anda keluar dari game Simplicity.");
                System.out.println("Bye...");
                System.exit(0);
            } else if (equals(menu, "HELP")) {
                Print.showMenuBegin();
            } else {
                System.out.println("Perintah tidak valid! Ketik 'HELP' untuk melihat menu game.");
            }
        }

        // Cek apakah user menggunakan LOAD GAME
        if (Sim.getListSim().size() == 0) {
            System.out.println("Tidak ada Sim yang tersedia. Silakan daftarkan Sim terlebih dahulu.");
            currentSim = createNewSim();
        } else {
            System.out.print("Apakah anda ingin membuat Sim baru? (YA/TIDAK): ");
            String ans = scanner.nextLine();
            if (equals(ans, "YA")) {
                // Cek apakah masih ada tanah kosong di World
                if (world.isHouseBuildAble()) {
                    boolean done = false;
                    Sim newSim;
                    while (!done) {
                        newSim = menuAddSim();
                        if (newSim != null) {
                            currentSim = newSim;
                            done = true;
                        } else {
                            System.out.println("Gagal. Silakan daftarkan Sim di lokasi yang berbeda!");
                        }
                    }
                } else {
                    System.out.println("World penuh, tidak memungkinkan membuat Sim baru.");
                    System.out.println("Silakan memilih Sim yang sudah ada!");
                    currentSim = chooseSim();
                }
            } else { // Jawaban selain YA dan TIDAK akan dianggap TIDAK
                currentSim = chooseSim();
            }
        }

        System.out.println("Selamat bermain!");
        System.out.println("Ketik 'HELP' untuk melihat menu game yang tersedia.");

        // Looping game
        while (isStarted) {
            // Cek apakah currentSim masih ada di listSim (tidak dihapus karena mati)
            boolean checked = false;
            while (!checked) {
                if (Sim.getListSim().contains(currentSim)) {
                    checked = true;
                } else {
                    System.out.println("Sim yang anda mainkan mati karena depresi, silakan lakukan penggantian Sim.");
                    if (Sim.getListSim().size() == 0) {
                        System.out.println("Tidak bisa dilakukan pergantian Sim. Tidak terdapat Sim lain yang terdaftar.");
                        System.out.println("Silakan buat Sim baru.");
                        currentSim = createNewSim();
                    } else {
                        currentSim = chooseSim();
                    }
                    System.out.println("Selamat bermain!");
                }
            }

            // Input command
            System.out.print("Masukkan perintah: ");
            String menu = scanner.nextLine();
            if (equals(menu, "HELP")) {
                Print.showMenu();

            } else if (equals(menu, "EXIT")) {
                System.out.println("Anda keluar dari game Simplicity");
                System.out.println("Bye...");
                System.exit(0);

            } else if (equals(menu, "VIEW SIM INFO")) {
                Print.viewSimInfo(currentSim);

            } else if (equals(menu, "VIEW CURRENT LOCATION")) {
                Print.viewSimLoc(currentSim);

            } else if (equals(menu, "VIEW INVENTORY")) {
                Print.viewSimInventory(currentSim);

            } else if (equals(menu, "UPGRADE HOUSE")) {
                currentSim.upgradeHouse(currentSim.getSimLoc().getHouse());
                // selama 18 menit tapi bisa ditinggal
                time.sleep(1080);

            } else if (equals(menu, "MOVE ROOM")) {
                moveRoom();

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
                if (time.getDay() > dayAddSim) {
                    // cek masi ada spot kosong di world ato ngga
                    if (world.isHouseBuildAble()) {
                        boolean done = false;
                        Sim newSim;
                        while (!done) {
                            newSim = menuAddSim();
                            if (newSim != null) {
                                dayAddSim = time.getDay(); // hanya dapat dilakukan 1 hari sekali
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
                    Sim oldSim = currentSim;
                    boolean done = false;
                    Sim newSim = null;
                    while (!done) {
                        newSim = chooseSim();
                        if (equals(newSim.getFullName(), oldSim.getFullName())) {
                            System.out.println("Nama Sim sama dengan yang sedang anda mainkan");
                            System.out.println("Masukkan nama Sim yang berbeda");
                        } else {
                            done = true;
                        }
                    }
                    currentSim = newSim;
                    System.out.println("Sim berhasil diganti. Selamat bermain!");
                }

            } else if (equals(menu, "LIST OBJECT")) {
                // menampilkan daftar objek dalam sebuah ruangan
                // asumsi: sim harus berada di dalam ruangan untuk melihat daftar objek dalam
                // ruangan tsb
                ArrayList<NonFood> listObjek = currentSim.getSimLoc().getRoom().getListObjek();
                if (listObjek.size() > 0) {
                    int i = 1;
                    for (NonFood nonFood : listObjek) {
                        System.out.println(i + ". " + nonFood.getObjekName());
                        i++;
                    }
                } else {
                    System.out.println("Tidak ada daftar objek dalam ruangan " + currentSim.getSimLoc().getRoom().getRoomName() + ".");
                }

            } else if (equals(menu, "GO TO OBJECT")) {
                currentSim.goToObjek();

            } else if (equals(menu, "CHANGE JOB")) {
                // harus setidaknya 12 menit bekerja
                if (currentSim.getWorkTime() >= 720) {
                    currentSim.newJob();
                    currentSim.setDayChangeJob(time.getDay());
                } else {
                    System.out.println("Sim hanya dapat mengganti pekerjaan jika sudah bekerja setidaknya 12 menit");
                }

            } else if (equals(menu, "ACTION")) {
                if (equals(currentSim.getObjLoc(), "Kasur single") || equals(currentSim.getObjLoc(), "Kasur queen size") || equals(currentSim.getObjLoc(), "Kasur king size")){
                    Print.showAction("Sleep");
                } else if (equals(currentSim.getObjLoc(), "Toilet")){
                    Print.showAction("Pee");
                } else if (equals(currentSim.getObjLoc(), "Kompor gas") || equals(currentSim.getObjLoc(), "Kompor listrik")){
                    Print.showAction("Cook");
                } else if (equals(currentSim.getObjLoc(), "Meja dan kursi")){
                    Print.showAction("Eat");
                } else if (equals(currentSim.getObjLoc(), "Jam")){
                    Print.showAction("Check Time");
                } else {
                    Print.showAction(null);
                }

                System.out.print("Masukkan aksi yang ingin dijalankan: ");
                String act = scanner.nextLine();

                if (equals(act, "WORK")) {
                    // pekerjaan baru hanya dapat dikerjakan 1 hari setelah hari penggantian pekerjaan
                    if (time.getDay() > currentSim.getDayChangeJob()) {
                        // validasi waktu kerja kelipatan 120
                        int simWorkTime = validateTime("kerja", 120);
                        currentSim.addStatus("Work", simWorkTime);
                        time.sleepMain(simWorkTime);
                        // efek kerja
                        currentSim.work(simWorkTime);
                        currentSim.deleteStatus("Work");
                    } else {
                        System.out.println("Pekerjaan baru hanya dapat dikerjakan 1 hari setelah hari penggantian pekerjaan");
                    }

                } else if (equals(act, "EXERCISE")) {
                    // validasi waktu olahraga kelipatan 20
                    int simExerciseTime = validateTime("olahraga", 20);
                    currentSim.addStatus("Exercise", simExerciseTime);
                    time.sleepMain(simExerciseTime);
                    // efek olahraga
                    currentSim.exercise(simExerciseTime);
                    currentSim.deleteStatus("Exercise");

                } else if (equals(act, "SLEEP")) {
                    if (equals(currentSim.getObjLoc(), "Kasur single") || equals(currentSim.getObjLoc(), "Kasur queen size") || equals(currentSim.getObjLoc(), "Kasur king size")){
                        // sim sebagai manusia harus memiliki waktu tidur min 3 mnt setiap harinya
                        // efek tidak tidur -> -5 kesehatan dan -5 mood setelah 10 mnt tanpa tidur
                        // apakah harus 3 menit langsung atau boleh dicicil?
                        // penambahan efek tidur apakah akumulasi dalam hari tersebut atau langsung
                        // dibagi tiap tidur

                        System.out.print("Masukkan durasi tidur (dalam detik): ");
                        int simSleepTime = scanner.nextInt();
                        currentSim.addStatus("Sleep", simSleepTime);
                        time.sleepMain(simSleepTime);
                        // efek tidur
                        currentSim.sleep(simSleepTime);
                        currentSim.deleteStatus("Sleep");
                    } else {
                        System.out.println("Sim hanya dapat melakukan aksi ini jika sedang di kasur");
                        System.out.println("Silahkan melakukan action - go to object ke kasur untuk menjalankan aksi ini");
                    }

                } else if (equals(act, "EAT")) {
                    if (equals(currentSim.getObjLoc(), "Meja dan kursi")){
                        // Duration belum diset
                        currentSim.addStatus("Eat", 0);
                        // efek makan
                        currentSim.eat();
                        currentSim.deleteStatus("Eat");
                    } else {
                        System.out.println("Sim hanya dapat melakukan aksi ini jika sedang di Meja dan kursi");
                        System.out.println("Silahkan melakukan action - go to object ke Meja dan kursi untuk menjalankan aksi ini");
                    }

                } else if (equals(act, "COOK")) {
                    if (equals(currentSim.getObjLoc(), "Kompor gas") || equals(currentSim.getObjLoc(), "Kompor listrik")){
                        Print.showCookingMenu();
                        // Duration belum diset
                        currentSim.addStatus("Cook", 0);
                        // efek memasak
                        currentSim.cook();
                        currentSim.deleteStatus("Cook");
                    } else {
                        System.out.println("Sim hanya dapat melakukan aksi ini jika sedang di Kompor gas atau Kompor listrik");
                        System.out.println("Silahkan melakukan action - go to object ke Kompor gas atau Kompor listrik untuk menjalankan aksi ini");
                    }

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

                    // validasi waktu berkunjung kelipatan 30
                    int simVisitTime = validateTime("berkunjung", 30);

                    // waktu yang diperlukan untuk berkunjung ke rumah
                    // perhitungan/pemilihan titik rumah dari SIM yang ingin dikunjungi dibebaskan -> belum ditentuin
                    double x = Math.pow(houseLoc.getX() - currentSim.getSimLoc().getPoint().getX(), 2);
                    double y = Math.pow(houseLoc.getY() - currentSim.getSimLoc().getPoint().getY(), 2);
                    double walkTime = Math.sqrt(x + y);

                    int time_total = simVisitTime + (int) walkTime;

                    currentSim.addStatus("Visit", time_total);
                    time.sleepMain(time_total);
                    // efek berkunjung
                    currentSim.visit(time_total);
                    currentSim.deleteStatus("Visit");

                } else if (equals(act, "PEE")) {
                    if (equals(currentSim.getObjLoc(), "Toilet")){
                        // sim minimal buang air 1 kali tiap habis makan
                        // efek tidak buang air: -5 kesehatan dan -5 mood 4 menit setelah makan tanpa
                        // buang air -> gimana

                        currentSim.addStatus("Pee", 10);
                        // siklus 10 detik
                        time.sleepMain(10);
                        // efek buang air
                        currentSim.pee();
                        currentSim.deleteStatus("Pee");
                    } else {
                        System.out.println("Sim hanya dapat melakukan aksi ini jika sedang di toilet");
                        System.out.println("Silahkan melakukan action - go to object ke toilet untuk menjalankan aksi ini");
                    }

                } else if (equals(act, "UPGRADE HOUSE")) {
                    // currentSim.upgradeHouse(house);
                    currentSim.upgradeHouse(currentSim.getSimLoc().getHouse());
                    // selama 18 menit tapi bisa ditinggal
                    time.sleep(1080);

                } else if (equals(act, "BUY ITEM")) {
                    Print.showBuyObjectMenu();
                    currentSim.buyItem();

                } else if (equals(act, "MOVE ROOM")) {
                    moveRoom();

                } else if (equals(act, "VIEW INVENTORY")) {
                    System.out.println(time.getTime());
                    Print.viewSimInventory(currentSim);

                } else if (equals(act, "INSTALL ITEM")) {

                } else if (equals(act, "CHECK TIME")) {
                    if (equals(currentSim.getObjLoc(), "Jam")){
                        System.out.println("Waktu yang tersisa di- " + time.getTime());
                        // sisa waktu yang masih ada untuk seluruh tindakan yang bisa ditinggal

                    } else {
                        System.out.println("Sim hanya dapat melakukan aksi ini jika sedang di jam");
                        System.out.println("Silahkan melakukan action - go to object ke jam untuk menjalankan aksi ini");
                    }

                } else {
                    System.out.println("Aksi tidak valid");
                }
            } else {
                System.out.println("Perintah tidak valid");
            }
        }
        scanner.close();
    }

    // Input durasi. Validasi sampai durasi merupakan kelipatan X
    public int validateTime(String action, int kelipatan) {
        boolean done = false;
        int time = 0;
        while (!done) {
            System.out.print("Masukkan durasi " + action + " (dalam detik): ");
            time = scanner.nextInt();
            if (time % kelipatan != 0) {
                System.out.println("Durasi " + action + " harus kelipatan " + kelipatan);
            } else {
                done = true;
            }
        }
        return time;
    }

    // Bersihkan layar terminal
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

    // Buat Sim. Validasi sampai nama Sim ada di listSim
    public Sim chooseSim() {
        Print.printListSim();
        while (true) {
            System.out.print("Masukkan nama Sim yang ingin dimainkan: ");
            String simName = scanner.nextLine();
            if (Sim.isNotRegistered(simName)) {
                System.out.println("Sim tidak ditemukan di list Sim!");
                Print.printListSim();
            } else {
                // Ambil Sim di listSim
                return Sim.get(simName);
            }
        }
    }

    // Buat Sim baru (bukan Sim pertama)
    public Sim menuAddSim() {
        // Validasi nama Sim
        System.out.print("Masukkan nama Sim yang ingin anda tambahkan: ");
        String simName = scanner.nextLine();
        while (Sim.isNotRegistered(simName)) {
            System.out.println("Nama Sim telah terdaftar. Silakan menggunakan nama lain.");
            Print.printListSim();
            System.out.print("Masukkan nama Sim yang ingin anda tambahkan: ");
            simName = scanner.nextLine();
        }

        // Validasi lokasi rumah Sim
        world.printMatrixHouse();
        System.out.println("Masukkan titik untuk mendirikan rumah: ");
        boolean done = false;
        int x = 0, y = 0;
        while (!done) {
            System.out.print("X: ");
            x = scanner.nextInt();
            System.out.print("Y: ");
            y = scanner.nextInt();
            if ((x < 0 || x > world.getWorldLength() - 1) || (y < 0 || y > world.getWorldWidth() - 1)) {
                System.out.println("Titik tidak valid. World berukuran 64x64.");
            } else {
                done = true;
            }
        }

        // Buat Sim
        Point houseLoc = new Point(x, y);
        if (world.isWorldAvail(houseLoc)) {
            Sim newSim = new Sim(simName, houseLoc);
            System.out.println("Sim berhasil didaftarkan.");
            return newSim;
        } else {
            System.out.println("Tidak memungkinkan untuk membuat rumah baru di lokasi tersebut.");
            System.out.println("Pendaftaran Sim baru gagal!");
            return null;
        }
    }

    // Buat Sim baru (Sim pertama)
    public Sim createNewSim() {
        System.out.print("Masukkan nama Sim anda: ");
        String simName = scanner.nextLine();
        while (equals(simName.trim(), "") && Sim.isNotRegistered(simName)) {
            if (equals(simName.trim(), "")) {
                System.out.print("Nama Sim tidak boleh kosong.");
            } else {
                System.out.print("Nama Sim sudah terdaftar. Silakan masukkan nama lain.");
            }
            System.out.println("Masukkan nama Sim anda: ");
            simName = scanner.nextLine();
        }
        System.out.println("Sim dengan nama " + simName + " berhasil dibuat.");
        return new Sim(simName);
    }

    // Sim pindah ruangan
    public void moveRoom() {
        House house = currentSim.getSimLoc().getHouse();
        ArrayList<Room> listRoom = house.getListRoom();
        if (listRoom.size() == 1) {
            System.out.println("Hanya ada Ruangan Utama di rumah ini.");
        } else {
            System.out.println("Daftar ruangan yang terdapat di dalam rumah: ");
            int i = 1;
            for (Room room : listRoom) {
                System.out.println(i + ". " + room.getRoomName());
                i++;
            }

            String oldRoom = currentSim.getSimLoc().getRoom().getRoomName();

            while (true) {
                System.out.print("Masukkan nama ruangan yang ingin didatangi: ");
                String roomName = scanner.nextLine();
                if (Main.equals(oldRoom, roomName)) {
                    System.out.println("Nama ruangan sama dengan tempat Sim berada ");
                    System.out.println("Sim berada di ruangan " + oldRoom);
                    System.out.println();
                } else {
                    if (house.get(roomName) == null) {
                        System.out.println("Tidak ditemukan ruangan bernama " + roomName);
                        System.out.println();
                    } else {
                        currentSim.moveRoom(roomName);
                        break;
                    }
                }
            }
        }
    }
}
