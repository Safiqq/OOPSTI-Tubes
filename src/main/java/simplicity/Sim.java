package simplicity;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

import com.google.common.base.Stopwatch;

public class Sim {
    private Location simLoc;
    private String fullName;
    private Occupation occupation;
    private int money;
    private Inventory inventory;
    private Motive motive;
    private String status;
    private Time currentTime;
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
        System.out.println(" ");

        // objek non makanan
        if (inventory.getBoxNonFood().length == 0) {
            System.out.println("Sim " + fullName + " tidak memiliki objek non-makanan dalam inventory");
        } else {
            int i = 0;
            for (IdentityHashMap.Entry<NonFood, Integer> entry : inventory.getBoxNonFood().getMapT().entrySet()) {
                System.out.println((++i) + ". Objek: " + entry.getKey().getObjekName() + ", Jumlah: " + entry.getValue());
            }
        }

        // objek bahan makanan
        if (inventory.getBoxGroceries().length == 0) {
            System.out.println("Sim " + fullName + " tidak memiliki objek bahan makanan dalam inventory");
        } else {
            int i = 0;
            for (IdentityHashMap.Entry<Groceries, Integer> entry : inventory.getBoxGroceries().getMapT().entrySet()) {
                System.out.println((++i) + ". Objek: " + entry.getKey().getObjekName() + ", Jumlah: " + entry.getValue());
            }
        }
        // objek makanan
        if (inventory.getBoxFood().length == 0) {
            System.out.println("Sim " + fullName + " tidak memiliki objek makanan dalam inventory");
        } else {
            int i = 0;
            for (IdentityHashMap.Entry<Food, Integer> entry : inventory.getBoxFood().getMapT().entrySet()) {
                System.out.println((++i) + ". Objek: " + entry.getKey().getObjekName() + ", Jumlah: " + entry.getValue());
            }
        }
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public void setListInventory(Inventory inventory) {
        this.inventory = inventory;
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

    public void setCurrentTime(Time time) {
        this.currentTime = time;
    }

    public void work(int time) {
        // pekerjaan baru hanya dapat dikerjakan 1 hari setelah hari penggantian
        // pekerjaan
        if (currentTime.getDay() > dayChangeJob) {
            // validasi time kelipatan 120
            boolean done = false;
            Scanner scanner = new Scanner(System.in);
            while (!done) {
                if (time % 120 != 0) {
                    System.out.println("Durasi bekerja harus kelipatan 120");
                    System.out.print("Masukkan durasi kerja (dalam detik): ");
                    time = scanner.nextInt();
                } else {
                    done = true;
                }
            }
            scanner.close();

            // ini ngabisin waktu kerja nunggu ato lgsg keskip??

            // efek -10 kekenyangan/30 dtk, -10 mood/30 dtk
            // pake list Effect?
            int minusPoints = -10 * (time / 30);
            motive.changeHunger(minusPoints);
            motive.changeMood(minusPoints);

            workTime += time;
            // anggap pokoknya harus 4 menit (240 dtk) baru digaji, ga liat harinya
            int notPaid = workTime - paidTime;
            if (notPaid > 240) {
                int payday = workTime / 240;
                money += payday * occupation.getDailySalary();
                paidTime += payday * 240; // kalo ada time sisa yg belum dibayar
            }
        } else {
            System.out.println("Pekerjaan baru hanya dapat dikerjakan 1 hari setelah hari penggantian pekerjaan");
        }
    }

    public void newJob() {
        // harus > 12 menit bekerja
        // asumsi: 12 menit di pekerjaan lama
        if (workTime >= 720) {
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
                dayChangeJob = currentTime.getDay();
                workTime = 0;
            }
        } else {
            System.out.println("Sim hanya dapat mengganti pekerjaan jika sudah bekerja setidaknya 12 menit");
        }
    }

