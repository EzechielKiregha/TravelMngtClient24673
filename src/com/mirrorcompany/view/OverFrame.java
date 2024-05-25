/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OverFrame extends JFrame {
    private JLayeredPane bodyContainer;
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public OverFrame() {
        setTitle("Overview");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Initialize bodyContainer as JLayeredPane
        bodyContainer = new JLayeredPane();
        getContentPane().add(bodyContainer, BorderLayout.CENTER);

        // Initialize contentPanel with CardLayout
        contentPanel = new JPanel(new CardLayout());
        bodyContainer.add(contentPanel, JLayeredPane.DEFAULT_LAYER);

        // Create tab buttons
        JPanel tabPanel = new JPanel();
        tabPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton btnHome = new JButton("Home");
        JButton btnTravelM = new JButton("Travel Management");
        JButton btnBook = new JButton("Booking");
        JButton btnCom = new JButton("Community");
        JButton btnProfile = new JButton("Profile");
        JButton logout = new JButton("Logout");

        // Add buttons to tab panel
        tabPanel.add(btnHome);
        tabPanel.add(btnTravelM);
        tabPanel.add(btnBook);
        tabPanel.add(btnCom);
        tabPanel.add(btnProfile);
        tabPanel.add(logout);

        getContentPane().add(tabPanel, BorderLayout.NORTH);

        // Add content panels for each tab
        JPanel homePanel = createPanel(Color.WHITE, "Home Panel");
        JPanel travelMPanel = createPanel(Color.GREEN, "Travel Management Panel");
        JPanel bookPanel = createPanel(Color.BLUE, "Booking Panel");
        JPanel comPanel = createPanel(Color.YELLOW, "Community Panel");
        JPanel profilePanel = createPanel(Color.PINK, "Profile Panel");

        contentPanel.add(homePanel, "home");
        contentPanel.add(travelMPanel, "travelM");
        contentPanel.add(bookPanel, "book");
        contentPanel.add(comPanel, "com");
        contentPanel.add(profilePanel, "profile");

        cardLayout = (CardLayout) contentPanel.getLayout();

        // Add action listeners to switch panels on tab click
        btnHome.addActionListener(e -> showPanel("home"));
        btnTravelM.addActionListener(e -> showPanel("travelM"));
        btnBook.addActionListener(e -> showPanel("book"));
        btnCom.addActionListener(e -> showPanel("com"));
        btnProfile.addActionListener(e -> showPanel("profile"));
        logout.addActionListener(e -> logout());

        setVisible(true);
    }

    private JPanel createPanel(Color color, String text) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.add(new JLabel(text));
        return panel;
    }

    private void showPanel(String panelName) {
        cardLayout.show(contentPanel, panelName);
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "Logged out!");
        // Perform logout operations here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OverFrame::new);
    }
}

