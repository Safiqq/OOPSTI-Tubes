package simplicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Action {
    private static final List<Action> listAction = new ArrayList<>();
    private String actionName;
    private String description;
    private List<Effect> listEffect;

    public Action(String actionName, String description) {
        this(actionName, description, new ArrayList<>());
    }

    public Action(String actionName, String description, List<Effect> listEffect) {
        this.actionName = actionName;
        this.description = description;
        this.listEffect = listEffect;
    }

    public static List<Action> getListAction() {
        return listAction;
    }

    public static Action get(String actionName) {
        for (Action action : listAction) {
            if (Main.equals(action.getActionName(), actionName)) {
                return action;
            }
        }
        return null;
    }

    public static void fillListAction() {
        /* Aksi Kerja */
        Effect[] arrayEffect1 = {new Effect("hunger", -10, 30), new Effect("mood", -10, 30)};
        listAction.add(new Action("Work", "Sim harus bekerja.", Arrays.asList(arrayEffect1)));

        /* Aksi Olahraga */
        Effect[] arrayEffect2 = {new Effect("health", 5, 20), new Effect("hunger", -5, 20),
                new Effect("mood", 10, 20)};
        listAction.add(new Action("Exercise", "Sim dapat berolahraga", Arrays.asList(arrayEffect2)));

        /* Aksi Tidur */
        Effect[] arrayEffect31 = {new Effect("mood", 30, 240), new Effect("health", 20, 240)};
        listAction.add(new Action("Sleep", "Sim sebagai manusia harus memiliki waktu tidur minimum 3 menit setiap harinya.", Arrays.asList(arrayEffect31)));

        /* Tidak Tidur */
        Effect[] arrayEffect32 = {new Effect("health", -5, 0), new Effect("mood", -5, 0)};
        listAction.add(new Action("Not Sleep", "Sim sebagai manusia harus memiliki waktu tidur minimum 3 menit setiap harinya.", Arrays.asList(arrayEffect32)));

        /* Aksi Makan */
        Effect[] arrayEffect4 = {new Effect("hunger", 0, 30)};
        listAction.add(new Action("Eat", "Makan berarti Sim mengambil makanan yang ada di Inventory untuk kemudian dikonsumsi.", Arrays.asList(arrayEffect4)));

        /* Aksi Memasak */
        Effect[] arrayEffect5 = {new Effect("mood", 10, 0)};
        listAction.add(new Action("Cook", "Sim bisa membuat makanan dengan melakukan tindakan masak. Makanan yang berhasil dibuat akan dimasukkan ke dalam sebuah inventory.", Arrays.asList(arrayEffect5)));

        /* Aksi Berkunjung */
        Effect[] arrayEffect6 = {new Effect("mood", 10, 30), new Effect("hunger", -10, 30)};
        listAction.add(new Action("Visit", "Sim dapat berkunjung ke rumah lain yang berada pada world.", Arrays.asList(arrayEffect6)));

        /* Aksi Buang Air */
        Effect[] arrayEffect71 = {new Effect("hunger", -20, 10), new Effect("mood", 10, 10)};
        listAction.add(new Action("Pee", "Sim sebagai manusia virtual memiliki organ pencernaan layaknya manusia biasa.", Arrays.asList(arrayEffect71)));

        /* Tidak Buang Air */
        Effect[] arrayEffect72 = {new Effect("health", -5, 240), new Effect("mood", -5, 240)};
        listAction.add(new Action("Not Pee", "Sim sebagai manusia virtual memiliki organ pencernaan layaknya manusia biasa.", Arrays.asList(arrayEffect72)));

        /* Aksi Upgrade Rumah */
        listAction.add(new Action("Upgrade House", "Sim bisa menghabiskan uang virtual untuk meningkatkan rumahnya dengan menambah ruangan."));

        /* Aksi Beli Barang */
        listAction.add(new Action("Buy Item", "Sim bisa menghabiskan uang virtual untuk mengisi rumahnya dengan barang-barang."));

        /* Aksi Berpindah Ruangan */
        listAction.add(new Action("Move Room", "Sims dapat berpindah ruangan untuk melakukan interaksi pada objek yang terdapat pada ruangan berbeda."));

        /* Aksi Melihat Inventory */
        listAction.add(new Action("View Inventory", "Sims dapat melihat inventory yang berisi dengan makanan, barang-barang yang sedang tidak terpasang pada ruangan, dan objek-objek lainnya."));

        /* Aksi Memasang Barang */
        listAction.add(new Action("Install Item", "Dalam dunia Sim-Plicity, pemasangan barang apapun dapat dilakukan dengan instan."));

        /* Aksi Melihat Waktu */
        listAction.add(new Action("Check Time", "Tindakan ini akan menunjukkan sisa waktu pada hari tersebut beserta sisa waktu yang masih ada untuk seluruh tindakan yang bisa ditinggal."));

        /* Aksi Buatan */
        /* Aksi Naik Meja dan Kursi */
        Effect[] arrayEffect8 = {new Effect("mood", 10, 15), new Effect("hunger", -5, 15)};
        listAction.add(new Action("Climb Table and Chair", "Sims dapat naik ke atas meja dan kursi untuk melepas kekesalan.", Arrays.asList(arrayEffect8)));

        /* Aksi Menyalakan Kompor */
        listAction.add(new Action("Turn On Stove", "Kompor harus dinyalakan terlebih dahulu untuk bisa memasak."));

        /* Aksi Mematikan Kompor */
        listAction.add(new Action("Turn Off Stove", "Kompor harus dimatikan setelah memasak."));

        /* Tidak Mematikan Kompor */
        Effect[] arrayEffect9 = {new Effect("health", -5, 10)};
        listAction.add(new Action("Not Turn Off Stove", "Kompor harus dimatikan setelah memasak.", Arrays.asList(arrayEffect9)));

        /* Aksi Duduk */
        Effect[] arrayEffect10 = {new Effect("mood", 10, 10), new Effect("health", 5, 10)};
        listAction.add(new Action("Sit", "Sims dapat duduk di meja dan kursi ataupun kasur.", Arrays.asList(arrayEffect10)));

        /* Aksi Cuci Tangan */
        Effect[] arrayEffect111 = {new Effect("health", 10, 10)};
        listAction.add(new Action("Wash Hand", "Sims dapat cuci tangan di wastafel.", Arrays.asList(arrayEffect111)));

        /* Aksi Tidak Cuci Tangan */
        Effect[] arrayEffect112 = {new Effect("health", -15, 180)};
        listAction.add(new Action("Not Wash Hand", "Sims dapat cuci tangan di wastafel.", Arrays.asList(arrayEffect112)));

        /* Aksi Bercermin */
        Effect[] arrayEffect12 = {new Effect("mood", 10, 15)};
        listAction.add(new Action("Look Mirror", "Sims dapat bercermin di cermin.", Arrays.asList(arrayEffect12)));

        /* Aksi Lempar Cermin */
        Effect[] arrayEffect13 = {new Effect("mood", 20, 0)};
        listAction.add(new Action("Throw Mirror", "Cermin yang dilempar akan hilang.", Arrays.asList(arrayEffect13)));
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Effect> getListEffect() {
        return listEffect;
    }

    public void setListEffect(List<Effect> listEffect) {
        this.listEffect = listEffect;
    }

    public void addEffect(Effect effect) {
        listEffect.add(effect);
    }

    public void deleteEffect(String key) {
        listEffect.removeIf(effect -> Main.equals(effect.getMotiveName(), key));
    }
}
