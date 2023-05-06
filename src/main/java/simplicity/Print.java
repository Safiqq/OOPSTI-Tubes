package simplicity;

import com.google.common.primitives.Ints;

import java.util.Map;
import java.util.Set;

public class Print {
    public static void printMenu(int[] numbers) {
        int i = 0;
        for (int j = 0; j < Main.getMenu().length; j++) {
            if (Ints.contains(numbers, j)) {
                System.out.println(++i + ". " + Main.getMenu()[j]);
            }
        }
    }

    public static void showMenuBegin() {
        System.out.println("Menu game yang tersedia:");
        printMenu(new int[]{0, 1, 2});
    }

    public static void showMenu() {
        System.out.println("Menu game yang tersedia:");
        printMenu(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
    }

    public static void showAction(String objekName) {
        System.out.println("Aksi yang dapat dipilih:");

        NonFood objek = NonFood.get(objekName);
        int i = 0;
        for (Action action : objek.getListAction()) {
            System.out.println(++i + ". " + action.getActionName());
        }
    }

    public static void showCookingMenu() {
        int i = 0;
        for (Food food : Food.getListFood()) {
            System.out.print(++i + ". " + food.getObjekName() + " (Kekenyangan: " + food.getFoodHunger() + ", Bahan: ");
            int groceriesCount = food.getListGroceries().size();
            for (int j = 0; j < groceriesCount; j++) {
                System.out.print(food.getObjekName());
                if (j != groceriesCount - 1) {
                    System.out.print(", ");
                } else {
                    System.out.println(")");
                }
            }
        }
    }

    public static void showBuyObjectMenu() {
        System.out.println("Berikut list objek non makanan yang dapat dibeli:");
        int i = 0;
        for (NonFood objek : NonFood.getListNonFood()) {
            System.out.println(++i + ". " + objek.getObjekName() + " (Dimensi: " + objek.getLength() + "x" + objek.getWidth() + ", Harga: " + objek.getPrice() + ")");
        }
        System.out.println("Berikut list objek bahan makanan yang dapat dibeli:");
        for (Groceries groceries : Groceries.getListGroceries()) {
            System.out.println(++i + ". " + groceries.getObjekName() + " (Kekenyangan: " + groceries.getHunger() + ", Harga: " + groceries.getPrice() + ")");
        }
    }

    public static void printListObjek(Room room) {
        System.out.println("Objek yang ada di ruang " + room.getRoomName() + ":");
        int i = 0;
        for (NonFood objek : room.getListObjek()) {
            System.out.println(++i + ". " + objek.getObjekName() + " [(" + objek.getStartPoint().getX() + ", " + objek.getStartPoint().getY() + ") - (" + objek.getEndPoint().getX() + ", " + objek.getEndPoint().getY() + ")]");
        }
    }

    public static void printStatus(Sim sim) {
        Set<String> keys = sim.getMapStatus().keySet();
        if (keys.size() > 0) {
            System.out.println("Status Sim sekarang:");
            int i = 0;
            for (String status : sim.getMapStatus().keySet()) {
                System.out.println(++i + ". " + status + " (Waktu tersisa " + sim.getMapStatus().get(status) + " detik)");
            }
        } else {
            System.out.println("Sim tidak memiliki status yang sedang aktif.");
        }
    }

    public static void printListSim() {
        System.out.println("Daftar Sim yang dapat dimainkan:");
        int i = 0;
        for (Sim sim : Sim.getListSim()) {
            System.out.println(++i + ". " + sim.getFullName());
        }
    }

    public static void viewSimFood(Sim sim) {
        // Menampilkan makanan yang ada di inventory
        Box<Food> boxFood = sim.getInventory().getBoxFood();

        System.out.println("========Berikut merupakan makanan yang Anda punya========");
        if (boxFood.getLength() == 0) {
            System.out.println("Sim " + sim.getFullName() + " tidak memiliki objek makanan dalam inventory.");
        } else {
            int i = 0;
            for (Map.Entry<String, Integer> entry : boxFood.getMap().entrySet()) {
                System.out.println((++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue());
            }
        }
    }

    public static void viewSimInfo(Sim sim) {
        System.out.println("Nama Sim: " + sim.getFullName());
        System.out.println("Pekerjaan Sim: " + sim.getOccupation().getJobName());
        System.out.println("Kesehatan Sim: " + sim.getMotive().getHealth());
        System.out.println("Kekenyangan Sim: " + sim.getMotive().getHunger());
        System.out.println("Mood Sim: " + sim.getMotive().getMood());
        System.out.println("Uang Sim: " + sim.getMoney());
    }

    public static void viewSimLoc(Sim sim) {
        System.out.println("Rumah milik: " + sim.getSimLoc().getHouse().getOwner());
        System.out.println("Nama ruangan: " + sim.getSimLoc().getRoom().getRoomName());
        System.out.println("X: " + sim.getSimLoc().getPoint().getX() + ", Y: " + sim.getSimLoc().getPoint().getY());
    }

    public static void viewSimInventory(Sim sim) {
        System.out.println("Berikut merupakan inventory yang dimiliki oleh Sim " + sim.getFullName());
        System.out.println();

        /* Objek NonMakanan */
        System.out.println("========Objek Non-Makanan========");
        if (sim.getInventory().getBoxNonFood().getLength() == 0) {
            System.out.println("Sim " + sim.getFullName() + " tidak memiliki objek non-makanan dalam inventory");
        } else {
            int i = 0;
            for (Map.Entry<String, Integer> entry : sim.getInventory().getBoxNonFood().getMap().entrySet()) {
                System.out.println((++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue());
            }
        }

        /* Objek Bahan Makanan */
        System.out.println("========Objek Bahan Makanan========");
        if (sim.getInventory().getBoxGroceries().getLength() == 0) {
            System.out.println("Sim " + sim.getFullName() + " tidak memiliki objek bahan makanan dalam inventory");
        } else {
            int i = 0;
            for (Map.Entry<String, Integer> entry : sim.getInventory().getBoxGroceries().getMap().entrySet()) {
                System.out.println((++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue());
            }
        }

        /* Objek Makanan */
        System.out.println("========Objek Makanan========");
        if (sim.getInventory().getBoxFood().getLength() == 0) {
            System.out.println("Sim " + sim.getFullName() + " tidak memiliki objek makanan dalam inventory");
        } else {
            int i = 0;
            for (Map.Entry<String, Integer> entry : sim.getInventory().getBoxFood().getMap().entrySet()) {
                System.out.println((++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue());
            }
        }
    }
}