    public void exercise(int time) {
        // validasi time kelipatan 20
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        while (!done) {
            if (time % 20 != 0) {
                System.out.println("Durasi olahraga harus kelipatan 20");
                System.out.print("Masukkan durasi olahraga (dalam detik): ");
                time = scanner.nextInt();
            } else {
                done = true;
            }
        }
        scanner.close();

        // efek +5 kesehatan/20 dtk, -5 kekenyangan/20 dtk, +10 mood/20 dtk
        // pake list Effect?
        int plusHealth = 5 * (time / 20);
        int minusHunger = -5 * (time / 20);
        int plusMood = 10 * (time / 20);
        motive.changeHealth(plusHealth);
        motive.changeHunger(minusHunger);
        motive.changeMood(plusMood);

        // waktunya gmn

    }

    public void sleep(int time) {
        // efek tidur
        // +30 mood/240 dtk, +20 kesehatan/240 dtk
        int plusMood = 30 * (time / 240);
        int plusHealth = 20 * (time / 240);
        motive.changeMood(plusMood);
        motive.changeHealth(plusHealth);

        // waktunya gmn

    }

    // public void eat(Food food) {

    // }

    // public void cook(Food food) {

    // }

    public void visit(Point point, int time) {
        // waktu yang diperlukan untuk berkunjung ke rumah
        // perhitungan/pemilihan titik rumah dari SIM yang ingin dikunjungi dibebaskan -> belum ditentuin
        double x = Math.pow(point.getX()-simLoc.getPoint().getX(), 2);
        double y = Math.pow(point.getY()-simLoc.getPoint().getY(), 2);
        double walkTime = Math.sqrt(x+y);

        // pemain diminta memasukkan waktu durasi kelipatan 30 detik
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        while (!done) {
            if (time % 30 != 0){
                System.out.println("Durasi berkunjung harus kelipatan 30");
                System.out.print("Masukkan durasi berkunjung (dalam detik): ");
                time = scanner.nextInt();
            } else {
                done = true;
            }
        }

        scanner.close();

        // +10 mood/30 dtk, -10 kekenyangan/30 dtk
        int plusMood = 10 * (time / 30);
        int minusHunger = -10 * (time / 30);
        motive.changeMood(plusMood);
        motive.changeHunger(minusHunger);

    }

