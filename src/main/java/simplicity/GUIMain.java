package simplicity;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.*;

public class GUIMain extends Main {
    private final static String basePath = "src/main/resources";
    private final static String imagePath = basePath + "/images";
    private final static String fontPath = basePath + "/fonts";
    private final String simsColorCode = "#a6e329";
    private final String greyColorCode = "#808080";
    private Font font16;
    private Font font20;

    public GUIMain() {
    }

    public void start() {
        importFont();
        font16 = new Font("The Sims Sans", Font.PLAIN, 16);
        font20 = new Font("The Sims Sans", Font.PLAIN, 20);
        showMainFrame();
    }

    public void importFont() {
        final GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
        try {
            // final File fontFile = new File(fontPath, "SimsLLHP.ttf");
            final File fontFile = new File(fontPath, "Loucos Lyne.otf");
            if (fontFile.exists()) {
                Font FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                if (!AVAILABLE_FONT_FAMILY_NAMES.contains(FONT.getFontName())) {
                    GE.registerFont(FONT);
                }
            }
        } catch (FontFormatException | IOException exception) {
            showMessagePopup(null, exception.getMessage(), exception.getClass().getSimpleName());
        }
    }

    public void setupFrame(JFrame frame) {
        frame.setLayout(null);
        frame.setSize(1280, 720);
        frame.setResizable(false);
    }

    public void resetupFrame(JFrame frame) {
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addToFrame(JFrame frame, Component[] components) {
        for (Component component : components) {
            frame.add(component);
        }
    }

    public void addToPanel(JPanel panel, Component[] components) {
        for (Component component : components) {
            panel.add(component);
        }
    }

    public void showMainFrame() {
        JFrame frame = new JFrame("SimPlicity - Start Screen");
        setupFrame(frame);

        JLabel label = createLabelImage("startScreen.png", 1280, 720);
        label.setBounds(0, 0, 1280, 720);

        JButton button1 = createButton("Start Game", e -> {
            String simName = showInputPopup(frame, "Masukkan nama SIM:", "Create SIM");
            if (simName != null) {
                while (equals(simName.trim(), "")) {
                    simName = showInputPopup(frame, "Masukkan nama SIM:", "Create SIM");
                    if (simName == null)
                        break;
                }
                if (simName != null) {
                    currentSim = new Sim(simName);
                    frame.setVisible(false);
                    showWorldFrame();
                }
            }
        });
        button1.setBounds((1280 - button1.getPreferredSize().width) / 2, 360, button1.getPreferredSize().width, button1.getPreferredSize().height);

        JButton button2 = createButton("About", e -> {
            frame.setVisible(false);
            showAboutFrame();
        });
        button2.setBounds((1280 - button2.getPreferredSize().width) / 2, 420, button2.getPreferredSize().width, button2.getPreferredSize().height);

        addToFrame(frame, new Component[]{button1, button2, label});

        resetupFrame(frame);
    }

    public JPanel createRoomInfo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Room room = currentSim.getSimLoc().getRoom();
        JLabel label0 = createLabel("Nama Ruangan: " + room.getRoomName());
        JLabel label1 = createLabel("Ruangan Sebelah Atas: " + (room.getUpperSide() != null ? room.getUpperSide().getRoomName() : "-"));
        JLabel label2 = createLabel("Ruangan Sebelah Kanan: " + (room.getRightSide() != null ? room.getRightSide().getRoomName() : "-"));
        JLabel label3 = createLabel("Ruangan Sebelah Bawah: " + (room.getBottomSide() != null ? room.getBottomSide().getRoomName() : "-"));
        JLabel label4 = createLabel("Ruangan Sebelah Kiri: " + (room.getLeftSide() != null ? room.getLeftSide().getRoomName() : "-"));

        panel.add(label0);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        return panel;
    }

    public JPanel createSimInfo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label0 = createLabel("Nama Sim: " + currentSim.getFullName());
        JLabel label1 = createLabel("Pekerjaan Sim: " + currentSim.getOccupation().getJobName());
        JLabel label2 = createLabel("Kesehatan Sim: " + currentSim.getMotive().getHealth());
        JLabel label3 = createLabel("Kekenyangan Sim: " + currentSim.getMotive().getHunger());
        JLabel label4 = createLabel("Mood Sim: " + currentSim.getMotive().getMood());
        JLabel label5 = createLabel("Uang Sim: " + currentSim.getMoney());

        panel.add(label0);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        return panel;
    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    public JLabel createLabelImage(String path, int width, int height) {
        JLabel label = new JLabel();
        label.setIcon(createImage(path, width, height));
        return label;
    }

