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
        JFrame frame = new JFrame();
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
            String simName = JOptionPane.showInputDialog(frame, "Masukkan nama SIM:");
            currentSim = new Sim(simName);
            frame.setVisible(false);
            showWorldFrame();
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
        JFrame frame = new JFrame();
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

    public void showRoomFrame() {

    }

    public void showWorldFrame() {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(65, 65));

        for (int i = 0; i < (65 * 65); i++) {
            JLabel label;
            Point houseLoc = currentSim.getSimLoc().getHouse().getHouseLoc();
            System.out.println("X: " + houseLoc.getX() + ", Y: " + houseLoc.getY());
            if (i == (houseLoc.getY() * 65 + houseLoc.getX())) label = createLabel("  X", 6);
            else label = createLabel("       ", 6);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            final int _i = i;
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.setVisible(false);
                    System.out.println("X: " + _i % 65 + ", Y: " + _i / 65);
                }
            });
            panel.add(label);
        }

        frame.add(panel);
        frame.add(createSimInfo());
        frame.add(createButton("Back", e -> {
            frame.setVisible(false);
            showMainFrame();
        }));

        frame.pack();
        frame.setSize(1280, 700);
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
