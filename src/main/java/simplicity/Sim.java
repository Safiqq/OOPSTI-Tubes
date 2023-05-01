package simplicity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Sim {
    private static final List<Sim> listSim = new ArrayList<>();
    private final Location simLoc;
    private String fullName;
    private Occupation occupation;
    private int money;
    private Inventory inventory;
    private Motive motive;
    private String status = "idle";
    private int workTime = 0;
    private int paidTime = 0;
    private int dayChangeJob = 0;

    public Sim(String fullName) {
        this.motive = new Motive(); // inisiasi di class motive
        this.money = 100;
        this.occupation = new Occupation(); // inisiasi di class occupation
        this.fullName = fullName;
        this.inventory = new Inventory(); // inisiasi di class inventory
        House house = new House(fullName);
        this.simLoc = new Location(house, house.getDefaultRoom(), new Point(3, 3)); // inisiasi di class location

        listSim.add(this);
    }

    public Sim(String fullName, Point houseLoc) {
        this.motive = new Motive(); // inisiasi di class motive
        this.money = 100;
        this.occupation = new Occupation(); // inisiasi di class occupation
        this.fullName = fullName;
        this.inventory = new Inventory(); // inisiasi di class inventory
        House house = new House(fullName, houseLoc);
        this.simLoc = new Location(house, house.getDefaultRoom(), new Point(3, 3)); // inisiasi di class location

        listSim.add(this);
    }

    public static List<Sim> getListSim() {
        return listSim;
    }

    public void viewSimInfo() {
        System.out.println("Nama Sim: " + fullName);
        System.out.println("Pekerjaan Sim: " + occupation.getJobName());
        System.out.println("Kesehatan Sim: " + motive.getHealth());
        System.out.println("Kekenyangan Sim: " + motive.getHunger());
        System.out.println("Mood Sim: " + motive.getMood());
        System.out.println("Uang Sim: " + money);
    }

    public void viewSimLoc() {
        System.out.println("Lokasi Sim: ");
        System.out.println("Rumah milik: " + simLoc.getHouse().getOwner());
        System.out.println("Nama ruangan: " + simLoc.getRoom().getRoomName());
        System.out.println("X: " + simLoc.getPoint().getX() + ", Y: " + simLoc.getPoint().getY());
    }

    public void viewSimInventory() {
        System.out.println("Berikut merupakan inventory yang dimiliki oleh Sim " + fullName);
        System.out.println();

        /* Objek NonMakanan */
        System.out.println("========Objek Non-Makanan========");
        if (inventory.getBoxNonFood().getLength() == 0) {
            System.out.println("Sim " + fullName + " tidak memiliki objek non-makanan dalam inventory");
        } else {
            int i = 0;
            for (Map.Entry<String, Integer> entry : inventory.getBoxNonFood().getMap().entrySet()) {
                System.out.println((++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue());
            }
        }

        /* Objek Bahan Makanan */
        System.out.println("========Objek Bahan Makanan========");
        if (inventory.getBoxGroceries().getLength() == 0) {
            System.out.println("Sim " + fullName + " tidak memiliki objek bahan makanan dalam inventory");
        } else {
            int i = 0;
            for (Map.Entry<String, Integer> entry : inventory.getBoxGroceries().getMap().entrySet()) {
                System.out.println((++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue());
            }
        }

        /* Objek Makanan */
        System.out.println("========Objek Makanan========");
        if (inventory.getBoxFood().getLength() == 0) {
            System.out.println("Sim " + fullName + " tidak memiliki objek makanan dalam inventory");
        } else {
            int i = 0;
            for (Map.Entry<String, Integer> entry : inventory.getBoxFood().getMap().entrySet()) {
                System.out.println((++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue());
            }
        }
    }

    public void viewSimFood() {
        // Menampilkan makanan yang ada di inventory
        System.out.println("========Berikut merupakan makanan yang kamu punya========");
        if (inventory.getBoxFood().getLength() == 0) {
            System.out.println("Sim " + fullName + " tidak memiliki objek makanan dalam inventory");
        } else {
            int i = 0;
            for (Map.Entry<String, Integer> entry : inventory.getBoxFood().getMap().entrySet()) {
                System.out.println((++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue());
            }
        }

    }

    public boolean isMoneyEnough(int hargaobjek) {
        return hargaobjek <= money;
    }

    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public int getWorkTime() {
        return workTime;
    }
    
    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }
    
    public int getPaidTime() {
        return paidTime;
    }
    
    public void setPaidTime(int paidTime) {
        this.paidTime = paidTime;
    }
    
    public int getDayChangeJob() {
        return dayChangeJob;
    }
    
    public void setDayChangeJob(int dayChangeJob) {
        this.dayChangeJob = dayChangeJob;
    }
    
    public Occupation getOccupation() {
        return this.occupation;
    }
    
    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }
    
    public int getMoney() {
        return this.money;
    }
    
    public void setMoney(int money) {
        this.money = money;
    }
    
    public Inventory getInventory() {
        return this.inventory;
    }
    
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    public Motive getMotive() {
        return this.motive;
    }
    
    public void setMotive(Motive motive) {
        this.motive = motive;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Location getSimLoc() {
        return this.simLoc;
    }
    
    public void moveRoom() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Room> listRoom = simLoc.getHouse().getListRoom();
        System.out.println("Daftar ruangan yang terdapat di dalam rumah: ");
        int i = 1;
        for (Room room : listRoom) {
            System.out.println(i + ". " + room.getRoomName());
            i++;
        }
        
        String oldRoom = simLoc.getRoom().getRoomName();
        
        boolean done = false;
        while (!done) {
            System.out.print("Masukkan nama ruangan yang ingin didatangi: ");
            String roomName = scanner.nextLine();
            if (oldRoom.equals(roomName.toUpperCase())) {
                System.out.println("Nama ruangan sama dengan tempat Sim berada ");
                System.out.println("Sim berada di ruangan " + oldRoom);
                System.out.println();
            } else {
                for (Room room : listRoom) {
                    if ((roomName.toUpperCase()).equals(room.getRoomName())) {
                        simLoc.setRoom(room);
                        // Sim teleportasi di point 3,3 dalam ruangan
                        simLoc.getPoint().setX(3);
                        simLoc.getPoint().setY(3);
                        done = true;
                        break;
                    }
                }
                
                if (!done) {
                    System.out.println("Tidak ditemukan ruangan bernama " + roomName);
                    System.out.println();
                }
            }
        }
        scanner.close();
    }
    
    public void work(int time) {
        workTime += time;
        // anggap pokoknya harus 4 menit (240 dtk) baru digaji, ga liat harinya
        int notPaid = workTime - paidTime;
        if (notPaid > 240) {
            int payday = workTime / 240;
            money += payday * occupation.getDailySalary();
            paidTime += payday * 240; // kalo ada time sisa yg belum dibayar
        }
        
        // efek -10 kekenyangan/30 dtk, -10 mood/30 dtk
        int minusPoints = -10 * (time / 30);
        
        // menghapus sim dari list sim jika mati
        try {
            motive.changeHunger(minusPoints);
            motive.changeMood(minusPoints);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Sim dengan nama " + this.getFullName() + " dihapus dari daftar Sim");
            listSim.remove(this);
        }
    }
    
    public void newJob() {
        Occupation oldJob = occupation;
        occupation.changeJob();
        // harus bayar 1/2 dari gaji harian pekerjaan baru
        int payChangeJob = (int) (0.5 * occupation.getDailySalary());
        if (money < payChangeJob) {
            System.out.println("Uang tidak mencukupi untuk pindah pekerjaan");
            occupation.setJobName(oldJob.getJobName());
            occupation.setDailySalary(oldJob.getDailySalary());
        } else {
            money -= payChangeJob;
            workTime = 0;
        }
    }
    
    public void exercise(int time) {
        // efek +5 kesehatan/20 dtk, -5 kekenyangan/20 dtk, +10 mood/20 dtk
        int plusHealth = 5 * (time / 20);
        int minusHunger = -5 * (time / 20);
        int plusMood = 10 * (time / 20);
        
        // menghapus sim dari list sim jika mati
        try {
            motive.changeHealth(plusHealth);
            motive.changeHunger(minusHunger);
            motive.changeMood(plusMood);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Sim dengan nama " + this.getFullName() + " dihapus dari daftar Sim");
            listSim.remove(this);
        }
    }
    
    public void sleep(int time) {
        // efek tidur
        // +30 mood/240 dtk, +20 kesehatan/240 dtk
        int plusMood = 30 * (time / 240);
        int plusHealth = 20 * (time / 240);
        
        // menghapus sim dari list sim jika mati
        try {
            motive.changeMood(plusMood);
            motive.changeHealth(plusHealth);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Sim dengan nama " + this.getFullName() + " dihapus dari daftar Sim");
            listSim.remove(this);
        }
    }

    public boolean checkGroceries(String namaGroc) {
        return getInventory().getBoxGroceries().isNotEmpty(namaGroc);
    }

    public void deleteGroceriesFromInventory(String namagroc) {
        getInventory().getBoxGroceries().delete(namagroc);
    }
    
    public boolean checkFood(String namaFood) {
        return getInventory().getBoxFood().isNotEmpty(namaFood);
    }
    
    public void eat() {
        Scanner scanner = new Scanner(System.in);
        viewSimFood();
        System.out.print("Mau makan apa? ");
        String maumakan = scanner.nextLine();
        if (Main.equals(maumakan, "Nasi Ayam")) {
            if (checkFood("Nasi Ayam")) {
                Food nasyam = getInventory().getBoxFood().get("Nasi Ayam");
                simEat(nasyam);
            } else {
                System.out.println("Kamu tidak memiliki makanan " + maumakan);
            }
        } else if (Main.equals(maumakan, "Nasi Kari")) {
            if (checkFood("Nasi Kari")) {
                Food naskar = getInventory().getBoxFood().get("Nasi Kari");
                simEat(naskar);
            } else {
                System.out.println("Kamu tidak memiliki makanan " + maumakan);
            }
        } else if (Main.equals(maumakan, "Susu Kacang")) {
            if (checkFood("Susu Kacang")) {
                Food suskac = getInventory().getBoxFood().get("Susu Kacang");
                simEat(suskac);
            } else {
                System.out.println("Kamu tidak memiliki makanan " + maumakan);
            }
        } else if (Main.equals(maumakan, "Tumis Sayur")) {
            if (checkFood("Tumis Sayur")) {
                Food tumsay = getInventory().getBoxFood().get("Tumis Sayur");
                simEat(tumsay);
            } else {
                System.out.println("Kamu tidak memiliki makanan " + maumakan);
            }
        } else if (Main.equals(maumakan, "Bistik")) {
            if (checkFood("Bistik")) {
                Food biwstik = getInventory().getBoxFood().get("Bistik");
                simEat(biwstik);
            } else {
                System.out.println("Kamu tidak memiliki makanan " + maumakan);
            }
        } else {
            System.out.println("Kamu tidak memiliki makanan " + maumakan);
        }
        scanner.close();
    }
    
    // thread hapus aja jd sleepMain di main
    public void simEat(Food nyam) {
        Thread thread = new Thread(() -> {
            System.out.println("Sedang Memakan " + nyam.getObjekName());
            System.out.println(".......Please wait.......");
            try {
                for (int k = 30; k >= 1; k--) {
                    System.out.println("Time remaining " + k + " seconds");
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Kamu selesai makan!");
        getInventory().getBoxFood().delete(nyam);

        // menghapus sim dari list sim jika mati
        try {
            motive.changeHunger(nyam.getFoodHunger());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Sim dengan nama " + this.getFullName() + " dihapus dari daftar Sim");
            listSim.remove(this);
        }
    }

    // thread hapus aja jd sleepMain di main
    public void cooking(Food makanan) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Cooking " + makanan.getObjekName());
                int sleeptime = makanan.getFoodHunger() * 3 / 2 * 1000;
                System.out.println(".......Please wait.......");
                try {
                    for (int k = (sleeptime / 1000); k >= 1; k--) {
                        System.out.println("Time remaining " + k + " seconds");
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Masakanmu selesai!");
        getInventory().getBoxFood().add(makanan);

        // menghapus sim dari list sim jika mati
        try {
            motive.changeMood(10);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Sim dengan nama " + this.getFullName() + " dihapus dari daftar Sim");
            listSim.remove(this);
        }
    }

    public void cook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nomor masakan yang ingin dibuat: ");
        int cooknumber = scanner.nextInt();

        if (cooknumber == 1) {
            if (checkGroceries("Nasi") && checkGroceries("Ayam")) {
                Food nasiayam = new Food("Nasi Ayam", 16);
                cooking(nasiayam);
                deleteGroceriesFromInventory("Nasi");
                deleteGroceriesFromInventory("Ayam");
                System.out.println("Berhasil memasak");

            } else {
                System.out.println("Bahan makananmu kurang :(");
            }
        } else if (cooknumber == 2) {
            if (checkGroceries("Nasi") && checkGroceries("Kentang") && checkGroceries("Wortel")
                    && checkGroceries("Sapi")) {
                Food nasikari = new Food("Nasi Kari", 30);
                cooking(nasikari);
                deleteGroceriesFromInventory("Nasi");
                deleteGroceriesFromInventory("Kentang");
                deleteGroceriesFromInventory("Wortel");
                deleteGroceriesFromInventory("Sapi");
                System.out.println("Berhasil memasak");

            } else {
                System.out.println("Bahan makananmu kurang :(");
            }
        } else if (cooknumber == 3) {
            if (checkGroceries("Susu") && checkGroceries("Kacang")) {
                Food susukacang = new Food("Susu Kacang", 5);
                cooking(susukacang);
                deleteGroceriesFromInventory("Susu");
                deleteGroceriesFromInventory("Kacang");
                System.out.println("Berhasil memasak");

            } else {
                System.out.println("Bahan makananmu kurang :(");
            }
        } else if (cooknumber == 4) {
            if (checkGroceries("Wortel") && checkGroceries("Bayam")) {
                Food tumissayur = new Food("Tumis Sayur", 5);
                cooking(tumissayur);
                deleteGroceriesFromInventory("Wortel");
                deleteGroceriesFromInventory("Bayam");
                System.out.println("Berhasil memasak");

            } else {
                System.out.println("Bahan makananmu kurang :(");
            }
        } else if (cooknumber == 5) {
            if (checkGroceries("Kentang") && checkGroceries("Sapi")) {
                Food kentangsapi = new Food("Bistik", 22);
                cooking(kentangsapi);
                deleteGroceriesFromInventory("Kentang");
                deleteGroceriesFromInventory("Sapi");
                System.out.println("Berhasil memasak");

            } else {
                System.out.println("Bahan makananmu kurang :(");
            }
        } else {
            System.out.println("Masukkan nomor yang sesuai dong");
        }
        scanner.close();
    }

    public void visit(int time) {
        // +10 mood/30 dtk, -10 kekenyangan/30 dtk
        int plusMood = 10 * (time / 30);
        int minusHunger = -10 * (time / 30);

        // menghapus sim dari list sim jika mati
        try {
            motive.changeMood(plusMood);
            motive.changeHunger(minusHunger);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Sim dengan nama " + this.getFullName() + " dihapus dari daftar Sim");
            listSim.remove(this);
        }
    }

    public void pee() {
        // efek buang air: -20 kekenyangan/1 siklus (10 dtk), +10 mood/1 siklus (10 dtk)
        // 1 siklus = 10 dtk

        int minusHunger = -20;
        int plusMood = 10;

        // menghapus sim dari list sim jika mati
        try {
            motive.changeHunger(minusHunger);
            motive.changeMood(plusMood);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Sim dengan nama " + this.getFullName() + " dihapus dari daftar Sim");
            listSim.remove(this);
        }
    }

    public void upgradeHouse(House house) {
        Scanner scan = new Scanner(System.in);
        System.out.println("List ruangan di rumahmu : ");
        int i = 1;
        for(Room room : house.getListRoom()){
            System.out.println(i +". " + room.getRoomName());
            i++;
        }

        if (isMoneyEnough(1500)) {
            // kalau rumah sekarang cuma ada 1 ruangan
            if (house.getListRoom().size() == 1) {
                Room currentRoom = house.getDefaultRoom();
                System.out.print("Masukkan nama ruangan baru : ");
                String newRoomName = scan.nextLine();

                // loop untuk mendapatkan lokasi ruangan baru yang valid
                boolean roomLocValid = false;
                while (!roomLocValid) {
                    System.out.printf("Pilih lokasi %s disebelah Ruangan Utama (kiri/kanan/atas/bawah) : ",
                            newRoomName);
                    String roomLoc = scan.nextLine();
                    if (Main.equals(roomLoc, "KANAN")) {
                        Room newRoom = new Room(newRoomName, null, null, currentRoom, null);
                        currentRoom.setRightSide(newRoom);
                        simLoc.getHouse().addListRoom(newRoom);
                        roomLocValid = true;
                    } else if (Main.equals(roomLoc, "KIRI")) {
                        Room newRoom = new Room(newRoomName, null, null, null, currentRoom);
                        currentRoom.setLeftSide(newRoom);
                        simLoc.getHouse().addListRoom(newRoom);
                        roomLocValid = true;
                    } else if (Main.equals(roomLoc, "ATAS")) {
                        Room newRoom = new Room(newRoomName, null, currentRoom, null, null);
                        currentRoom.setUpperSide(newRoom);
                        simLoc.getHouse().addListRoom(newRoom);
                        roomLocValid = true;
                    } else if (Main.equals(roomLoc, "BAWAH")) {
                        Room newRoom = new Room(newRoomName, currentRoom, null, null, null);
                        currentRoom.setBottomSide(newRoom);
                        simLoc.getHouse().addListRoom(newRoom);
                        roomLocValid = true;
                    } else {
                        System.out.println("Lokasi tidak valid.");
                    }
                }
                // decrease money
                money = money - 1500;
            }
            // kalau rumah sekarang ada >1 ruangan
            else {
                // loop untuk mendapatkan ruangan acuan
                boolean pivotValid = false;
                while (!pivotValid) {
                    System.out.print("Masukkan nama salah satu ruangan sebagai acuan : ");
                    String pivotRoomName = scan.nextLine();
                    // cek ruangan acuan ada atau tidak
                    for (Room currentRoom : house.getListRoom()) {
                        if (Main.equals(currentRoom.getRoomName(), pivotRoomName)) {
                            System.out.print("Masukkan nama ruangan baru : ");
                            String newRoomName = scan.nextLine();

                            // loop untuk medapatkan lokasi ruangan baru yang valid
                            boolean roomLocValid = false;
                            while (!roomLocValid) {
                                System.out.printf("Pilih lokasi %s disebelah %s (kiri/kanan/atas/bawah) : ",
                                        newRoomName, pivotRoomName);
                                String roomLoc = scan.nextLine();
                                if (Main.equals(roomLoc, "KANAN") && currentRoom.getRightSide() == null) {
                                    Room newRoom = new Room(newRoomName, null, null, currentRoom, null);
                                    currentRoom.setRightSide(newRoom);
                                    simLoc.getHouse().addListRoom(newRoom);
                                    roomLocValid = true;
                                } else if (Main.equals(roomLoc, "KIRI") && currentRoom.getLeftSide() == null) {
                                    Room newRoom = new Room(newRoomName, null, null, null, currentRoom);
                                    currentRoom.setLeftSide(newRoom);
                                    simLoc.getHouse().addListRoom(newRoom);
                                    roomLocValid = true;
                                } else if (Main.equals(roomLoc, "ATAS") && currentRoom.getUpperSide() == null) {
                                    Room newRoom = new Room(newRoomName, null, currentRoom, null, null);
                                    currentRoom.setUpperSide(newRoom);
                                    simLoc.getHouse().addListRoom(newRoom);
                                    roomLocValid = true;
                                } else if (Main.equals(roomLoc, "BAWAH") && currentRoom.getBottomSide() == null) {
                                    Room newRoom = new Room(newRoomName, currentRoom, null, null, null);
                                    currentRoom.setBottomSide(newRoom);
                                    simLoc.getHouse().addListRoom(newRoom);
                                    roomLocValid = true;
                                } else {
                                    System.out.println("Lokasi tidak valid atau lokasi yang dipilih sudah diisi ruangan lain.");
                                }
                            }
                            pivotValid = true;
                            break;
                        } else {
                            System.out.println("Ruangan tidak dikenali.");
                        }
                    }
                }
            }
            // decrease money
            money = money - 1500;
        } else {
            System.out.println("Uang sim tidak cukup untuk upgrade rumah");
        }
        scan.close();
    }

    public void buyItem() {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        int buynumber = 0;
        while (!done) {
            System.out.println("Masukkan nomor item yang ingin dibeli");
            System.out.print("Nomor : ");
            buynumber = scanner.nextInt();
            if (buynumber >= 1 && buynumber <= 16) {
                done = true;
            } else {
                System.out.println("Nomor tidak teridentifikasi");
                System.out.println("Masukkan nomor yang tersedia");
            }
        }

        if (buynumber == 1) {
            NonFood kasurSingle = new NonFood("Kasur single");
            if (isMoneyEnough(kasurSingle.getObjPrice())) {
                setMoney(getMoney() - kasurSingle.getObjPrice());
                getInventory().getBoxNonFood().add(kasurSingle);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 2) {
            NonFood kasurQ = new NonFood("Kasur queen size");
            if (isMoneyEnough(kasurQ.getObjPrice())) {
                setMoney(getMoney() - kasurQ.getObjPrice());
                getInventory().getBoxNonFood().add(kasurQ);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 3) {
            NonFood kasurK = new NonFood("Kasur king size");
            if (isMoneyEnough(kasurK.getObjPrice())) {
                setMoney(getMoney() - kasurK.getObjPrice());
                getInventory().getBoxNonFood().add(kasurK);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 4) {
            NonFood toilet = new NonFood("Toilet");
            if (isMoneyEnough(toilet.getObjPrice())) {
                setMoney(getMoney() - toilet.getObjPrice());
                getInventory().getBoxNonFood().add(toilet);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 5) {
            NonFood komgas = new NonFood("Kompor gas");
            if (isMoneyEnough(komgas.getObjPrice())) {
                setMoney(getMoney() - komgas.getObjPrice());
                getInventory().getBoxNonFood().add(komgas);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 6) {
            NonFood komlistrik = new NonFood("Kompor listrik");
            if (isMoneyEnough(komlistrik.getObjPrice())) {
                setMoney(getMoney() - komlistrik.getObjPrice());
                getInventory().getBoxNonFood().add(komlistrik);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 7) {
            NonFood mejakursi = new NonFood("Meja dan kursi");
            if (isMoneyEnough(mejakursi.getObjPrice())) {
                setMoney(getMoney() - mejakursi.getObjPrice());
                getInventory().getBoxNonFood().add(mejakursi);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 8) {
            NonFood jam = new NonFood("Jam");
            if (isMoneyEnough(jam.getObjPrice())) {
                setMoney(getMoney() - jam.getObjPrice());
                getInventory().getBoxNonFood().add(jam);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 9) {
            Groceries nasi = new Groceries("Nasi", 5, 5);
            if (isMoneyEnough(nasi.getGrocPrice())) {
                setMoney(getMoney() - nasi.getGrocPrice());
                getInventory().getBoxGroceries().add(nasi);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 10) {
            Groceries kentang = new Groceries("Kentang", 3, 4);
            if (isMoneyEnough(kentang.getGrocPrice())) {
                setMoney(getMoney() - kentang.getGrocPrice());
                getInventory().getBoxGroceries().add(kentang);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 11) {
            Groceries ayam = new Groceries("Ayam", 10, 8);
            if (isMoneyEnough(ayam.getGrocPrice())) {
                setMoney(getMoney() - ayam.getGrocPrice());
                getInventory().getBoxGroceries().add(ayam);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 12) {
            Groceries sapi = new Groceries("Sapi", 12, 15);
            if (isMoneyEnough(sapi.getGrocPrice())) {
                setMoney(getMoney() - sapi.getGrocPrice());
                getInventory().getBoxGroceries().add(sapi);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 13) {
            Groceries wortel = new Groceries("Wortel", 3, 2);
            if (isMoneyEnough(wortel.getGrocPrice())) {
                setMoney(getMoney() - wortel.getGrocPrice());
                getInventory().getBoxGroceries().add(wortel);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 14) {
            Groceries bayam = new Groceries("Bayam", 3, 2);
            if (isMoneyEnough(bayam.getGrocPrice())) {
                setMoney(getMoney() - bayam.getGrocPrice());
                getInventory().getBoxGroceries().add(bayam);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else if (buynumber == 15) {
            Groceries kacang = new Groceries("Kacang", 2, 2);
            if (isMoneyEnough(kacang.getGrocPrice())) {
                setMoney(getMoney() - kacang.getGrocPrice());
                getInventory().getBoxGroceries().add(kacang);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        } else {
            Groceries susu = new Groceries("Susu", 2, 1);
            if (isMoneyEnough(susu.getGrocPrice())) {
                setMoney(getMoney() - susu.getGrocPrice());
                getInventory().getBoxGroceries().add(susu);
                System.out.println("Berhasil Membeli Barang!");
            } else {
                System.out.println("Uang-mu kurang :( ");
            }
        }
        scanner.close();
    }

    public void installItem() {
        Scanner scan = new Scanner(System.in);
        // kalau sim sedang berada di rumah sendiri
        if (!Main.equals(getFullName(), simLoc.getHouse().getOwner())) {
            boolean barangValid = false;
            Box<NonFood> boxNonFood = getInventory().getBoxNonFood();

            System.out.println("List barang di inventory kamu : ");
            int i = 1;
            for(NonFood barang : boxNonFood.getList()){
                System.out.println(i + ". " + barang.getObjekName() + " - " + boxNonFood.getCount(barang.getObjekName()));
                i++;
            }

            // looping input nama barang
            while (!barangValid) {
                System.out.print("Masukkan nama barang yang ingin dipasang : ");
                String namaBarang = scan.nextLine();
                System.out.println();
                // cek barang di inventory
                for (NonFood barang : boxNonFood.getList()) {
                    // kalau barang ada di inventory
                    if (Main.equals(namaBarang, barang.getObjekName())) {
                        boolean roomValid = false;

                        // looping input nama ruangan
                        while (!roomValid) {
                            System.out.print("Masukkan nama ruangan : ");
                            String roomName = scan.nextLine();
                            System.out.println();
                            // cek valid ruangan
                            for (Room room : simLoc.getHouse().getListRoom()) {
                                // kalau nama ruangan valid
                                if (Main.equals(roomName, room.getRoomName())) {
                                    boolean pointValid = false;
                                    while (!pointValid) {
                                        // input lokasi barang di ruangan
                                        System.out.print("Masukkan lokasi awal sumbu X barang : ");
                                        int startX = scan.nextInt();
                                        System.out.println();

                                        System.out.print("Masukkan lokasi awal sumbu Y barang : ");
                                        int startY = scan.nextInt();
                                        System.out.println();

                                        System.out.print("Masukkan lokasi akhir sumbu X barang : ");
                                        int endX = scan.nextInt();
                                        System.out.println();

                                        System.out.print("Masukkan lokasi akhir sumbu Y barang : ");
                                        int endY = scan.nextInt();
                                        System.out.println();

                                        // kalau point berada diluar 0-5
                                        if (startX < 0 || startX > 5 || startY < 0 || startY > 5 || endX < 0 || endX > 5
                                                || endY < 0 || endY > 5) {
                                            System.out.println("Point tidak valid.");
                                            // kalau point berada di antara 0-5
                                        } else {
                                            int length = startX - endX + 1;
                                            int width = startY - endY + 1;
                                            // kalau point sesuai dengan ukuran barang
                                            if ((length == barang.getObjLength()) && (width == barang.getObjWidth())) {
                                                Point startPoint = new Point(startX, startY);
                                                Point endPoint = new Point(endX, endY);
                                                // kalau lokasi yang dipilih tersedia
                                                if (room.isSpaceEmpty(startPoint, endPoint)) {
                                                    room.insertBarang(barang);
                                                    room.addListObjek(barang);
                                                    boxNonFood.delete(barang.getObjekName());
                                                    pointValid = true;
                                                }
                                                // kalau lokasi yang dipilih ga tersedia
                                                else {
                                                    System.out.println("Area ruangan tidak kosong.");
                                                }
                                            }
                                            // kalau point tidak sesuai ukuran barang
                                            else {
                                                System.out.println("Point tidak sesuai dengan ukuran barang.");
                                            }
                                        }
                                    }
                                    roomValid = true;
                                    break;
                                }
                                // kalau nama ruangan tidak valid
                                else {
                                    System.out.println("Ruangan tidak dikenali.");
                                }
                            }

                        }
                        barangValid = true;
                        break;
                    }
                    // kalau barang ga ada di inventory
                    else {
                        System.out.println("Barang tidak ada di inventory.");
                    }
                }

            }
        }
        // kalau sim berada di rumah orang lain
        else {
            System.out.println("Sim harus berada di rumah sendiri untuk memasang barang");
        }
        scan.close();
    }

    public void goTo() {
        // cuma untuk pindah di satu ruangan
        Scanner scan = new Scanner(System.in);
        boolean pointValid = false;
        while (!pointValid) {
            System.out.print("Masukan titik X tujuan : ");
            int X = scan.nextInt();
            System.out.println();

            System.out.print("Masukan titik Y tujuan : ");
            int Y = scan.nextInt();
            System.out.println();

            if (X < 0 || X > 5 || Y < 0 || Y > 5) {
                System.out.println("Point tidak valid.");
            } else {
                Point point = new Point(X, Y);
                simLoc.setPoint(point);
                pointValid = true;
            }
        }
        scan.close();
    }

    public void goToObjek() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan nama barang : ");
        String objekName = scan.nextLine();
        System.out.println();

        ArrayList<NonFood> listBarang = simLoc.getRoom().getListObjek();
        for (NonFood barang : listBarang) {
            // kalo barang ada di ruangan
            if (Main.equals(objekName, barang.getObjekName())) {
                NonFood[][] matrixBarang = simLoc.getRoom().getMatrixBarang();
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (matrixBarang[i][j] == barang) {
                            simLoc.getPoint().setX(i);
                            simLoc.getPoint().setY(j);
                            break;
                        }
                    }
                }
                break;
            }
            // kalo barang ga ada di ruangan
            else {
                System.out.printf("%s tidak tersedia di %s.", barang.getObjekName(), simLoc.getRoom());
            }
        }
        scan.close();
    }

}