    public void pee() {
        // efek buang air: -20 kekenyangan/1 siklus (10 dtk), +10 mood/1 siklus (10 dtk)
        // 1 siklus = 10 dtk

        int minusHunger = -20;
        int plusMood = 10;
        motive.changeHunger(minusHunger);
        motive.changeMood(plusMood);
    }
    //
    //upgradeHouse masih kurang syncorize time
    //
    public void upgradeHouse(House house) {
        Scanner scan = new Scanner(System.in);

        if (money >= 1500){
            //kalau rumah sekarang cuma ada 1 ruangan
            if (house.getListRoom().size()==1){
                Room currentRoom = house.getDefaultRoom();
                System.out.print("Masukkan nama ruangan baru : ");
                String newRoomName = scan.nextLine();
                
                //loop untuk mendapatkan lokasi ruangan baru yang valid
                boolean roomLocValid = false;
                while (!roomLocValid){
                    System.out.printf("Pilih lokasi %d disebelah RUANGAN UTAMA (kiri/kanan/atas/bawah) : ",newRoomName);
                    String roomLoc = scan.nextLine();
                    if (roomLoc.toUpperCase() == "KANAN"){
                        Room newRoom = new Room(newRoomName,null,null,currentRoom,null);
                        currentRoom.setRightSide(newRoom);
                        roomLocValid = true;
                    }
                    else if (roomLoc.toUpperCase() == "KIRI"){
                        Room newRoom = new Room(newRoomName,null,null,null,currentRoom);
                        currentRoom.setLeftSide(newRoom);
                        roomLocValid = true;
                    }
                    else if (roomLoc.toUpperCase() == "ATAS"){
                        Room newRoom = new Room(newRoomName,null,currentRoom,null,null);
                        currentRoom.setUpperSide(newRoom);
                        roomLocValid = true;
                    }
                    else if (roomLoc.toUpperCase() == "BAWAH"){
                        Room newRoom = new Room(newRoomName,currentRoom,null,null,null);
                        currentRoom.setBottomSide(newRoom);
                        roomLocValid = true;
                    }
                    else{
                        System.out.println("Lokasi tidak valid.");
                    }
                }
                //decrease money money
                money = money-1500;
            }

            //kalau rumah sekarang ada >1 ruangan
            else{
                //loop untuk mendapatkan ruangan acuan
                boolean pivotValid = false;
                while (!pivotValid){
                    System.out.print("Masukkan nama salah satu ruangan sebagai acuan : ");
                    String pivotRoomName = scan.nextLine();
                    //cek ruangan acuan ada atau tidak
                    for (Room currentRoom : house.getListRoom()){
                        if (currentRoom.getRoomName().equals(pivotRoomName.toUpperCase())){
                            System.out.print("Masukkan nama ruangan baru : ");
                            String newRoomName = scan.nextLine();
                            
                            //loop untuk medapatkan lokasi ruangan baru yang valid
                            boolean roomLocValid = false;
                            while (!roomLocValid){
                                System.out.printf("Pilih lokasi %d disebelah %d (kiri/kanan/atas/bawah) : ",newRoomName,pivotRoomName);
                                String roomLoc = scan.nextLine();
                                if ((roomLoc.toUpperCase() == "KANAN") && currentRoom.getRightSide() == null){
                                    Room newRoom = new Room(newRoomName,null,null,currentRoom,null);
                                    currentRoom.setRightSide(newRoom);
                                    roomLocValid = true;
                                }
                                else if ((roomLoc.toUpperCase() == "KIRI") && currentRoom.getLeftSide() == null){
                                    Room newRoom = new Room(newRoomName,null,null,null,currentRoom);
                                    currentRoom.setLeftSide(newRoom);
                                    roomLocValid = true;
                                }
                                else if ((roomLoc.toUpperCase() == "ATAS") && currentRoom.getUpperSide() == null){
                                    Room newRoom = new Room(newRoomName,null,currentRoom,null,null);
                                    currentRoom.setUpperSide(newRoom);
                                    roomLocValid = true;
                                }
                                else if ((roomLoc.toUpperCase() == "BAWAH") && currentRoom.getBottomSide() == null) {
                                    Room newRoom = new Room(newRoomName,currentRoom,null,null,null);
                                    currentRoom.setBottomSide(newRoom);
                                    roomLocValid = true;
                                }
                                else{
                                    System.out.println("Lokasi tidak valid atau lokasi yang dipilih sudah diisi ruangan lain.");
                                }
                            }
                            //decrease money
                            money = money-1500;
                            pivotValid = true;
                        }
                        else{
                            System.out.println("Ruangan tidak dikenali");
                        }
                    }
                }
            }
        // ganngerti syncronize waktu nya gmn wkwkwk
        }
        else{
            System.out.println("Uang kamu tidak cukup untuk upgrade rumah");
        }
        scan.close();

    }

    // public void buyItem(Objek objek) {

    // }
    

    // public void installItem(NonFood nonFood) {

    // }

    public void goTo () {
    //cuma untuk pindah di satu ruangan
        Scanner scan = new Scanner(System.in);
        boolean pointValid = false;
        while(!pointValid){
            System.out.print("Masukan titik X tujuan : ");
            int X = scan.nextInt();
            System.out.println();
    
            System.out.print("Masukan titik Y tujuan : ");
            int Y = scan.nextInt();
            System.out.println();
    
            if ( X < 0 || X > 5 || Y < 0 || Y > 5){
                System.out.println("Point tidak valid.");
            }
            else{
                Point point = new Point(X,Y);
                simLoc.setPoint(point);
                pointValid = true;
            }
        }
        scan.close();
    }
}
