package com.amboiko;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class InfoPanel extends JFrame {
    public InfoPanel() {
        super("com.amboiko.InfoPanel");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 0));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        URL imageUrl = getClass().getClassLoader().getResource("images/bull.png");
        URL textUrl = getClass().getClassLoader().getResource("text.txt");
        JPanel insideImageLabel = getBorderedLayout();
        JPanel insideTextPanel = getBorderedLayout();

        String insideText = getText(textUrl.getPath().substring(textUrl.getPath().lastIndexOf("/") + 1));

        JLabel bullLabel = new JLabel(new ImageIcon(imageUrl));
        JLabel bullTextLabel = new JLabel(imageUrl.getPath(), SwingConstants.CENTER);


        JLabel insideTextLabel = new JLabel(insideText);
        JLabel insideTextPathLabel = new JLabel(textUrl.getPath(), SwingConstants.CENTER);


        // BULL PANEL
        insideImageLabel.add(bullLabel);
        insideImageLabel.add(bullTextLabel);

        // INSIDE TEXT PANEL
        insideTextPanel.add(insideTextLabel);
        insideTextPanel.add(insideTextPathLabel);

        panel.add(insideImageLabel);
        panel.add(insideTextPanel);
        add(panel);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String getText(String path) {
        Scanner scanner = null;
        StringBuilder result = new StringBuilder();

        // JLabel does not show \n - using <html> is a trick ))
        result.append("<html>");
        try {
            scanner = getScannerFor(path);
            while (scanner.hasNext()) {
                result.append(scanner.nextLine());
                result.append("<br/>");
            }
        } catch (FileNotFoundException e) {
            result.append(e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        result.append("</html>");
        return result.toString();
    }

    private Scanner getScannerFor(String path) throws FileNotFoundException {
        return new Scanner(Objects.requireNonNull(InfoPanel.class.getClassLoader().getResourceAsStream(path)));
    }

    private JPanel getBorderedLayout() {
        JPanel panel = new JPanel(new GridLayout(2, 0));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return panel;
    }
}
