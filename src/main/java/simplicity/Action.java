package simplicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Action {
    private String actionName;
    private String description;
    private List<Effect> listEffect;
    private static List<Action> listAction = new ArrayList<Action>();

    public Action(String actionName, String description) {
        this.actionName = actionName;
        this.description = description;
        this.listEffect = new ArrayList<Effect>();
    }

    public Action(String actionName, String description, List<Effect> listEffect) {
        this.actionName = actionName;
        this.description = description;
        this.listEffect = listEffect;
    }

    private void fillListAction() {
        // kerja
        Effect[] arrayEffect1 = { new Effect("kekenyangan", -10, 30), new Effect("mood", -10, 30) };
        listAction.add(new Action("kerja",
                "Sim harus bekerja. Selama bekerja, sim kekenyangan dan mood Sim akan berkurang. Kerja juga akan menghasilkan uang dengan jumlah yang bergantung pada pekerjaan dari Sim.",
                Arrays.asList(arrayEffect1)));
        Arrays.fill(arrayEffect1, null);

        // olahraga
        Effect[] arrayEffect2 = { new Effect("kesehatan", 5, 20), new Effect("kekenyangan", -5, 20),
                new Effect("mood", 10, 20) };
        listAction.add(new Action("olahraga",
                "Sim dapat berolahraga untuk menaikkan kesehatan dan mood. Tetapi, olahraga akan menurunkan kekenyangan.",
                Arrays.asList(arrayEffect2)));
        Arrays.fill(arrayEffect2, null);

        // tidur
        Effect[] arrayEffect3 = { new Effect("mood", 30, 240), new Effect("kesehatan", 20, 240) };
        listAction.add(new Action("tidur",
                "SIm sebagai manusia harus memiliki waktu tidur minimum 3 menit setiap harinya atau kesehatan dari sim akan berkurang.",
                Arrays.asList(arrayEffect3)));
        Arrays.fill(arrayEffect3, null);

        // makan
        Effect[] arrayEffect4 = { new Effect("kekenyangan", 0, 30) };
        listAction.add(new Action("makan",
                "Makan berarti Sim mengambil makanan yang ada di Inventory untuk kemudian dikonsumsi. Konsumsi makanan akan mengurangi jumlah makanan terkait pada inventory sejumlah 1 buah dan meningkatkan tingkat kekenyangan Sim sejumlah satuan kekenyangan makanan terkait.",
                Arrays.asList(arrayEffect4)));
        Arrays.fill(arrayEffect4, null);

        // memasak
        Effect[] arrayEffect5 = { new Effect("mood", 10, 0) };
        listAction.add(new Action("memasak",
                "Sim bisa membuat makanan dengan melakukan tindakan masak. Masak akan memilih dari suatu menu, dengan setiap makanan dari menu memiliki waktu pembuatan makanan serta memenuhi rasa kekenyangan sejumlah satuan tertentu. Makanan yang berhasil dibuat akan dimasukkan ke dalam sebuah inventory. Waktu yang diperlukan untuk memasak adalah (1.5*tingkat kekenyangan) masakan",
                Arrays.asList(arrayEffect5)));
        Arrays.fill(arrayEffect5, null);

        // berkunjung
        // Effect[] arrayEffect6 = {};

        // buang air
        // Effect[] arrayEffect7 = {};

        // upgrade rumah
        Effect[] arrayEffect8 = { new Effect("", 0, 1080) };
        listAction.add(new Action("upgrade rumah",
                "Sim bisa menghabiskan uang virtual untuk meningkatkan rumahnya dengan menambah ruangan. Setiap penambahan ruangan membutuhkan waktu sejumlah 18 menit.",
                Arrays.asList(arrayEffect8)));
        Arrays.fill(arrayEffect8, null);

        // beli barang
        // Effect[] arrayEffect9 = {};

        // berpindah ruangan
        listAction.add(new Action("berpindah ruangan",
                "Sims dapat berpindah ruangan untuk melakukan interaksi pada objek yang terdapat pada ruangan berbeda."));

        // melihat inventory
        // Effect[] arrayEffect11 = {};

        // memasang barang
        // Effect[] arrayEffect12 = {};

        // melihat waktu
        // Effect[] arrayEffect13 = {};
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
