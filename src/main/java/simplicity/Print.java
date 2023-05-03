package simplicity;

import com.google.common.primitives.Ints;

import java.util.List;
import java.util.Map;

public class Print {
    public static void printMenu(int[] numbers) {
        int i = 1;
        for (int j = 0; j < Main.getMenu().length; j++) {
            if (Ints.contains(numbers, j)) {
                System.out.println(i + ". " + Main.getMenu()[j]);
                i++;
            }
        }
    }

    public static void showMenuBegin() {
        System.out.println("Menu game yang tersedia:");
        printMenu(new int[]{0, 1, 2});
    }

    public static void showMenu() {
        System.out.println("Menu game yang tersedia:");
        printMenu(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13});
    }

    public static void showAction(String action) {
        System.out.println("Aksi yang dapat dipilih:");

        int i = 1;
        List<Action> listAction = Action.getListAction();
        for (int j = 0; j < Action.getListAction().size(); j++) {
            if (!listAction.get(j).getActionName().contains("Not") && !listAction.get(j).getActionName().contains("Sleep") && !listAction.get(j).getActionName().contains("Eat") && !listAction.get(j).getActionName().contains("Cook") && !listAction.get(j).getActionName().contains("Pee") && !listAction.get(j).getActionName().contains("Check")) {
                System.out.println(i + ". " + listAction.get(j).getActionName() + " - " + listAction.get(j).getDescription());
                i++;
            }
        }

        System.out.print(i);
        if (Main.equals(action, "Pee")){
            System.out.println(". " + Action.get("Pee").getActionName() + " - " + Action.get("Pee").getDescription());
        } else if (Main.equals(action, "Sleep")){
            System.out.println(". " + Action.get("Sleep").getActionName() + " - " + Action.get("Sleep").getDescription());
        } else if (Main.equals(action, "Eat")){
            System.out.println(". " + Action.get("Eat").getActionName() + " - " + Action.get("Eat").getDescription());
        } else if (Main.equals(action, "Cook")){
            System.out.println(". " + Action.get("Cook").getActionName() + " - " + Action.get("Cook").getDescription());
        } else if (Main.equals(action, "Check Time")){
            System.out.println(". " + Action.get("Check Time").getActionName() + " - " + Action.get("Check Time").getDescription());
        }
    }

    public static void showCookingMenu() {
        String cookingMenu = "Mau masak apa hari ini? \n" +
                "1. Nasi Ayam (Kekenyangan : 16, Bahan : Nasi,Ayam) \n" +
                "2. Nasi Kari (Kekenyangan : 30, Bahan : Nasi,Kentang,Wortel,Sapi) \n" +
                "3. Susu Kacang (Kekenyangan : 5, Bahan : Susu,Kacang) \n" +
                "4. Tumis Sayur (Kekenyangan : 5, Bahan : Wortel,Bayam) \n" +
                "5. Bistik (Kekenyangan : 22, Bahan : Kentang,Sapi) \n";
        System.out.println(cookingMenu);
    }

    public static void showBuyObjectMenu() {
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

    public static void printListSim() {
        System.out.println("Daftar Sim yang dapat dimainkan: ");
        int i = 1;
        for (Sim sim : Sim.getListSim()) {
            System.out.println(i + ". " + sim.getFullName());
            i++;
        }
        System.out.println();
    }

    public static void viewSimFood(Sim sim) {
        // Menampilkan makanan yang ada di inventory
        Box<Food> boxFood = sim.getInventory().getBoxFood();
        
        System.out.println("========Berikut merupakan makanan yang kamu punya========");
        if (boxFood.getLength() == 0) {
            System.out.println("Sim " + sim.getFullName() + " tidak memiliki objek makanan dalam inventory");
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
        System.out.println("Lokasi Sim: ");
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
