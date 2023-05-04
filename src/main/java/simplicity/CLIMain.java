package simplicity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

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
            System.out.print("Apakah Anda ingin membuat Sim baru? (YA/TIDAK): ");
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
                    System.out.println("Sim yang Anda mainkan mati karena depresi, silakan lakukan penggantian Sim.");
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
                upgradeHouse();

            } else if (equals(menu, "MOVE ROOM")) {
                moveRoom();

            } else if (equals(menu, "EDIT ROOM")) {
                boolean done = false;
                while (!done) {
                    System.out.print("Apakah Anda ingin membeli barang baru atau memindahkan barang? (beli/pindah)");
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
                            System.out.println("Nama Sim sama dengan yang sedang Anda mainkan.");
                            System.out.println("Masukkan nama Sim yang berbeda!");
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
                    System.out.println("Tidak ada daftar objek dalam ruangan "
                            + currentSim.getSimLoc().getRoom().getRoomName() + ".");
                }

            } else if (equals(menu, "GO TO OBJECT")) {
                currentSim.goToObjek();

            } else if (equals(menu, "CHANGE JOB")) {
                // harus setidaknya 12 menit bekerja
                if (currentSim.getTotalWorkTime() >= 720) {
                    currentSim.newJob();
                    currentSim.setDayChangeJob(time.getDay());
                } else {
                    System.out.println("Sim hanya dapat mengganti pekerjaan jika sudah bekerja setidaknya 12 menit.");
                }

            } else if (equals(menu, "ACTION")) {
                Print.showAction(currentSim.getObjLoc());

                System.out.print("Masukkan aksi yang ingin dijalankan: ");
                String act = scanner.nextLine();

                if (equals(act, "WORK")) {
                    // pekerjaan baru hanya dapat dikerjakan 1 hari setelah hari penggantian
                    // pekerjaan
                    if (time.getDay() > currentSim.getDayChangeJob()) {
                        // validasi waktu kerja kelipatan 120
                        int simWorkTime = validateTime("kerja", 120);
                        // cek waktu sisa dalam hari
                        int remainingSeconds = time.getMinute() * 60 + time.getSecond();
                        // jika sim kerja lebih dari sisa waktu, maka sisa waktu dihitung kerja esok
                        // harinya
                        if (simWorkTime > remainingSeconds) {
                            currentSim.setStoreWorkTime(simWorkTime - remainingSeconds);
                            currentSim.setWorkTime(currentSim.getWorkTime() + remainingSeconds);
                        } else {
                            currentSim.setWorkTime(currentSim.getWorkTime() + simWorkTime);
                        }

                        // menjalankan pekerjaan
                        currentSim.addStatus("Work", simWorkTime);
                        time.sleepMain(currentSim, simWorkTime);

                        // hitung gaji
                        if (time.getDay() > currentSim.getDayWork()) {
                            // gaji harian (4 menit kerja = 240 dtk)
                            // gaji baru dihitung setelah berganti hari
                            if (currentSim.getWorkTime() > 240) {
                                int payday = currentSim.getOccupation().getDailySalary()
                                        * (currentSim.getWorkTime() / 240);
                                currentSim.setMoney(currentSim.getMoney() + payday);

                                currentSim.setDayWork(time.getDay());
                                currentSim.setWorkTime(0);
                            }
                            // tidak bekerja lebih dari 4 menit, maka tidak digaji

                            // klo ada waktu kerja yg kepotong hari, ditambah ke workTime setelah berganti
                            // hari dan telah direset 0
                            if (currentSim.getStoreWorkTime() > 0) {
                                currentSim.setWorkTime(currentSim.getWorkTime() + currentSim.getStoreWorkTime());
                                currentSim.setStoreWorkTime(0);
                            }
                        }

                    } else {
                        System.out.println("Pekerjaan baru hanya dapat dikerjakan 1 hari setelah hari penggantian pekerjaan.");
                    }

                } else if (equals(act, "EXERCISE")) {
                    // validasi waktu olahraga kelipatan 20
                    int simExerciseTime = validateTime("olahraga", 20);

                    currentSim.addStatus("Exercise", simExerciseTime);
                    time.sleepMain(currentSim, simExerciseTime);

                    // efek olahraga
                    currentSim.exercise(simExerciseTime);

                } else if (equals(act, "SLEEP")) {
                    if (currentSim.getObjLoc().contains("Kasur")) {
                        // sim sebagai manusia harus memiliki waktu tidur min 3 mnt setiap harinya
                        // efek tidak tidur -> -5 kesehatan dan -5 mood setelah 10 mnt tanpa tidur
                        // apakah harus 3 menit langsung atau boleh dicicil?
                        // penambahan efek tidur apakah akumulasi dalam hari tersebut atau langsung
                        // dibagi tiap tidur

                        System.out.print("Masukkan durasi tidur (dalam detik): ");
                        int simSleepTime = scanner.nextInt();

                        currentSim.deleteStatus("Not Sleep");
                        currentSim.addStatus("Sleep", simSleepTime);
                        time.sleepMain(currentSim, simSleepTime);
                        currentSim.addStatus("Not Sleep", 10 * 60);

                        // efek tidur
                        currentSim.sleep(simSleepTime);
                    } else {
                        System.out.println("Sim hanya dapat melakukan aksi ini jika sedang di kasur.");
                        System.out.println("Silakan melakukan Action - Go to Object ke kasur untuk menjalankan aksi ini.");
                    }

                } else if (equals(act, "EAT")) {
                    if (equals(currentSim.getObjLoc(), "Meja dan kursi")) {
                        // Duration belum diset
                        currentSim.addStatus("Eat", 0);
                        // efek makan
                        eat();
                    } else {
                        System.out.println("Sim hanya dapat melakukan aksi ini jika sedang di meja dan kursi.");
                        System.out.println("Silakan melakukan Action - Go to Object ke meja dan kursi untuk menjalankan aksi ini.");
                    }

                } else if (equals(act, "COOK")) {
                    if (currentSim.getObjLoc().contains("Kompor")) {
                        Print.showCookingMenu();
                        // efek memasak
                        System.out.print("Masukkan nomor masakan yang ingin dibuat: ");
                        int cooknumber = scanner.nextInt();

                        Food food = null;
                        if (cooknumber == 1 && currentSim.checkGroceries("Nasi") && currentSim.checkGroceries("Ayam")) {
                            food = new Food("Nasi Ayam");
                        } else if (cooknumber == 2 && currentSim.checkGroceries("Nasi")
                                && currentSim.checkGroceries("Kentang") && currentSim.checkGroceries("Wortel")
                                && currentSim.checkGroceries("Sapi")) {
                            food = new Food("Nasi Kari");
                        } else if (cooknumber == 3 && currentSim.checkGroceries("Susu")
                                && currentSim.checkGroceries("Kacang")) {
                            food = new Food("Susu Kacang");
                        } else if (cooknumber == 4 && currentSim.checkGroceries("Wortel")
                                && currentSim.checkGroceries("Bayam")) {
                            food = new Food("Tumis Sayur");
                        } else if (cooknumber == 5 && currentSim.checkGroceries("Kentang")
                                && currentSim.checkGroceries("Sapi")) {
                            food = new Food("Bistik");
                        } else {
                            if (cooknumber < 1 || cooknumber > 5) {
                                System.out.println("Masukkan nomor yang sesuai dong!");
                            } else {
                                System.out.println("Bahan makananmu kurang :(");
                            }
                        }

                        if (food != null) {
                            currentSim.cook(food);
                            for (Groceries groceries : food.getListGroceries()) {
                                currentSim.deleteGroceriesFromInventory(groceries.getObjekName());
                            }
                            System.out.println("Berhasil memasak.");
                            currentSim.addStatus("Cook", (int) (1.5 * food.getFoodHunger()));
                        }
                    } else {
                        System.out.println("Sim hanya dapat melakukan aksi ini jika sedang di kompor gas atau kompor listrik.");
                        System.out.println("Silahkan melakukan Action - Go to Object ke kompor gas atau kompor listrik untuk menjalankan aksi ini.");
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
                    // perhitungan/pemilihan titik rumah dari SIM yang ingin dikunjungi dibebaskan
                    // -> belum ditentuin
                    double x = Math.pow(houseLoc.getX() - currentSim.getSimLoc().getPoint().getX(), 2);
                    double y = Math.pow(houseLoc.getY() - currentSim.getSimLoc().getPoint().getY(), 2);
                    double walkTime = Math.sqrt(x + y);

                    int time_total = simVisitTime + (int) walkTime;

                    currentSim.addStatus("Visit", time_total);
                    time.sleepMain(currentSim, time_total);
                    // efek berkunjung
                    currentSim.visit(time_total);

                } else if (equals(act, "PEE")) {
                    if (equals(currentSim.getObjLoc(), "Toilet")) {
                        // sim minimal buang air 1 kali tiap habis makan
                        // efek tidak buang air: -5 kesehatan dan -5 mood 4 menit setelah makan tanpa
                        // buang air -> gimana

                        currentSim.deleteStatus("Not Pee");
                        currentSim.addStatus("Pee", 10);
                        time.sleepMain(currentSim, 10);

                        // efek buang air
                        currentSim.pee();
                    } else {
                        System.out.println("Sim hanya dapat melakukan aksi ini jika sedang di toilet.");
                        System.out.println("Silahkan melakukan action - go to object ke toilet untuk menjalankan aksi ini.");
                    }

                } else if (equals(act, "UPGRADE HOUSE")) {
                    upgradeHouse();

                } else if (equals(act, "BUY ITEM")) {
                    Print.showBuyObjectMenu();
                    buyItem();

                } else if (equals(act, "MOVE ROOM")) {
                    moveRoom();

                } else if (equals(act, "VIEW INVENTORY")) {
                    System.out.println(time.getTime());
                    Print.viewSimInventory(currentSim);

                } else if (equals(act, "INSTALL ITEM")) {
                    installItem();

                } else if (equals(act, "CHECK TIME")) {
                    if (equals(currentSim.getObjLoc(), "Jam")) {
                        System.out.println("Waktu yang tersisa di- " + time.getTime());
                        // sisa waktu yang masih ada untuk seluruh tindakan yang bisa ditinggal
                    } else {
                        System.out.println("Sim hanya dapat melakukan aksi ini jika sedang di jam.");
                        System.out
                                .println("Silahkan melakukan Action - Go to Object ke jam untuk menjalankan aksi ini.");
                    }

                } else if (equals(act, "CLIMB TABLE AND CHAIR")) {
                    if (equals(currentSim.getObjLoc(), "Meja dan kursi")) {
                        System.out.println("Sim naik ke meja dan kursi.");
                    } else {
                        System.out.println("Sim hanya dapat melakukan aksi ini jika sedang di meja dan kursi.");
                        System.out.println("Silahkan melakukan Action - Go to Object ke meja dan kursi untuk menjalankan aksi ini.");
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
        System.out.print("Masukkan nama Sim yang ingin Anda tambahkan: ");
        String simName = scanner.nextLine();
        while (Sim.isNotRegistered(simName)) {
            System.out.println("Nama Sim telah terdaftar. Silakan menggunakan nama lain.");
            Print.printListSim();
            System.out.print("Masukkan nama Sim yang ingin Anda tambahkan: ");
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
            if ((x < 0 || x > world.getLength() - 1) || (y < 0 || y > world.getWidth() - 1)) {
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
        System.out.print("Masukkan nama Sim Anda: ");
        String simName = scanner.nextLine();
        while (equals(simName.trim(), "") && Sim.isNotRegistered(simName)) {
            if (equals(simName.trim(), "")) {
                System.out.print("Nama Sim tidak boleh kosong.");
            } else {
                System.out.print("Nama Sim sudah terdaftar. Silakan masukkan nama lain.");
            }
            System.out.println("Masukkan nama Sim Anda:");
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

    // Sim upgrade rumah (tambah ruangan)
    public void upgradeHouse() {
        House house = currentSim.getSimLoc().getHouse();
        List<Room> listRoom = house.getListRoom();
        Room tempRoom = currentSim.getSimLoc().getRoom();
        System.out.println("List ruangan di rumahmu:");
        for (int i = 0; i < listRoom.size(); i++) {
            System.out.println((i + 1) + ". " + listRoom.get(i).getRoomName());
        }

        if (currentSim.isMoneyEnough(1500)) {
            // Kalau rumah sekarang cuma ada 1 ruangan
            if (listRoom.size() > 1) {
                while (true) {
                    System.out.print("Masukkan nama salah satu ruangan sebagai acuan: ");
                    String roomName = scanner.nextLine();
                    tempRoom = house.get(roomName);
                    if (tempRoom != null) {
                        break;
                    } else {
                        System.out.println("Ruangan tidak dikenali...");
                    }
                }
            }

            System.out.print("Masukkan nama ruangan baru:");
            String newRoomName = scanner.nextLine();

            // Loop untuk mendapatkan lokasi ruangan baru yang valid
            while (true) {
                System.out.printf("Pilih lokasi %s disebelah Ruangan Utama (kiri/kanan/atas/bawah): ", newRoomName);
                String roomLoc = scanner.nextLine();
                if (Main.equals(roomLoc, "KANAN")) {
                    Room newRoom = new Room(newRoomName, null, null, tempRoom, null);
                    tempRoom.setRightSide(newRoom);
                    house.addListRoom(newRoom);
                    break;
                } else if (Main.equals(roomLoc, "KIRI")) {
                    Room newRoom = new Room(newRoomName, null, null, null, tempRoom);
                    tempRoom.setLeftSide(newRoom);
                    house.addListRoom(newRoom);
                    break;
                } else if (Main.equals(roomLoc, "ATAS")) {
                    Room newRoom = new Room(newRoomName, null, tempRoom, null, null);
                    tempRoom.setUpperSide(newRoom);
                    house.addListRoom(newRoom);
                    break;
                } else if (Main.equals(roomLoc, "BAWAH")) {
                    Room newRoom = new Room(newRoomName, tempRoom, null, null, null);
                    tempRoom.setBottomSide(newRoom);
                    house.addListRoom(newRoom);
                    break;
                } else {
                    System.out.println("Lokasi tidak valid.");
                }
            }
            // Decrease money
            currentSim.setMoney(currentSim.getMoney() - 1500);
            currentSim.addStatus("Upgrade house-" + newRoomName, 18 * 60);
        } else {
            System.out.println("Uang Sim tidak cukup untuk upgrade rumah.");
        }
    }

    public void buyItem() {
        int buynumber;

        while (true) {
            System.out.println("Masukkan nomor item yang ingin dibeli.");
            System.out.print("Nomor: ");
            buynumber = scanner.nextInt();
            if (buynumber >= 1 && buynumber <= 18) {
                break;
            } else {
                System.out.println("Nomor tidak teridentifikasi. Masukkan nomor yang tersedia.");
            }
        }

        Objek objek = null;

        if (buynumber == 1 && currentSim.isMoneyEnough(NonFood.get("Kasur Single").getPrice())) {
            objek = new NonFood("Kasur Single");
        } else if (buynumber == 2 && currentSim.isMoneyEnough(NonFood.get("Kasur Queen Size").getPrice())) {
            objek = new NonFood("Kasur Queen Size");
        } else if (buynumber == 3 && currentSim.isMoneyEnough(NonFood.get("Kasur King Size").getPrice())) {
            objek = new NonFood("Kasur King Size");
        } else if (buynumber == 4 && currentSim.isMoneyEnough(NonFood.get("Toilet").getPrice())) {
            objek = new NonFood("Toilet");
        } else if (buynumber == 5 && currentSim.isMoneyEnough(NonFood.get("Kompor Gas").getPrice())) {
            objek = new NonFood("Kompor Gas");
        } else if (buynumber == 6 && currentSim.isMoneyEnough(NonFood.get("Kompor Listrik").getPrice())) {
            objek = new NonFood("Kompor Listrik");
        } else if (buynumber == 7 && currentSim.isMoneyEnough(NonFood.get("Meja dan Kursi").getPrice())) {
            objek = new NonFood("Meja dan Kursi");
        } else if (buynumber == 8 && currentSim.isMoneyEnough(NonFood.get("Jam").getPrice())) {
            objek = new NonFood("Jam");
        } else if (buynumber == 9 && currentSim.isMoneyEnough(NonFood.get("Wastafel").getPrice())) {
            objek = new NonFood("Wastafel");
        } else if (buynumber == 10 && currentSim.isMoneyEnough(NonFood.get("Cermin").getPrice())) {
            objek = new NonFood("Cermin");
        } else if (buynumber == 11 && currentSim.isMoneyEnough(Groceries.get("Nasi").getPrice())) {
            objek = new Groceries("Nasi");
        } else if (buynumber == 12 && currentSim.isMoneyEnough(Groceries.get("Kentang").getPrice())) {
            objek = new Groceries("Kentang");
        } else if (buynumber == 13 && currentSim.isMoneyEnough(Groceries.get("Ayam").getPrice())) {
            objek = new Groceries("Ayam");
        } else if (buynumber == 14 && currentSim.isMoneyEnough(Groceries.get("Sapi").getPrice())) {
            objek = new Groceries("Sapi");
        } else if (buynumber == 15 && currentSim.isMoneyEnough(Groceries.get("Wortel").getPrice())) {
            objek = new Groceries("Wortel");
        } else if (buynumber == 16 && currentSim.isMoneyEnough(Groceries.get("Bayam").getPrice())) {
            objek = new Groceries("Bayam");
        } else if (buynumber == 17 && currentSim.isMoneyEnough(Groceries.get("Kacang").getPrice())) {
            objek = new Groceries("Kacang");
        } else if (buynumber == 18 && currentSim.isMoneyEnough(Groceries.get("Susu").getPrice())) {
            objek = new Groceries("Susu");
        }

        Random rd = new Random();
        double randomizer = rd.nextDouble(1.5);
        int waktukirim = (int) (randomizer * 30);

        if (objek != null) {
            if (Main.equals(objek.getClass().getSimpleName(), "Groceries")) {
                if (currentSim.isMoneyEnough(((Groceries) objek).getPrice())) {
                    currentSim.setMoney(currentSim.getMoney() - ((Groceries) objek).getPrice());
                    currentSim.getInventory().getBoxGroceries().add((Groceries) objek);
                    time.sleepMain(currentSim, waktukirim);
                    System.out.println("Berhasil membeli barang!");
                } else {
                    System.out.println("Uangmu kurang :(");
                }
            } else if (Main.equals(objek.getClass().getSimpleName(), "NonFood")) {
                if (currentSim.isMoneyEnough(((NonFood) objek).getPrice())) {
                    currentSim.setMoney(currentSim.getMoney() - ((NonFood) objek).getPrice());
                    currentSim.getInventory().getBoxNonFood().add((NonFood) objek);
                    time.sleepMain(currentSim, waktukirim);
                    System.out.println("Berhasil membeli barang!");
                } else {
                    System.out.println("Uangmu kurang :(");
                }
            }
        }
    }

    public void installItem() {
        // kalau sim sedang berada di rumah sendiri
        if (!Main.equals(currentSim.getFullName(), currentSim.getSimLoc().getHouse().getOwner())) {
            boolean barangValid = false;
            Box<NonFood> boxNonFood = currentSim.getInventory().getBoxNonFood();

            System.out.println("List barang di inventory Anda:");
            int i = 1;
            for (NonFood barang : boxNonFood.getList()) {
                System.out.println(i + ". " + barang.getObjekName() + " - " + boxNonFood.getCount(barang.getObjekName()));
                i++;
            }

            // looping input nama barang
            while (!barangValid) {
                System.out.print("Masukkan nama barang yang ingin dipasang: ");
                String namaBarang = scanner.nextLine();
                System.out.println();
                // cek barang di inventory
                for (NonFood barang : boxNonFood.getList()) {
                    // kalau barang ada di inventory
                    if (Main.equals(namaBarang, barang.getObjekName())) {
                        boolean roomValid = false;

                        // looping input nama ruangan
                        while (!roomValid) {
                            System.out.print("Masukkan nama ruangan: ");
                            String roomName = scanner.nextLine();
                            System.out.println();
                            // cek valid ruangan
                            for (Room room : currentSim.getSimLoc().getHouse().getListRoom()) {
                                // kalau nama ruangan valid
                                if (Main.equals(roomName, room.getRoomName())) {
                                    boolean pointValid = false;
                                    while (!pointValid) {
                                        // input lokasi barang di ruangan
                                        System.out.print("Masukkan lokasi awal sumbu X barang: ");
                                        int startX = scanner.nextInt();
                                        System.out.println();

                                        System.out.print("Masukkan lokasi awal sumbu Y barang: ");
                                        int startY = scanner.nextInt();
                                        System.out.println();

                                        System.out.print("Masukkan lokasi akhir sumbu X barang: ");
                                        int endX = scanner.nextInt();
                                        System.out.println();

                                        System.out.print("Masukkan lokasi akhir sumbu Y barang: ");
                                        int endY = scanner.nextInt();
                                        System.out.println();

                                        // Kalau point berada diluar 0-5
                                        if (startX < 0 || startX > 5 || startY < 0 || startY > 5 || endX < 0 || endX > 5
                                                || endY < 0 || endY > 5) {
                                            System.out.println("Point tidak valid.");
                                            // Kalau point berada di antara 0-5
                                        } else {
                                            int length = startX - endX + 1;
                                            int width = startY - endY + 1;
                                            // Kalau point sesuai dengan ukuran barang
                                            if ((length == barang.getLength()) && (width == barang.getWidth())) {
                                                Point startPoint = new Point(startX, startY);
                                                Point endPoint = new Point(endX, endY);
                                                // Kalau lokasi yang dipilih tersedia
                                                if (room.isSpaceEmpty(startPoint, endPoint)) {
                                                    room.insertBarang(barang);
                                                    room.addListObjek(barang);
                                                    boxNonFood.delete(barang.getObjekName());
                                                    pointValid = true;
                                                }
                                                // Kalau lokasi yang dipilih ga tersedia
                                                else {
                                                    System.out.println("Area ruangan tidak kosong.");
                                                }
                                            }
                                            // Kalau point tidak sesuai ukuran barang
                                            else {
                                                System.out.println("Point tidak sesuai dengan ukuran barang.");
                                            }
                                        }
                                    }
                                    roomValid = true;
                                    break;
                                }
                                // Kalau nama ruangan tidak valid
                                else {
                                    System.out.println("Ruangan tidak dikenali.");
                                }
                            }

                        }
                        barangValid = true;
                        break;
                    }
                    // Kalau barang ga ada di inventory
                    else {
                        System.out.println("Barang tidak ada di inventory.");
                    }
                }

            }
        } else { // Kalau sim berada di rumah orang lain
            System.out.println("Sim harus berada di rumah sendiri untuk memasang barang");
        }
    }

    public void eat() {
        Print.viewSimFood(currentSim);
        System.out.print("Mau makan apa? ");
        String namaMakanan = scanner.nextLine();
        if (Food.get(namaMakanan) != null) {
            if (currentSim.checkFood(namaMakanan)) {
                Food food = currentSim.getInventory().getBoxFood().get(namaMakanan);
                System.out.println("Sedang Memakan " + food.getObjekName());
                System.out.println(".......Please wait.......");
                Action.get("Eat").getListEffect().get(0).setMotiveEffect(food.getFoodHunger());
                currentSim.addStatus("Eat", 30);
                time.sleepMain(currentSim, 30);

                System.out.println("Anda selesai makan!");
                currentSim.getInventory().getBoxFood().delete(food);
                currentSim.addStatus("Not Pee", 4 * 60);
            } else {
                System.out.println("Anda tidak memiliki makanan " + namaMakanan);
            }
        } else {
            System.out.println("Tidak ada makanan dengan nama " + namaMakanan);
        }
    }
}
