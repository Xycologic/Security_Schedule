package com.mycompany.securityschedule.view;

import com.mycompany.securityschedule.controller.SecurityController;
import com.mycompany.securityschedule.model.Security;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddSecurityDialog extends JDialog {
    private JTextField idField;
    private JTextField nameField;
    private JButton addButton;
    private SecurityController securityController;

    public AddSecurityDialog(JFrame parent, SecurityController securityController) {
        super(parent, "Add Security", true);
        this.securityController = securityController;

        initComponents();

        setLocationRelativeTo(parent);
        pack();
    }

    private void initComponents() {
        idField = new JTextField(10);
        nameField = new JTextField(20);
        addButton = new JButton("Add");
        addButton.addActionListener(e -> addSecurity());

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("ID (example: 1234): "));
        panel.add(idField);
        panel.add(new JLabel("Name: "));
        panel.add(nameField);
        panel.add(addButton);

        getContentPane().add(panel);
    }

    private void addSecurity() {
        int id = Integer.parseInt(idField.getText().trim());
        String name = nameField.getText().trim();

        try {
            securityController.addSecurity(id, name);
            JOptionPane.showMessageDialog(this, "Security added successfully!");
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding security: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
