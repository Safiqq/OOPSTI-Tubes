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
        return get(simName) == null;
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
        mapStatus.keySet().removeIf(key -> Main.equals(key, status));
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

    public boolean checkGroceries(String namaGroc) {
        return getInventory().getBoxGroceries().isNotEmpty(namaGroc);
    }

    public void deleteGroceriesFromInventory(String namagroc) {
        getInventory().getBoxGroceries().delete(namagroc);
    }

    public boolean checkFood(String namaFood) {
        return getInventory().getBoxFood().isNotEmpty(namaFood);
    }

    public String getObjLoc() {
        // mengembalikan nama objek dimana sim sedang berada
        NonFood[][] matriksBarang = simLoc.getRoom().getMatrixBarang();
        NonFood obj = matriksBarang[simLoc.getPoint().getY()][simLoc.getPoint().getX()];
        return obj.getObjekName();
    }
}
