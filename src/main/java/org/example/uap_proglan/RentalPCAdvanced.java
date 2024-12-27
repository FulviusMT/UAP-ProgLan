package org.example.uap_proglan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class RentalPCAdvanced {

    public static void main(String[] args) {
        // Login Frame
        JFrame loginFrame = new JFrame("Login Admin");
        loginFrame.setSize(400, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginFrame.add(usernameLabel);
        loginFrame.add(usernameField);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);
        loginFrame.add(new JLabel());
        loginFrame.add(loginButton);

        loginFrame.setVisible(true);

        // Main Frame (Hidden by Default)
        JFrame mainFrame = new JFrame("Rental PC - Admin");
        mainFrame.setSize(900, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        // Panel Utama
        JPanel rentalPanel = new JPanel(null);
        mainFrame.add(rentalPanel, BorderLayout.CENTER);

        // Label dan Input untuk Nama Pelanggan
        JLabel nameLabel = new JLabel("Nama Pelanggan:");
        nameLabel.setBounds(20, 20, 120, 25);
        rentalPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 20, 200, 25);
        rentalPanel.add(nameField);

        // Label dan Input untuk Waktu Pemakaian (Jam)
        JLabel timeLabel = new JLabel("Waktu (Jam):");
        timeLabel.setBounds(20, 60, 120, 25);
        rentalPanel.add(timeLabel);

        JTextField timeField = new JTextField();
        timeField.setBounds(150, 60, 200, 25);
        rentalPanel.add(timeField);

        // Label dan Input untuk Tarif
        JLabel rateLabel = new JLabel("Tarif per Jam:");
        rateLabel.setBounds(20, 100, 120, 25);
        rentalPanel.add(rateLabel);

        JTextField rateField = new JTextField("10000");
        rateField.setBounds(150, 100, 200, 25);
        rentalPanel.add(rateField);

        // Label dan Dropdown untuk Pilihan PC
        JLabel pcLabel = new JLabel("Pilih PC:");
        pcLabel.setBounds(20, 140, 120, 25);
        rentalPanel.add(pcLabel);

        JComboBox<String> pcComboBox = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            pcComboBox.addItem("PC " + i);
        }
        pcComboBox.setBounds(150, 140, 200, 25);
        rentalPanel.add(pcComboBox);

        // Tombol Tambah Data
        JButton addButton = new JButton("Tambah");
        addButton.setBounds(150, 180, 100, 25);
        rentalPanel.add(addButton);

        // Tombol Selesai
        JButton finishButton = new JButton("Selesai");
        finishButton.setBounds(270, 180, 100, 25);
        rentalPanel.add(finishButton);

        // Tabel untuk Menampilkan Data
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("PC");
        tableModel.addColumn("Nama");
        tableModel.addColumn("Waktu (Jam)");
        tableModel.addColumn("Total Biaya");

        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(20, 220, 740, 300);
        rentalPanel.add(tableScrollPane);

        // Status PC (1-10)
        Map<Integer, Boolean> pcStatus = new HashMap<>();
        Map<Integer, JLabel> pcStatusIcons = new HashMap<>();
        JPanel pcStatusPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        pcStatusPanel.setBounds(400, 20, 400, 200);

        for (int i = 1; i <= 10; i++) {
            pcStatus.put(i, true); // True berarti tersedia
            JLabel pcIcon = new JLabel("PC " + i, SwingConstants.CENTER);
            pcIcon.setOpaque(true);
            pcIcon.setBackground(Color.GREEN);
            pcStatusPanel.add(pcIcon);
            pcStatusIcons.put(i, pcIcon);
        }
        rentalPanel.add(pcStatusPanel);

        // Action Listener untuk Login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if ("FulviusMT".equals(username) && "0ur0l4b0s".equals(password)) {
                    loginFrame.dispose();
                    mainFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Username atau password salah!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action Listener untuk Tambah Data
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int time = Integer.parseInt(timeField.getText());
                    int rate = Integer.parseInt(rateField.getText());
                    int selectedPC = pcComboBox.getSelectedIndex() + 1;

                    if (!pcStatus.get(selectedPC)) {
                        JOptionPane.showMessageDialog(mainFrame, "PC " + selectedPC + " sedang digunakan!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int totalCost = time * rate;

                    // Tandai PC sebagai digunakan
                    pcStatus.put(selectedPC, false);
                    pcStatusIcons.get(selectedPC).setBackground(Color.RED);

                    // Tambahkan data ke tabel
                    tableModel.addRow(new Object[]{"PC " + selectedPC, name, time, totalCost});

                    // Reset Input
                    nameField.setText("");
                    timeField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Input tidak valid. Harap masukkan angka untuk waktu.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action Listener untuk Tombol Selesai
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(mainFrame, "Pilih pelanggan yang selesai.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String pcName = tableModel.getValueAt(selectedRow, 0).toString();
                int pcNumber = Integer.parseInt(pcName.split(" ")[1]);
                int totalCost = Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());

                // Jendela Baru untuk Pembayaran
                JFrame paymentFrame = new JFrame("Pembayaran");
                paymentFrame.setSize(400, 200);
                paymentFrame.setLayout(new GridLayout(3, 2));

                JLabel totalLabel = new JLabel("Total Biaya:");
                JTextField totalField = new JTextField(String.valueOf(totalCost));
                totalField.setEditable(false);

                JLabel paymentLabel = new JLabel("Nominal Bayar:");
                JTextField paymentField = new JTextField();

                JButton confirmButton = new JButton("Bayar");

                paymentFrame.add(totalLabel);
                paymentFrame.add(totalField);
                paymentFrame.add(paymentLabel);
                paymentFrame.add(paymentField);
                paymentFrame.add(new JLabel());
                paymentFrame.add(confirmButton);

                paymentFrame.setVisible(true);

                // Action Listener untuk Konfirmasi Pembayaran
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int payment = Integer.parseInt(paymentField.getText());
                            if (payment < totalCost) {
                                JOptionPane.showMessageDialog(paymentFrame, "Pembayaran tidak cukup!", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(paymentFrame, "Pembayaran berhasil! Kembalian: " + (payment - totalCost));
                                pcStatus.put(pcNumber, true);
                                pcStatusIcons.get(pcNumber).setBackground(Color.GREEN);
                                tableModel.removeRow(selectedRow);
                                paymentFrame.dispose();
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(paymentFrame, "Input tidak valid untuk nominal bayar.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });
    }
}