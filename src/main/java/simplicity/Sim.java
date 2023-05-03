package simplicity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Sim {
    private static final List<Sim> listSim = new ArrayList<>();
    private final Location simLoc;
    private String fullName;
    private Motive motive;
    private int money;
    private Occupation occupation;
    private Inventory inventory;
    private Map<String, Integer> mapStatus;
    private int workTime = 0;
    private int dayWork = 0;
    private int storeWorkTime = 0;
    private int totalWorkTime = 0;
    private int dayChangeJob = 0;

    public Sim(String fullName) {
        this.fullName = fullName;
        motive = new Motive(); // inisiasi di class motive
        money = 100;
        occupation = new Occupation(); // inisiasi di class occupation
        inventory = new Inventory(); // inisiasi di class inventory
        House house = new House(fullName);
        simLoc = new Location(house, house.getDefaultRoom(), new Point(3, 3)); // inisiasi di class location

        listSim.add(this);
    }

    public Sim(String fullName, Point houseLoc) {
        this.fullName = fullName;
        motive = new Motive(); // inisiasi di class motive
        money = 100;
        occupation = new Occupation(); // inisiasi di class occupation
        inventory = new Inventory(); // inisiasi di class inventory
        House house = new House(fullName, houseLoc);
        simLoc = new Location(house, house.getDefaultRoom(), new Point(3, 3)); // inisiasi di class location

        listSim.add(this);
    }

    public static List<Sim> getListSim() {
        return listSim;
    }

    public static boolean isNotRegistered(String simName) {
        return get(simName) != null;
    }

    public static Sim get(String simName) {
        for (Sim sim : Sim.getListSim()) {
            if (Main.equals(simName, sim.getFullName())) {
                return sim;
            }
        }
        return null;
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

    public int getDayWork() {
        return dayWork;
    }

    public void setDayWork(int dayWork) {
        this.dayWork = dayWork;
    }

    public int getStoreWorkTime() {
        return storeWorkTime;
    }

    public void setStoreWorkTime(int storeWorkTime) {
        this.storeWorkTime = storeWorkTime;
    }

    public int getTotalWorkTime() {
        return totalWorkTime;
    }

    public void setTotalWorkTime(int totalWorkTime) {
        this.totalWorkTime = totalWorkTime;
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

    public Map<String, Integer> getMapStatus() {
        return mapStatus;
    }

    public void setMapStatus(Map<String, Integer> mapStatus) {
        this.mapStatus = mapStatus;
    }

    public void addStatus(String status, int duration) {
        List<Action> listAction = Action.getListAction();
        for (Action action : listAction) {
            if (Main.equals(status, action.getActionName())) {
                mapStatus.put(status, duration);
            }
        }
    }

    public void deleteStatus(String status) {
        List<Action> listAction = Action.getListAction();
        for (Action action : listAction) {
            if (Main.equals(status, action.getActionName())) {
                mapStatus.remove(status);
            }
        }
    }

    public Location getSimLoc() {
        return this.simLoc;
    }

    public void moveRoom(String roomName) {
        simLoc.setRoom(simLoc.getHouse().get(roomName));
        simLoc.getPoint().setX(3);
        simLoc.getPoint().setY(3);
    }

    public void work(int time) {
        totalWorkTime += time;

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
            totalWorkTime = 0;
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
        Print.viewSimFood(this);
        System.out.print("Mau makan apa? ");
        String maumakan = scanner.nextLine();
        if (Main.equals(maumakan, "Nasi Ayam")) {
            if (checkFood("Nasi Ayam")) {
                Food nasyam = getInventory().getBoxFood().get("Nasi Ayam");
                simEat(nasyam);
            } else {
                System.out.println("Anda tidak memiliki makanan " + maumakan);
            }
        } else if (Main.equals(maumakan, "Nasi Kari")) {
            if (checkFood("Nasi Kari")) {
                Food naskar = getInventory().getBoxFood().get("Nasi Kari");
                simEat(naskar);
            } else {
                System.out.println("Anda tidak memiliki makanan " + maumakan);
            }
        } else if (Main.equals(maumakan, "Susu Kacang")) {
            if (checkFood("Susu Kacang")) {
                Food suskac = getInventory().getBoxFood().get("Susu Kacang");
                simEat(suskac);
            } else {
                System.out.println("Anda tidak memiliki makanan " + maumakan);
            }
        } else if (Main.equals(maumakan, "Tumis Sayur")) {
            if (checkFood("Tumis Sayur")) {
                Food tumsay = getInventory().getBoxFood().get("Tumis Sayur");
                simEat(tumsay);
            } else {
                System.out.println("Anda tidak memiliki makanan " + maumakan);
            }
        } else if (Main.equals(maumakan, "Bistik")) {
            if (checkFood("Bistik")) {
                Food biwstik = getInventory().getBoxFood().get("Bistik");
                simEat(biwstik);
            } else {
                System.out.println("Anda tidak memiliki makanan " + maumakan);
            }
        } else {
            System.out.println("Anda tidak memiliki makanan " + maumakan);
        }
        scanner.close();
    }

    // thread hapus aja jd sleepMain di main
    public void simEat(Food nyam) {
        System.out.println("Sedang Memakan " + nyam.getObjekName());
        System.out.println(".......Please wait.......");

        System.out.println("Anda selesai makan!");
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
    public void cook(Food makanan) {
        System.out.println("Cooking " + makanan.getObjekName());
        int sleeptime = makanan.getFoodHunger() * 3 / 2 * 1000;
        System.out.println(".......Please wait.......");
        try {
            for (int k = (sleeptime / 1000); k >= 1; k--) {
                System.out.println("Time remaining " + k + " seconds");
            }
        } catch (Exception e) {
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
            System.out.println("Sim dengan nama " + this.getFullName() + " dihapus dari daftar Sim!");
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
            System.out.println("Sim dengan nama " + this.getFullName() + " dihapus dari daftar Sim!");
            listSim.remove(this);
        }
    }

    public void buyItem() {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        int buynumber = 0;
        while (!done) {
            System.out.println("Masukkan nomor item yang ingin dibeli.");
            System.out.print("Nomor: ");
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
        scanner.close();
    }

    public void installItem() {
        Scanner scan = new Scanner(System.in);
        // kalau sim sedang berada di rumah sendiri
        if (!Main.equals(getFullName(), simLoc.getHouse().getOwner())) {
            boolean barangValid = false;
            Box<NonFood> boxNonFood = getInventory().getBoxNonFood();

            System.out.println("List barang di inventory Anda:");
            int i = 1;
            for (NonFood barang : boxNonFood.getList()) {
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
                        if (matrixBarang[j][i] == barang) {
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

    public String getObjLoc() {
        // mengembalikan nama objek dimana sim sedang berada
        NonFood[][] matriksBarang = simLoc.getRoom().getMatrixBarang();
        NonFood obj = matriksBarang[simLoc.getPoint().getY()][simLoc.getPoint().getX()];
        return obj.getObjekName();
    }
}
