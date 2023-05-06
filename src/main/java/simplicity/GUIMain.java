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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class GUIMain extends Main {
    private final static String basePath = "src/main/resources";
    private final static String imagePath = basePath + "/images";
    private final static String fontPath = basePath + "/fonts";

    public GUIMain() {
    }

    public void start() {
        importFont();
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
            JOptionPane.showMessageDialog(null, exception.getMessage());
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
            String simName = JOptionPane.showInputDialog(frame, "Masukkan nama SIM:", "Create SIM", JOptionPane.QUESTION_MESSAGE);
            if (simName != null) {
                while (equals(simName.trim(), "")) {
                    simName = JOptionPane.showInputDialog(frame, "Masukkan nama SIM:", "Create SIM", JOptionPane.QUESTION_MESSAGE);
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
        ImageIcon icon = resizeImage(new ImageIcon(img), width, height);
        return icon;
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
        return createButton(text, callback, "#a6e329");
    }

    public JButton createButton(String text, ActionListener callback, String colorCode) {
        JButton button = new RoundedButton(text, 40, Color.decode(colorCode));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(callback);
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
        JButton button = createButton("Back", e -> {
            frame.setVisible(false);
            showMainFrame();
        }, "#808080");
        button.setBounds(40, 40, button.getPreferredSize().width, button.getPreferredSize().height);

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
                        JOptionPane.showMessageDialog(_frame, "Belum ada rumah di (" + _i % world.getWidth() + ", " + _i / world.getLength() + ").");
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

        JPanel panel = createWorld(frame);
        panel.setBounds(80, 16, panel.getPreferredSize().width, panel.getPreferredSize().height);
        //        frame.add(createSimInfo());
        JButton button = createButton("Back", e -> {
            frame.setVisible(false);
            showMainFrame();
        });

        addToFrame(frame, new Component[]{button, panel, background});

        resetupFrame(frame);
    }

    public ImageIcon resizeImage(ImageIcon currentIcon, int width, int height) {
        return new ImageIcon(currentIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }
}
