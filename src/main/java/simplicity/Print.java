package simplicity;

import com.google.common.primitives.Ints;

import java.util.List;

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

    public static void showAction() {
        System.out.println("Aksi yang dapat dipilih:");
        // Change Job gatau masuk action ato ngga
        int i = 1;
        List<Action> listAction = Action.getListAction();
        for (int j = 0; j < Action.getListAction().size(); j++) {
            if (!listAction.get(j).getActionName().contains("Not")) {
                System.out.println(i + ". " + listAction.get(j).getActionName());
                i++;
            }
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
}
