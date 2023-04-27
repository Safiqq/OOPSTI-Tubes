package simplicity;

import com.google.common.primitives.Ints;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final World world = World.getWorld();
    private static Time time;
    private final List<Sim> listSim = new ArrayList<>();
    private final String[] menu = {"Start Game - Memulai permainan", "Help - Melihat menu game yang tersedia", "Exit - Keluar dari permainan", "View Sim Info - Menampilkan informasi setiap atribut dari Sim", "View Current Location - Menampilkan lokasi dari Sim", "View Inventory - Menampilkan isi inventory yang dimiliki Sim", "Upgrade House - Melakukan penambahan ruangan dalam rumah", "Move Room - Perpindahan lokasi ke ruang lain yang ada pada rumah yang sedang ditempati Sim", "Edit Room - Melakukan perubahan pada ruangan", "Add Sim - Menambahkan sebuah Sim dalam world", "Change Sim - Mengganti ke Sim lain untuk dimainkan", "List Object - Menampilkan daftar objek dalam sebuah ruangan", "Go to Object - Sim berjalan menuju suatu objek", "Action - Melakukan sebuah aksi pada suatu objek"};
    private Sim currentSim = null;
    private int dayAddSim = 0;

    public static boolean equals(String str1, String str2) {
        return str1.equalsIgnoreCase(str2);
    }

    public static void main(String[] args) {
        // cobaJavaSwing.start();
        Main main = new Main();
        Action.fillListAction();
        time = new Time();
        // time.runTime();
        // time.setIsNotIdle(true);
        main.showMenuBegin();
        boolean isStarted = false;
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
                main.showMenuBegin();
            } else {
                System.out.println("Perintah tidak valid");
            }
        }

        world.printMatrixHouse();

        System.out.println(time.getTime());
        // pilih Sim
        if (main.listSim.size() == 0) {
            System.out.println("Tidak ada Sim yang tersedia, silahkan daftarkan Sim anda terlebih dahulu");
            System.out.print("Masukkan nama Sim anda: ");
            String simName = scanner.nextLine();
            Sim newSim = new Sim(simName);
            main.listSim.add(newSim);
            main.currentSim = newSim;
            System.out.println("Selamat bermain!");
            world.printMatrixHouse();

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
                            System.out.println("Silahkan mencoba mendaftarkan Sim baru di lokasi yang berbeda");
                        }
                    }
                } else {
                    System.out.println("World penuh, tidak memungkinkan membuat Sim baru");
                    System.out.println("Silahkan memilih Sim yang sudah ada");
                    main.currentSim = main.chooseSim();
                    System.out.println("Selamat bermain!");
                }

            } else { // jawaban selain ya dan tidak dianggap tidak
                main.currentSim = main.chooseSim();
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
            if (equals(menu, "HELP")) {
                main.showMenu();
            } else if (equals(menu, "EXIT")) {
                System.out.println("Anda keluar dari game Simplicity");
                System.out.println("Waktu yang tersisa di " + time.getTime());
                System.out.println("Bye...");
                System.exit(0);
            } else if (equals(menu, "VIEW SIM INFO")) {
                main.currentSim.viewSimInfo();

            } else if (equals(menu, "VIEW CURRENT LOCATION")) {
                main.currentSim.viewSimLoc();

            } else if (equals(menu, "VIEW INVENTORY")) {
                main.currentSim.viewSimInventory();

            } else if (equals(menu, "UPGRADE HOUSE")) {

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
                        Sim newSim = null;
                        while (!done) {
                            newSim = main.menuAddSim();
                            if (newSim != null) {
                                main.dayAddSim = time.getDay(); // hanya dapat dilakukan 1 hari sekali
                                done = true;
                            } else {
                                System.out.println("Silahkan mencoba mendaftarkan Sim baru di lokasi yang berbeda");
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
                if (main.listSim.size() == 1) {
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
                    System.out.println("Tidak ada daftar objek dalam ruangan " + main.currentSim.getSimLoc().getRoom().getRoomName() + ".");
                }

            } else if (equals(menu, "GO TO OBJECT")) {

            } 
            else if (equals(menu, "HESOYAM")) {
                main.currentSim.setMoney(9999999);
                System.out.println("Selamat! Kamu mendapatkan uang jajan dari Hotman Paris");
                
            } else if (equals(menu, "ACTION")) {
                // time.setIsNotIdle(true);
                main.showAction();
                System.out.print("Masukkan aksi yang ingin dijalankan: ");
                String act = scanner.nextLine();
                if (equals(act, "WORK")) {
                    // time.setIsNotIdle(true);
                    System.out.print("Masukkan durasi kerja (dalam detik): ");
                    int simWorkTime = scanner.nextInt();
                    main.currentSim.work(simWorkTime);

                } else if (equals(act, "CHANGE JOB")) { // action ato taruh di work?
                    // time.setIsNotIdle(true);
                    main.currentSim.newJob();

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

                } else if (equals(act, "EAT")) {

                } else if (equals(act, "COOK")) {
                    main.showCookingMenu();
                    System.out.println("Masukkan nomor masakan yang ingin dibuat : ");
                    int cooknumber = scanner.nextInt();

                    if(cooknumber == 1){
                        if(main.checkGroceries("Nasi") && main.checkGroceries("Ayam")){
                            System.out.println("Berhasil memasak");
                        }
                        else{
                            System.out.println("Bahan makananmu kurang :(");
                        }
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
                            main.printListSim();
                        } else {
                            done = true;
                        }
                    }

                    System.out.print("Masukkan durasi berkunjung (dalam detik): ");
                    int simVisitTime = scanner.nextInt();

                    main.currentSim.visit(houseLoc, simVisitTime);

                } else if (equals(act, "PEE")) {
                    // sim minimal buang air 1 kali tiap habis makan
                    // efek tidak buang air: -5 kesehatan dan -5 mood 4 menit setelah makan tanpa buang air -> gimana

                    main.currentSim.pee();
                    // waktu = 10 dtk
                    
                } else if (equals(act, "UPGRADE HOUSE")) {

                }
                ///////////////////////////////////////////////////////////////////////////////////////////
                else if (equals(act, "BUY ITEM")) {

                    main.showBuyObjectMenu();
                    System.out.println("Mau beli nomor berapa?");
                    System.out.print("Nomor : ");
                    int buynumber = scanner.nextInt();

                    if (buynumber == 1) {
                        NonFood kasurSingle = new NonFood("Kasur single");
                        if (main.isMoneyEnough(kasurSingle.getObjPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - kasurSingle.getObjPrice());
                           main.currentSim.getInventory().getBoxNonFood().add(kasurSingle);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }

                    else if (buynumber == 2) {
                        NonFood kasurQ = new NonFood("Kasur queen size");
                        if (main.isMoneyEnough(kasurQ.getObjPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - kasurQ.getObjPrice());
                           main.currentSim.getInventory().getBoxNonFood().add(kasurQ);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }

                    else if (buynumber == 3) {
                        NonFood kasurK = new NonFood("Kasur king size");
                        if (main.isMoneyEnough(kasurK.getObjPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - kasurK.getObjPrice());
                           main.currentSim.getInventory().getBoxNonFood().add(kasurK);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }

                    else if (buynumber == 4) {
                        NonFood toilet = new NonFood("Toilet");
                        if (main.isMoneyEnough(toilet.getObjPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - toilet.getObjPrice());
                           main.currentSim.getInventory().getBoxNonFood().add(toilet);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }

                    else if (buynumber == 5) {
                        NonFood komgas = new NonFood("Kompor gas");
                        if (main.isMoneyEnough(komgas.getObjPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - komgas.getObjPrice());
                           main.currentSim.getInventory().getBoxNonFood().add(komgas);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }

                    else if (buynumber == 6) {
                        NonFood komlistrik = new NonFood("Kompor listrik");
                        if (main.isMoneyEnough(komlistrik.getObjPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - komlistrik.getObjPrice());
                           main.currentSim.getInventory().getBoxNonFood().add(komlistrik);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }
                    else if (buynumber == 7) {
                        NonFood mejakursi = new NonFood("Meja dan kursi");
                        if (main.isMoneyEnough(mejakursi.getObjPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - mejakursi.getObjPrice());
                           main.currentSim.getInventory().getBoxNonFood().add(mejakursi);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }
                    else if (buynumber == 8) {
                        NonFood jam = new NonFood("Jam");
                        if (main.isMoneyEnough(jam.getObjPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - jam.getObjPrice());
                           main.currentSim.getInventory().getBoxNonFood().add(jam);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }

                    else if (buynumber == 9) {
                        Groceries nasi = new Groceries("Nasi",5,5);
                        if (main.isMoneyEnough(nasi.getGrocPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - nasi.getGrocPrice());
                           main.currentSim.getInventory().getBoxGroceries().add(nasi);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }

                    else if (buynumber == 10) {
                        Groceries kentang = new Groceries("Kentang",3,4);
                        if (main.isMoneyEnough(kentang.getGrocPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - kentang.getGrocPrice());
                           main.currentSim.getInventory().getBoxGroceries().add(kentang);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }

                    else if (buynumber == 11) {
                        Groceries ayam = new Groceries("Ayam",10,8);
                        if (main.isMoneyEnough(ayam.getGrocPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - ayam.getGrocPrice());
                           main.currentSim.getInventory().getBoxGroceries().add(ayam);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }

                    else if (buynumber == 12) {
                        Groceries sapi = new Groceries("Sapi",12,15);
                        if (main.isMoneyEnough(sapi.getGrocPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - sapi.getGrocPrice());
                           main.currentSim.getInventory().getBoxGroceries().add(sapi);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }
                    else if (buynumber == 13) {
                        Groceries wortel = new Groceries("Wortel",3,2);
                        if (main.isMoneyEnough(wortel.getGrocPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - wortel.getGrocPrice());
                           main.currentSim.getInventory().getBoxGroceries().add(wortel);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }
                    else if (buynumber == 14) {
                        Groceries bayam = new Groceries("Bayam",3,2);
                        if (main.isMoneyEnough(bayam.getGrocPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - bayam.getGrocPrice());
                           main.currentSim.getInventory().getBoxGroceries().add(bayam);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }
                    else if (buynumber == 15) {
                        Groceries kacang = new Groceries("Kacang",2,2);
                        if (main.isMoneyEnough(kacang.getGrocPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - kacang.getGrocPrice());
                           main.currentSim.getInventory().getBoxGroceries().add(kacang);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }
                    else if (buynumber == 16) {
                        Groceries susu = new Groceries("Susu",2,1);
                        if (main.isMoneyEnough(susu.getGrocPrice(), main.currentSim.getMoney())) {
                           main.currentSim.setMoney(main.currentSim.getMoney() - susu.getGrocPrice());
                           main.currentSim.getInventory().getBoxGroceries().add(susu);
                           System.out.println("Berhasil Membeli Barang!");
                       } 
                       else {
                           System.out.println("Uang-mu kurang :( ");
                       }
                    }
                    else{
                        System.out.println("Nomor tidak terindentifikasi, masukkan nomor yang tersedia");
                    }
                    ///////////////////////////////////////////////////////////////////////////////////////////

                } 
            
                
                else if (equals(act, "MOVE ROOM")) {
                    main.currentSim.moveRoom();

                } else if (equals(act, "VIEW INVENTORY")) {
                    // time.setIsNotIdle(true);
                    System.out.println(time.getTime());
                    main.currentSim.viewSimInventory();

                } else if (equals(act, "INSTALL ITEM")) {

                } else if (equals(act, "CHECK TIME")) {
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

    public void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) Runtime.getRuntime().exec("cls");
            else Runtime.getRuntime().exec("clear");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void printMenu(int[] numbers) {
        int i = 1;
        for (int j = 0; j < menu.length; j++) {
            if (Ints.contains(numbers, j)) {
                System.out.println(i + ". " + menu[j]);
                i++;
            }
        }
    }

    public void showMenuBegin() {
        System.out.println("Menu game yang tersedia:");
        printMenu(new int[]{0, 1, 2});
    }

    public void showMenu() {
        System.out.println("Menu Game:");
        printMenu(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13});
    }

    public void showAction() {
        System.out.println("Aksi yang dapat dipilih:");
//        Change Job gatau masuk action ato ngga
        int i = 1;
        List<Action> listAction = Action.getListAction();
        for (int j = 0; j < Action.getListAction().size(); j++) {
//            if (!equals(listAction.get(j).getActionName(), "Tidak buang air")) {
            System.out.println(i + ". " + listAction.get(j).getActionName());
            i++;
//            }
        }
    }

    //////////////////////////////////////////////////////////
    public void showCookingMenu() {
        StringBuilder cookingMenu = new StringBuilder();
        cookingMenu.append("Mau masak apa hari ini? \n");
        cookingMenu.append("1. Nasi Ayam (Kekenyangan : 16, Bahan : Nasi,Ayam) \n");
        cookingMenu.append("2. Nasi Kari (Kekenyangan : 30, Bahan : Nasi,Kentang,Wortel,Sapi) \n");
        cookingMenu.append("3. Susu Kacang (Kekenyangan : 5, Bahan : Susu,Kacang) \n");
        cookingMenu.append("4. Tumis Sayur (Kekenyangan : 5, Bahan : Wortel,Bayam) \n");
        cookingMenu.append("5. Bistik (Kekenyangan : 22, Bahan : Kentang,Sapi) \n");
        System.out.println(cookingMenu);
    }

    public void showBuyObjectMenu() {
        String buyobjectMenu = "Berikut list object non makanan yang dapat dibeli: \n" +
                "1. Kasur Single (Dimensi : 4 x 1, Harga : 50) \n" +
                "2. Kasur Queen Size (Dimensi : 4 x 2, Harga : 100) \n" +
                "3. Kasur King Size (Dimensi : 5 x 2, Harga : 150) \n" +
                "4. Toilet (Dimensi : 1 x 1, Harga : 50) \n" +
                "5. Kompor Gas (Dimensi : 2 x 1, Harga : 100) \n" +
                "6. Kompor Listrik (Dimensi : 1 x 1, Harga : 200) \n" +
                "7. Meja dan Kursi (Dimensi : 3 x 3, Harga : 50) \n" +
                "8. Jam (Dimensi : 1 x 1, Harga : 10) \n" +
                "\n" +
                "Berikut list object bahan makanan yang dapat dibeli: \n" +
                "9. Nasi (Kekenyangan : 5, Harga 5)\n" +
                "10. Kentang (Kekenyangan : 4, Harga 3)\n" +
                "11. Ayam (Kekenyangan : 8, Harga 10)\n" +
                "12. Sapi (Kekenyangan : 15, Harga 12)\n" +
                "13. Wortel (Kekenyangan : 2, Harga 3)\n" +
                "14. Bayam (Kekenyangan : 2, Harga 3)\n" +
                "15. Kacang (Kekenyangan : 2, Harga 2)\n" +
                "16. Susu (Kekenyangan : 1, Harga 2)\n";
        System.out.println(buyobjectMenu);
    }

    public boolean isMoneyEnough(int hargaobjek, int duitSim) {
        return hargaobjek <= duitSim;
    }

    //////////////////////////////////////////////////////////

    public void printListSim() {
        System.out.println("Daftar Sim yang dapat dimainkan: ");
        int i = 1;
        for (Sim sim : listSim) {
            System.out.println(i + ". " + sim.getFullName());
            i++;
        }
        System.out.println();
    }

    public Sim chooseSim() {
        printListSim();
        boolean done = false;
        String simName = null;
        while (!done) {
            System.out.print("Masukkan nama Sim yang ingin dimainkan: ");
            simName = scanner.nextLine();
            if (isNotRegistered(simName)) {
                System.out.println("Sim tidak ditemukan di list Sim");
                printListSim();
            } else {
                // ambil Sim di list
                for (Sim sim : listSim) {
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
        for (Sim sim : listSim) {
            if (equals(simName, sim.getFullName())) {
                return false;
            }
        }

        return true;
    }

    public Sim menuAddSim() {
        // validasi nama Sim
        boolean found = true;
        String simName = null;
        while (found) {
            System.out.print("Masukkan nama Sim yang ingin anda tambahkan: ");
            simName = scanner.nextLine();
            if (isNotRegistered(simName)) {
                found = false;
            } else {
                System.out.println("Nama Sim telah terdaftar. Silahkan menggunakan nama lain");
                printListSim();
            }
        }

        // validasi lokasi rumah Sim
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
            listSim.add(newSim);
            System.out.println("Sim berhasil didaftarkan");
            world.printMatrixHouse();
            return newSim;
        } else {
            System.out.println("Tidak memungkinkan untuk membuat rumah baru di lokasi tersebut");
            System.out.println("Pendaftaran Sim baru gagal");
            return null;
        }
    }

    public boolean checkGroceries(String grocName){
        return currentSim.getInventory().getBoxGroceries().getMapT().containsKey(grocName);
    }

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
