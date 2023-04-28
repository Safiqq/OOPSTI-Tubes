package simplicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Action {
    private static final List<Action> listAction = new ArrayList<Action>();
    private String actionName;
    private String description;
    private List<Effect> listEffect;

    public Action(String actionName, String description) {
        this(actionName, description, new ArrayList<Effect>());
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
        Effect[] arrayEffect1 = {new Effect("kekenyangan", -10, 30), new Effect("mood", -10, 30)};
        listAction.add(new Action("Work",
                "Sim harus bekerja. Selama bekerja, sim kekenyangan dan mood Sim akan berkurang. Kerja juga akan " +
                        "menghasilkan uang dengan jumlah yang bergantung pada pekerjaan dari Sim.",
                Arrays.asList(arrayEffect1)));
        Arrays.fill(arrayEffect1, null);

        /* Aksi Olahraga */
        Effect[] arrayEffect2 = {new Effect("kesehatan", 5, 20), new Effect("kekenyangan", -5, 20),
                new Effect("mood", 10, 20)};
        listAction.add(new Action("Exercise",
                "Sim dapat berolahraga untuk menaikkan kesehatan dan mood. Tetapi, olahraga akan menurunkan " +
                        "kekenyangan.",
                Arrays.asList(arrayEffect2)));
        Arrays.fill(arrayEffect2, null);

        /* Aksi Tidur */
        Effect[] arrayEffect31 = {new Effect("mood", 30, 240), new Effect("kesehatan", 20, 240)};
        listAction.add(new Action("Sleep",
                "SIm sebagai manusia harus memiliki waktu tidur minimum 3 menit setiap harinya atau kesehatan dari " +
                        "sim akan berkurang.",
                Arrays.asList(arrayEffect31)));
        Arrays.fill(arrayEffect31, null);

        /* Tidak Tidur */
        Effect[] arrayEffect32 = {new Effect("kesehatan", -5, 0), new Effect("mood", -5, 0)};
        listAction.add(new Action("Sleep",
                "SIm sebagai manusia harus memiliki waktu tidur minimum 3 menit setiap harinya atau kesehatan dari " +
                        "sim akan berkurang.",
                Arrays.asList(arrayEffect32)));
        Arrays.fill(arrayEffect32, null);

        /* Aksi Makan */
        Effect[] arrayEffect4 = {new Effect("kekenyangan", 0, 30)};
        listAction.add(new Action("Eat",
                "Makan berarti Sim mengambil makanan yang ada di Inventory untuk kemudian dikonsumsi. Konsumsi " +
                        "makanan akan mengurangi jumlah makanan terkait pada inventory sejumlah 1 buah dan " +
                        "meningkatkan tingkat kekenyangan Sim sejumlah satuan kekenyangan makanan terkait.",
                Arrays.asList(arrayEffect4)));
        Arrays.fill(arrayEffect4, null);

        /* Aksi Memasak */
        Effect[] arrayEffect5 = {new Effect("mood", 10, 0)};
        listAction.add(new Action("Cook",
                "Sim bisa membuat makanan dengan melakukan tindakan masak. Masak akan memilih dari suatu menu, dengan" +
                        " setiap makanan dari menu memiliki waktu pembuatan makanan serta memenuhi rasa kekenyangan " +
                        "sejumlah satuan tertentu. Makanan yang berhasil dibuat akan dimasukkan ke dalam sebuah " +
                        "inventory. Waktu yang diperlukan untuk memasak adalah (1.5*tingkat kekenyangan) masakan.",
                Arrays.asList(arrayEffect5)));
        Arrays.fill(arrayEffect5, null);

        /* Aksi Berkunjung */
        Effect[] arrayEffect6 = {new Effect("mood", 10, 30), new Effect("kekenyangan", -10, 30)};
        listAction.add(new Action("Visit",
                "Sim dapat berkunjung ke rumah lain yang berada pada world. Waktu yang dihabiskan untuk kunjungan " +
                        "dapat berefek ke mood dari Sim. Diperlukan waktu untuk berkunjung dari titik SIM ke titik " +
                        "rumah SIM lainnya. Perhitungan waktu tersebut dapat dilakukan dengan rumus √(x2-x1)2+(y2-y1)" +
                        "2.",
                Arrays.asList(arrayEffect6)));
        Arrays.fill(arrayEffect6, null);

        /* Aksi Buang Air */
        Effect[] arrayEffect71 = {new Effect("kekenyangan", -20, 10), new Effect("mood", 10, 10)};
        listAction.add(new Action("Pee",
                "Sim sebagai manusia virtual memiliki organ pencernaan layaknya manusia biasa. Akibatnya, setiap hari" +
                        " ia harus buang air setidaknya 1 kali setiap habis makan. Apabila tidak dilakukan, maka mood" +
                        " dan kesehatan Sim akan berkurang.",
                Arrays.asList(arrayEffect71)));
        Arrays.fill(arrayEffect71, null);

        /* Tidak Buang Air */
        Effect[] arrayEffect72 = {new Effect("kesehatan", -5, 240), new Effect("mood", -5, 240)};
        listAction.add(new Action("Not Pee",
                "Sim sebagai manusia virtual memiliki organ pencernaan layaknya manusia biasa. Akibatnya, setiap hari" +
                        " ia harus buang air setidaknya 1 kali setiap habis makan. Apabila tidak dilakukan, maka mood" +
                        " dan kesehatan Sim akan berkurang.",
                Arrays.asList(arrayEffect72)));
        Arrays.fill(arrayEffect72, null);

        /* Aksi Upgrade Rumah */
        Effect[] arrayEffect8 = {new Effect("", 0, 1080)};
        listAction.add(new Action("Upgrade House",
                "Sim bisa menghabiskan uang virtual untuk meningkatkan rumahnya dengan menambah ruangan. Setiap " +
                        "penambahan ruangan membutuhkan waktu sejumlah 18 menit.",
                Arrays.asList(arrayEffect8)));
        Arrays.fill(arrayEffect8, null);

        /* Aksi Beli Barang */
        Random random = new Random();
        // random.nextInt(13) + 4 = [4..16]
        Effect[] arrayEffect9 = {new Effect("", 0, random.nextInt(13) + 4)};
        listAction.add(new Action("Buy Item",
                "Sim bisa menghabiskan uang virtual untuk mengisi rumahnya dengan barang-barang. Karena situasi " +
                        "pengiriman barang yang tidak pasti, maka waktu kedatangan barang tidak dapat dipastikan. " +
                        "Maka diketahui bahwa durasi pengiriman barang akan selalu acak tetapi tetap dalam range " +
                        "waktu X menit.",
                Arrays.asList(arrayEffect9)));
        Arrays.fill(arrayEffect9, null);

        /* Aksi Berpindah Ruangan */
        listAction.add(new Action("Move Room",
                "Sims dapat berpindah ruangan untuk melakukan interaksi pada objek yang terdapat pada ruangan berbeda" +
                        "."));

        /* Aksi Melihat Inventory */
        listAction.add(new Action("View Inventory",
                "Sims dapat melihat inventory yang berisi dengan makanan, barang-barang yang sedang tidak terpasang " +
                        "pada ruangan, dan objek-objek lainnya."));

        /* Aksi Memasang Barang */
        listAction.add(new Action("Install Item",
                "Dalam dunia Sim-Plicity, pemasangan barang apapun dapat dilakukan dengan instan. Barang yang akan " +
                        "dipasang harus muat dalam ruangan."));

        /* Aksi Melihat Waktu */
        listAction.add(new Action("Check Time", "Tindakan ini membutuhkan objek Jam, dan akan menunjukkan sisa waktu " +
                "pada hari tersebut beserta sisa waktu yang masih ada untuk seluruh tindakan yang bisa ditinggal."));
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
        for (Effect effect : listEffect) {
            if (effect.getMotiveName().equals(key)) {
                listEffect.remove(effect);
            }
        }
    }
}
