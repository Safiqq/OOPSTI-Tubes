package simplicity;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
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
    private final Font font16;
    private final Font font20;
    private boolean isActionFrameActive;

    public GUIMain() {
        importFont();
        isActionFrameActive = false;
        font16 = new Font("The Sims Sans", Font.PLAIN, 16);
        font20 = new Font("The Sims Sans", Font.PLAIN, 20);
        time = new Time();
    }

    public void start() {
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void resetupFrame(JFrame frame) {
        resetupFrame(frame, 1280, 720);
    }

    public void resetupFrame(JFrame frame, int width, int height) {
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
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

        JLabel label = createLabelImage("startscreen.png", 1280, 720);
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

    public ImageIcon createRotatedImage(String path, int width, int height, int degrees) {
        BufferedImage img;
        try {
            img = ImageIO.read(new File(imagePath, path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icon = rotateImage(new ImageIcon(img), degrees);
        return resizeImage(icon, width, height);
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
        JButton backButton = createBackButton(frame, this::showMainFrame);

        addToFrame(frame, new Component[]{label1, label2, label3, label4, label5, label6, label7, label8, backButton, background});

        resetupFrame(frame);
    }

    public JPanel createRoom() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        ImageIcon karakter = createImage("sim/sim1.png", 108, 108);
        ImageIcon lantai = createImage("floor/floor2.png", 108, 108);
        ImageIcon cermin = createImage("nonfood/cermin.png", 108, 108);
        ImageIcon jam = createImage("nonfood/jam.png", 108, 108);
        ImageIcon kasurking = createRotatedImage("nonfood/kasurking.png", 5 * 108, 2 * 108, 90);
        ImageIcon kasurqueen = createRotatedImage("nonfood/kasurqueen.png", 4 * 108, 2 * 108, 90);
        ImageIcon kasursingle = createRotatedImage("nonfood/kasursingle.png", 4 * 108, 108, 90);
        ImageIcon komporgas = createImage("nonfood/komporgas.png", 2 * 108, 108);
        ImageIcon komporlistrik = createImage("nonfood/komporlistrik.png", 108, 108);
        ImageIcon mejakursi = createImage("nonfood/mejakursi.png", 3 * 108, 3 * 108);
        ImageIcon pintu = createImage("nonfood/pintu.png", 108, 108);
        ImageIcon toilet = createImage("nonfood/toilet.png", 108, 108);
        ImageIcon wastafel = createImage("nonfood/wastafel.png", 108, 108);

        JLabel sim = new JLabel();
        sim.setIcon(karakter);
        sim.setBounds(currentSim.getSimLoc().getPoint().getX() * 108, currentSim.getSimLoc().getPoint().getY() * 108, 108, 108);
        sim.setOpaque(true);

        sim.setToolTipText(currentSim.getFullName());
        panel.add(sim);

        for (int i = 0; i < 6 * 6; i++) {
            NonFood barang = currentSim.getSimLoc().getRoom().getMatrixBarang()[i / 6][i % 6];
            if (barang != null) {
                if (barang.getStartPoint().getX() == i % 6 && barang.getStartPoint().getY() == i / 6) {
                    JLabel objek = new JLabel();
                    if (equals(barang.getObjekName(), "Kasur Single")) {
                        objek.setIcon(kasursingle);
                    } else if (equals(barang.getObjekName(), "Kasur Queen Size")) {
                        objek.setIcon(kasurqueen);
                    } else if (equals(barang.getObjekName(), "Kasur King Size")) {
                        objek.setIcon(kasurking);
                    } else if (equals(barang.getObjekName(), "Toilet")) {
                        objek.setIcon(toilet);
                        objek.setBounds((i % 6) * 108, (i / 6) * 108, 108, 108);
                    } else if (equals(barang.getObjekName(), "Kompor Gas")) {
                        objek.setIcon(komporgas);
                    } else if (equals(barang.getObjekName(), "Kompor Listrik")) {
                        objek.setIcon(komporlistrik);
                    } else if (equals(barang.getObjekName(), "Meja dan Kursi")) {
                        objek.setIcon(mejakursi);
                    } else if (equals(barang.getObjekName(), "Jam")) {
                        objek.setIcon(jam);
                    } else if (equals(barang.getObjekName(), "Wastafel")) {
                        objek.setIcon(wastafel);
                    } else if (equals(barang.getObjekName(), "Cermin")) {
                        objek.setIcon(cermin);
                    }
                    objek.setBounds((i % 6) * 108, (i / 6) * 108, objek.getPreferredSize().width, objek.getPreferredSize().height);
                    objek.setOpaque(true);

                    objek.setToolTipText(barang.getObjekName() + " (" + i % 6 + ", " + i / 6 + ")");
                    panel.add(objek);
                }
            } else {
                JLabel tileBackground = new JLabel();
                tileBackground.setIcon(lantai);
                tileBackground.setBounds((i % 6) * 108, (i / 6) * 108, tileBackground.getPreferredSize().width, tileBackground.getPreferredSize().height);
                tileBackground.setOpaque(true);

                tileBackground.setToolTipText("(" + i % 6 + ", " + i / 6 + ")");
                panel.add(tileBackground);
            }
        }

        panel.setPreferredSize(new Dimension(648, 648));
        return panel;
    }

    public void showRoomFrame(Point houseLoc, String roomName) {
        JFrame frame = new JFrame("SimPlicity - House (" + houseLoc.getX() + ", " + houseLoc.getY() + ") - " + roomName);
        setupFrame(frame);
        frame.setResizable(true);

        JLabel background = createLabelImage("background.png", 1280, 720);
        background.setBounds(0, 0, 1280, 720);

        JPanel panelRoom = createRoom();
        panelRoom.setBounds(200, 16, panelRoom.getPreferredSize().width, panelRoom.getPreferredSize().height);
        JPanel panelMenu = createMenu(frame);
        panelMenu.setBounds(908, 20, panelMenu.getPreferredSize().width, panelMenu.getPreferredSize().height);
        JButton backButton = createBackButton(frame, this::showWorldFrame);

        addToFrame(frame, new Component[]{backButton, panelRoom, panelMenu, background});

        resetupFrame(frame);
    }

    public void showHelpFrame() {
        JFrame frame = new JFrame("SimPlicity - Help");
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
        JButton backButton = createBackButton(frame, this::showWorldFrame);
        addToFrame(frame, new Component[]{label, backButton, background});

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
            upgradeHouse(frame);
        }, simsColorCode);
        JButton button6 = createCallbackButton("MOVE ROOM", () -> {
            moveRoom(frame);
        }, simsColorCode);
        JButton button7 = createCallbackButton("EDIT ROOM", () -> {
            while (true) {
                String ans = showInputPopup(frame, "Apakah Anda ingin membeli barang baru atau memindahkan barang? (Beli/Pindah): ", "Edit Room");
                if (equals(ans, "BELI")) {
                    buyItem(frame);
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
                    showMessagePopup(frame, "Perintah tidak valid.", "Failed");
                }
            }
        }, simsColorCode);
        JButton button8 = createCallbackButton("ADD SIM", () -> {
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
        JButton button9 = createCallbackButton("CHANGE SIM", () -> {
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
                        showMessagePopup(frame, "Nama Sim sama dengan yang sedang Anda mainkan.<br>Masukkan nama Sim yang berbeda!", "Failed");
                    } else {
                        break;
                    }
                }
                currentSim = newSim;
                showMessagePopup(frame, "Sim berhasil diganti. Selamat bermain!", "Succeed");
            }
        }, simsColorCode);
        JButton button10 = createCallbackButton("LIST OBJECT", () -> {
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
        JButton button11 = createCallbackButton("GO TO OBJECT", () -> {
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
        JButton button12 = createCallbackButton("CHANGE JOB", () -> {
            if (currentSim.getTotalWorkTime() >= 720) {
                Occupation occupation = currentSim.getOccupation();

                // Change job
                String text = "Daftar pekerjaan yang tersedia: ";
                List<String> keys = Occupation.getKeys();
                for (int i = 0; i < keys.size(); i++) {
                    text += "<br>" + (i + 1) + ". " + keys.get(i);
                }

                String oldJobName = occupation.getJobName();
                int oldDailySalary = occupation.getDailySalary();
                String jobName;
                do {
                    text += "<br><br>Masukkan nama pekerjaan baru: ";
                    jobName = showInputPopup(frame, text, "Occupation Name");
                    if (equals(jobName, oldJobName)) {
                        showMessagePopup(frame, "Pekerjaan baru sama dengan pekerjaan yang lama.", "Failed");
                    }
                } while (equals(jobName, oldJobName));
                occupation.setJobName(jobName);
                occupation.setDailySalary(Occupation.getListJob().get(jobName));

                // Harus bayar 1/2 dari gaji harian pekerjaan baru
                int payChangeJob = (int) (0.5 * occupation.getDailySalary());
                if (currentSim.getMoney() < payChangeJob) {
                    showMessagePopup(frame, "Uang tidak mencukupi untuk pindah pekerjaan.", "Failed");
                    occupation.setJobName(oldJobName);
                    occupation.setDailySalary(oldDailySalary);
                } else {
                    currentSim.setMoney(currentSim.getMoney() - payChangeJob);
                    currentSim.setTotalWorkTime(0);
                }
                currentSim.setDayChangeJob(time.getDay());
            } else {
                showMessagePopup(frame, "Sim hanya dapat mengganti pekerjaan jika sudah bekerja setidaknya 12 menit.", "Failed");
            }
        }, simsColorCode);
        JButton button13 = createCallbackButton("ACTION", () -> {
            if (!isActionFrameActive) {
                isActionFrameActive = true;
                showActionFrame();
            }

        }, simsColorCode);

        addToPanel(panel, new Component[]{button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13});

        return panel;
    }

    public void showActionFrame() {
        JFrame frame = new JFrame("SimPlicity - Action");
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                isActionFrameActive = false;
            }
        });

        JLabel label = createLabel("Pilih aksi:");
        label.setFont(font16);
        label.setForeground(Color.black);
        label.setBounds((256 - label.getPreferredSize().width) / 2, 72, label.getPreferredSize().width, label.getPreferredSize().height);

        String[] filteredActionNames = NonFood.get(currentSim.getObjLoc()).getListAction().stream()
                .map(Action::getActionName)
                .filter(name -> !name.contains("Not"))
                .toArray(String[]::new);
        JComboBox<String> comboBox = new JComboBox<>(filteredActionNames);
        comboBox.setFont(font16);
        comboBox.setBounds((256 - comboBox.getPreferredSize().width) / 2, 96, comboBox.getPreferredSize().width, comboBox.getPreferredSize().height);

        JButton submitButton = createCallbackButton("Submit", () -> {
            String act = (String) comboBox.getSelectedItem();
            System.out.println(0);
            if (equals(act, "WORK")) {
                System.out.println(1);
                // pekerjaan baru hanya dapat dikerjakan 1 hari setelah hari penggantian pekerjaan
                if (time.getDay() > currentSim.getDayChangeJob()) {
                    System.out.println(2);
                    // validasi waktu kerja kelipatan 120
                    int simWorkTime = validateTime(frame, "kerja", 120);
                    // cek waktu sisa dalam hari
                    int remainingSeconds = time.getMinute() * 60 + time.getSecond();
                    // jika sim kerja lebih dari sisa waktu, maka sisa waktu dihitung kerja esok
                    // harinya
                    if (simWorkTime > remainingSeconds) {
                        currentSim.setStoreWorkTime(simWorkTime - remainingSeconds);
                        currentSim.setWorkTime(currentSim.getWorkTime() + remainingSeconds);
                    } else {
                        currentSim.setWorkTime(currentSim.getWorkTime() + simWorkTime);
                    }

                    // menjalankan pekerjaan
                    currentSim.addStatus("Work", simWorkTime);
                    time.sleepMain(currentSim, simWorkTime);

                    currentSim.setTotalWorkTime(currentSim.getTotalWorkTime() + simWorkTime);

                    // tambah waktu berkunjung jika Sim di rumah orang
                    currentSim.visitingEffect(simWorkTime);

                } else {
                    showMessagePopup(frame, "Pekerjaan baru hanya dapat dikerjakan 1 hari setelah hari penggantian pekerjaan.", "Failed");
                }

            } else if (equals(act, "EXERCISE")) {
                // validasi waktu olahraga kelipatan 20
                int simExerciseTime = validateTime(frame, "olahraga", 20);

                currentSim.addStatus("Exercise", simExerciseTime);
                time.sleepMain(currentSim, simExerciseTime);

                // tambah waktu berkunjung jika Sim di rumah orang
                currentSim.visitingEffect(simExerciseTime);

            } else if (equals(act, "SLEEP")) {
                if (currentSim.getObjLoc().contains("Kasur")) {
                    int simSleepTime = validateTime(frame, "tidur", 4 * 60);

                    currentSim.deleteStatus("Not Sleep");
                    currentSim.addStatus("Sleep", simSleepTime);
                    time.sleepMain(currentSim, simSleepTime);
                    currentSim.addStatus("Not Sleep", 10 * 60);

                    // tambah waktu berkunjung jika Sim di rumah orang
                    currentSim.visitingEffect(simSleepTime);

                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di kasur.<br>Silakan menjalankan menu Go to Object ke kasur untuk menjalankan aksi ini.", "Failed");
                }

            } else if (equals(act, "EAT")) {
                if (equals(currentSim.getObjLoc(), "Meja dan kursi")) {
                    // efek makan
                    // Menampilkan makanan yang ada di inventory
                    Box<Food> boxFood = currentSim.getInventory().getBoxFood();

                    String text = "========Berikut merupakan makanan yang Anda punya========";
                    if (boxFood.getLength() == 0) {
                        text += "<br>Sim " + currentSim.getFullName() + " tidak memiliki objek makanan dalam inventory.";
                    } else {
                        int i = 0;
                        for (Map.Entry<String, Integer> entry : boxFood.getMap().entrySet()) {
                            text += "<br>" + (++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue();
                        }
                    }
                    text += "<br><br>Mau makan apa? : ";
                    String namaMakanan = showInputPopup(frame, text, "Food Number");
                    if (Food.get(namaMakanan) != null) {
                        if (currentSim.checkFood(namaMakanan)) {
                            Food food = currentSim.getInventory().getBoxFood().get(namaMakanan);
                            showMessagePopup(frame, "Sedang Memakan " + food.getObjekName() + "<br>.......Please wait.......", "Succeed");
                            Action.get("Eat").getListEffect().get(0).setMotiveEffect(food.getFoodHunger());
                            currentSim.addStatus("Eat", 30);
                            time.sleepMain(currentSim, 30);

                            showMessagePopup(frame, "Anda selesai makan!", "Succeed");
                            currentSim.getInventory().getBoxFood().delete(food);
                            currentSim.addStatus("Not Pee", 4 * 60);
                            currentSim.addStatus("Not Wash Hand", 3 * 60);
                        } else {
                            showMessagePopup(frame, "Anda tidak memiliki makanan " + namaMakanan, "Failed");
                        }
                    } else {
                        showMessagePopup(frame, "Tidak ada makanan dengan nama " + namaMakanan, "Failed");
                    }
                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di meja dan kursi.<br>Silakan menjalankan menu Go to Object ke meja dan kursi untuk menjalankan aksi ini.", "Failed");
                }

            } else if (equals(act, "COOK")) {
                if (currentSim.getObjLoc().contains("Kompor")) {
                    String text = "Makanan yang tersedia:";
                    int i = 0;
                    for (Food food : Food.getListFood()) {
                        text += "<br>" + (++i) + ". " + food.getObjekName() + " (Kekenyangan: " + food.getFoodHunger() + ", Bahan: ";
                        int groceriesCount = food.getListGroceries().size();
                        for (int j = 0; j < groceriesCount; j++) {
                            text += food.getObjekName();
                            if (j != groceriesCount - 1) {
                                text += ", ";
                            } else {
                                text += ")";
                            }
                        }
                    }
                    // efek memasak
                    text += "<br><br>Masukkan nomor masakan yang ingin dibuat: ";
                    int cooknumber = Integer.parseInt(showInputPopup(frame, text, "Food Number"));

                    Food food = null;
                    if (cooknumber == 1 && currentSim.checkGroceries("Nasi") && currentSim.checkGroceries("Ayam")) {
                        food = new Food("Nasi Ayam");
                    } else if (cooknumber == 2 && currentSim.checkGroceries("Nasi") && currentSim.checkGroceries("Kentang") && currentSim.checkGroceries("Wortel") && currentSim.checkGroceries("Sapi")) {
                        food = new Food("Nasi Kari");
                    } else if (cooknumber == 3 && currentSim.checkGroceries("Susu") && currentSim.checkGroceries("Kacang")) {
                        food = new Food("Susu Kacang");
                    } else if (cooknumber == 4 && currentSim.checkGroceries("Wortel") && currentSim.checkGroceries("Bayam")) {
                        food = new Food("Tumis Sayur");
                    } else if (cooknumber == 5 && currentSim.checkGroceries("Kentang") && currentSim.checkGroceries("Sapi")) {
                        food = new Food("Bistik");
                    } else {
                        if (cooknumber < 1 || cooknumber > 5) {
                            showMessagePopup(frame, "Masukkan nomor yang sesuai dong!", "Failed");
                        } else {
                            showMessagePopup(frame, "Bahan makananmu kurang :(", "Failed");
                        }
                    }

                    if (food != null) {
                        int cookTime = food.getFoodHunger() * 3 / 2;
                        Action.get("Cook").getListEffect().get(0).setCooldown(cookTime);
                        currentSim.addStatus("Cook", cookTime);
                        time.sleepMain(currentSim, cookTime);

                        // tambah waktu berkunjung jika Sim di rumah orang
                        currentSim.visitingEffect(cookTime);

                        for (Groceries groceries : food.getListGroceries()) {
                            currentSim.deleteGroceriesFromInventory(groceries.getObjekName());
                        }
                        currentSim.getInventory().getBoxFood().add(food);
                        showMessagePopup(frame, "Berhasil memasak.", "Succeed");
                    }
                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di kompor gas atau kompor listrik.<br>Silakan menjalankan menu Go to Object ke kompor gas atau kompor listrik untuk menjalankan aksi ini.", "Failed");
                }

            } else if (equals(act, "VISIT")) {
                if (Sim.getListSim().size() == 1) {
                    showMessagePopup(frame, "Tidak ada rumah Sim lain yang tersedia.<br>Rumah Sim " + currentSim.getFullName() + " adalah rumah satu-satunya di World.", "Failed");
                } else {
                    // efek berkunjung dari rumah sebelumnya
                    currentSim.visit(currentSim.getVisitTime());
                    currentSim.setVisitTime(0);

                    Point houseLoc;
                    String ownerHouse;
                    while (true) {
                        String text = "Daftar Sim:";
                        int i = 0;
                        for (Sim sim : Sim.getListSim()) {
                            text += "<br>" + (++i) + ". " + sim.getFullName();
                        }
                        text += "<br><br>Masukkan nama pemilik rumah yang ingin dikunjungi: ";
                        ownerHouse = showInputPopup(frame, text, "House Owner");
                        houseLoc = world.searchHouse(ownerHouse);
                        if (houseLoc == null) {
                            showMessagePopup(frame, "Tidak ada rumah yang dimiliki oleh " + ownerHouse + ".", "Failed");
                        } else {
                            if (houseLoc.getX() == currentSim.getSimLoc().getHouse().getHouseLoc().getX() && houseLoc.getY() == currentSim.getSimLoc().getHouse().getHouseLoc().getY()) {
                                showMessagePopup(frame, "Rumah yang ingin dituju Sim sama dengan rumah lokasi Sim tengah berada.", "Failed");
                            } else {
                                break;
                            }
                        }
                    }

                    showMessagePopup(frame, "Mengunjungi rumah " + ownerHouse + "...<br>Lokasi rumah di World: ( " + houseLoc.getX() + ", " + houseLoc.getY() + ")", "Succeed");

                    // waktu yang diperlukan untuk berkunjung ke rumah
                    // x1, y1 titik rumah tempat sim berada, x2, y2 titik rumah yang ingin dikunjungi
                    double x = Math.pow(houseLoc.getX() - currentSim.getSimLoc().getHouse().getHouseLoc().getX(), 2);
                    double y = Math.pow(houseLoc.getY() - currentSim.getSimLoc().getHouse().getHouseLoc().getY(), 2);
                    int walkTime = (int) Math.sqrt(x + y);

                    currentSim.addStatus("Visit", walkTime);
                    time.sleepMain(currentSim, walkTime);
                    int apply = walkTime / Action.get("Visit").getListEffect().get(0).getCooldown();
                    for (int i = 0; i < apply; i++) {
                        currentSim.applyEffect("Visit");
                    }

                    // lokasi Sim baru di Ruang Utama point 3,3
                    House visited = world.findHouse(houseLoc);
                    currentSim.getSimLoc().setHouse(visited);
                    currentSim.getSimLoc().setRoom(visited.get("Ruang Utama"));
                    currentSim.getSimLoc().getPoint().setX(3);
                    currentSim.getSimLoc().getPoint().setY(3);

                    // efek tidak berlaku untuk rumah Sim sendiri
                    if (equals(ownerHouse, currentSim.getFullName())) {
                        showMessagePopup(frame, "Sim " + currentSim.getFullName() + " sudah berada di rumahnya sendiri.<br>Efek berkunjung tidak berlaku untuk rumah Sim sendiri", "Failed");
                    } else {
                        showMessagePopup(frame, "Sim " + currentSim.getFullName() + " sudah sampai di rumah " + visited.getOwner() + ".", "Succeed");
                    }
                }

            } else if (equals(act, "PEE")) {
                if (equals(currentSim.getObjLoc(), "Toilet")) {
                    // sim minimal buang air 1 kali tiap habis makan
                    // efek tidak buang air: -5 kesehatan dan -5 mood 4 menit setelah makan tanpa buang air

                    currentSim.deleteStatus("Not Pee");
                    currentSim.addStatus("Pee", 10);
                    time.sleepMain(currentSim, 10);

                    // tambah waktu berkunjung jika Sim di rumah orang
                    currentSim.visitingEffect(10);

                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di toilet.<br>Silakan menjalankan menu Go to Object ke toilet untuk menjalankan aksi ini.", "Failed");
                }

            } else if (equals(act, "UPGRADE HOUSE")) {
                if (equals(currentSim.getSimLoc().getHouse().getOwner(), currentSim.getFullName())) {
                    upgradeHouse(frame);
                } else {
                    showMessagePopup(frame, "Sim tidak sedang berada di rumah sendiri.<br>Silakan kembali ke rumah sendiri untuk melakukan upgrade House!", "Failed");
                }

            } else if (equals(act, "BUY ITEM")) {
                buyItem(frame);

            } else if (equals(act, "MOVE ROOM")) {
                moveRoom(frame);

            } else if (equals(act, "VIEW INVENTORY")) {
                String text = time.getTime();
                text += "<br><br>Berikut merupakan inventory yang dimiliki oleh Sim " + currentSim.getFullName();

                /* Objek NonMakanan */
                text += "<br><br>========Objek Non-Makanan========";
                if (currentSim.getInventory().getBoxNonFood().getLength() == 0) {
                    text += "<br>Sim " + currentSim.getFullName() + " tidak memiliki objek non-makanan dalam inventory";
                } else {
                    int i = 0;
                    for (Map.Entry<String, Integer> entry : currentSim.getInventory().getBoxNonFood().getMap().entrySet()) {
                        text += "<br>" + (++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue();
                    }
                }

                /* Objek Bahan Makanan */
                text += "<br><br>========Objek Bahan Makanan========";
                if (currentSim.getInventory().getBoxGroceries().getLength() == 0) {
                    text += "<br>Sim " + currentSim.getFullName() + " tidak memiliki objek bahan makanan dalam inventory";
                } else {
                    int i = 0;
                    for (Map.Entry<String, Integer> entry : currentSim.getInventory().getBoxGroceries().getMap().entrySet()) {
                        text += "<br>" + (++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue();
                    }
                }

                /* Objek Makanan */
                text += "<br><br>========Objek Makanan========";
                if (currentSim.getInventory().getBoxFood().getLength() == 0) {
                    text += "<br>Sim " + currentSim.getFullName() + " tidak memiliki objek makanan dalam inventory";
                } else {
                    int i = 0;
                    for (Map.Entry<String, Integer> entry : currentSim.getInventory().getBoxFood().getMap().entrySet()) {
                        text += "<br>" + (++i) + ". Objek: " + entry.getKey() + ", Jumlah: " + entry.getValue();
                    }
                }

                showMessagePopup(frame, text, "View Inventory");

            } else if (equals(act, "INSTALL ITEM")) {
                Room room = currentSim.getSimLoc().getRoom();
                String text = "Objek yang ada di ruang " + room.getRoomName() + ":";
                int i = 0;
                for (NonFood objek : room.getListObjek()) {
                    text += "<br>" + (++i) + ". " + objek.getObjekName() + " [(" + objek.getStartPoint().getX() + ", " + objek.getStartPoint().getY() + ") - (" + objek.getEndPoint().getX() + ", " + objek.getEndPoint().getY() + ")]";
                }

                if (equals(currentSim.getFullName(), currentSim.getSimLoc().getHouse().getOwner())) {
                    Box<NonFood> boxNonFood = currentSim.getInventory().getBoxNonFood();
                    if (boxNonFood.getList().size() > 0) {
                        text += "<br><br>List barang di inventory Anda:";
                        i = 0;
                        for (NonFood barang : boxNonFood.getList()) {
                            text += "<br>" + (++i) + ". Objek: " + barang.getObjekName() + ", Jumlah: " + boxNonFood.getCount(barang.getObjekName());
                        }

                        // Looping input barang
                        boolean barangValid = false;
                        while (!barangValid) {
                            text += "<br><br>Masukkan nomor barang yang ingin dipasang: ";
                            int numBarang = Integer.parseInt(showInputPopup(frame, text, "Item Number"));

                            if (numBarang <= i && numBarang > 0) {
                                barangValid = true;
                                NonFood targetBarang = boxNonFood.getList().get(numBarang - 1);
                                int length = targetBarang.getLength();
                                int width = targetBarang.getWidth();

                                //Print list room yang ada di rumah
                                text = "List ruangan yang ada:";
                                int j = 0;
                                for (Room roomx : currentSim.getSimLoc().getHouse().getListRoom()) {
                                    text += "<br>" + (++j) + ". " + roomx.getRoomName();
                                }

                                //Looping input ruangan
                                boolean roomValid = false;
                                while (!roomValid) {
                                    text += "<br><br>Masukkan nomor ruangan: ";
                                    int numRoom = Integer.parseInt(showInputPopup(frame, text, "Room Number"));

                                    if (numRoom <= j && numRoom > 0) {
                                        roomValid = true;
                                        Room targetRoom = currentSim.getSimLoc().getHouse().getListRoom().get(numRoom - 1);

                                        //Looping input lokasi barang
                                        boolean pointValid = false;
                                        while (!pointValid) {
                                            int startX = Integer.parseInt(showInputPopup(frame, "Masukkan lokasi awal sumbu X barang: ", "Install Item - Start Point - X"));
                                            int startY = Integer.parseInt(showInputPopup(frame, "Masukkan lokasi awal sumbu Y barang: ", "Install Item - Start Point - Y"));

                                            if (startX >= 0 && startX <= 5 && startY >= 0 && startY <= 5) {
                                                int endX = startX + length - 1;
                                                int endY = startY + width - 1;

                                                if (endX <= 5 && endY <= 5) {
                                                    Point startPoint = new Point(startX, startY);
                                                    Point endPoint = new Point(endX, endY);

                                                    if (targetRoom.isSpaceEmpty(startPoint, endPoint)) {
                                                        targetBarang.setStartPoint(startPoint);
                                                        targetBarang.setEndPoint(endPoint);
                                                        targetRoom.insertBarang(targetBarang);
                                                        boxNonFood.delete(targetBarang.getObjekName());
                                                        showMessagePopup(frame, "Barang berhasil dipasang di " + targetRoom.getRoomName() + ".", "Succeed");
                                                        pointValid = true;
                                                    } else {
                                                        showMessagePopup(frame, "Sudah ada barang lain di lokasi yang dipilih.", "Failed");
                                                    }

                                                } else {
                                                    showMessagePopup(frame, "Lokasi yang dipilih kurang besar.", "Failed");
                                                }
                                            } else {
                                                showMessagePopup(frame, "Input lokasi tidak valid.", "Failed");
                                            }

                                        }
                                    } else {
                                        showMessagePopup(frame, "Nomor ruangan tidak valid", "Failed");
                                    }
                                }
                            } else {
                                showMessagePopup(frame, "Nomor barang tidak valid.", "Failed");
                            }
                        }
                    } else {
                        showMessagePopup(frame, "Anda tidak memiliki barang di inventory.", "Failed");
                    }
                } else {
                    showMessagePopup(frame, "Sim harus berada di rumah sendiri untuk memasang barang.", "Failed");
                }

            } else if (equals(act, "CHECK TIME")) {
                if (equals(currentSim.getObjLoc(), "Jam")) {
                    String text = "Waktu yang tersisa di- " + time.getTime();
                    // sisa waktu yang masih ada untuk seluruh tindakan yang bisa ditinggal
                    Set<String> keys = currentSim.getMapStatus().keySet();
                    if (keys.size() > 0) {
                        text += "<br><br>Status Sim sekarang:";
                        int i = 0;
                        for (String status : currentSim.getMapStatus().keySet()) {
                            text += "<br>" + (++i) + ". " + status + " (Waktu tersisa " + currentSim.getMapStatus().get(status) + " detik)";
                        }
                    } else {
                        text += "<br<br>Sim tidak memiliki status yang sedang aktif.";
                    }
                    showMessagePopup(frame, text, "Succeed");
                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di jam.<br>Silakan menjalankan menu Go to Object ke jam untuk menjalankan aksi ini.", "Failed");
                }

            } else if (equals(act, "CLIMB TABLE AND CHAIR")) {
                if (equals(currentSim.getObjLoc(), "Meja dan kursi")) {
                    int simClimbTime = validateTime(frame, "naik ke meja dan kursi", 15);
                    currentSim.addStatus("Climb Table and Chair", simClimbTime);
                    showMessagePopup(frame, "Sim naik ke meja dan kursi.", "Succeed");
                    time.sleepMain(currentSim, simClimbTime);

                    // tambah waktu berkunjung jika Sim di rumah orang
                    currentSim.visitingEffect(simClimbTime);

                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di meja dan kursi.<br>Silakan menjalankan menu Go to Object ke meja dan kursi untuk menjalankan aksi ini.", "Failed");
                }
            } else if (equals(act, "TURN ON STOVE")) {
                if (currentSim.getObjLoc().contains("Kompor")) {
                    showMessagePopup(frame, "Sim menyalakan kompor.", "Succeed");
                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di kompor.<br>Silakan menjalankan menu Go to Object ke kompor untuk menjalankan aksi ini.", "Failed");
                }
            } else if (equals(act, "TURN OFF STOVE")) {
                if (currentSim.getObjLoc().contains("Kompor")) {
                    showMessagePopup(frame, "Sim mematikan kompor.", "Succeed");
                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di kompor.<br>Silakan menjalankan menu Go to Object ke kompor untuk menjalankan aksi ini.", "Failed");
                }
            } else if (equals(act, "SIT")) {
                if (currentSim.getObjLoc().contains("Kasur") || equals(currentSim.getObjLoc(), "Meja dan kursi")) {
                    int simSitTime = validateTime(frame, "duduk", 10);
                    currentSim.addStatus("Sit", simSitTime);
                    showMessagePopup(frame, "Sim duduk di ." + currentSim.getObjLoc(), "Succeed");
                    time.sleepMain(currentSim, simSitTime);

                    // tambah waktu berkunjung jika Sim di rumah orang
                    currentSim.visitingEffect(simSitTime);

                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di meja dan kursi ataupun kasur.<br>Silakan menjalankan menu Go to Object ke meja dan kursi atau kasur untuk menjalankan aksi ini.", "Failed");
                }
            } else if (equals(act, "WASH HAND")) {
                if (equals(currentSim.getObjLoc(), "Wastafel")) {
                    // sim minimal cuci tangan 1 kali tiap habis makan
                    // efek tidak cuci tangan: -15 health 3 menit setelah makan tidak cuci tangan

                    int simWashTime = validateTime(frame, "cuci tangan", 10);
                    currentSim.deleteStatus("Not Wash Hand");
                    currentSim.addStatus("Wash Hand", simWashTime);
                    showMessagePopup(frame, "Sim mencuci tangan.", "Succeed");
                    time.sleepMain(currentSim, simWashTime);

                    // tambah waktu berkunjung jika Sim di rumah orang
                    currentSim.visitingEffect(simWashTime);

                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di wastafel.<br>Silakan menjalankan menu Go to Object ke wastafel untuk menjalankan aksi ini.", "Failed");
                }
            } else if (equals(act, "LOOK MIRROR")) {
                if (equals(currentSim.getObjLoc(), "Cermin")) {
                    int simMirrorTime = validateTime(frame, "bercermin", 15);
                    currentSim.addStatus("Look Mirror", simMirrorTime);
                    showMessagePopup(frame, "Sim bercermin.", "Succeed");
                    time.sleepMain(currentSim, simMirrorTime);

                    // tambah waktu berkunjung jika Sim di rumah orang
                    currentSim.visitingEffect(simMirrorTime);

                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di cermin.<br>Silakan menjalankan menu Go to Object ke cermin untuk menjalankan aksi ini.", "Failed");
                }
            } else if (equals(act, "DANCE MIRROR")) {
                if (equals(currentSim.getObjLoc(), "Cermin")) {
                    int simDanceTime = validateTime(frame, "joget", 15);
                    currentSim.addStatus("Dance Mirror", simDanceTime);
                    showMessagePopup(frame, "Now playing: Cupid - Twin Version<br>Sim joget di depan cermin!", "Succeed");
                    time.sleepMain(currentSim, simDanceTime);

                    // tambah waktu berkunjung jika Sim di rumah orang
                    currentSim.visitingEffect(simDanceTime);

                } else {
                    showMessagePopup(frame, "Sim hanya dapat melakukan aksi ini jika sedang di cermin.<br>Silakan menjalankan menu Go to Object ke cermin untuk menjalankan aksi ini.", "Failed");
                }
            }
        }, simsColorCode);
        submitButton.setBounds((256 - submitButton.getPreferredSize().width) / 2, 144, submitButton.getPreferredSize().width, submitButton.getPreferredSize().height);

        JButton backButton = createBackButton(frame, () -> {
            isActionFrameActive = false;
        });
        backButton.setBounds(10, 10, backButton.getPreferredSize().width, backButton.getPreferredSize().height);

        addToFrame(frame, new Component[]{label, comboBox, submitButton, backButton});

        resetupFrame(frame, 256, 256);
    }

    public JPanel createWorld(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(world.getWidth(), world.getLength()));

        ImageIcon home = createImage("floor/floor2.png", 8, 8);
        ImageIcon notHome = createImage("floor/grass.png", 8, 8);

        for (int i = 0; i < (world.getWidth() * world.getLength()); i++) {
            JLabel label = new JLabel();
            Point houseLoc = currentSim.getSimLoc().getHouse().getHouseLoc();
            if (!World.getWorld().isWorldAvail(new Point(i % world.getWidth(), i / world.getLength()))) {
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
                        if (currentSim.getSimLoc().getHouse().getHouseLoc().getX() == _i % world.getWidth() && currentSim.getSimLoc().getHouse().getHouseLoc().getY() == _i / world.getLength()) {
                            frame.setVisible(false);
                            showRoomFrame(_houseLoc, "Ruang Utama");
                        } else {
                            showMessagePopup(_frame, "Silakan menjalankan menu Visit untuk menjalankan aksi ini.", "Failed");
                        }
                    } else {
                        showMessagePopup(_frame, "Belum ada rumah di (" + _i % world.getWidth() + ", " + _i / world.getLength() + ").", "World Map");
                    }
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
        JFrame frame = new JFrame("SimPlicity - World");
        setupFrame(frame);

        JLabel background = createLabelImage("background.png", 1280, 720);
        background.setBounds(0, 0, 1280, 720);

        JPanel panelWorld = createWorld(frame);
        panelWorld.setBounds(200, 16, panelWorld.getPreferredSize().width, panelWorld.getPreferredSize().height);
        JPanel panelMenu = createMenu(frame);
        panelMenu.setBounds(908, 20, panelMenu.getPreferredSize().width, panelMenu.getPreferredSize().height);
        JButton backButton = createBackButton(frame, this::showMainFrame);

        addToFrame(frame, new Component[]{backButton, panelWorld, panelMenu, background});

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

    public ImageIcon rotateImage(ImageIcon icon, int degrees) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();

        BufferedImage rotated = new BufferedImage(h, w, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = rotated.createGraphics();
        g2d.rotate(Math.toRadians(degrees));
        g2d.drawImage(icon.getImage(), 0, -h, null);
        g2d.dispose();

        return new ImageIcon(rotated);
    }

    public void upgradeHouse(JFrame frame) {
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
    }

    public void buyItem(JFrame frame) {
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
    }

    public void moveRoom(JFrame frame) {
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
    }

    public int validateTime(JFrame frame, String action, int kelipatan) {
        boolean done = false;
        int time = 0;
        while (true) {
            time = Integer.parseInt(showInputPopup(frame, "Masukkan durasi " + action + " (kelipatan " + kelipatan + ", dalam detik): ", "Duration"));
            if (time % kelipatan != 0) {
                showMessagePopup(frame, "Durasi " + action + " harus kelipatan " + kelipatan, "Failed");
            } else {
                break;
            }
        }
        return time;
    }
}
