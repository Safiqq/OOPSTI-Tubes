package simplicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Action {
    private String actionName;
    private String description;
    private List<Effect> listEffect;
    private static List<Action> listAction = new ArrayList<Action>();

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

    // public static void printListAction() {
    //     int i = 1;
    //     for (Action action: listAction) {
    //         System.out.println(i + ". " + action.getActionName());
    //         i++;
    //     }
    // }

    private void fillListAction() {
        // kerja
        Effect[] arrayEffect1 = { new Effect("kekenyangan", -10, 30), new Effect("mood", -10, 30) };
        listAction.add(new Action("Kerja",
                "Sim harus bekerja. Selama bekerja, sim kekenyangan dan mood Sim akan berkurang. Kerja juga akan menghasilkan uang dengan jumlah yang bergantung pada pekerjaan dari Sim.",
                Arrays.asList(arrayEffect1)));
        Arrays.fill(arrayEffect1, null);

        // olahraga
        Effect[] arrayEffect2 = { new Effect("kesehatan", 5, 20), new Effect("kekenyangan", -5, 20),
                new Effect("mood", 10, 20) };
        listAction.add(new Action("Olahraga",
                "Sim dapat berolahraga untuk menaikkan kesehatan dan mood. Tetapi, olahraga akan menurunkan kekenyangan.",
                Arrays.asList(arrayEffect2)));
        Arrays.fill(arrayEffect2, null);

        // tidur
        Effect[] arrayEffect3 = { new Effect("mood", 30, 240), new Effect("kesehatan", 20, 240) };
        listAction.add(new Action("Tidur",
                "SIm sebagai manusia harus memiliki waktu tidur minimum 3 menit setiap harinya atau kesehatan dari sim akan berkurang.",
                Arrays.asList(arrayEffect3)));
        Arrays.fill(arrayEffect3, null);

        // makan
        Effect[] arrayEffect4 = { new Effect("kekenyangan", 0, 30) };
        listAction.add(new Action("Makan",
                "Makan berarti Sim mengambil makanan yang ada di Inventory untuk kemudian dikonsumsi. Konsumsi makanan akan mengurangi jumlah makanan terkait pada inventory sejumlah 1 buah dan meningkatkan tingkat kekenyangan Sim sejumlah satuan kekenyangan makanan terkait.",
                Arrays.asList(arrayEffect4)));
        Arrays.fill(arrayEffect4, null);

        // memasak
        Effect[] arrayEffect5 = { new Effect("mood", 10, 0) };
        listAction.add(new Action("Memasak",
                "Sim bisa membuat makanan dengan melakukan tindakan masak. Masak akan memilih dari suatu menu, dengan setiap makanan dari menu memiliki waktu pembuatan makanan serta memenuhi rasa kekenyangan sejumlah satuan tertentu. Makanan yang berhasil dibuat akan dimasukkan ke dalam sebuah inventory. Waktu yang diperlukan untuk memasak adalah (1.5*tingkat kekenyangan) masakan.",
                Arrays.asList(arrayEffect5)));
        Arrays.fill(arrayEffect5, null);

        // berkunjung
        Effect[] arrayEffect6 = { new Effect("mood", 10, 30), new Effect("kekenyangan", -10, 30) };
        listAction.add(new Action("Berkunjung",
                "Sim dapat berkunjung ke rumah lain yang berada pada world. Waktu yang dihabiskan untuk kunjungan dapat berefek ke mood dari Sim. Diperlukan waktu untuk berkunjung dari titik SIM ke titik rumah SIM lainnya. Perhitungan waktu tersebut dapat dilakukan dengan rumus √(x2-x1)2+(y2-y1)2.",
                Arrays.asList(arrayEffect6)));
        Arrays.fill(arrayEffect6, null);

        // buang air
        Effect[] arrayEffect71 = { new Effect("kekenyangan", -20, 10), new Effect("mood", 10, 10) };
        listAction.add(new Action("Buang air",
                "Sim sebagai manusia virtual memiliki organ pencernaan layaknya manusia biasa. Akibatnya, setiap hari ia harus buang air setidaknya 1 kali setiap habis makan. Apabila tidak dilakukan, maka mood dan kesehatan Sim akan berkurang.",
                Arrays.asList(arrayEffect71)));
        Arrays.fill(arrayEffect71, null);

        // tidak buang air
        Effect[] arrayEffect72 = { new Effect("kesehatan", -5, 240), new Effect("mood", -5, 240) };
        listAction.add(new Action("Tidak buang air",
                "Sim sebagai manusia virtual memiliki organ pencernaan layaknya manusia biasa. Akibatnya, setiap hari ia harus buang air setidaknya 1 kali setiap habis makan. Apabila tidak dilakukan, maka mood dan kesehatan Sim akan berkurang.",
                Arrays.asList(arrayEffect72)));
        Arrays.fill(arrayEffect72, null);

        // upgrade rumah
        Effect[] arrayEffect8 = { new Effect("", 0, 1080) };
        listAction.add(new Action("Upgrade rumah",
                "Sim bisa menghabiskan uang virtual untuk meningkatkan rumahnya dengan menambah ruangan. Setiap penambahan ruangan membutuhkan waktu sejumlah 18 menit.",
                Arrays.asList(arrayEffect8)));
        Arrays.fill(arrayEffect8, null);

        // beli barang
        Random random = new Random();
        // random.nextInt(13) + 4 = [4..16]
        Effect[] arrayEffect9 = { new Effect("", 0, random.nextInt(13) + 4) };
        listAction.add(new Action("Beli barang",
                "Sim bisa menghabiskan uang virtual untuk mengisi rumahnya dengan barang-barang. Karena situasi pengiriman barang yang tidak pasti, maka waktu kedatangan barang tidak dapat dipastikan. Maka diketahui bahwa durasi pengiriman barang akan selalu acak tetapi tetap dalam range waktu X menit.",
                Arrays.asList(arrayEffect9)));
        Arrays.fill(arrayEffect9, null);

        // berpindah ruangan
        listAction.add(new Action("Berpindah ruangan",
                "Sims dapat berpindah ruangan untuk melakukan interaksi pada objek yang terdapat pada ruangan berbeda."));

        // melihat inventory
        listAction.add(new Action("Melihat inventory",
                "Sims dapat melihat inventory yang berisi dengan makanan, barang-barang yang sedang tidak terpasang pada ruangan, dan objek-objek lainnya."));

        // memasang barang
        listAction.add(new Action("Memasang barang",
                "Dalam dunia Sim-Plicity, pemasangan barang apapun dapat dilakukan dengan instan. Barang yang akan dipasang harus muat dalam ruangan."));

        // melihat waktu
        listAction.add(new Action("Melihat waktu",
                "Tindakan ini membutuhkan objek Jam, dan akan menunjukkan sisa waktu pada hari tersebut beserta sisa waktu yang masih ada untuk seluruh tindakan yang bisa ditinggal."));
    }

    public static Action get(String actionName) {
        for (Action action : listAction) {
            if (Main.equals(action.getActionName(), actionName)) {
                return action;
            }
        }
        return null;
    }

    public String getActionName() {
        return actionName;
    }

    public String getDescription() {
        return description;
    }

    public List<Effect> getListEffect() {
        return listEffect;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setDescription(String description) {
        this.description = description;
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
