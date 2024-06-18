/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.securityschedule.view;

/**
 *
 * @author ventus
 */
import com.mycompany.securityschedule.controller.ScheduleController;
import com.mycompany.securityschedule.model.Security;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddScheduleDialog extends JDialog {
    private JTextField dateField;
    private JComboBox<Security> securityComboBox;
    private JComboBox<String> shiftComboBox;
    private ScheduleController scheduleController;

    public AddScheduleDialog(Frame owner, ScheduleController scheduleController) {
        super(owner, "Add Schedule", true);
        this.scheduleController = scheduleController;
        setSize(400, 300);
        setLocationRelativeTo(owner);
        initComponents();
        loadSecurityList();
    }

    private void initComponents() {
        dateField = new JTextField();
        securityComboBox = new JComboBox<>();
        shiftComboBox = new JComboBox<>(new String[]{"7.00 - 14.00", "14.00 - 22.00", "22.00 - 7.00"});

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Date (XXXX-XX-XX):"));
        panel.add(dateField);
        panel.add(new JLabel("Security:"));
        panel.add(securityComboBox);
        panel.add(new JLabel("Shift:"));
        panel.add(shiftComboBox);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            try {
                onAddButtonClicked();
            } catch (SQLException ex) {
                Logger.getLogger(AddScheduleDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        panel.add(new JPanel());
        panel.add(addButton);

        add(panel);
    }

    private void loadSecurityList() {
        List<Security> securityList = scheduleController.getAllSecurity();
        for (Security security : securityList) {
            securityComboBox.addItem(security);
        }
    }

    private void onAddButtonClicked() throws SQLException {
        String date = dateField.getText();
        Security selectedSecurity = (Security) securityComboBox.getSelectedItem();
        String shift = (String) shiftComboBox.getSelectedItem();

        if (date.isEmpty() || selectedSecurity == null || shift == null) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        scheduleController.addSchedule(selectedSecurity.getId(), date, shift);
        JOptionPane.showMessageDialog(this, "Schedule added successfully!");
        dispose();
    }
}