    public ImageIcon createImage(String path, int width, int height) {
        BufferedImage img;
        try {
            img = ImageIO.read(new File(imagePath, path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resizeImage(new ImageIcon(img), width, height);
    }

    public JLabel createLabel(String text, int x, int y) {
        JLabel label = createLabel(text);
        label.setBounds((1280 - x - label.getPreferredSize().width) / 2, y, label.getPreferredSize().width, label.getPreferredSize().height);
        return label;
    }

    public JLabel createLabel(String text) {
        return createLabel(text, Component.LEFT_ALIGNMENT, 20);
    }

    public JLabel createLabel(String text, float alignment) {
        return createLabel(text, alignment, 20);
    }

    public JLabel createLabel(String text, int fontSize) {
        return createLabel(text, Component.LEFT_ALIGNMENT, fontSize);
    }

    public JLabel createLabel(String text, float alignment, int fontSize) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(alignment);
        label.setFont(new Font("The Sims Sans", Font.PLAIN, fontSize));
        label.setForeground(Color.white);
        return label;
    }

    public JButton createButton(String text, ActionListener callback) {
        return createButton(text, callback, simsColorCode);
    }

    public JButton createButton(String text, ActionListener callback, int fontSize) {
        JButton button = new RoundedButton(text, 40, Color.decode(simsColorCode), fontSize);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(callback);
        return button;
    }

    public JButton createButton(String text, ActionListener callback, String colorCode) {
        JButton button = new RoundedButton(text, 40, Color.decode(colorCode));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(callback);
        return button;
    }

    public JButton createBackButton(JFrame oldFrame, Callback callback) {
        JButton button = createCallbackButton("Back", oldFrame, callback, greyColorCode);
        button.setBounds(40, 40, button.getPreferredSize().width, button.getPreferredSize().height);
        return button;
    }

    public JButton createCallbackButton(String text, JFrame oldFrame, Callback callback, String colorCode) {
        JButton button = createButton(text, e -> {
            oldFrame.setVisible(false);
            callback.cb();
        }, colorCode);
        button.setFont(font20);
        return button;
    }

    public JButton createCallbackButton(String text, Callback callback, String colorCode) {
        JButton button = createButton(text, e -> callback.cb(), colorCode);
        button.setFont(font20);
        return button;
    }

    public void showAboutFrame() {
        JFrame frame = new JFrame("SimPlicity - About");
        setupFrame(frame);

        JLabel background = createLabelImage("background.png", 1280, 720);
        background.setBounds(0, 0, 1280, 720);
        JLabel label1 = createLabel("IF2212 Pemrograman Berorientasi Objek STI", 0, 150);
        JLabel label2 = createLabel("Kelompok 8", 0, 200);
        JLabel label3 = createLabel("1. Michael Ryan Martin (18219046)", 0, 250);
        JLabel label4 = createLabel("2. Syafiq Ziyadul Arifin (18221048)", 0, 280);
        JLabel label5 = createLabel("3. Nixon Deflin Kurniawan (18221150)", 0, 310);
        JLabel label6 = createLabel("4. Fredrick Runie Taslim (18221156)", 0, 340);
        JLabel label7 = createLabel("5. Rania Sasi Kirana (18221168)", 0, 370);
        JLabel label8 = createLabel("https://github.com/Safiqq/OOPSTI-Tubes", 0, 420);
        label8.setForeground(Color.BLUE.brighter());
        label8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Safiqq/OOPSTI-Tubes"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        JButton button = createBackButton(frame, this::showMainFrame);

        addToFrame(frame, new Component[]{label1, label2, label3, label4, label5, label6, label7, label8, button, background});

        resetupFrame(frame);
    }

    public JPanel createRoom() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(Room.getRoomWidth(), Room.getRoomLength()));

        for (int i = 0; i < (Room.getRoomWidth() * Room.getRoomLength()); i++) {
            NonFood barang = currentSim.getSimLoc().getRoom().getMatrixBarang()[i / 6][i % 6];
            // JLabel label = createLabel(" ", 90);
            JPanel panelx = createPanel(100, 100);
            if (barang != null) {
                panelx.setBackground(Color.RED);
                panelx.setToolTipText(barang.getObjekName() + " (" + i % 6 + ", " + i / 6 + ")");
            } else {
                panelx.setBackground(new Color(255, 255, 255));
                panelx.setToolTipText("(" + i % 6 + ", " + i / 6 + ")");
            }
            panelx.setOpaque(true);
            panelx.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panelx.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    ToolTipManager.sharedInstance().setInitialDelay(0);
                    ToolTipManager.sharedInstance().setDismissDelay(60 * 1000);
                }
            });
            panel.add(panelx);
        }
        return panel;
    }

    public void showRoomFrame(Point houseLoc, String roomName) {
        JFrame frame = new JFrame("House (" + houseLoc.getX() + ", " + houseLoc.getY() + ") - " + roomName);
        setupFrame(frame);

        String[] filteredActionNames = Action.getListAction().stream()
                .map(Action::getActionName)
                .filter(name -> !name.contains("Not"))
                .toArray(String[]::new);
        JComboBox<String> comboBox = new JComboBox<>(filteredActionNames);

        JButton button = createButton("Submit", e -> {
            // do action here
            System.out.println(comboBox.getSelectedItem());
        });
        // JButton button = createCallbackButton("Submit", frame, () -> doAction());

        JPanel panelRoom = createRoom();
        JPanel panelSim = createSimInfo();
        JPanel panelRoomInfo = createRoomInfo();

        addToFrame(frame, new Component[]{button, panelRoom, panelSim, panelRoomInfo});

        resetupFrame(frame);
    }

    public void showHelpFrame() {
        JFrame frame = new JFrame();
        setupFrame(frame);

        JLabel background = createLabelImage("background.png", 1280, 720);
        background.setBounds(0, 0, 1280, 720);
        int y = 88;
        JLabel label = createLabel("HELP", 0, y);
        y += 50;
        for (String s : menu) {
            JLabel labelx = createLabel(s, 0, y);
            y += 30;
            frame.add(labelx);
        }
        JButton button = createBackButton(frame, this::showWorldFrame);
        addToFrame(frame, new Component[]{label, button, background});

        resetupFrame(frame);
    }

    public JPanel createMenu(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Border lineBorder = BorderFactory.createLineBorder(Color.white, 2);
        Border titledBorder = BorderFactory.createTitledBorder(lineBorder, "Menu Game", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font20, Color.white);
        panel.setBorder(titledBorder);
        panel.setOpaque(false);

        JButton button1 = createCallbackButton("HELP", frame, this::showHelpFrame, simsColorCode);
        JButton button2 = createCallbackButton("VIEW SIM INFO", () -> {
            String text = "Nama Sim: " + currentSim.getFullName() + "<br>" +
                    "Pekerjaan Sim: " + currentSim.getOccupation().getJobName() + "<br>" +
                    "Kesehatan Sim: " + currentSim.getMotive().getHealth() + "<br>" +
                    "Kekenyangan Sim: " + currentSim.getMotive().getHunger() + "<br>" +
                    "Mood Sim: " + currentSim.getMotive().getMood() + "<br>" +
                    "Uang Sim: " + currentSim.getMoney();
            showMessagePopup(frame, text, "View Sim Info");
        }, simsColorCode);
        JButton button3 = createCallbackButton("VIEW CURRENT LOCATION", () -> {
            String text = "Rumah milik: " + currentSim.getSimLoc().getHouse().getOwner() + "<br>" +
                    "Lokasi rumah: (" + currentSim.getSimLoc().getHouse().getHouseLoc().getX() + ", " + currentSim.getSimLoc().getHouse().getHouseLoc().getY() + ")<br>" +
                    "Nama ruangan: " + currentSim.getSimLoc().getRoom().getRoomName() + "<br>" +
                    "X: " + currentSim.getSimLoc().getPoint().getX() + ", Y: " + currentSim.getSimLoc().getPoint().getY();
            showMessagePopup(frame, text, "View Current Location");
        }, simsColorCode);
        JButton button4 = createCallbackButton("VIEW INVENTORY", () -> {
            StringBuilder text = new StringBuilder("========Objek Non-Makanan========");
            if (currentSim.getInventory().getBoxNonFood().getLength() == 0) {
                text.append("<br>Sim ").append(currentSim.getFullName()).append(" tidak memiliki objek non-makanan dalam inventory");
            } else {
                int i = 0;
                for (Map.Entry<String, Integer> entry : currentSim.getInventory().getBoxNonFood().getMap().entrySet()) {
                    text.append("<br>").append(++i).append(". Objek: ").append(entry.getKey()).append(", Jumlah: ").append(entry.getValue());
                }
            }
            text.append("<br><br>========Objek Bahan Makanan========");
            if (currentSim.getInventory().getBoxGroceries().getLength() == 0) {
                text.append("<br>Sim ").append(currentSim.getFullName()).append(" tidak memiliki objek non-makanan dalam inventory");
            } else {
                int i = 0;
                for (Map.Entry<String, Integer> entry : currentSim.getInventory().getBoxGroceries().getMap().entrySet()) {
                    text.append("<br>").append(++i).append(". Objek: ").append(entry.getKey()).append(", Jumlah: ").append(entry.getValue());
                }
            }
            text.append("<br><br>========Objek Makanan========");
            if (currentSim.getInventory().getBoxFood().getLength() == 0) {
                text.append("<br>Sim ").append(currentSim.getFullName()).append(" tidak memiliki objek non-makanan dalam inventory");
            } else {
                int i = 0;
                for (Map.Entry<String, Integer> entry : currentSim.getInventory().getBoxFood().getMap().entrySet()) {
                    text.append("<br>").append(++i).append(". Objek: ").append(entry.getKey()).append(", Jumlah: ").append(entry.getValue());
                }
            }
            showMessagePopup(frame, text.toString(), "View Inventory");
        }, simsColorCode);
        JButton button5 = createCallbackButton("UPGRADE HOUSE", () -> {
            if (equals(currentSim.getSimLoc().getHouse().getOwner(), currentSim.getFullName())) {
                if (currentSim.getMapStatus().get("Upgrade House") == null) {
                    if (currentSim.isMoneyEnough(1500)) {
                        House house = currentSim.getSimLoc().getHouse();
                        List<Room> listRoom = house.getListRoom();
                        Room tempRoom = currentSim.getSimLoc().getRoom();
                        StringBuilder text = new StringBuilder("List ruangan di rumahmu:");
                        for (int i = 0; i < listRoom.size(); i++) {
                            text.append("<br>").append(i + 1).append(". ").append(listRoom.get(i).getRoomName());
                        }

                        text.append("<br><br>Masukkan nama salah satu ruangan sebagai acuan: ");
                        if (listRoom.size() > 1) {
                            do {
                                String roomName = showInputPopup(frame, text.toString(), "Nama Ruangan");
                                tempRoom = house.get(roomName);
                            } while (tempRoom == null);
                        }

                        String newRoomName = showInputPopup(frame, "Masukkan nama ruangan baru:", "Nama Ruangan");
                        Room _tempRoom = tempRoom;

                        // Loop untuk mendapatkan lokasi ruangan baru yang valid
                        while (true) {
                            String roomLoc = showInputPopup(frame, "Pilih lokasi " + newRoomName + " di sebelah " + tempRoom.getRoomName() + " (KIRI/KANAN/ATAS/BAWAH): ", "Lokasi Ruangan");
                            if (equals(roomLoc, "KANAN")) {
                                currentSim.addStatus("Upgrade House", 18 * 60);
                                Thread thread = new Thread(() -> {
                                    while (currentSim.getMapStatus().get("Upgrade House") != null) {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    Room newRoom = new Room(newRoomName, null, null, _tempRoom, null);
                                    _tempRoom.setRightSide(newRoom);
                                    house.addListRoom(newRoom);
                                    currentSim.setMoney(currentSim.getMoney() - 1500);
                                });
                                thread.start();
                                break;
                            } else if (Main.equals(roomLoc, "KIRI")) {
                                currentSim.addStatus("Upgrade House", 18 * 60);
                                Thread thread = new Thread(() -> {
                                    while (currentSim.getMapStatus().get("Upgrade House") != null) {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    Room newRoom = new Room(newRoomName, null, null, null, _tempRoom);
                                    _tempRoom.setLeftSide(newRoom);
                                    house.addListRoom(newRoom);
                                    currentSim.setMoney(currentSim.getMoney() - 1500);
                                });
                                thread.start();
                                break;
                            } else if (Main.equals(roomLoc, "ATAS")) {
                                currentSim.addStatus("Upgrade House", 18 * 60);
                                Thread thread = new Thread(() -> {
                                    while (currentSim.getMapStatus().get("Upgrade House") != null) {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    Room newRoom = new Room(newRoomName, null, _tempRoom, null, null);
                                    _tempRoom.setUpperSide(newRoom);
                                    house.addListRoom(newRoom);
                                    currentSim.setMoney(currentSim.getMoney() - 1500);
                                });
                                thread.start();
                                break;
                            } else if (Main.equals(roomLoc, "BAWAH")) {
                                currentSim.addStatus("Upgrade House", 18 * 60);
                                Thread thread = new Thread(() -> {
                                    while (currentSim.getMapStatus().get("Upgrade House") != null) {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    Room newRoom = new Room(newRoomName, _tempRoom, null, null, null);
                                    _tempRoom.setBottomSide(newRoom);
                                    house.addListRoom(newRoom);
                                    currentSim.setMoney(currentSim.getMoney() - 1500);
                                });
                                thread.start();
                                break;
                            }
                        }
                    } else {
                        showMessagePopup(frame, "Uang Sim tidak cukup untuk upgrade rumah.", "Failed");
                    }
                } else {
                    showMessagePopup(frame, "Anda sudah upgrade rumah! Tunggu sampai upgrade rumah selesai.", "Failed");
                }
            } else {
                showMessagePopup(frame, "Sim tidak sedang berada di rumah sendiri.<br>Silakan kembali ke rumah sendiri untuk melakukan upgrade House!", "Failed");
            }
        }, simsColorCode);
        JButton button6 = createCallbackButton("MOVE ROOM", () -> {
            StringBuilder text;
            House house = currentSim.getSimLoc().getHouse();
            ArrayList<Room> listRoom = house.getListRoom();
            if (listRoom.size() == 1) {
                showMessagePopup(frame, "Hanya ada Ruangan Utama di rumah ini.", "Move Room");
            } else {
                text = new StringBuilder("Daftar ruangan yang terdapat di dalam rumah: ");
                int i = 1;
                for (Room room : listRoom) {
                    text.append("<br>").append(i).append(". ").append(room.getRoomName());
                    i++;
                }
                showMessagePopup(frame, text.toString(), "List Rooms");

                String oldRoom = currentSim.getSimLoc().getRoom().getRoomName();

                while (true) {
                    String roomName = showInputPopup(frame, "Masukkan nama ruangan yang ingin didatangi: ", "Nama Ruangan");
                    if (!equals(oldRoom, roomName)) {
                        if (house.get(roomName) != null) {
                            currentSim.moveRoom(roomName);
                            showMessagePopup(frame, "Berhasil pindah ruangan!", "Move Room");
                            break;
                        }
                    }
                }
            }
        }, simsColorCode);
        JButton button7 = createCallbackButton("EDIT ROOM", frame, () -> {
            while (true) {
                String ans = showInputPopup(frame, "Apakah Anda ingin membeli barang baru atau memindahkan barang? (Beli/Pindah): ", "Edit Room");
                if (equals(ans, "BELI")) {
                    if (currentSim.getMapStatus().get("Buy Item") == null) {
                        Print.showBuyObjectMenu();
                        int buynumber;

                        do {
                            buynumber = Integer.parseInt(showInputPopup(frame, "Masukkan nomor item yang ingin dibeli.<br>Nomor:", "Buy Item"));
                        } while (buynumber < 1 || buynumber > 18);

                        Objek objek;

                        if (buynumber == 1 && currentSim.isMoneyEnough(NonFood.get("Kasur Single").getPrice())) {
                            objek = new NonFood("Kasur Single");
                        } else if (buynumber == 2 && currentSim.isMoneyEnough(NonFood.get("Kasur Queen Size").getPrice())) {
                            objek = new NonFood("Kasur Queen Size");
                        } else if (buynumber == 3 && currentSim.isMoneyEnough(NonFood.get("Kasur King Size").getPrice())) {
                            objek = new NonFood("Kasur King Size");
                        } else if (buynumber == 4 && currentSim.isMoneyEnough(NonFood.get("Toilet").getPrice())) {
                            objek = new NonFood("Toilet");
                        } else if (buynumber == 5 && currentSim.isMoneyEnough(NonFood.get("Kompor Gas").getPrice())) {
                            objek = new NonFood("Kompor Gas");
                        } else if (buynumber == 6 && currentSim.isMoneyEnough(NonFood.get("Kompor Listrik").getPrice())) {
                            objek = new NonFood("Kompor Listrik");
                        } else if (buynumber == 7 && currentSim.isMoneyEnough(NonFood.get("Meja dan Kursi").getPrice())) {
                            objek = new NonFood("Meja dan Kursi");
                        } else if (buynumber == 8 && currentSim.isMoneyEnough(NonFood.get("Jam").getPrice())) {
                            objek = new NonFood("Jam");
                        } else if (buynumber == 9 && currentSim.isMoneyEnough(NonFood.get("Wastafel").getPrice())) {
                            objek = new NonFood("Wastafel");
                        } else if (buynumber == 10 && currentSim.isMoneyEnough(NonFood.get("Cermin").getPrice())) {
                            objek = new NonFood("Cermin");
                        } else if (buynumber == 11 && currentSim.isMoneyEnough(Groceries.get("Nasi").getPrice())) {
                            objek = new Groceries("Nasi");
                        } else if (buynumber == 12 && currentSim.isMoneyEnough(Groceries.get("Kentang").getPrice())) {
                            objek = new Groceries("Kentang");
                        } else if (buynumber == 13 && currentSim.isMoneyEnough(Groceries.get("Ayam").getPrice())) {
                            objek = new Groceries("Ayam");
                        } else if (buynumber == 14 && currentSim.isMoneyEnough(Groceries.get("Sapi").getPrice())) {
                            objek = new Groceries("Sapi");
                        } else if (buynumber == 15 && currentSim.isMoneyEnough(Groceries.get("Wortel").getPrice())) {
                            objek = new Groceries("Wortel");
                        } else if (buynumber == 16 && currentSim.isMoneyEnough(Groceries.get("Bayam").getPrice())) {
                            objek = new Groceries("Bayam");
                        } else if (buynumber == 17 && currentSim.isMoneyEnough(Groceries.get("Kacang").getPrice())) {
                            objek = new Groceries("Kacang");
                        } else if (buynumber == 18 && currentSim.isMoneyEnough(Groceries.get("Susu").getPrice())) {
                            objek = new Groceries("Susu");
                        } else {
                            objek = null;
                        }

                        Random rd = new Random();
                        int randomizer = rd.nextInt(5) + 1; // [1..5]
                        int waktukirim = randomizer * 30;

                        if (objek != null) {
                            final String _statusName = "Buy Item";
                            if (equals(objek.getClass().getSimpleName(), "Groceries")) {
                                if (currentSim.isMoneyEnough(((Groceries) objek).getPrice())) {
                                    currentSim.setMoney(currentSim.getMoney() - ((Groceries) objek).getPrice());
                                    showMessagePopup(frame, "Mengirim barang...", "Succeed");

                                    currentSim.addStatus(_statusName, waktukirim);
                                    Thread thread = new Thread(() -> {
                                        while (currentSim.getMapStatus().get(_statusName) != null) {
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                        currentSim.getInventory().getBoxGroceries().add((Groceries) objek);
                                        showMessagePopup(frame, "Berhasil membeli barang!", "Succeed");
                                    });
                                    thread.start();
                                } else {
                                    showMessagePopup(frame, "Uangmu kurang :(", "Failed");
                                }
                            } else if (equals(objek.getClass().getSimpleName(), "NonFood")) {
                                if (currentSim.isMoneyEnough(((NonFood) objek).getPrice())) {
                                    currentSim.setMoney(currentSim.getMoney() - ((NonFood) objek).getPrice());
                                    showMessagePopup(frame, "Mengirim barang...", "Succeed");

                                    currentSim.addStatus(_statusName, waktukirim);
                                    Thread thread = new Thread(() -> {
                                        while (currentSim.getMapStatus().get(_statusName) != null) {
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                        currentSim.getInventory().getBoxNonFood().add((NonFood) objek);
                                        showMessagePopup(frame, "Berhasil membeli barang!", "Succeed");
                                    });
                                    thread.start();
                                } else {
                                    showMessagePopup(frame, "Uangmu kurang :(", "Failed");
                                }
                            }
                        }
                    } else {
                        showMessagePopup(frame, "Anda sudah memesan barang! Tunggu sampai barang datang baru beli lagi.", "Failed");
                    }
                    break;
                } else if (equals(ans, "PINDAH")) {
                    if (equals(currentSim.getSimLoc().getHouse().getOwner(), currentSim.getFullName())) {
                        Room room = currentSim.getSimLoc().getRoom();
                        // Print list objek di ruangan
                        StringBuilder text = new StringBuilder("Objek yang ada di ruang " + room.getRoomName() + ":");
                        int i = 0;
                        for (NonFood objek : currentSim.getSimLoc().getRoom().getListObjek()) {
                            text.append("<br>").append(++i).append(". ").append(objek.getObjekName()).append(" [(").append(objek.getStartPoint().getX()).append(", ").append(objek.getStartPoint().getY()).append(") - (").append(objek.getEndPoint().getX()).append(", ").append(objek.getEndPoint().getY()).append(")]");
                        }

                        text.append("<br><br>Masukkan nomor barang yang ingin dipindahkan: ");
                        int numBarang = Integer.parseInt(showInputPopup(frame, text.toString(), "Item Number"));

                        ArrayList<NonFood> listBarang = currentSim.getSimLoc().getRoom().getListObjek();
                        if (numBarang <= listBarang.size()) {
                            NonFood targetBarang = listBarang.get(numBarang - 1);
                            int lengthBarang = targetBarang.getLength();
                            int widthBarang = targetBarang.getWidth();

                            boolean pointValid = false;
                            while (!pointValid) {
                                int startX = Integer.parseInt(showInputPopup(frame, "Masukkan lokasi awal sumbu X (0-5): ", "Start Point - X"));

                                int startY = Integer.parseInt(showInputPopup(frame, "Masukkan lokasi awal sumbu Y (0-5): ", "Start Point - Y"));

                                if (startX >= 0 && startX <= 5 && startY >= 0 && startY <= 5) {
                                    int endX = startX + lengthBarang - 1;
                                    int endY = startY + widthBarang - 1;

                                    if (endX > 5 || endY > 5) {
                                        showMessagePopup(frame, "Lokasi yang dipilih kurang besar.", "Failed");
                                    } else {
                                        Point startPoint = new Point(startX, startY);
                                        Point endPoint = new Point(endX, endY);

                                        NonFood[][] matrixBarang = currentSim.getSimLoc().getRoom().getMatrixBarang();
                                        for (i = targetBarang.getStartPoint().getX(); i <= targetBarang.getEndPoint().getX(); i++) {
                                            for (int j = targetBarang.getStartPoint().getY(); j <= targetBarang.getEndPoint().getY(); j++) {
                                                matrixBarang[j][i] = null;
                                            }
                                        }
                                        if (currentSim.getSimLoc().getRoom().isSpaceEmpty(startPoint, endPoint)) {
                                            targetBarang.setStartPoint(startPoint);
                                            targetBarang.setEndPoint(endPoint);
                                            for (i = targetBarang.getStartPoint().getY(); i <= targetBarang.getEndPoint().getY(); i++) {
                                                for (int j = targetBarang.getStartPoint().getX(); j <= targetBarang.getEndPoint().getX(); j++) {
                                                    matrixBarang[i][j] = targetBarang;
                                                }
                                            }
                                            showMessagePopup(frame, "Barang " + targetBarang.getObjekName() + " berhasil dipindah.", "Succeed");
                                            pointValid = true;
                                        } else {
                                            for (i = targetBarang.getStartPoint().getX(); i <= targetBarang.getEndPoint().getX(); i++) {
                                                for (int j = targetBarang.getStartPoint().getY(); j <= targetBarang.getEndPoint().getY(); j++) {
                                                    matrixBarang[j][i] = targetBarang;
                                                }
                                            }
                                            showMessagePopup(frame, "Lokasi sudah diisi barang lain.", "Failed");
                                        }
                                    }
                                } else {
                                    showMessagePopup(frame, "Masukkan lokasi tidak valid.", "Failed");
                                }
                            }
                        } else { // Kalau numBarang lebih dari total barang
                            showMessagePopup(frame, "Masukan tidak valid. Pilih nomor yang tersedia.", "Failed");
                        }
                    } else {
                        showMessagePopup(frame, "Tidak dapat memindahkan barang di rumah Sim lain.", "Failed");
                    }
                    break;
                } else {
                    System.out.println("Perintah tidak valid.");
                }
            }
        }, simsColorCode);
        JButton button8 = createCallbackButton("ADD SIM", frame, () -> {
            if (time.getDay() > dayAddSim) {
                // cek masi ada spot kosong di world ato ngga
                if (world.isHouseBuildAble()) {
                    Sim newSim;
                    while (true) {
                        String simName = showInputPopup(frame, "Masukkan nama Sim yang ingin Anda tambahkan: ", "Sim Name");
                        while (!Sim.isNotRegistered(simName)) {
                            showMessagePopup(frame, "Nama Sim telah terdaftar. Silakan menggunakan nama lain.", "Failed");
                            Print.printListSim();
                            simName = showInputPopup(frame, "Masukkan nama Sim yang ingin Anda tambahkan: ", "Sim Name");
                        }

                        // Validasi lokasi rumah Sim
                        world.printMatrixHouse();
                        int x, y;
                        while (true) {
                            x = Integer.parseInt(showInputPopup(frame, "Masukkan titik untuk mendirikan rumah:<br>X:", "House - Point - X"));
                            y = Integer.parseInt(showInputPopup(frame, "Masukkan titik untuk mendirikan rumah:<br>Y:", "House - Point - Y"));
                            if ((x < 0 || x > world.getLength() - 1) || (y < 0 || y > world.getWidth() - 1)) {
                                showMessagePopup(frame, "Titik tidak valid. World berukuran 64x64.", "Failed");
                            } else {
                                break;
                            }
                        }

                        // Buat Sim
                        Point houseLoc = new Point(x, y);
                        if (world.isWorldAvail(houseLoc)) {
                            newSim = new Sim(simName, houseLoc);
                            showMessagePopup(frame, "Sim berhasil didaftarkan.", "Succeed");
                        } else {
                            newSim = null;
                            showMessagePopup(frame, "Tidak memungkinkan untuk membuat rumah baru di lokasi tersebut.", "Succeed");
                            showMessagePopup(frame, "Pendaftaran Sim baru gagal!", "Succeed");
                        }
                        if (newSim != null) {
                            dayAddSim = time.getDay(); // hanya dapat dilakukan 1 hari sekali
                            break;
                        } else {
                            showMessagePopup(frame, "Silakan mencoba mendaftarkan Sim baru di lokasi yang berbeda.", "Failed");
                        }
                    }
                } else {
                    showMessagePopup(frame, "World penuh, tidak memungkinkan membuat Sim baru.", "Failed");
                }
            } else {
                showMessagePopup(frame, "Menu 'ADD SIM' hanya dapat dijalankan 1 hari sekali.", "Failed");
            }
        }, simsColorCode);
        JButton button9 = createCallbackButton("CHANGE SIM", frame, () -> {
            // di listSim hanya ada 1 sim
            if (Sim.getListSim().size() == 1) {
                showMessagePopup(frame, "Tidak bisa dilakukan pergantian Sim. Hanya terdapat 1 Sim yang terdaftar.<br>Lakukan 'ADD SIM' untuk menambah Sim baru!", "Failed");
            } else {
                Sim oldSim = currentSim;
                Sim newSim;
                while (true) {
                    while (true) {
                        StringBuilder text = new StringBuilder("Daftar Sim yang dapat dimainkan:");
                        int i = 0;
                        for (Sim sim : Sim.getListSim()) {
                            text.append("<br>").append(++i).append(". ").append(sim.getFullName());
                        }
                        text.append("<br><br>Masukkan nama Sim yang ingin dimainkan: ");

                        String simName = showInputPopup(frame, text.toString(), "Choose Sim");
                        if (Sim.isNotRegistered(simName)) {
                            showMessagePopup(frame, "Sim tidak ditemukan di list Sim!", "Failed");
                            Print.printListSim();
                        } else {
                            // Ambil Sim di listSim
                            newSim = Sim.get(simName);
                            break;
                        }
                    }
                    if (equals(newSim.getFullName(), oldSim.getFullName())) {
                        System.out.println("Nama Sim sama dengan yang sedang Anda mainkan.");
                        System.out.println("Masukkan nama Sim yang berbeda!");
                    } else {
                        break;
                    }
                }
                currentSim = newSim;
                System.out.println("Sim berhasil diganti. Selamat bermain!");
            }
        }, simsColorCode);
        JButton button10 = createCallbackButton("LIST OBJECT", frame, () -> {
            ArrayList<NonFood> listObjek = currentSim.getSimLoc().getRoom().getListObjek();
            if (listObjek.size() > 0) {
                Room room = currentSim.getSimLoc().getRoom();
                StringBuilder text = new StringBuilder("Objek yang ada di ruang " + room.getRoomName() + ":");
                int i = 0;
                for (NonFood objek : room.getListObjek()) {
                    text.append("<br>").append(++i).append(". ").append(objek.getObjekName()).append(" [(").append(objek.getStartPoint().getX()).append(", ").append(objek.getStartPoint().getY()).append(") - (").append(objek.getEndPoint().getX()).append(", ").append(objek.getEndPoint().getY()).append(")]");
                }
                showMessagePopup(frame, text.toString(), "List Object");
            } else {
                showMessagePopup(frame, "Tidak ada daftar objek dalam ruangan " + currentSim.getSimLoc().getRoom().getRoomName() + ".", "Failed");
            }
        }, simsColorCode);
        JButton button11 = createCallbackButton("GO TO OBJECT", frame, () -> {
            Room room = currentSim.getSimLoc().getRoom();
            StringBuilder text = new StringBuilder("Objek yang ada di ruang " + room.getRoomName() + ":");
            int i = 0;
            for (NonFood objek : room.getListObjek()) {
                text.append("<br>").append(++i).append(". ").append(objek.getObjekName()).append(" [(").append(objek.getStartPoint().getX()).append(", ").append(objek.getStartPoint().getY()).append(") - (").append(objek.getEndPoint().getX()).append(", ").append(objek.getEndPoint().getY()).append(")]");
            }

            ArrayList<NonFood> listBarang = currentSim.getSimLoc().getRoom().getListObjek();

            text.append("<br><br>Masukkan nomor barang yang dituju: ");
            int numBarang = Integer.parseInt(showInputPopup(frame, text.toString(), "Item Number"));

            if (numBarang <= listBarang.size()) {
                NonFood targetBarang = listBarang.get(numBarang - 1);
                currentSim.getSimLoc().getPoint().setX(targetBarang.getStartPoint().getX());
                currentSim.getSimLoc().getPoint().setY(targetBarang.getStartPoint().getY());
                showMessagePopup(frame, "Anda berhasil berpindah tempat ke objek " + currentSim.getObjLoc() + ".", "Succeed");
            } else { // Kalau numBarang lebih dari total barang
                showMessagePopup(frame, "Masukan tidak valid. Pilih nomor yang tersedia.", "Failed");
            }
        }, simsColorCode);
        JButton button12 = createCallbackButton("CHANGE JOB", frame, () -> {
            if (currentSim.getTotalWorkTime() >= 720) {
                newJob();
                currentSim.setDayChangeJob(time.getDay());
            } else {
                showMessagePopup(frame, "Sim hanya dapat mengganti pekerjaan jika sudah bekerja setidaknya 12 menit.", "Failed");
            }
        }, simsColorCode);
        JButton button13 = createCallbackButton("ACTION", frame, this::showHelpFrame, simsColorCode);

        addToPanel(panel, new Component[]{button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13});

        return panel;
    }

    public JPanel createWorld(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(world.getWidth(), world.getLength()));
        ImageIcon home = createImage("floor/floor2.png", 8, 8);
        ImageIcon notHome = createImage("floor/grass.png", 8, 8);

        for (int i = 0; i < (world.getWidth() * world.getLength()); i++) {
            JLabel label = new JLabel();
            Point houseLoc = currentSim.getSimLoc().getHouse().getHouseLoc();
            if (i % world.getWidth() == houseLoc.getX() && i / world.getLength() == houseLoc.getY()) {
                label.setIcon(home);
            } else {
                label.setIcon(notHome);
            }
            label.setOpaque(true);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            final Point _houseLoc = houseLoc;
            final JFrame _frame = frame;
            final int _i = i;
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!world.isWorldAvail(new Point(_i % world.getWidth(), _i / world.getLength()))) {
                        frame.setVisible(false);
                        showRoomFrame(_houseLoc, "Ruang Utama");
                    } else {
                        showMessagePopup(_frame, "Belum ada rumah di (" + _i % world.getWidth() + ", " + _i / world.getLength() + ").", "World Map");
                    }
                    System.out.println("X: " + _i % world.getWidth() + ", Y: " + _i / world.getLength());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ToolTipManager.sharedInstance().setInitialDelay(0);
                    ToolTipManager.sharedInstance().setDismissDelay(60 * 1000);
                }
            });
            label.setToolTipText("(" + i % world.getWidth() + ", " + i / world.getLength() + ")");
            panel.add(label);
        }
        return panel;
    }

    public void showWorldFrame() {
        JFrame frame = new JFrame("World Map");
        setupFrame(frame);

        JLabel background = createLabelImage("background.png", 1280, 720);
        background.setBounds(0, 0, 1280, 720);

        JPanel panelWorld = createWorld(frame);
        panelWorld.setBounds(200, 16, panelWorld.getPreferredSize().width, panelWorld.getPreferredSize().height);
        JPanel panelMenu = createMenu(frame);
        panelMenu.setBounds(908, 20, panelMenu.getPreferredSize().width, panelMenu.getPreferredSize().height);
        JButton button = createBackButton(frame, this::showMainFrame);

        addToFrame(frame, new Component[]{button, panelWorld, panelMenu, background});

        resetupFrame(frame);
    }

    public void showMessagePopup(JFrame frame, String text, String title) {
        JLabel label = new JLabel("<html>" + text + "</html>");
        label.setFont(font16);
        JOptionPane.showMessageDialog(frame, label, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public String showInputPopup(JFrame frame, String text, String title) {
        JLabel label = new JLabel("<html>" + text + "</html>");
        label.setFont(font16);
        return JOptionPane.showInputDialog(frame, label, title, JOptionPane.QUESTION_MESSAGE);
    }

    public ImageIcon resizeImage(ImageIcon currentIcon, int width, int height) {
        return new ImageIcon(currentIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }
}
