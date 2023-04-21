package simplicity;

import java.util.*;

public class Sim {
    private String fullName;
    private Occupation occupation;
    private int money;
    private Inventory inventory;
    private Motive motive;
    private String status;
    private Location simLoc;
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
        // this.simLoc = new Location(null, null, null); //inisiasi di class location
    }

    public String getFullName() {
        return this.fullName;
    }

    public Occupation getOccupation() {
        return this.occupation;
    }

    public int getMoney() {
        return this.money;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Motive getMotive() {
        return this.motive;
    }

    public String getStatus() {
        return this.status;
    }

    public Location getSimLoc() {
        return this.simLoc;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setListInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setMotive(Motive motive) {
        this.motive = motive;
    }

    public void setStatus(String status) {
        this.status = status;
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
            }
        } else {
            System.out.println("Sim hanya dapat mengganti pekerjaan jika sudah bekerja setidaknya 12 menit");
        }
    }

    // public void exercise() {

    // }

    // public void sleep(int timeMillis) {

    // }

    // public void eat(Food food) {

    // }

    // public void cook(Food food) {

    // }

    // public void visit(Point point) {

    // }

    // public void pee() {

    // }

    // public void upgradeHouse() {

    // }

    // public void buyItem(Objek objek) {

    // }

    // public void moveRoom(Room room) {

    // }

    // public void installItem(NonFood nonFood) {

    // }

    // public void goTo (Point point) {

    // }
}
