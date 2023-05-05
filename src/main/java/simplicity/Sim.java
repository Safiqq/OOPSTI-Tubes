package simplicity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        mapStatus = new HashMap<>();
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
        mapStatus = new HashMap<>();
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

    public void applyEffect(String actionName) {
        Action action = Action.get(actionName);
        for (Effect effect : action.getListEffect()) {
            try {
                if (Main.equals(effect.getMotiveName(), "hunger")) {
                    motive.changeHunger(effect.getMotiveEffect());
                } else if (Main.equals(effect.getMotiveName(), "health")) {
                    motive.changeHealth(effect.getMotiveEffect());
                } else if (Main.equals(effect.getMotiveName(), "mood")) {
                    motive.changeMood(effect.getMotiveEffect());
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Sim dengan nama " + fullName + " dihapus dari daftar Sim.");
                Sim.getListSim().remove(this);
            }
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

    public String getObjLoc() {
        // mengembalikan nama objek dimana sim sedang berada
        NonFood[][] matriksBarang = simLoc.getRoom().getMatrixBarang();
        NonFood obj = matriksBarang[simLoc.getPoint().getY()][simLoc.getPoint().getX()];
        return obj.getObjekName();
    }
}
