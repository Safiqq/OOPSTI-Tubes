package simplicity;

public class Sim {
    private String fullName;
    private Occupation occupation;
    private int money;
    private Inventory inventory;
    private Motive motive;
    private String status;
    private Location simLoc;

    public Sim(String fullName) {
        this.motive = new Motive(); //inisiasi di class motive
        this.money = 100;
        this.occupation = new Occupation(); //inisiasi di class occupation
        this.fullName = fullName;
        this.inventory = new Inventory(); //inisiasi di class inventory
        //this.simLoc = new Location(null, null, null); //inisiasi di class location
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

    // public void work() {

    // }
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
