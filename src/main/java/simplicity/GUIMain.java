package simplicity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GUIMain extends Main {
    private final static String basePath = "src/main/resources";
    private final static String imagePath = basePath + "/images";
    private final static String fontPath = basePath + "/fonts";

    public GUIMain() {
        super();
    }

    public void start() {
        importFont();
        showMainFrame();
    }

    public void importFont() {
        final GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
        try {
            final File fontFile = new File(fontPath, "SimsLLHP.ttf");
            if (fontFile.exists()) {
                Font FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                if (!AVAILABLE_FONT_FAMILY_NAMES.contains(FONT.getFontName())) {
                    GE.registerFont(FONT);
                }
            }
        } catch (FontFormatException | IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    public void showMainFrame() {
        JFrame frame = new JFrame("SimPlicity - Start Screen");
        frame.setLayout(new FlowLayout());
        frame.setSize(1280, 720);
        frame.setResizable(false);

        BufferedImage img;
        try {
            img = ImageIO.read(new File(imagePath, "startScreen.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icon = resizeImage(new ImageIcon(img), frame.getWidth(), frame.getHeight());

        JLabel label0 = new JLabel();
        label0.setIcon(icon);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(createButton("Start Game", e -> {
            String simName = JOptionPane.showInputDialog(frame, "Masukkan nama SIM:", "Create SIM", JOptionPane.QUESTION_MESSAGE);
            if (simName != null) {
                while (equals(simName.trim(), "")) {
                    simName = JOptionPane.showInputDialog(frame, "Masukkan nama SIM:", "Create SIM", JOptionPane.QUESTION_MESSAGE);
                    if (simName == null) break;
                }
                if (simName != null) {
                    currentSim = new Sim(simName);
                    frame.setVisible(false);
                    showWorldFrame();
                }
            }
        }));
        panel.add(createButton("About", e -> {
            frame.setVisible(false);
            showAboutFrame();
        }));

        frame.add(label0);
        frame.add(panel);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
        label.setFont(new Font("SimLLHP", Font.PLAIN, fontSize));
        return label;
    }

    public JButton createButton(String text, ActionListener callback) {
        JButton button = new JButton(text);
        button.addActionListener(callback);
        button.setFont(new Font("SimLLHP", Font.PLAIN, 20));
        return button;
    }

    public void showAboutFrame() {
        JFrame frame = new JFrame("SimPlicity - About");
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(createLabel("IF2212 Pemrograman Berorientasi Objek STI", Component.CENTER_ALIGNMENT));
        panel.add(createLabel(" "));
        panel.add(createLabel("Kelompok 8", Component.CENTER_ALIGNMENT));
        panel.add(createLabel("1. Michael Ryan Martin (18219046)", Component.CENTER_ALIGNMENT));
        panel.add(createLabel("2. Syafiq Ziyadul Arifin (18221048)", Component.CENTER_ALIGNMENT));
        panel.add(createLabel("3. Nixon Deflin Kurniawan (18221150)", Component.CENTER_ALIGNMENT));
        panel.add(createLabel("4. Fredrick Runie Taslim (18221156)", Component.CENTER_ALIGNMENT));
        panel.add(createLabel("5. Rania Sasi Kirana (18221168)", Component.CENTER_ALIGNMENT));

        frame.add(panel);
        frame.add(createButton("Back", e -> {
            frame.setVisible(false);
            showMainFrame();
        }));

        frame.pack();
        frame.setSize(720, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JPanel createRoom() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(Room.getRoomWidth(), Room.getRoomLength()));

        for (int i = 0; i < (Room.getRoomWidth() * Room.getRoomLength()); i++) {
            NonFood barang = currentSim.getSimLoc().getRoom().getMatrixBarang()[i / 6][i % 6];
            //            JLabel label = createLabel("      ", 90);
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
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);

        JPanel panel = new JPanel();
        String[] filteredActionNames = Action.getListAction().stream()
                .map(Action::getActionName)
                .filter(name -> !name.contains("Not"))
                .toArray(String[]::new);
        JComboBox<String> cb = new JComboBox<>(filteredActionNames);

        panel.add(cb);
        panel.add(createButton("Submit", e -> {
            // do action here
            System.out.println(cb.getSelectedItem());
        }));

        frame.add(createRoom());
        frame.add(createSimInfo());
        frame.add(createRoomInfo());
        frame.add(createButton("Back", e -> {
            frame.setVisible(false);
            showWorldFrame();
        }));
        frame.add(panel);

        frame.pack();
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JPanel createWorld(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(world.getWorldWidth(), world.getWorldLength()));

        for (int i = 0; i < (world.getWorldWidth() * world.getWorldLength()); i++) {
            JLabel label;
            Point houseLoc = currentSim.getSimLoc().getHouse().getHouseLoc();
            label = createLabel("       ", 6);
            if (i % world.getWorldWidth() == houseLoc.getX() && i / world.getWorldLength() == houseLoc.getY()) {
                label.setBackground(new Color(166, 227, 41));
            } else {
                label.setBackground(new Color(255, 255, 255));
            }
            label.setOpaque(true);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            final Point _houseLoc = houseLoc;
            final JFrame _frame = frame;
            final int _i = i;
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!world.isWorldAvail(new Point(_i % world.getWorldWidth(), _i / world.getWorldLength()))) {
                        frame.setVisible(false);
                        showRoomFrame(_houseLoc, "Ruang Utama");
                    } else {
                        JOptionPane.showMessageDialog(_frame, "Belum ada rumah di (" + _i % world.getWorldWidth() + ", " + _i / world.getWorldLength() + ").");
                    }
                    System.out.println("X: " + _i % world.getWorldWidth() + ", Y: " + _i / world.getWorldLength());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ToolTipManager.sharedInstance().setInitialDelay(0);
                    ToolTipManager.sharedInstance().setDismissDelay(60 * 1000);
                }
            });
            label.setToolTipText("(" + i % world.getWorldWidth() + ", " + i / world.getWorldLength() + ")");
            panel.add(label);
        }
        return panel;
    }

    public void showWorldFrame() {
        JFrame frame = new JFrame("World Map");
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);

        frame.add(createWorld(frame));
        frame.add(createSimInfo());
        frame.add(createButton("Back", e -> {
            frame.setVisible(false);
            showMainFrame();
        }));

        frame.pack();
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public ImageIcon resizeImage(ImageIcon currentIcon, int width, int height) {
        int nw = currentIcon.getIconWidth();
        int nh = currentIcon.getIconHeight();

        if (currentIcon.getIconWidth() > width) {
            nw = width;
            nh = (nw * currentIcon.getIconHeight()) / currentIcon.getIconWidth();
        }
        if (nh > height) {
            nh = height;
            nw = (currentIcon.getIconWidth() * nh) / currentIcon.getIconHeight();
        }

        return new ImageIcon(currentIcon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
}
