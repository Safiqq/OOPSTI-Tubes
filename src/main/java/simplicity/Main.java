package simplicity;

public abstract class Main {
    protected static final World world = World.getWorld();
    protected static final String[] menu = {
            "Start Game - Memulai permainan",
            "Help - Melihat menu game yang tersedia",
            "Exit - Keluar dari permainan",
            "View Sim Info - Menampilkan informasi setiap atribut dari Sim",
            "View Current Location - Menampilkan lokasi dari Sim",
            "View Inventory - Menampilkan isi inventory yang dimiliki Sim",
            "Upgrade House - Melakukan penambahan ruangan dalam rumah",
            "Move Room - Perpindahan lokasi ke ruang lain yang ada pada rumah yang sedang ditempati Sim",
            "Edit Room - Melakukan perubahan pada ruangan",
            "Add Sim - Menambahkan sebuah Sim dalam world",
            "Change Sim - Mengganti ke Sim lain untuk dimainkan",
            "List Object - Menampilkan daftar objek dalam sebuah ruangan",
            "Go to Object - Sim berjalan menuju suatu objek",
            "Change Job - Sim dapat mengganti pekerjaan",
            "Action - Melakukan sebuah aksi pada suatu objek"
    };

    protected static Time time;
    protected Sim currentSim;
    protected int dayAddSim;

    public static String[] getMenu() {
        return menu;
    }

    public static boolean equals(String str1, String str2) {
        return str1.equalsIgnoreCase(str2);
    }

    public static void main(String[] args) {
        Action.fillListAction();
        Main main;
        if (args.length > 0) {
            if (equals(args[0], "cli")) {
                main = new CLIMain();
                main.start();
            }
        }
        main = new GUIMain();
        main.start();
    }

    public void start() {}

}
